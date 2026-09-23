package com.coronado.clinica.data

object MockData {

    val medicos = listOf(
        Medico(
            id = 1,
            nombre = "Dra. Carmen Rojas",
            especialidad = Especialidad.MEDICINA_GENERAL,
            calificacion = 4.9f,
            bio = "Médica general con más de 12 años de experiencia en atención primaria. " +
                    "Especialista en medicina preventiva y seguimiento de pacientes crónicos."
        ),
        Medico(
            id = 2,
            nombre = "Dr. Luis Vega",
            especialidad = Especialidad.MEDICINA_GENERAL,
            calificacion = 4.7f,
            bio = "Médico general enfocado en diagnóstico temprano y medicina familiar. " +
                    "Atención cálida y cercana con sus pacientes."
        ),
        Medico(
            id = 3,
            nombre = "Dra. Ana Quispe",
            especialidad = Especialidad.PEDIATRIA,
            calificacion = 4.8f,
            bio = "Pediatra con 10 años de experiencia atendiendo recién nacidos, niños y adolescentes. " +
                    "Especialista en crecimiento y desarrollo infantil."
        ),
        Medico(
            id = 4,
            nombre = "Dr. Marco Salas",
            especialidad = Especialidad.PEDIATRIA,
            calificacion = 4.6f,
            bio = "Pediatra dedicado al control del niño sano y a la vacunación oportuna. " +
                    "Comprometido con la salud de los más pequeños."
        ),
        Medico(
            id = 5,
            nombre = "Dr. Pedro Huamán",
            especialidad = Especialidad.CARDIOLOGIA,
            calificacion = 4.9f,
            bio = "Cardiólogo con 15 años de experiencia en el diagnóstico y tratamiento de enfermedades del corazón. " +
                    "Realiza ecocardiogramas y pruebas de esfuerzo."
        ),
        Medico(
            id = 6,
            nombre = "Dra. Rosa Delgado",
            especialidad = Especialidad.DERMATOLOGIA,
            calificacion = 4.8f,
            bio = "Dermatóloga especializada en salud de la piel, diagnóstico de lesiones y tratamientos estéticos. " +
                    "Atención personalizada y seguimiento continuo."
        ),
        Medico(
            id = 7,
            nombre = "Dra. Lucía Mendoza",
            especialidad = Especialidad.GINECOLOGIA,
            calificacion = 4.7f,
            bio = "Ginecóloga-obstetra con experiencia en salud reproductiva de la mujer, " +
                    "control prenatal y chequeos ginecológicos anuales."
        )
    )

    val fechasDisponibles = listOf(
        "Lunes 05",
        "Martes 06",
        "Miércoles 07"
    )

    val horasDisponibles = listOf(
        "09:00",
        "10:30",
        "12:00",
        "15:30"
    )
}