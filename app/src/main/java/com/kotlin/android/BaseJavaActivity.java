package com.kotlin.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cygnet on 2/1/18.
 */

public class BaseJavaActivity extends AppCompatActivity {

    List<String> mList = new ArrayList<>(Arrays.asList("Abc"));
    String[] array = {"a"};
    public void loadActivity(Class aClass)
    {
        startActivity(new Intent(this, aClass));
    }
}
