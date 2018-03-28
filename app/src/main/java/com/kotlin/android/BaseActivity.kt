package com.kotlin.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logd("onCreate()")
    }

    fun logd(message: String) {
        Log.d(javaClass.simpleName, message)
        println(message)
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun loadActivity(aClass: Class<*>, aBundle: Bundle?) {
        startActivity(Intent(this, aClass).putExtra("bundle", aBundle))
    }

}