package com.vasquez.registrodenota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun PantallaPrincipal(modifier: Modifier) {
    var notFund by remember { mutableFloatStateOf(0f) }
    var notPOO by remember { mutableFloatStateOf(0f) }
    var notProg by remember { mutableFloatStateOf(0f) }
    var notBD by remember { mutableFloatStateOf(0f) }

    var redondear by remember { mutableStateOf(false) }
    var confirmado by remember { mutableStateOf(false) }
    var promedioPonderado by remember { mutableDoubleStateOf(0.0) }
    var promedioFinal by remember { mutableDoubleStateOf(0.0) }
    var mostrar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EFFF))
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Registro de Notas",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6B4EAF))
                .padding(
                    start = 16.dp,
                    top = 40.dp,
                    bottom = 18.dp
                )
        )
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Notas del ciclo",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Desliza para asignar cada nota (0 a 20)",
                color = Color.Gray,
                fontSize = 18.sp
            )
        }

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Redondear promedio final",
            )

            Switch(
                checked = redondear,
                onCheckedChange = {
                    redondear = it
                }
            )
        }

        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = confirmado,
                onCheckedChange = {
                    confirmado = it
                }
            )
            Text(
                text = "Confirmo que las notas son correctas",
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Button(
            onClick = {
                val ponde = (notFund * 0.20) + (notPOO * 0.25) + (notProg * 0.30) + (notBD * 0.25)
                promedioPonderado = ponde
                if (redondear) {
                    promedioFinal = kotlin.math.round(promedioPonderado)
                } else {
                    promedioFinal = promedioPonderado
                }
                mostrar = true
            },
            enabled = confirmado,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "CALCULAR PROMEDIO"
            )
        }

        if (mostrar) {
            Text(
                text = "Promedio ponderado: %.2f".format(promedioPonderado),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = if (redondear) {
                    "Promedio final: ${promedioFinal.toInt()}"
                } else {
                    "Promedio final: %.2f".format(promedioFinal)
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            val observacion = when {
                promedioFinal >= 17 -> "EXCELENTE"
                promedioFinal >= 13 -> "APROBADO"
                promedioFinal >= 10 -> "EN RECUPERACIÓN"
                else -> "DESAPROBADO"
            }
            Text(
                text = observacion,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "✓ Promedio calculado correctamente",
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CursoSlider(
    nombre: String,
    peso: Int,
    nota: Float,
    onNotaChange: (Float) -> Unit
) {
    Column {
        Text(text = "$nombre ($peso%)")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = nota,
                onValueChange = {
                    onNotaChange(it)
                },
                valueRange = 0f..20f,
                steps = 0,
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                Color(0xFF6B4EAF),
                                CircleShape
                            )
                    )
                },
                track = { sliderState ->
                    Box(Modifier.height(6.dp)) {
                        SliderDefaults.Track(
                            sliderState = sliderState,
                            thumbTrackGapSize = 0.dp,
                            trackInsideCornerSize = 3.dp,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color(0xFF5E4B8B),
                                inactiveTrackColor = Color(0xFFC7BBE5),
                                activeTickColor = Color.Transparent,

                                inactiveTickColor = Color.Transparent
                            )
                        )
                    }
                },
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(40.dp)
                    .background(
                        color = Color(0xFF6B4EAF),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${nota.toInt()}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

