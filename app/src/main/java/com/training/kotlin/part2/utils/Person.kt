package com.training.kotlin.part2.utils

import android.util.Log

/**
 * Created by cygnet on 25/1/18.
 */

data class Employee(var id: String)

class Person(id1: String) {

    private val TAG: String = Person::class.java.simpleName

    init {
        Log.d(TAG, "Learning Kotlin part - 2 init block 1: id = $id1")
    }

    private var name: String? = null

    constructor(id: String, name: String) : this(id) {
        this.name = name
    }

    init {
        name = "Test"
        Log.d(TAG, "Learning Kotlin part - 2 init block 2: id = $id1, Name = $name")
    }

    fun getId(){
        Log.d(TAG, "$name")
    }
}