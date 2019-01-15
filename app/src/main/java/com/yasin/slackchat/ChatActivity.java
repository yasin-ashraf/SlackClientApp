package com.yasin.slackchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatActivity extends AppCompatActivity {

    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        startWebSocket();
    }

    private void startWebSocket() {
        OkHttpClient client = new OkHttpClient();;
        Request request = new Request.Builder().url("wss://1-6.api.guardiancircle.com/ws/" + "_gcomm?subscribe-broadcast").build();
        Log.d("alert", request.headers().toString());
        WebsocketListener listener = new WebsocketListener();
        webSocket = client.newWebSocket(request, listener);
    }

    private final class WebsocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
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
        webSocket.close(3000,"Close Chat");
        super.onDestroy();
    }
}
