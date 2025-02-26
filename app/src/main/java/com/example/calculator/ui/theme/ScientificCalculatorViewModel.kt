package com.example.calculator.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlin.math.*
import androidx.lifecycle.ViewModel

class ScientificCalculatorViewModel : ViewModel() {
    var valueText by mutableStateOf<String>("0")
    private set
    var operator by mutableStateOf<String>("")
        private set

    var num = 0.0

    fun addValue(value: String){
        if (valueText == "0"){
            valueText = value
        }
        else {
            valueText += value
        }
    }
    fun calculate() {
        num = valueText.toDoubleOrNull() ?: 1.0    // Avoids crashing if input is invalid
        valueText = when (operator) {
            "sin" -> sin(degreeToRad())
            "cos" -> cos(degreeToRad())
            "tan" -> tan(degreeToRad())
            "sec" -> safeCalculate { 1 / cos(degreeToRad()) }
            "cosec" -> safeCalculate { 1 / sin(degreeToRad()) }
            "cot" -> safeCalculate { 1 / tan(degreeToRad()) }
            "log" -> safeCalculate { log10(num) }  // Using log10 for base-10 log
            "ln" -> safeCalculate { ln(num) }
            "sqrt" -> safeCalculate { sqrt(num) }
            "sqr" ->  (num * num)
            "reciprocal" ->  1/num
            "factorial" -> factorial(num.toInt())
            else -> "Invalid Operator"
        }.toString()
        operator = ""
    }

    // Helper function to catch errors and return a fallback value
    private fun safeCalculate(operation: () -> Double): String {
        return try {
            operation().toString()
        } catch (e: ArithmeticException) {
            "Error"
        }
    }

    // Convert degrees to radians
    private fun degreeToRad(): Double {
        return Math.toRadians(num)
    }


    fun assignOperator(operators: String){
       operator = operators
        valueText = valueText
    }

    fun allClear(){
        operator = ""
        valueText = ""
        num = 0.0
    }
    fun backFunction(){
        if (valueText.isNotEmpty()) valueText = valueText
            .substring(0, valueText.length - 1)
    }
    fun factorial(x : Int) : Int {
        if(x == 0 || x == 1){return 1}
        else{
            return factorial(x-1) * x
        }
    }
}