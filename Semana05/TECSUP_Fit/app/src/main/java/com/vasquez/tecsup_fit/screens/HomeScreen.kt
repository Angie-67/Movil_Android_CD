package com.vasquez.tecsup_fit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vasquez.tecsup_fit.model.Clase
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(navController: NavController) {
    var filtroseleccionado by remember { mutableStateOf("Hoy") }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "TECSUP Fit",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Clases disponibles",
            modifier = Modifier.padding(top = 8.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                FilterChip(
                    selected = filtroseleccionado == "Hoy",
                    onClick = {
                        filtroseleccionado = "Hoy"
                    },
                    label = {
                        Text("Hoy")
                    }
                )
            }

            item {
                FilterChip(
                    selected = filtroseleccionado == "Esta semana",
                    onClick = {
                        filtroseleccionado = "Esta semana"
                    },
                    label = {
                        Text("Esta semana")
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            items(clases) { clase ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(
                            "detalle_clase/${clase.id}"
                        )
                    }
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = clase.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = clase.horario,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}