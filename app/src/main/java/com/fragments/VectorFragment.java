package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bvchopda.fragmentsdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VectorFragment extends BaseFragment {

    private static final String TAG = "VectorFragment";
    private View view;

    public VectorFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void toast(String message)
    {
        super.toast(message);
        Log.e(TAG, "toast: message");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_vector, null);

        return view;
    }
}
