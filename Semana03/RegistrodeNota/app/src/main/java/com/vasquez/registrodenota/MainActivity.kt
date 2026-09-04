package com.vasquez.registrodenota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vasquez.registrodenota.ui.theme.RegistrodeNotaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrodeNotaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(modifier: Modifier){
    var notFund by remember { mutableFloatStateOf(0f) }
    var notPOO by remember { mutableFloatStateOf(0f) }
    var notProg by remember { mutableFloatStateOf(0f) }
    var notBD by remember { mutableFloatStateOf(0f) }

    var redondear by remember { mutableStateOf(false) }
    var confirmado by remember { mutableStateOf(false) }
    var promedioPonderado by remember { mutableDoubleStateOf(0.0) }
    var promedioFinal by remember { mutableDoubleStateOf(0.0) }
    var chipColor by remember { mutableStateOf(Color.Gray) }
    var mostrar by remember { mutableStateOf(false) }

    CursoSlider(
        "Fundamentos de Programación",
        20,
        notFund
    ) { notFund = it }

    CursoSlider(
        "Programación Orientada a Objetos",
        25,
        notPOO
    ) { notPOO = it }

    CursoSlider(
        "Programación en Móviles",
        30,
        notProg
    ) { notProg = it }

    CursoSlider(
        "Base de Datos",
        25,
        notBD
    ) { notBD = it }
}
@Composable
fun CursoSlider(
    nombre: String,
    peso: Int,
    nota: Float,
    onNotaChange: (Float) -> Unit
) {
    Column {
        Text(text = "$nombre ($peso%)")
        Slider(
            value = nota,
            onValueChange = {
                onNotaChange(it)
            },
            valueRange = 0f..20f,
            steps = 19
        )
        Text(text = "Nota: ${nota.toInt()}")
    }
}