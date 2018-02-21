package com.kotlin.android.activities

import android.os.Bundle
import android.view.View
import com.example.bvchopda.fragmentsdemo.R
import com.kotlin.android.BaseActivity
import kotlinx.android.synthetic.main.activity_operation.*

class OperationActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)
        buttonAddition.setOnClickListener(this)
        buttonSubtraction.setOnClickListener(this)
        buttonMultiplication.setOnClickListener(this)
        buttonDivision.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            buttonAddition -> {
                textViewSymbol.text = "+"
                textViewOperation.text = "Addition is"
                textViewAnswer.text = (editText1.text.toString().toInt() + editText2.text.toString().toInt()).toString()
            }
            buttonSubtraction -> {
                textViewSymbol.text = "-"
                textViewOperation.text = "Subtraction is"
                textViewAnswer.text = (editText1.text.toString().toInt() - editText2.text.toString().toInt()).toString()
            }
            buttonMultiplication -> {
                textViewSymbol.text = "x"
                textViewOperation.text = "Multiplication is"
                textViewAnswer.text = (editText1.text.toString().toInt() * editText2.text.toString().toInt()).toString()

            }
            buttonDivision -> {
                textViewSymbol.text = "/"
                textViewOperation.text = "Division is"
                textViewAnswer.text = String.format("%.2f", editText1.text.toString().toInt() / editText2.text.toString().toFloat())
            }
            else -> {
                logd(textViewAnswer.text.toString())
            }
        }
        logd(textViewAnswer.text.toString())
    }
}
