package com.vasquez.registrodenota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.remote.creation.compose.state.round
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasquez.registrodenota.ui.theme.RegistrodeNotaTheme
import kotlin.math.roundToInt

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
    val redon = Math.round(promedioPonderado)

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
                    start = 12.dp,
                    top = 40.dp,
                    bottom = 18.dp
                )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Notas del ciclo",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Text(
                text = "Desliza para asignar cada nota (0 a 20)",
                color = Color.Gray,
                fontSize = 15.sp
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
                .padding(10.dp),
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
                },
                colors = androidx.compose.material3.SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF6B4EAF)
                )
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
                .padding(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6B4EAF)
                )
        ) {
            Text(
                text = "CALCULAR PROMEDIO"
            )
        }

        if (mostrar) {

            val observacion = when {
                promedioFinal >= 17 -> "EXCELENTE"
                promedioFinal >= 13 -> "APROBADO"
                promedioFinal >= 10 -> "EN RECUPERACIÓN"
                else -> "DESAPROBADO"
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color(0xFFD9CFF0),
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Promedio ponderado:",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "%.2f".format(promedioPonderado),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (redondear) {
                            "Promedio final: ${redon}"
                        } else {
                            "Promedio final: %.2f".format(promedioFinal)
                        },
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6B4EAF)
                    )

                    if (redondear) {
                        Text(
                            text = "(redondeado)",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = when {
                                    promedioFinal >= 13 -> Color(0xFFDDEFE1)
                                    promedioFinal >= 10 -> Color(0xFFFFE8B3)
                                    else -> Color(0xFFF5D6D6)
                                },
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(
                                horizontal = 18.dp,
                                vertical = 7.dp
                            )
                    ) {
                        Text(
                            text = observacion,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                promedioFinal >= 13 -> Color(0xFF31813A)
                                promedioFinal >= 10 -> Color(0xFF9A6B00)
                                else -> Color(0xFFB3261E)
                            }
                        )
                    }
                }

                Text(
                    text = "✓ Promedio calculado correctamente",
                    color = Color(0xFF438A4A),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Desarrollado por: Angieluz Vasquez Macalupu",
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp))
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$nombre ($peso%)",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFFEEE6FC),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${nota.toInt()}",
                    color = Color(0xFF6B4EAF),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

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
                Box(
                    Modifier.height(6.dp)
                ) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )
    }
}

