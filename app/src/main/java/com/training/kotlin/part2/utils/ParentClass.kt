package com.training.kotlin.part2.utils

import com.training.kotlin.AppLog

/**
 * Created by cygnet on 14/2/18.
 */
open class ParentClass {

    open var message: String = "Test message"
    open fun parentMethod() {
        AppLog.d(javaClass.simpleName, "$message from parent class")
    }
}