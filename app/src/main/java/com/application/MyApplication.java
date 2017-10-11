package com.application;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cygnet on 11/9/17.
 */

public class MyApplication extends Application{

    private static MyApplication mMyApp;
    private EventBus mEventBus;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mMyApp = this;
    }

    public static MyApplication getAppInstance()
    {
        return mMyApp;
    }

    public EventBus getEventBus()
    {
        if (mEventBus == null)
            mEventBus = EventBus.getDefault();
        return mEventBus;
    }
}
