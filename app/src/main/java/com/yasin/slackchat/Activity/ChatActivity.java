package com.yasin.slackchat.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.yasin.slackchat.Adapter.ChannelsAdapter;
import com.yasin.slackchat.Adapter.MessageAdapter;
import com.yasin.slackchat.ApiUtils;
import com.yasin.slackchat.Database.DatabaseClient;
import com.yasin.slackchat.Model.Channel;
import com.yasin.slackchat.Model.Channels;
import com.yasin.slackchat.Model.History;
import com.yasin.slackchat.Model.Member;
import com.yasin.slackchat.Model.Message;
import com.yasin.slackchat.Model.RTMConnect;
import com.yasin.slackchat.Model.Self;
import com.yasin.slackchat.Model.SendMessage;
import com.yasin.slackchat.Model.Users;
import com.yasin.slackchat.R;
import com.yasin.slackchat.SessionManager;
import com.yasin.slackchat.SlackChat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;

public class ChatActivity extends AppCompatActivity implements ChannelsAdapter.ChannelSelectedListener {

    private WebSocket webSocket;
    private String webSocketUrl;
    private SessionManager sessionManager;
    private ViewFlipper viewFlipper;
    private ImageButton sendButton;
    private ProgressBar progressBar;
    private CoordinatorLayout snackBarView;
    private Snackbar snackbar;
    private RecyclerView messageRecyclerView;
    private RecyclerView channelsRecyclerView;
    private MessageAdapter messageAdapter;
    private EditText etMessageField;
    private String currentChannelId;
    private String currentmessageId;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.activity_chat);
        initViews();
        beginSession();
    }

    private void initViews() {
        viewFlipper = findViewById(R.id.viewFlipper);
        sendButton = findViewById(R.id.button_send);
        progressBar = findViewById(R.id.progressBar);
        snackBarView = findViewById(R.id.snackBarView);
        messageRecyclerView = findViewById(R.id.rv_chat);
        channelsRecyclerView = findViewById(R.id.rv_channels);
        etMessageField = findViewById(R.id.et_message);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        messageRecyclerView.addOnLayoutChangeListener(onLayoutChangeListener);
        channelsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        sendButton.setOnClickListener(sendButtonClickListener);
    }

    private void beginSession() {
        ApiUtils.getServices().getWebSocketUrl(sessionManager.getApiToken()).enqueue(new Callback<RTMConnect>() {
            @Override
            public void onResponse(@NonNull Call<RTMConnect> call, @NonNull retrofit2.Response<RTMConnect> response) {
                if(response.isSuccessful()){
                    RTMConnect rtmConnect = response.body();
                    if(rtmConnect.getOk()){
                        webSocketUrl = rtmConnect.getUrl();
                        Self self = rtmConnect.getSelf();
                        sessionManager.setSelfUserId(self.getId());
                        startWebSocket(webSocketUrl);
                        getAllChannels();
                        getAllUsers();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RTMConnect> call, @NonNull Throwable t) {
                snackbar = Snackbar.make(snackBarView,getString(R.string.something_went_wrong),Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(getString(R.string.try_again), view -> {
                    beginSession();
                    snackbar.dismiss();
                });
                if(!snackbar.isShown())
                    snackbar.show();
            }
        });
    }

    private void getAllChannels() {
        ApiUtils.getServices().getChannels(sessionManager.getApiToken()).enqueue(new Callback<Channels>() {
            @Override
            public void onResponse(Call<Channels> call, retrofit2.Response<Channels> response) {
                if(response.isSuccessful()){
                    Channels channels = response.body();
                    if(channels.getOk()){
                        ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels.getChannels(),ChatActivity.this);
                        channelsAdapter.setChannelSelectedListener(ChatActivity.this);
                        channelsRecyclerView.setAdapter(channelsAdapter);
                        for(Channel channel : channels.getChannels()){
                            SlackChat.getApp().getExecutor().execute(() ->
                                    DatabaseClient.getInstance(ChatActivity.this).getAppDatabase().channelsDao().saveChannels(channel));

                        }
                        getChannelHistory(channels.getChannels().get(0).getId());
                        currentChannelId = channels.getChannels().get(0).getId();
                    }
                }else {
                    snackbar = Snackbar.make(snackBarView,getString(R.string.fetching_channels_failed),Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(getString(R.string.retry), view -> {
                        getAllChannels();
                        snackbar.dismiss();
                    });
                    if(!snackbar.isShown())
                        snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<Channels> call, Throwable t) {
                snackbar = Snackbar.make(snackBarView,getString(R.string.something_went_wrong),Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(getString(R.string.retry), view -> {
                    getAllChannels();
                    snackbar.dismiss();
                });
                if(!snackbar.isShown())
                    snackbar.show();
            }
        });
    }

    @Override
    public void onChannelSelected(String channelId) {
        currentChannelId = channelId;
        getChannelHistory(channelId);
    }

    private void getAllUsers() {
        ApiUtils.getServices().getUsers(sessionManager.getApiToken()).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, retrofit2.Response<Users> response) {
                if(response.isSuccessful()){
                    Users users = response.body();
                    if(users.getOk()){
                        for(Member member: users.getMembers()){
                            SlackChat.getApp().getExecutor().execute(()->
                                    DatabaseClient.getInstance(ChatActivity.this).getAppDatabase().userDao().saveUser(member));
                        }
                    }else {
                        snackbar = Snackbar.make(snackBarView,getString(R.string.fetching_users_failed),Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction(getString(R.string.retry), view -> {
                            getAllUsers();
                            snackbar.dismiss();
                        });
                        if(!snackbar.isShown())
                            snackbar.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                snackbar = Snackbar.make(snackBarView,getString(R.string.something_went_wrong),Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(getString(R.string.retry), view -> {
                    getAllUsers();
                    snackbar.dismiss();
                });
                if(!snackbar.isShown())
                    snackbar.show();
            }
        });
    }

    private void getChannelHistory(String channelId) {
        progressBar.setVisibility(View.VISIBLE);
        ApiUtils.getServices().getChannelHistory(sessionManager.getApiToken(),channelId).enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, retrofit2.Response<History> response) {
                if(response.isSuccessful()) {
                    History history = response.body();
                    messages = history.getMessages();
                    List<Member> members = DatabaseClient.getInstance(ChatActivity.this).getAppDatabase().userDao().getUsers();
                    if(messageAdapter == null){
                        messageAdapter = new MessageAdapter(messages,ChatActivity.this,members);
                        messageRecyclerView.setAdapter(messageAdapter);
                    }else {
                        messageAdapter.setMessages(messages);
                        messageRecyclerView.setAdapter(messageAdapter);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                snackbar = Snackbar.make(snackBarView,getString(R.string.something_went_wrong),Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(getString(R.string.retry), view -> {
                    getAllUsers();
                    snackbar.dismiss();
                });
                if(!snackbar.isShown())
                    snackbar.show();
            }
        });
    }

    private RecyclerView.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (bottom < oldBottom) {
                messageRecyclerView.postDelayed(() -> messageRecyclerView.scrollToPosition(0), 100);
            }
        }
    };

    private void startWebSocket(String webSocketUrl) {
        OkHttpClient client = new OkHttpClient();;
        Request request = new Request.Builder().url(webSocketUrl).build();
        Log.d("url", request.headers().toString());
        WebsocketListener listener = new WebsocketListener();
        webSocket = client.newWebSocket(request, listener);
    }

    private final class WebsocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.d("WEBSOCKET","OPENED");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            Log.d("WEBSOCKET",text);
            onMessageReceived(text);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            Log.d("WEBSOCKET","CLOSED : " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }
    }

    private View.OnClickListener sendButtonClickListener = view -> {
        if(!etMessageField.getText().toString().isEmpty()){
            Random random = new Random();
            int id = random.nextInt(1000);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChannel(currentChannelId);
            sendMessage.setType("message");
            sendMessage.setText(etMessageField.getText().toString());
            sendMessage.setId(id);
            Gson gson = new Gson();
            String jsonString = gson.toJson(sendMessage);
            try {
                JSONObject request = new JSONObject(jsonString);
                webSocket.send(request.toString());
                Log.d("MESSAGE",request.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        if(webSocket != null){
            webSocket.close(3000,"Close Chat");
        }
        Log.d("WEBSOCKET","CLOSED");
        super.onDestroy();
    }

    private void onMessageReceived(String text) {
        JSONObject obj = null;
        String type = "",channel = "",replyTo = "",textStr = "";
        boolean ok = false;
        try {
            obj = new JSONObject(text);
            if(obj.has("type")){
                type = obj.getString("type");
            }
            if(obj.has("channel")){
                channel = obj.getString("channel");
            }
            if(obj.has("reply_to")){
                replyTo = obj.getString("reply_to");
            }
            if(obj.has("text")){
                textStr = obj.getString("text");
            }
            if(obj.has("ok")){
                ok = obj.getBoolean("ok");
            }

            if(replyTo != null && ok && etMessageField.getText().toString().trim().equals(textStr)){
                Message message = new Message();
                message.setType(type);
                message.setUser(sessionManager.getSelfUserId());
                message.setText(textStr);
                messages.add(0,message);
                messageAdapter.notifyDataSetChanged();

                messageRecyclerView.scrollToPosition(0);
                etMessageField.setText("");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
