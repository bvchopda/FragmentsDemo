package com.training.kotlin.part1.utils

import android.util.Log

/**
 * Created by cygnet on 15/1/18.
 */
class LogCat(private var message: String) {
    private val TAG = LogCat::class.java.simpleName
    private var instance: LogCat? = null

    fun getInstance(message: String): LogCat? {
        if (instance == null)
            instance = LogCat(message)
        return instance
    }

    fun d() {
        Log.d(TAG, message)
    }

    fun v() {
        Log.v(TAG, message)
    }

    fun e() {
        Log.e(TAG, message)
    }
}