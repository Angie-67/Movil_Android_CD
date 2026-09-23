package com.vasquez.tecsup_fit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vasquez.tecsup_fit.model.ReservaItem
import com.vasquez.tecsup_fit.screens.DetalleClaseScreen
import com.vasquez.tecsup_fit.screens.HomeScreen
import com.vasquez.tecsup_fit.screens.PerfilScreen
import com.vasquez.tecsup_fit.screens.ReservaScreen
import com.vasquez.tecsup_fit.screens.ReservasScreen
import com.vasquez.tecsup_fit.screens.RutinasScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val listaReservas = remember {
        mutableStateListOf(
            ReservaItem(id = 1, nombre = "Yoga", horario = "Hoy • 08:00 AM", sala = "Sala 1", estado = "Confirmada"),
            ReservaItem(id = 2, nombre = "Spinning", horario = "Hoy • 10:00 AM", sala = "Sala 2", estado = "Confirmada"),
            ReservaItem(id = 3, nombre = "Entrenamiento funcional", horario = "Ayer • 05:00 PM", sala = "Sala 3", estado = "Completada")
        )
    }

    val rutasPrincipales = listOf(
        Screen.Home.route,
        Screen.Reservas.route,
        Screen.Rutinas.route,
        Screen.Perfil.route
    )

    Scaffold(
        bottomBar = {
            if (rutaActual in rutasPrincipales) {
                BottomBar(
                    navController = navController,
                    rutaActual = rutaActual
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(navController)
            }

            composable(
                route = Screen.DetalleClase.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 1
                DetalleClaseScreen(
                    navController = navController,
                    id = id,
                    onReservarClase = { clase ->
                        val yaExiste = listaReservas.any { it.id == clase.id && it.estado == "Confirmada" }
                        if (!yaExiste) {
                            listaReservas.add(
                                0,
                                ReservaItem(
                                    id = clase.id,
                                    nombre = clase.nombre,
                                    horario = clase.horario,
                                    sala = clase.sala,
                                    estado = "Confirmada"
                                )
                            )
                        }
                    }
                )
            }

            composable(
                route = "reserva/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        defaultValue = 1
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 1
                ReservaScreen(navController = navController, id = id)
            }

            composable(Screen.Reserva.route) {
                ReservaScreen(navController = navController, id = 1)
            }

            composable(Screen.Reservas.route) {
                ReservasScreen(
                    navController = navController,
                    reservas = listaReservas
                )
            }

            composable(Screen.Rutinas.route) {
                RutinasScreen(navController)
            }

            composable(Screen.Perfil.route) {
                PerfilScreen(navController)
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    rutaActual: String?
) {
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 8.dp
    ) {
        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            label = {
                Text(
                    text = "Inicio",
                    fontWeight = if (rutaActual == Screen.Home.route) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            colors = itemColors
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Reservas.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Reservas"
                )
            },
            label = {
                Text(
                    text = "Reservas",
                    fontWeight = if (rutaActual == Screen.Reservas.route) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            colors = itemColors
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Rutinas.route,
            onClick = {
                navController.navigate(Screen.Rutinas.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Rutinas"
                )
            },
            label = {
                Text(
                    text = "Rutinas",
                    fontWeight = if (rutaActual == Screen.Rutinas.route) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            colors = itemColors
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Perfil.route,
            onClick = {
                navController.navigate(Screen.Perfil.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text(
                    text = "Perfil",
                    fontWeight = if (rutaActual == Screen.Perfil.route) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            colors = itemColors
        )
    }
}
