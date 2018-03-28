package com.fcm.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.fcm.FcmModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.magicaldrawingglow.android.R;
import com.magicaldrawingglow.android.SplashActivity;
import com.support.commons.AppLog;
import com.utils.Promotion;

public class FcmService extends FirebaseMessagingService {
    private static final String TAG = "FcmService";

    private final int NOTIFICATION_PLAY_STORE = 1;
    private final int NOTIFICATION_START_APP = 2;
    private final int NOTIFICATION_SIMPLE_URL = 3;


    /**
     * appName : Abc
     * packageName : com.abc.xyz
     * iconUrl : http://icon.png
     * title : Drawing Glow
     * message : Check this out this new app
     * url : http://www.google.com
     * notificationId : 1
     */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        AppLog.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            AppLog.e(TAG, "Message data payload: " + remoteMessage.getData());

//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
//                handleNow();
//            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            AppLog.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }

    private void handleNotification(String message) {
        Intent intent = null;
        String title = null, messageBody = null;
        try {
            FcmModel fcmModel = new Gson().fromJson(message, FcmModel.class);
            title = fcmModel.getTitle();
            messageBody = fcmModel.getMessage();
            switch (fcmModel.getNotificationId()) {
                case NOTIFICATION_PLAY_STORE:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(getString(R.string.rate_app_play) + fcmModel.getPackageName()));
                    break;
                case NOTIFICATION_START_APP:
                    intent = new Intent(this, SplashActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case NOTIFICATION_SIMPLE_URL:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(fcmModel.getUrl()));
                    break;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        try {
            String[] promotionData = Promotion.getInstance(this).getAppToPromote();
            String packageName;
            if (promotionData != null) {
                title = promotionData[0];
                packageName = promotionData[1];
                messageBody = getString(R.string.message_to_share);
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.rate_app_play) + packageName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent != null)
            sendNotification(title, messageBody, intent);
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
