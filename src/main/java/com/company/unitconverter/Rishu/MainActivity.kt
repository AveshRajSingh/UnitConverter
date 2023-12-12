package com.company.unitconverter.Rishu

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.unitconverter.Rishu.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the
                // 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue  by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meter")}
    var outputUnit by  remember { mutableStateOf("Meter")}
    var iExpand by remember { mutableStateOf(false)}
    var oExpand by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oConversionFactor = remember { mutableStateOf(1.00)}

    //Here is function that convert one unit into another or this is hole
    // logic of the program

    fun convertUnit(){

        //  ?:-> it is known an elvish operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        // here are all the UI element that are used for colum when we want om top of each other
        Text("Unit Converter",style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
               inputValue = it
                convertUnit()
            //here goes all the logic of what should happened
        }, label = { Text(text = "Enter value",style = MaterialTheme.typography.bodyMedium)})
        Spacer(modifier = Modifier.height(25.dp))
        // use row if you want to add UI element next to each other
        Row {
            // input box
            Box {
                //Input button
            Button(onClick = { iExpand = true }) {
                Text( text = inputUnit)
                Icon(Icons.Default.ArrowDropDown , contentDescription = "select button")
            }
            DropdownMenu(expanded = iExpand, onDismissRequest = {iExpand = false }) {
                DropdownMenuItem(

                    text = { Text("Centimeter") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Centimeter"
                        conversionFactor.value = 0.01
                        convertUnit()
                    }
                )
                DropdownMenuItem(

                    text = { Text("Meter") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Meter"
                        conversionFactor.value = 1.0
                        convertUnit()
                    }
                )
                DropdownMenuItem(

                    text = { Text("Feet") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnit()

                    }
                )
                DropdownMenuItem(

                    text = { Text("Millimetre") },
                    onClick = {

                        iExpand = false
                        inputUnit = "Millimetre"
                        conversionFactor.value = 0.001
                        convertUnit()
                    }
                )
            }

            }
            Spacer(modifier = Modifier.width(25.dp))
            // Output Box
            Box {
                // Output button
                Button(onClick = { oExpand = true }) {
                    Text( text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "select button")

                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false}) {
                    DropdownMenuItem(

                        text = { Text("Centimeter") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(

                        text = { Text("Meter") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Meter"
                            oConversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(

                        text = { Text("Feet") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(

                        text = { Text("Millimetre") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Millimetre"
                            oConversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        //Result text
        Text(" Result : $outputValue $outputUnit ",
            style = MaterialTheme.typography.headlineMedium)
    }
}
