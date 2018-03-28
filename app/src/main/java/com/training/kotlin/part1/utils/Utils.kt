package com.training.kotlin.part1.utils

/**
 * Created by cygnet on 11/1/18.
 */

fun String.isStringTooLong(): Boolean = this.length > 10

object Singleton {
    val name = "Abc"
}