package com.coronado.clinica.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.coronado.clinica.data.Cita
import com.coronado.clinica.data.CitaEstado
import com.coronado.clinica.data.Especialidad
import com.coronado.clinica.data.Medico
import com.coronado.clinica.data.MockData

class AppState {

    var especialidadSeleccionada by mutableStateOf<Especialidad?>(null)

    val medicos: List<Medico> = MockData.medicos

    val citas = mutableStateListOf<Cita>()

    private var siguienteIdCita = 1

    val citasConfirmadas: List<Cita>
        get() = citas.filter { it.estado == CitaEstado.CONFIRMADA }

    val citasCompletadas: List<Cita>
        get() = citas.filter { it.estado == CitaEstado.COMPLETADA }

    fun agendarCita(medico: Medico, fecha: String, hora: String): Cita {
        val cita = Cita(
            id = siguienteIdCita++,
            medico = medico,
            fecha = fecha,
            hora = hora,
            estado = CitaEstado.CONFIRMADA
        )
        citas.add(cita)
        return cita
    }

    fun cancelarCita(cita: Cita): Boolean {
        return citas.removeAll { it.id == cita.id }
    }

    fun completarCita(cita: Cita) {
        val index = citas.indexOfFirst { it.id == cita.id }
        if (index >= 0) {
            citas[index] = citas[index].copy(estado = CitaEstado.COMPLETADA)
        }
    }
}