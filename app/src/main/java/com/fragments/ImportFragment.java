package com.fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bvchopda.fragmentsdemo.R;
import com.utils.Child;
import com.utils.ForceUpdateAsync;
import com.utils.MySpinner;
import com.utils.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportFragment extends BaseFragment implements View.OnTouchListener {

    private static final String TAG = "ImportFragment";
    String value = "$0.00";
    Button btn;
    List<Map<String, String>> arrayList;
    private MySpinner spinner;
    private View view;
    private SimpleAdapter simpleAdapter;

    public ImportFragment()
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
        view = inflater.inflate(R.layout.content_main, null);
        /*TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;*/
        view.findViewById(R.id.importBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /*((MainActivity) getActivity()).replaceFragment(ImportFragment.this, new GalleryFragment(), view.findViewById(R.id.importBtn), "aaa");
                value = ((EditText) view.findViewById(R.id.et)).getText().toString();*/
//                Bundle bundle = new Bundle();
//                bundle.putString("data", value);
//                loadFragment(new GalleryFragment(), bundle);
//                new ForceUpdateAsync("1", getActivity()).execute();
                /*Person person = Person.getInstance()
                        .setAge(0)
                        .setName("Abc");
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();


                Child child = new Child.Builder()
                        .setName("abc")
                        .setAge(1)
                        .build();*/
                toast("Test");
            }
        });
        btn = (Button) view.findViewById(R.id.importBtn);
        btn.setOnTouchListener(this);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("a");
        str1.setSpan(new ForegroundColorSpan(Color.RED), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2 = new SpannableString("b");
        str2.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str2.length(), 0);
        builder.append(str2);

        SpannableString str3 = new SpannableString("c");
        str3.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str3.length(), 0);
        builder.append(str3);

        SpannableString str4 = new SpannableString("d");
        str4.setSpan(new ForegroundColorSpan(Color.GRAY), 0, str4.length(), 0);
        builder.append(str4);

        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(builder, TextView.BufferType.SPANNABLE);

        try
        {
            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "EDBWT.TTF");
            tv.setTypeface(typeface);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
//        ((TextView) view.findViewById(R.id.tv)).setText(getClass().getSimpleName());


        ((EditText) view.findViewById(R.id.et)).setText(value);
        final EditText et = ((EditText) view.findViewById(R.id.et));
        et.addTextChangedListener(new TextWatcher() {
            final Pattern sPattern = Pattern.compile("^-?\\$\\d{0,5}\\.?\\d{0,2}$");
            private CharSequence mText;

            private boolean isValid(CharSequence s)
            {
                return sPattern.matcher(s).matches();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                Log.d(TAG, "beforeTextChanged: " + s);
                mText = isValid(s) ? s : "";
                Log.e(TAG, "mText: " + mText);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.d(TAG, "afterTextChanged: " + s);
                if (!et.hasFocus())
                {
                    return;
                }
                if (!isValid(s) && !mText.toString().equals(s.toString()))
                {
                    et.setText(mText);
                }
                mText = null;
            }
        });


        spinner = (MySpinner) view.findViewById(R.id.spinner);
        String[] from = new String[]{"colorsData"};
        int[] to = new int[]{android.R.id.text1};
        arrayList = new ArrayList();
//        arrayList.add("1");
        arrayList.add(addData("1"));
//        arrayList.add("2");
        arrayList.add(addData("2"));
//        arrayList.add("3");
        arrayList.add(addData("3"));
//        arrayList.add("4");
        arrayList.add(addData("4"));
//        arrayList.add("5");
        arrayList.add(addData("5"));
        simpleAdapter = new SimpleAdapter(getActivity(), arrayList, android.R.layout.simple_spinner_item, from, to);
        simpleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(simpleAdapter);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.performClick();
            }
        }, 1000);*/
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: closed???");
//                spinner.setSelection(0, false);
//                spinner.setSelection(0);
//                spinner.performClick();
//                spinner.setVisibility(View.INVISIBLE);
                spinner.onDetachedFromWindow();
            }
        }, 5000);*/
        return view;
    }

    private Map<String, String> addData(String colorName)
    {
        Map<String, String> mapList = new HashMap<String, String>();
        mapList.put("colorsData", colorName);
        return mapList;
    }

    @Override
    public boolean getUserVisibleHint()
    {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        return false;
    }

    @Override
    public void onPause()
    {
//        spinner.onDetachedFromWindow();
//        View root = view.getRootView();
//        view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
//        view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
//        arrayList.clear();
//        simpleAdapter.notifyDataSetChanged();
        spinner.onDetachedFromWindow();
        super.onPause();
    }

}
