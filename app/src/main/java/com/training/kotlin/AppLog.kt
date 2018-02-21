package com.training.kotlin

import android.util.Log

/**
 * Created by cygnet on 14/2/18.
 */
class AppLog {

    companion object {
        fun d(tag: String, message: String) {
            Log.d(tag, message)
        }
    }
}