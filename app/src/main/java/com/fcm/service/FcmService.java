package com.fcm.service;

import android.util.Log;

import com.fcm.FcmModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmService extends FirebaseMessagingService {
    private final String TAG = "FcmService";

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
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            com.notification.NotificationManager.getInstance(this).handleExtras(remoteMessage
                    .getData(), false);
            return;
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
//                handleNow();
//            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null)
        {
            Log.e(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Message Notification Message: " + remoteMessage.getNotification().getBody
                    ());
            com.notification.NotificationManager notificationManager = com.notification.NotificationManager.getInstance(this);
            FcmModel fcmModel = new FcmModel();
            fcmModel.setTitle(remoteMessage.getNotification().getTitle());
            fcmModel.setMessage(remoteMessage.getNotification().getBody());
            fcmModel.setNotificationId(String.valueOf(notificationManager.NOTIFICATION_START_APP));
            notificationManager.handleNotification(fcmModel, false);
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }
}