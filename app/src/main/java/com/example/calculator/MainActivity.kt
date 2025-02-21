package com.example.calculator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val myViewModel: CalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
    Calculator(myViewModel)
            }
        }
    }
@Composable
 fun Calculator(viewModel : CalculatorViewModel = CalculatorViewModel()) {
    val operationScreen = viewModel.operationText
    val rowModifier =  Modifier.fillMaxWidth()
    val rhalgn = Arrangement.Absolute.SpaceEvenly
    val operator = viewModel.operatorSign
    Column(modifier = Modifier.fillMaxSize(1f).background(Color.Black),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
            )
    {Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        Text(text = operator.toString(),color = Color.Cyan,fontSize = 60.sp)
        Text(
            text = operationScreen,
            color = Color.Green,
            fontSize = 60.sp,
            textAlign = TextAlign.End,
            maxLines = 2
        )
    }
        Spacer(Modifier.padding(50.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('C', { viewModel.clearScreen() },Color.Red)
            IconButton(Icons.Default.Percent) { viewModel.operatorFun('%')}
            IconButton(Icons.Filled.ArrowBackIosNew) { viewModel.removeNumber()}
            NumberButton('=' ,{ viewModel.eval() } , Color.Green)
        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('1' ,{viewModel.addNumber('1')})
            NumberButton('2' ,{ viewModel.addNumber('2')})
            NumberButton('3' ,{ viewModel.addNumber('3')})
            NumberButton('/', {viewModel.operatorFun('/') }, Color.Cyan)

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('4' ,{viewModel.addNumber('4')})
            NumberButton('5' ,{viewModel.addNumber('5')})
            NumberButton('6' ,{viewModel.addNumber('6')})
            NumberButton('+' ,{viewModel.operatorFun('+')}, Color.Cyan)

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('7' ,{viewModel.addNumber('7')})
            NumberButton('8' ,{viewModel.addNumber('8')})
            NumberButton('9' ,{viewModel.addNumber('9')})
            NumberButton('-' ,{viewModel.operatorFun('-') }, Color.Cyan)

        }
        Spacer(Modifier.padding(10.dp))
        Row(horizontalArrangement =  rhalgn, modifier = rowModifier ) {
            NumberButton('^' ,{ viewModel.operatorFun('^')}, Color.Cyan)
            NumberButton('0' ,{viewModel.addNumber('0') })
            NumberButton('.', { viewModel.addNumber('.') }, Color.Cyan)
            NumberButton('x', {viewModel.operatorFun('x') }, Color.Cyan)

        }
        Spacer(Modifier.padding(10.dp))
        Text("Developed by Abhinav",color = Color.White)
    }
}


@Composable
fun NumberButton(number : Char, addNum :(Char) -> Unit,color: Color = Color.Gray){
    val buttonColor = ButtonDefaults.buttonColors(color)
    val buttonShape = RoundedCornerShape(50.dp)
    Button(onClick = {addNum(number)}, shape = buttonShape, colors = buttonColor,
        modifier = Modifier.size(75.dp)) {
        Text(text = "$number", color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp)
    }
}


@Composable
fun IconButton(icon: ImageVector,onClick: () -> Unit) {
    IconButton(onClick, modifier = Modifier
        .size(75.dp)
        .clip(CircleShape)
        .background(Color.Yellow)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "remove"
        )
    }
}

@Preview
@Composable
fun CalculatorPreview(){
    Calculator()
}