package com.vasquez.tecsup_fit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vasquez.tecsup_fit.screens.DetalleClaseScreen
import com.vasquez.tecsup_fit.screens.HomeScreen
import com.vasquez.tecsup_fit.screens.PerfilScreen
import com.vasquez.tecsup_fit.screens.ReservaScreen
import com.vasquez.tecsup_fit.screens.ReservasScreen
import com.vasquez.tecsup_fit.screens.RutinasScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

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

                val id = backStackEntry.arguments?.getInt("id") ?: 0

                DetalleClaseScreen(
                    navController = navController,
                    id = id
                )
            }

            composable(Screen.Reserva.route) {
                ReservaScreen(navController)
            }

            composable(Screen.Reservas.route) {
                ReservasScreen(navController)
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

    NavigationBar {

        NavigationBarItem(
            selected = rutaActual == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            label = {
                Text("Inicio")
            }
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Reservas.route,
            onClick = {
                navController.navigate(Screen.Reservas.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Reservas"
                )
            },
            label = {
                Text("Reservas")
            }
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Rutinas.route,
            onClick = {
                navController.navigate(Screen.Rutinas.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Rutinas"
                )
            },
            label = {
                Text("Rutinas")
            }
        )

        NavigationBarItem(
            selected = rutaActual == Screen.Perfil.route,
            onClick = {
                navController.navigate(Screen.Perfil.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text("Perfil")
            }
        )
    }
}