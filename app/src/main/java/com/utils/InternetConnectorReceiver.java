package com.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.application.MyApplication;

/**
 * Created by cygnet on 11/9/17.
 */

public class InternetConnectorReceiver extends BroadcastReceiver {

    public InternetConnectorReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();

            // Check internet connection and according to state change the
            // text of activity by calling method
            if (networkInfo != null && networkInfo.isConnected())
            {
                if (MyApplication.getAppInstance() != null && MyApplication.getAppInstance().getEventBus() != null)
                {
                    Log.e("InternetConnectorReceiv", "Connected");
                    MyApplication.getAppInstance().getEventBus().post(new SendScannedData("Connected"));
                } else
                {
                    Log.e("InternetConnectorReceiv", "Connected with AppInstance null");
                }
            } else
            {
                if (MyApplication.getAppInstance() != null && MyApplication.getAppInstance().getEventBus() != null)
                {
                    Log.e("InternetConnectorReceiv", "Disconnected");
                    MyApplication.getAppInstance().getEventBus().post(new SendScannedData("Disconnected"));
                } else
                {
                    Log.e("InternetConnectorReceiv", "Disconnected with AppInstance null");
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}