package com.example.calculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ScientificCalculator(viewModel : ScientificCalculatorViewModel ,
                         navController: NavController
) {
    val valueScreen = viewModel.valueText
    val operator = viewModel.operator
    val rhalgn = Arrangement.Absolute.SpaceEvenly
    val rowModifier = Modifier.fillMaxWidth()
    Column(
        modifier = Modifier.fillMaxSize(1f).background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = operator, color = MaterialTheme.colorScheme.onBackground,
                fontSize = 60.sp,
                textAlign = TextAlign.End,
                maxLines = 2
            )
            Spacer(Modifier.padding(5.dp))

            Text(
                text = valueScreen, fontSize = 60.sp, color = Color.Green,
                textAlign = TextAlign.End,
                maxLines = 2
            )
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            Button(onClick = { navController.navigate("simple_calculator") }) { Text("<->") }
            Spacer(Modifier.padding(50.dp))
        }
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface).fillMaxHeight(0.75f)
        , verticalArrangement = Arrangement.SpaceAround) {

            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {

                FunctionButton({ viewModel.allClear() }, "C")
                FunctionButton({ viewModel.backFunction() }, "<-")
                Button(onClick = { viewModel.calculate() }) { Text("=") }
                FunctionButton({ viewModel.assignOperator("factorial") }, "n!")

            }
            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {
                FunctionButton({ viewModel.addValue("1") }, "1")
                FunctionButton({ viewModel.addValue("2") }, "2")
                FunctionButton({ viewModel.addValue("3") }, "3")
                FunctionButton({ viewModel.assignOperator("sin") }, "sin")

            }
            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {
                FunctionButton({ viewModel.addValue("4") }, "4")
                FunctionButton({ viewModel.addValue("5") }, "5")
                FunctionButton({ viewModel.addValue("6") }, "6")
                FunctionButton({ viewModel.assignOperator("cos") }, "cos")

            }
            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {
                FunctionButton({ viewModel.addValue("7") }, "7")
                FunctionButton({ viewModel.addValue("8") }, "8")
                FunctionButton({ viewModel.addValue("9") }, "9")
                FunctionButton({ viewModel.assignOperator("tan") }, "tan")

            }
            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {
                FunctionButton({ viewModel.assignOperator("cosec") }, "cosec")
                FunctionButton({ viewModel.assignOperator("sec") }, "sec")
                FunctionButton({ viewModel.assignOperator("cot") }, "cot")
                FunctionButton({ viewModel.assignOperator("reciprocal") }, "1/x")
            }
            Row(horizontalArrangement = rhalgn, modifier = rowModifier) {
                FunctionButton({ viewModel.assignOperator("log") }, "log")
                FunctionButton({ viewModel.addValue("0") }, "0")
                FunctionButton({ viewModel.assignOperator("ln") }, "ln")
                FunctionButton({ viewModel.assignOperator("sqrt") }, "Sqrt")


            }
        }
    }
}
@Composable
fun FunctionButton(function: (String) -> Unit,text : String){
    Button(onClick = {function(text)},
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(height = 52.dp, width = 80.dp),colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor =MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text, maxLines = 1)
    }
}


@Preview
@Composable
fun ScientificCalculatorPreview(){
    val navController = rememberNavController()
    val viewModel = ScientificCalculatorViewModel()
    ScientificCalculator(viewModel,navController )
}
