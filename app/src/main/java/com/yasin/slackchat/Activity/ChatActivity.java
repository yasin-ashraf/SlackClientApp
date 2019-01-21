package com.yasin.slackchat.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

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
import com.yasin.slackchat.Model.Users;
import com.yasin.slackchat.R;
import com.yasin.slackchat.SessionManager;
import com.yasin.slackchat.SlackChat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
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
                        ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels.getChannels());
                        channelsAdapter.setChannelSelectedListener(ChatActivity.this);
                        channelsRecyclerView.setAdapter(channelsAdapter);
                        for(Channel channel : channels.getChannels()){
                            SlackChat.getApp().getExecutor().execute(() ->
                                    DatabaseClient.getInstance(ChatActivity.this).getAppDatabase().channelsDao().saveChannels(channel));

                        }
                        getChannelHistory(channels.getChannels().get(0).getId());
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
                    List<Message> messages = history.getMessages();
                    if(messageAdapter == null){
                        messageAdapter = new MessageAdapter(messages,ChatActivity.this);
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

            }
        });
    }

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
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }
    }

    private View.OnClickListener sendButtonClickListener = view -> {

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
        try {
            obj = new JSONObject(text);
            String type = obj.getString("type");
            String channel = obj.getString("channel");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
