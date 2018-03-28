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

import java.util.Calendar;

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
}
