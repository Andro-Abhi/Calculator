package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import java.lang.ArithmeticException


class SimpleCalculatorViewModel : ViewModel() {
    var operationText by mutableStateOf<String>("0")
        private set
    var operatorSign: Char = ' '
    var num1 = 0.0
    var num2 = 0.0
    fun addNumber(number: Char) {
        operationText = if (operationText == "0") {
            number.toString()
        } else {
            operationText + number
        }
    }

    fun removeNumber() {
        if (operationText.isNotEmpty()) operationText = operationText
            .substring(0, operationText.length - 1)
    }

    fun clearScreen() {
        operationText = "0"
        operatorSign = ' '
    }

    fun assignNum(): Double {
        if (operationText.isNotEmpty()) {
            val num = operationText.toDouble()
            return num
        } else {
            return 0.0
        }
    }


    fun operatorFun(operator: Char) {
        num1 = assignNum()
        operationText = "0"
        operatorSign = operator
    }

    fun eval() {
        if (operatorSign != ' ') {
            num2 = assignNum()
            operationText = when (operatorSign) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                'x' -> num1 * num2
                '÷' -> try {
                    num1 / num2
                } catch (e:ArithmeticException) {
                    "Not Defined"
                }
                '%' -> num1 * num2 / 100
                '^' -> num1.pow(num2)
                else -> 0
            }.toString()
        }
    }
}