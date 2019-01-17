package com.yasin.slackchat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yasin.slackchat.Model.RTMConnect;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;

public class ChatActivity extends AppCompatActivity {

    private WebSocket webSocket;
    private String webSocketUrl;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.activity_chat);
        beginSession();
    }

    private void beginSession() {

        ApiUtils.getServices().getWebSocketUrl("token goes here").enqueue(new Callback<RTMConnect>() {
            @Override
            public void onResponse(@NonNull Call<RTMConnect> call, @NonNull retrofit2.Response<RTMConnect> response) {
                if(response.isSuccessful()){
                    RTMConnect rtmConnect = response.body();
                    if(rtmConnect.getOk()){
                        webSocketUrl = rtmConnect.getUrl();
                        startWebSocket(webSocketUrl);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RTMConnect> call, @NonNull Throwable t) {

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
