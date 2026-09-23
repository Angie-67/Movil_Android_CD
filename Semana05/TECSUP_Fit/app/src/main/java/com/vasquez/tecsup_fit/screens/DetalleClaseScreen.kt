package com.vasquez.tecsup_fit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vasquez.tecsup_fit.model.Clase

@Composable
fun DetalleClaseScreen(
    navController: NavController,
    id: Int
) {

    val clases = listOf(
        Clase(
            id = 1,
            nombre = "Yoga",
            horario = "08:00 AM",
            sala = "Sala 1",
            duracion = "60 min",
            imagen = 0,
            descripcion = "Clase de yoga y relajación.",
            cuposDisponibles = 8,
            cuposTotales = 15
        ),
        Clase(
            id = 2,
            nombre = "Spinning",
            horario = "10:00 AM",
            sala = "Sala 2",
            duracion = "45 min",
            imagen = 0,
            descripcion = "Entrenamiento de ciclismo indoor.",
            cuposDisponibles = 5,
            cuposTotales = 12
        ),
        Clase(
            id = 3,
            nombre = "Entrenamiento funcional",
            horario = "05:00 PM",
            sala = "Sala 3",
            duracion = "60 min",
            imagen = 0,
            descripcion = "Ejercicios para mejorar fuerza y resistencia.",
            cuposDisponibles = 10,
            cuposTotales = 20
        )
    )

    val clase = clases.find { it.id == id }

    if (clase != null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = clase.nombre,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Horario: ${clase.horario}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Sala: ${clase.sala}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Duración: ${clase.duracion}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = clase.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = {
                    navController.navigate("reserva")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reservar cupo")
            }
        }
    }
}