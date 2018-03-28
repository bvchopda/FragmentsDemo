package com.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Android on 3/28/2018.
 */

public class Alarm extends BroadcastReceiver {
    private static final String TAG = "Alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
//        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
//        wl.acquire();
//        // Put here YOUR code.
//        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example
//        wl.release();
        if (intent != null && intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            Log.e(TAG, "onReceive: BOOT_COMPLETED");
            NotificationManager.getInstance(context).setAlarm();
            return;
        }
        NotificationManager.getInstance(context).showNotification();
    }
}
