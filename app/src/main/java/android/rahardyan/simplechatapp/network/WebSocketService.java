package android.rahardyan.simplechatapp.network;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.rahardyan.simplechatapp.BuildConfig;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.model.WebSocketMessage;
import android.rahardyan.simplechatapp.ui.chat.ChatActivity;
import android.rahardyan.simplechatapp.ui.topiclist.TopicListActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;


import com.google.gson.Gson;

import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class WebSocketService extends Service {
    private static final String TAG = WebSocketService.class.getSimpleName();
    private Gson gson;

    public WebSocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void start() {
        List<BasicNameValuePair> headers = new ArrayList<>();
        headers.add(new BasicNameValuePair("token", BuildConfig.USER_TOKEN));
        URI websocketUri = URI.create(BuildConfig.WEB_SOCKET_URL);
        android.rahardyan.simplechatapp.util.WebSocketClient webSocketClient = new android.rahardyan.simplechatapp.util.WebSocketClient(websocketUri, new android.rahardyan.simplechatapp.util.WebSocketClient.Listener() {
            @Override
            public void onConnect() {
                Log.d(TAG, "onConnect: websocket connected");
            }

            @Override
            public void onMessage(String message) {
                WebSocketMessage webSocketMessage = gson.fromJson(message, WebSocketMessage.class);
                EventBus.getDefault().post(webSocketMessage);
                String senderName = webSocketMessage.getData().getChanges().getHistories().getNewValue().get(0).getCreatedName();
                String comment = senderName + ": " + webSocketMessage.getData().getChanges().getHistories().getNewValue().get(0).getMessage();
                String issueId = webSocketMessage.getData().getChanges().getHistories().getNewValue().get(0).getIssueId();
                String topicName = webSocketMessage.getData().getSource().get(0).getParentName();
                Log.d(TAG, "onMessage: message " +comment);
                if (!senderName.toLowerCase().equals(getString(R.string.user_name))) {
                    showMessageNotification(comment, issueId, topicName);
                }
            }

            @Override
            public void onMessage(byte[] data) {

            }

            @Override
            public void onDisconnect(int code, String reason) {
                Log.d(TAG, "onDisconnect: websocket disconnected " + reason);
                start();
            }

            @Override
            public void onError(Exception error) {

            }
        }, headers);
        webSocketClient.connect();
    }

    private void showMessageNotification(String message, String issueId, String topicName) {
        Intent activeIntent = ChatActivity.generateIntent(getApplicationContext(), topicName, issueId);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext());
        taskStackBuilder.addParentStack(TopicListActivity.class);
        taskStackBuilder.addNextIntentWithParentStack(activeIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(212, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(topicName)
                .setContentText(message)
                .setTicker(message)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setGroupSummary(true)
                .setGroup("CHAT_NOTIF")
                .setPriority(10)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);
        NotificationManagerCompat.from(getBaseContext()).notify(10, notificationBuilder.build());
    }
}
