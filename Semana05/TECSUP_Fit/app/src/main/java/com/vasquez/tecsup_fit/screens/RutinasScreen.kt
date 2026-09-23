package com.vasquez.tecsup_fit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items

@Composable
fun RutinasScreen(navController: NavController) {

    val rutinas = listOf(
        "Rutina de fuerza - 45 min",
        "Rutina de cardio - 30 min",
        "Rutina de cuerpo completo - 60 min"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mis rutinas",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Planes de entrenamiento",
            modifier = Modifier.padding(top = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            items(rutinas) { rutina ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = rutina,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}