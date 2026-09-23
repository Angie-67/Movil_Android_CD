package com.vasquez.tecsup_fit.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetalleClase : Screen("detalle_clase/{id}")
    object Reserva : Screen("reserva")
    object Reservas : Screen("reservas")
    object Rutinas : Screen("rutinas")
    object Perfil : Screen("perfil")
}