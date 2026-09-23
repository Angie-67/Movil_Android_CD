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

@Composable
fun ReservaScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Reserva confirmada",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Tu reserva se realizó correctamente.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Clase: Yoga",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Horario: 08:00 AM",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Sala: Sala 1",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = {
                navController.navigate("reservas")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver mis reservas")
        }
    }
}