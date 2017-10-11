package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class GalleryFragment extends BaseFragment {


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_gallery, container, false);
        Log.d(getClass().getSimpleName(), "onCreateView()");
        Bundle bundle = getArguments();
        if (bundle != null) {
            final String str = bundle.getString("data");
            if (str == null) {
                Toast.makeText(getActivity(), "data is null", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "data is:" + str, Toast.LENGTH_SHORT).show();
            }
        }
        /*TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;*/
        view.findViewById(R.id.galleryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("data", str);
//                loadFragment(new SlideShowFragment(), bundle);
            }
        });
        ((TextView) view.findViewById(R.id.tv)).setText(getClass().getSimpleName());
        ((EditText) view.findViewById(R.id.et)).setText(getClass().getSimpleName());
        return view;
    }

}
