package com.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bvchopda.fragmentsdemo.MainActivity;

/**
 * Created by cygnet on 23/1/17.
 */

public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    @CallSuper
    public void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void loadFragment(BaseFragment fragment, Bundle bundle) {
        ((MainActivity) getActivity()).replaceFragment(fragment, bundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach(): " + getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate(): " + getClass().getSimpleName());
        // Test changes to merge it with branch1
        // Test changes2 to merge it with branch1
        // Changes done in master
        // Changes done in branch1
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView(): " + getClass().getSimpleName());
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated(): " + getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart(): " + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume(): " + getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause(): " + getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop(): " + getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView(): " + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy(): " + getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach(): " + getClass().getSimpleName());
    }
}
