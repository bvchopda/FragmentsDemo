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
public class ConstraintFragment extends BaseFragment {

    private static final String TAG = "ImportFragment";
    private View view;

    public ConstraintFragment()
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
        view = inflater.inflate(R.layout.fragment_constraint, null);

        return view;
    }
}
