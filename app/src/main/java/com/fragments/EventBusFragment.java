package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bvchopda.fragmentsdemo.R;
import com.presenters.EventBusPresenter;
import com.utils.SendScannedData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventBusFragment extends BaseFragment {


    private EventBusPresenter mPresenter;
    private static FragmentActivity mContext;

    public EventBusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_gallery, container, false);
        Log.d(getClass().getSimpleName(), "onCreateView()");
        mPresenter = new EventBusPresenter();
        mContext = getActivity();
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mPresenter.registerBus();
    }

    @Override
    public void onStop()
    {
        mPresenter.unRegisterBus();
        super.onStop();
    }

    public static void onScannedDataReceived(SendScannedData sendScannedData)
    {
        Toast.makeText(mContext, sendScannedData.getScannedUPC(), Toast.LENGTH_SHORT).show();
    }
}
