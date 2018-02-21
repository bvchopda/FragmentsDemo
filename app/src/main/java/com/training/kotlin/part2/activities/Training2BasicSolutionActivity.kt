package com.training.kotlin.part2.activities

import android.os.Bundle
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import com.training.kotlin.part2.utils.ChildClass
import com.training.kotlin.part2.utils.Employee
import com.training.kotlin.part2.utils.Person
import kotlinx.android.synthetic.main.activity_training_1_basic_solution.*

/**
 * Created by cygnet on 10/1/18.
 */
class Training2BasicSolutionActivity : BaseActivity() {

    private var index: Int = -1
    private var mTasks = listOf(
            "1. Prepare a class with \"Primary & Secondary constructor\" and use multiple \"init\" block and log, Log \"Learning Kotlin part - 2 init block 1\" & Log \"Learning Kotlin part - 2 init block 2\"",
            "2. Prepare Inheritance class demo with" +
                    "\n\t2.1 Overriding Methods" +
                    "\n\t2.2 Overriding Properties"/*,
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
            "18. Write the example of break and continue at label, as well as return at label"*/
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_1_basic_solution)
        val aBundle = intent.getBundleExtra("bundle")
        index = aBundle?.getInt("index") ?: 1
        tvTaskDetails.text = mTasks[index]
        btnSolution.setOnClickListener({
            doSolution()
        })
    }

    private fun doSolution() {
        when (index + 1) {
            1 -> constructors()
            2 -> inheritance()
        /*3 -> extensionFun()
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
        18 -> breakContinueLabel()*/
        }
    }

    private fun inheritance() {
        ChildClass().parentMethod()
    }

    // 1. Prepare a class with "Primary & Secondary constructor" and use multiple "init" block and log, Log "Learning Kotlin part - 2 init block 1" & Log "Learning Kotlin part - 2 init block 2"
    private fun constructors() {
        Person("1")
        val person = Person("1", "Test")
        Employee("Test").id
    }

}