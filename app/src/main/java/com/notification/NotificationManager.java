package com.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.bvchopda.fragmentsdemo.MainActivity;
import com.example.bvchopda.fragmentsdemo.R;
import com.fcm.FcmModel;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by Android on 3/28/2018.
 * Purpose:
 */

public class NotificationManager {
    private static final String TAG = "NotificationManager";
    private static NotificationManager notificationManager;
    private static Context context;

    public static NotificationManager getInstance(Context ctx) {
        if (notificationManager == null)
            notificationManager = new NotificationManager();
        context = ctx;
        return notificationManager;
    }

    private boolean isNeedToSetAlarm() {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        Log.e(TAG, "isNeedToSetAlarm:" + (pendingIntent == null));
        return pendingIntent == null;
    }

    void setAlarm() {
        if (isNeedToSetAlarm()) {
            Intent intent = new Intent(context, Alarm.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 10);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            assert alarmManager != null;
            Log.e(TAG, "Setting alarm...");
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), DAYS_IN_MILLISECONDS, pendingIntent);
        }
    }

    void showNotification() {
        if (isNeedToShowNotification()) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            String channelId = context.getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(context.getString(R.string.app_name))
                            .setContentText("Hey you haven't draw since long. Let's draw and " +
                                    "show your skills to others.")
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);

            android.app.NotificationManager notificationManager =
                    (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }

            assert notificationManager != null;
            Log.e(TAG, "Showing Notification...");
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }

    private final String LAST_OPENED = "LAST_OPENED";
    //    private final long MINUTES_IN_MILLISECONDS = 2 * 60 * 1000;
    private final long DAYS_IN_MILLISECONDS = 2 * 24 * 60 * 60 * 1000;

    private boolean isNeedToShowNotification() {
        long lastOpened = getLastOpened();
        if (lastOpened != 0) {
            if (Calendar.getInstance().getTimeInMillis() - lastOpened > DAYS_IN_MILLISECONDS) {
                Log.e(TAG, "isNeedToShowNotification: true");
                return true;
            }
        }
        Log.e(TAG, "isNeedToShowNotification: false");
        return false;
    }

    public void setLastOpened(long mill) {
        context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit().putLong(LAST_OPENED, mill).apply();
        setAlarm();
    }

    private long getLastOpened() {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getLong(LAST_OPENED, 0);
    }

    /* Push Notification */

    public interface NotificationConstants
    {
        String appName = "appName";
        String packageName = "packageName";
        String iconUrl = "iconUrl";
        String title = "title";
        String message = "message";
        String url = "url";
        String notificationId = "notificationId";
    }

    private final int NOTIFICATION_PLAY_STORE = 1;
    public final int NOTIFICATION_START_APP = 2;
    private final int NOTIFICATION_SIMPLE_URL = 3;

    public void handleExtras(Map<String, String> data, boolean isFromIntent)
    {
        try
        {
            FcmModel fcmModel = new FcmModel();
            if (data.containsKey(NotificationConstants.appName))
                fcmModel.setAppName(data.get(NotificationConstants.appName));
            if (data.containsKey(NotificationConstants.packageName))
                fcmModel.setPackageName(data.get(NotificationConstants.packageName));
            if (data.containsKey(NotificationConstants.iconUrl))
                fcmModel.setIconUrl(data.get(NotificationConstants.iconUrl));
            if (data.containsKey(NotificationConstants.title))
                fcmModel.setTitle(data.get(NotificationConstants.title));
            if (data.containsKey(NotificationConstants.message))
                fcmModel.setMessage(data.get(NotificationConstants.message));
            if (data.containsKey(NotificationConstants.url))
                fcmModel.setUrl(data.get(NotificationConstants.url));
            if (data.containsKey(NotificationConstants.notificationId))
                fcmModel.setNotificationId(data.get(NotificationConstants.notificationId));
            handleNotification(fcmModel, isFromIntent);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handleNotification(FcmModel fcmModel, boolean isFromIntent)
    {
        Intent intent = null;
        String title = null, messageBody = null;
        try
        {
            title = fcmModel.getTitle();
            messageBody = fcmModel.getMessage();
            switch (fcmModel.getNotificationId())
            {
                case NOTIFICATION_PLAY_STORE:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(context.getString(R.string.rate_app_play) + fcmModel.getPackageName()));
                    break;
                case NOTIFICATION_START_APP:
                    intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case NOTIFICATION_SIMPLE_URL:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(fcmModel.getUrl()));
                    break;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        /*try {
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
        }*/
        if (intent != null)
        {
            if (isFromIntent)
            {
                context.startActivity(intent);
            } else
            {
                sendNotification(title, messageBody, intent);
            }
        }
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody, Intent intent)
    {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = context.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) context.getSystemService(Context
                        .NOTIFICATION_SERVICE);

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
