package com.example.calculator.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calculator.SimpleCalculatorViewModel

@Composable
fun SimpleCalculator(viewModel : SimpleCalculatorViewModel, navController: NavController) {
    val operationScreen = viewModel.operationText
    val rowModifier =  Modifier.fillMaxWidth()
    val rhalgn = Arrangement.Absolute.SpaceEvenly
    val operator = viewModel.operatorSign
    Column(modifier = Modifier.fillMaxSize(1f).background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        Text(text = operator.toString(), color = MaterialTheme.colorScheme.onBackground, fontSize = 40.sp)
        Text(
            text = operationScreen,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 40.sp,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis
        )

    }
        Spacer(Modifier.padding(35.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = {navController.navigate("scientific_calculator")}, Modifier.size(50.dp)){
                Icon(imageVector = Icons.Filled.Calculate,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(35.dp))}
            Spacer(Modifier.padding(20.dp))
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 2.dp,
            modifier = Modifier.padding(8.dp)
        )
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier) {
            FunctionButton('C', {viewModel.clearScreen() }, MaterialTheme.colorScheme.error,MaterialTheme.colorScheme.onError)
            IconButtons(Icons.Default.Percent) { viewModel.operatorFun('%')}
            IconButtons(Icons.Filled.ArrowBackIosNew) { viewModel.removeNumber()}
            FunctionButton('=' , {viewModel.eval()})
        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('1') {viewModel.addNumber('1')}
            NumberButton('2') { viewModel.addNumber('2')}
            NumberButton('3') { viewModel.addNumber('3')}
            FunctionButton('÷', {viewModel.operatorFun('÷') })

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('4') {viewModel.addNumber('4')}
            NumberButton('5') {viewModel.addNumber('5')}
            NumberButton('6') {viewModel.addNumber('6')}
            FunctionButton('+' ,{viewModel.operatorFun('+')})

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('7') {viewModel.addNumber('7')}
            NumberButton('8') {viewModel.addNumber('8')}
            NumberButton('9') {viewModel.addNumber('9')}
            FunctionButton('-' ,{viewModel.operatorFun('-') })

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            FunctionButton('^' ,{ viewModel.operatorFun('^')})
            NumberButton('0') {viewModel.addNumber('0') }
            FunctionButton('.', { viewModel.addNumber('.') })
            FunctionButton('x', {viewModel.operatorFun('x') })

        }
        Spacer(Modifier.padding(10.dp))
        Text("Developed by Abhinav",color = MaterialTheme.colorScheme.onBackground)
    }

}


@Composable
fun NumberButton(number : Char,
                 addNum :(Char) -> Unit){
    val buttonShape = RoundedCornerShape(50.dp)
    Button(onClick = {addNum(number)}, shape = buttonShape,
        modifier = Modifier.size(60.dp), colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor =MaterialTheme.colorScheme.onPrimary
        )) {
        Text(text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun FunctionButton(number : Char,
                 addNum :(Char) -> Unit,
                   color: Color = MaterialTheme.colorScheme.secondary,
                   contentColor: Color = MaterialTheme.colorScheme.onSecondary){
    val buttonShape = RoundedCornerShape(50.dp)
    Button(onClick = {addNum(number)}, shape = buttonShape,
        modifier = Modifier.size(60.dp), colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = contentColor
        )) {
        Text(text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp)
    }
}


@Composable
fun IconButtons(icon: ImageVector,onClick: () -> Unit) {
    IconButton(
        onClick, modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "remove",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}
@Preview
@Composable
fun Preview(){
    val viewModel = SimpleCalculatorViewModel()
    val navController = rememberNavController()
    SimpleCalculator(viewModel, navController = navController)
}
