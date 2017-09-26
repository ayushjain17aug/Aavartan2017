package com.technocracy.app.aavartan.fcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.api.Notifications;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingService.class.getSimpleName();
    private NotificationUtils notificationUtils;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /*
        Log.e("FireBase ", "onMessageReceived");

        Notifications notification = new Notifications(Integer.parseInt(remoteMessage.getData().get("id")),
                remoteMessage.getData().get("type"), Integer.parseInt(remoteMessage.getData().get("event_id")),
                remoteMessage.getData().get("title"), remoteMessage.getData().get("message"),
                remoteMessage.getData().get("image_url"),
                remoteMessage.getData().get("created_at"));

        db.addNotification(notification);

        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "Title: " + notification.getTitle());
        Log.e(TAG, "message: " + notification.getMessage());
        Log.e(TAG, "image: " + notification.getImageUrl());
        Log.e(TAG, "timestamp: " + notification.getCreatedAt());
        Log.e(TAG, "notification_type" + notification.getType());

        Intent resultIntent = new Intent(getApplicationContext(), NotificationsActivity.class);
        resultIntent.putExtra("message", notification.getMessage());
        resultIntent.putExtra("title", notification.getTitle());
        resultIntent.putExtra("image_url", notification.getImageUrl());
        resultIntent.putExtra("created_at", notification.getCreatedAt());

        showNotificationMessage(getApplicationContext(), notification.getTitle(),
                notification.getMessage(), notification.getCreatedAt(), resultIntent,
                notification.getImageUrl(), notification.getType());
    */}

   /* private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl, String type) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | IntentCompat.FLAG_ACTIVITY_TASK_ON_HOME);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl, type);
    } */
}