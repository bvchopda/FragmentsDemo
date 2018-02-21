package com.training.kotlin.part1.activities

import android.os.Bundle
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import com.training.kotlin.part1.utils.LogCat
import com.training.kotlin.part1.utils.Singleton
import com.training.kotlin.part1.utils.isStringTooLong
import kotlinx.android.synthetic.main.activity_training_1_basic_solution.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cygnet on 10/1/18.
 */
class Training1BasicSolutionActivity : BaseActivity() {

    private var index: Int = -1
    private var mTasks = listOf(
            "1. Create a kotlin file, which has variable initialization and creating a function which has a single argument of Array of string and print all elements of array.",
            "2. Create a list of elements which can have 20 elements and loop through step 3 and print all elements on console using iterator as well as show the index of each element.",
            "3. Create any one extension function for string which we can use in any Kotlin file.",
            "4. Create an array of string, which have 20 elements and create and use the if else conditions.",
            "5. Create an function which can have object as a argument, and check does if it is type string then print \"It is string\", if object is long then print \"It is long\" else return \"Object not identified.\"",
            "6. Write the usage of single expression function",
            "7. Write the usage of while and doWhile loop",
            "8. Write an example of string template and call the extension function which is created at Question 3 inside expression",
            "9. Write a function which can hold the nullable arguments and return non-nullable value",
            "10. Create a list of element and print all element except last element using range expression",
            "11. create a basic class and their instance",
            "12. Call the multiple functions in sequence using \"WITH\" for instance created of class",
            "13. Write an example of lambda expression",
            "14. Write the example of set, map and list for mutable and immutable and print the elements of it. (Note : For map, key and value both should be print)",
            "15. Create a singleton object and call it to any other KT file",
            "16. Write the example, for nullable check and if value is null then print something, and else print something. (Note : You should not use if/else or when for this task)",
            "17. Execute block of code if any variable is not null. (Note : You should not use if/else or When)",
            "18. Write the example of break and continue at label, as well as return at label"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_1_basic_solution)
        val aBundle = intent.getBundleExtra("bundle")
        index = aBundle?.getInt("index") ?: 12
        tvTaskDetails.text = mTasks[index]
        if (index == 10 || index == 11) {
            tvTaskDetails.text = mTasks[10] + "\n" + mTasks[11]
        } else if (index == 15 || index == 16) {
            tvTaskDetails.text = mTasks[15] + "\n" + mTasks[16]
        }
        btnSolution.setOnClickListener({
            doSolution()
        })
    }

    private fun doSolution() {
        when (index + 1) {
            1 -> simpleArrayInitialization()
            2 -> simpleLoop()
            3 -> extensionFun()
            4 -> simpleIfElse()
            5 -> whenExpression()
            6 -> singleExpressionFunction()
            7 -> whileDoWhile()
            8 -> extensionFunInExpression()
            9 -> funWithNullableParam()
            10 -> rangeExpression()
            11, 12 -> classAndWith()
            13 -> lambdaExpression()
            14 -> setMapList()
            15 -> singletonInstance()
            16, 17 -> nullCheckWithoutIfWhen(null)
            18 -> breakContinueLabel()
        }
    }

    //18. Write the example of break and continue at label, as well as return at label
    private fun breakContinueLabel() {
        loopi@ for (i in 5..100) {
            loopj@ for (j in 1..100 step 5) {
                tvSolution.append("\n$i x $j = ${i * j}")
                if (i * j > 100) {
                    tvSolution.append("\nBreak")
                    break@loopi
                }
                if (j > 10) {
                    tvSolution.append("\nContinue")
                    continue@loopj
                }
            }
        }
    }

    //16. Write the example, for nullable check and if value is null then print something, and else print something. (Note : You should not use if/else or when for this task)
    //17. Execute block of code if any variable is not null. (Note : You should not use if/else or When)

    private fun nullCheckWithoutIfWhen(str: String?) {
        tvSolution.append("Length is ${str?.length ?: "Empty"}")

        str?.let {
            tvSolution.append("str is not null. value: $str")
        }
    }

    // 15. Create a singleton object and call it to any other KT file
    private fun singletonInstance() {
        tvSolution.text = "Value of singleton variable: ${Singleton.name}"
    }

    // 14. Write the example of set, map and list for mutable and immutable and print the elements of it. (Note : For map, key and value both should be print)
    private fun setMapList() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        tvSolution.append("Map values are:")
        for ((k, v) in map) {
            tvSolution.append("\n\"$k\": $v")
        }
        val list = mutableListOf(1, 2, 3, 4, 5)
        // alternate-> val list: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5)
        tvSolution.append("\n\nMutable list is:")
        tvSolution.append("\n$list")
        list.add(6)
        tvSolution.append("\n$list")

        val imMutableList = listOf(11, 12, 13, 14)
        // alternate-> val imMutableList: List<Int> = listOf(11, 12, 13, 14)
        tvSolution.append("\n\nImmutable list is:")
        tvSolution.append("\n$imMutableList")

        val strings = hashSetOf("a", "b", "c", "c", "d")
        // alternate ->  val strings: HashSet<String> = hashSetOf("a", "b", "c", "c")
        tvSolution.append("\n\nHasSet list is:")
        tvSolution.append("\n$strings\n")
    }

    // 13. Write an example of lambda expression
    val max = { a: Int, b: Int ->
        if (a > b)
            a
        else
            b
    }
    private fun lambdaExpression() {
        println(max(10,4))
        tvSolution.text = max(10,4).toString()
    }

    // 11. create a basic class and their instance
    // 12. Call the multiple functions in sequence using "WITH" for instance created of class
    private fun classAndWith() {
        var logcat = LogCat("Test message")
        with(logcat) {
            d()
            v()
            e()
        }
    }

    // 10. Create a list of element and print all element except last element using range expression
    private fun rangeExpression() {
        val list = List(20, { i -> i + 1 })
        for (i in 0 until list.size - 1) {
            tvSolution.append("$i: ${list[i]}    ")
        }
    }

    // 9. Write the function which can hold the nullable arguments and return non-nullable value
    private fun funWithNullableParam() {
        tvSolution.append("\nDo you drink? - ${getSolution(true)}")
        tvSolution.append("\nAre you drunk? - ${getSolution(false)}")
        tvSolution.append("\nThen let's have one. - ${getSolution(null)}")
    }

    private fun getSolution(aFlag: Boolean?): String {
        return if (aFlag == null || aFlag == false) {
            "No"
        } else {
            "Yes"
        }
    }

    // 8. Write an example of string template and call the extension function which is created at Question 3 inside expression
    private fun extensionFunInExpression() {
        val str = "String to check extension function"
        tvSolution.text = "\"$str\" - isLong: ${str.isStringTooLong()}"
    }

    // 7. Write the usage of while and doWhile loop
    private fun whileDoWhile() {
        val list: MutableList<Int> = ArrayList()
        var index = -1
        while (list.size < 10) {
            index++
            list.add(index + 1)
        }
        tvSolution.append("$list")
        tvSolution.append("\n\nList with step 2 is: ")
        do {
            tvSolution.append("${list[index]},")
            index--
        } while (index > -1)
    }

    // 6. Write the usage of single expression function
    private fun singleExpressionFunction() {
        tvSolution.text = "Addition is ${addition(3, 2)}"
    }

    private fun addition(x: Int, y: Int) = x + y

    // 5. Create an function which can have object as a argument, and check does if it is type string then print "It is string", if object is long then print "It is long" else return "Object not identified."
    private fun whenExpression() {
        checkObjType("kotlin is good language")
        checkObjType(25.14.toLong())
        checkObjType(123)
    }

    private fun checkObjType(obj: Any?) {
        when (obj) {
            is String -> tvSolution.append("\nObject is string: $obj")
            is Long -> tvSolution.append("\nObject is long: $obj")
            is Int -> tvSolution.append("\nObject is int: $obj")
            else -> tvSolution.append("\nObject not identified")
        }
    }

    // 4. Create an array of string, which have 20 elements and create and use the if else conditions.
    private fun simpleIfElse() {
        val arr = List(20, { i -> i })
        for (value in arr) {
            /*var isPrime = true
            for (i in 2 until value) {
                if (value % i == 0){
                    isPrime = false
                    break
                }
            }*/
            // Does same like above loop
            val isPrime = (2 until value).none { value % it == 0 }
            if (isPrime) {
                tvSolution.append("\n$value is prime")
            } else {
                tvSolution.append("\n$value is not prime")
            }
        }
    }

    // 3. Create any one extension function for string which we can use in any Kotlin file.0
    private fun extensionFun() {
        val str = "String to check extension function"
        val isLong = str.isStringTooLong()
        if (isLong) {
            logd("String is too long")
            tvSolution.text = "String is too long"
        } else {
            logd("String is short")
            tvSolution.text = "String is short"
        }
    }

    // 2. Create a list of elements which can have 20 elements and loop through step 3 and print all elements on console using iterator as well as show the index of each element.
    private fun simpleLoop() {
        val list = List(20, { i -> i })
        var str = ""
        for (index in list.indices step 3) {
            str += "($index, ${list[index]}), "
        }
        tvSolution.text = str
        println(str)
    }

    // 1. Create a kotlin file, which has variable initialization and creating a function which has a single argument of Array of string and print all elements of array.
    private fun simpleArrayInitialization() {
        val arr = arrayOf("Let's", "learn", "kotlin")
        printArray(arr)
    }

    private fun printArray(arr: Array<String>) {
        println("Array: ${Arrays.deepToString(arr)}")
        tvSolution.text = "Array: ${Arrays.deepToString(arr)}"
    }

}