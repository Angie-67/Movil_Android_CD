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

data class ReservaItem(
    val id: Int,
    val nombre: String,
    val horario: String,
    val sala: String,
    val estado: String = "Confirmada"
)

object ClasesData {
    val listaCompleta = listOf(
        Clase(
            id = 1,
            nombre = "Yoga",
            horario = "Hoy • 08:00 AM",
            sala = "Sala 1",
            duracion = "60 min",
            imagen = 0,
            descripcion = "Clase de yoga y relajación para flexibilidad y calma mental.",
            cuposDisponibles = 8,
            cuposTotales = 15
        ),
        Clase(
            id = 2,
            nombre = "Spinning",
            horario = "Hoy • 10:00 AM",
            sala = "Sala 2",
            duracion = "45 min",
            imagen = 0,
            descripcion = "Entrenamiento de ciclismo indoor de alta intensidad.",
            cuposDisponibles = 5,
            cuposTotales = 12
        ),
        Clase(
            id = 3,
            nombre = "Entrenamiento funcional",
            horario = "Hoy • 05:00 PM",
            sala = "Sala 3",
            duracion = "60 min",
            imagen = 0,
            descripcion = "Ejercicios para mejorar fuerza, agilidad y resistencia.",
            cuposDisponibles = 10,
            cuposTotales = 20
        ),
        Clase(
            id = 4,
            nombre = "Pilates Mat",
            horario = "Mañana • 09:00 AM",
            sala = "Sala 1",
            duracion = "50 min",
            imagen = 0,
            descripcion = "Fortalecimiento de la musculatura profunda y postura.",
            cuposDisponibles = 12,
            cuposTotales = 15
        ),
        Clase(
            id = 5,
            nombre = "Crossfit & Core",
            horario = "Jueves • 06:00 PM",
            sala = "Sala 4",
            duracion = "60 min",
            imagen = 0,
            descripcion = "Circuito de potencia física y resistencia muscular intensa.",
            cuposDisponibles = 4,
            cuposTotales = 10
        ),
        Clase(
            id = 6,
            nombre = "Boxeo Fitness",
            horario = "Viernes • 07:00 PM",
            sala = "Sala 2",
            duracion = "45 min",
            imagen = 0,
            descripcion = "Técnicas de boxeo combinadas con trabajo cardiovascular.",
            cuposDisponibles = 6,
            cuposTotales = 12
        ),
        Clase(
            id = 7,
            nombre = "Zumba & Dance",
            horario = "Sábado • 11:00 AM",
            sala = "Sala 3",
            duracion = "50 min",
            imagen = 0,
            descripcion = "Sesión rítmica de baile para mejorar coordinación y cardio.",
            cuposDisponibles = 15,
            cuposTotales = 25
        )
    )

    fun obtenerPorId(id: Int): Clase {
        return listaCompleta.find { it.id == id } ?: listaCompleta.first()
    }
}
