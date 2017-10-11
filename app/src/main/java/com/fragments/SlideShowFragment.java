package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bvchopda.fragmentsdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlideShowFragment extends BaseFragment {


    public SlideShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        /*TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;*/
        Bundle bundle = getArguments();
        final String str = bundle.getString("data");
        if (str == null) {
            Toast.makeText(getActivity(), "data is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "data is:" + str, Toast.LENGTH_SHORT).show();
        }
        ((TextView) view.findViewById(R.id.tv)).setText(getClass().getSimpleName());
        ((EditText) view.findViewById(R.id.et)).setText(getClass().getSimpleName());
        return view;
    }

}
