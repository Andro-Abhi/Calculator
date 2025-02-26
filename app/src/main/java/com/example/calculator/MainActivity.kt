package com.example.calculator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.ScientificCalculator
import com.example.calculator.ui.theme.ScientificCalculatorViewModel
import com.example.calculator.ui.theme.SimpleCalculator
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val simpleViewModel: SimpleCalculatorViewModel by viewModels()
    val scientificViewModel: ScientificCalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculatorTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "simple_calculator") {

                    composable("simple_calculator") {
                        SimpleCalculator(
                            simpleViewModel,
                            navController
                        )
                    }
                    composable("scientific_calculator") {
                        ScientificCalculator(
                            scientificViewModel,
                            navController
                        )
                    }
                }
            }
        }

    }
}









