package com.coronado.clinica.data

enum class Especialidad(val etiqueta: String) {
    MEDICINA_GENERAL("Medicina General"),
    PEDIATRIA("Pediatría"),
    CARDIOLOGIA("Cardiología"),
    DERMATOLOGIA("Dermatología"),
    GINECOLOGIA("Ginecología")
}

data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: Especialidad,
    val calificacion: Float,
    val bio: String
)

enum class CitaEstado(val etiqueta: String) {
    CONFIRMADA("Confirmada"),
    COMPLETADA("Completada")
}

data class Cita(
    val id: Int,
    val medico: Medico,
    val fecha: String,
    val hora: String,
    val estado: CitaEstado
)