package com.vasquez.tecsup_fit.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.DetalleClase.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DetalleClaseScreen(navController, id)
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