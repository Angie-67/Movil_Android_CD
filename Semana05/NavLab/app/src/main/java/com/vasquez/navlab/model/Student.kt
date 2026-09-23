package com.vasquez.navlab.model

data class Student(
    val id: Int,
    val name: String,
    val career: String,
    val studentCode: String,
    val email: String,
    val faculty: String,
    val bio: String
)

object StudentRepository {
    val students = listOf(
        Student(
            id = 1,
            name = "Angieluz Vasquez",
            career = "Diseño y Desarrollo de Software",
            studentCode = "2024-0001",
            email = "angieluz.vasquez@tecsup.edu.pe",
            faculty = "Tecnología e Innovación",
            bio = "Estudiante destacada de Diseño y Desarrollo de Software en IV Ciclo con interés en desarrollo Android."
        ),
        Student(
            id = 2,
            name = "Maria Garcia",
            career = "Arquitectura",
            studentCode = "2024-0002",
            email = "maria.garcia@example.com",
            faculty = "Arquitectura y Diseño",
            bio = "Apasionada por el diseño sostenible y la innovación urbana."
        ),
        Student(
            id = 3,
            name = "Carlos Perez",
            career = "Medicina",
            studentCode = "2024-0003",
            email = "carlos.perez@example.com",
            faculty = "Ciencias de la Salud",
            bio = "Interesado en la investigación médica y la salud pública."
        ),
        Student(
            id = 4,
            name = "Ana Lopez",
            career = "Derecho",
            studentCode = "2024-0004",
            email = "ana.lopez@example.com",
            faculty = "Derecho y Ciencias Políticas",
            bio = "Enfocada en derecho digital y propiedad intelectual."
        ),
        Student(
            id = 5,
            name = "Luis Ramirez",
            career = "Administración",
            studentCode = "2024-0005",
            email = "luis.ramirez@example.com",
            faculty = "Negocios y Economía",
            bio = "Especializado en gestión de proyectos y emprendimiento."
        )
    )

    fun getStudentById(id: Int): Student {
        return students.find { it.id == id } ?: students.first()
    }
}
