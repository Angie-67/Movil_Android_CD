package com.vasquez.listadetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasquez.listadetareas.ui.theme.ListadeTareasTheme

data class Tarea(
    val id: Int,
    val nombre: String,
    val completada: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListadeTareasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF4F6FA) // Fondo de la imagen
                ) {
                    PantallaTareas()
                }
            }
        }
    }
}

@Composable
fun PantallaTareas() {
    var textoTarea by remember { mutableStateOf("") }
    var contadorId by remember { mutableIntStateOf(1) }
    val listaTareas = remember { mutableStateListOf<Tarea>() }

    // El color azul marino se ha ajustado exactamente al tono más oscuro e intenso de la imagen (0xFF1A237E)
    val colorAzulTecsup = Color(0xFF1A237E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        // Título centrado y en azul oscuro (Lista de tareas - Tecsup)
        Text(
            text = "Lista de tareas - Tecsup",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = colorAzulTecsup,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Campo de texto con la etiqueta de la nueva imagen
        OutlinedTextField(
            value = textoTarea,
            onValueChange = { textoTarea = it },
            label = { Text("¿Qué tarea tienes pendiente?") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorAzulTecsup,
                focusedLabelColor = colorAzulTecsup,
                unfocusedBorderColor = colorAzulTecsup,
                unfocusedLabelColor = Color.Gray
            )
        )

        // Botón azul oscuro ovalado
        Button(
            onClick = {
                if (textoTarea.isNotBlank()) {
                    listaTareas.add(Tarea(id = contadorId++, nombre = textoTarea))
                    textoTarea = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorAzulTecsup),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Agregar tarea",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Contador de tareas totales centrado
        Text(
            text = "Total de tareas: ${listaTareas.size}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF555555),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp)
        )

        // Lista de tareas con tarjetas blancas
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listaTareas, key = { it.id }) { tarea ->
                ItemTarea(
                    tarea = tarea,
                    onEliminar = { listaTareas.remove(tarea) },
                    onCambiarEstado = { completada ->
                        val index = listaTareas.indexOf(tarea)
                        if (index != -1) {
                            listaTareas[index] = listaTareas[index].copy(completada = completada)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ItemTarea(
    tarea: Tarea,
    onEliminar: () -> Unit,
    onCambiarEstado: (Boolean) -> Unit
) {
    val colorAzulTecsup = Color(0xFF1A237E)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = tarea.completada,
                    onCheckedChange = { onCambiarEstado(it) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorAzulTecsup,
                        uncheckedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = tarea.nombre,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // Ícono de tacho de basura estilizado en texto/emoji para evitar problemas de dependencias
            IconButton(onClick = onEliminar) {
                Text(
                    text = "🗑",
                    fontSize = 22.sp,
                    color = Color(0xFF90A4AE)
                )
            }
        }
    }
}