package com.vasquez.tecsup_fit.model

data class Clase(
    val id: Int,
    val nombre: String,
    val horario: String,
    val sala: String,
    val duracion: String,
    val imagen: Int,
    val descripcion: String,
    val cuposDisponibles: Int,
    val cuposTotales: Int
)