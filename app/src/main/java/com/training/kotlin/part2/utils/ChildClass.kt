package com.training.kotlin.part2.utils

import com.training.kotlin.AppLog

/**
 * Created by cygnet on 14/2/18.
 */
class ChildClass : ParentClass() {

    override var message: String = "Test2" + super.message

    override fun parentMethod() {
        super.parentMethod()
        AppLog.d(javaClass.simpleName, "$message from Child class")
    }
}