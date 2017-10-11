package com.presenters;

import com.application.MyApplication;
import com.fragments.EventBusFragment;
import com.utils.SendScannedData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by cygnet on 11/9/17.
 */

public class EventBusPresenter {

    private final EventBus mEventBus;

    public EventBusPresenter()
    {
        mEventBus = MyApplication.getAppInstance().getEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(String message)
    {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onScannedDataReceived(SendScannedData sendScannedData)
    {
        EventBusFragment.onScannedDataReceived(sendScannedData);
    }

    public void registerBus()
    {
        if (!mEventBus.isRegistered(this)) {
            mEventBus.register(this);
        }
    }

    public void unRegisterBus()
    {
        if (mEventBus.isRegistered(this)) {
            mEventBus.unregister(this);
        }
    }

}
