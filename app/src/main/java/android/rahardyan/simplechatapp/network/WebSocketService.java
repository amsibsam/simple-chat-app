package android.rahardyan.simplechatapp.network;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.rahardyan.simplechatapp.BuildConfig;
import android.util.Log;


import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.drafts.Draft_75;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.WebSocket;
import io.crossbar.autobahn.WebSocketConnection;
import io.crossbar.autobahn.WebSocketException;

public class WebSocketService extends Service {
    private static final String TAG = WebSocketService.class.getSimpleName();
//    private final WebSocketConnection mConnection = new WebSocketConnection();
    private WebSocketClient webSocketClient;

    public WebSocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectToWebSocket();
//        startWebsocket();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startWebsocket() {
        WebSocketFactory webSocketFactory = new WebSocketFactory();
        try {
            com.neovisionaries.ws.client.WebSocket webSocket = webSocketFactory.createSocket(BuildConfig.WEB_SOCKET_URL+":443", 5000)
                    .addHeader("token", "12497228-cb3a-4fb7-ae99-be150f70be97");
            webSocket.connectAsynchronously();
            webSocket.addListener(new WebSocketAdapter() {
                @Override
                public void onConnected(com.neovisionaries.ws.client.WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                    super.onConnected(websocket, headers);
                    Log.d(TAG, "onConnected: web socket connected");
                }

                @Override
                public void onCloseFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {
                    super.onCloseFrame(websocket, frame);
                    Log.d(TAG, "onCloseFrame: websocket closed" + frame.getCloseReason() );
                }

                @Override
                public void onError(com.neovisionaries.ws.client.WebSocket websocket, com.neovisionaries.ws.client.WebSocketException cause) throws Exception {
                    super.onError(websocket, cause);
                    Log.e(TAG, "onError: error", cause);
                }

                @Override
                public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, String text) throws Exception {
                    super.onTextMessage(websocket, text);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToWebSocket() {
        Map<String, String> header = new HashMap<>();
        header.put("token", "12497228-cb3a-4fb7-ae99-be150f70be97");
        URI webSocketURI = null;
        try {
            webSocketURI = new URI(BuildConfig.WEB_SOCKET_URL+":443");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        webSocketClient = new WebSocketClient(webSocketURI, new Draft_6455(), header, 5000) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d(TAG, "onOpen: websocket open");
            }

            @Override
            public void onMessage(String message) {
                Log.d(TAG, "onMessage: websocket message "+message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d(TAG, "onClose: webSocketClose "+reason + " code "+code);
            }

            @Override
            public void onError(Exception ex) {
                Log.e(TAG, "onError: connect websocket", ex);
            }
        };
        webSocketClient.connect();
    }

//    private class WebSocketOperation extends AsyncTask<String, Void, String> {
//        private WebSocketClient webSocketClient;
//
//        @Override
//        protected String doInBackground(String... params) {
////            start();
//            return "executed";
//        }
//
//        private void start() {
//            try {
//                mConnection.connect("wss://dev.innerbeans.org/common/mobileSocket" + ":443", new WebSocket.ConnectionHandler() {
//                    @Override
//                    public void onOpen() {
//                        Log.d(TAG, "onOpen: websocket open");
//                    }
//
//                    @Override
//                    public void onClose(int code, String reason) {
//                        Log.d(TAG, "onClose: webSocketClose "+reason+" code"+code);
//                    }
//
//                    @Override
//                    public void onTextMessage(String payload) {
//
//                    }
//
//                    @Override
//                    public void onRawTextMessage(byte[] payload) {
//
//                    }
//
//                    @Override
//                    public void onBinaryMessage(byte[] payload) {
//
//                    }
//                });
//            } catch (WebSocketException e) {
//
//                Log.d(TAG, e.toString());
//            }
//        }
//
//    }
}
