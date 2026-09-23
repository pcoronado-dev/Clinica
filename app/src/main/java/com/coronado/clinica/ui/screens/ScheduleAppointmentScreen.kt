package com.coronado.clinica.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.clinica.data.Medico
import com.coronado.clinica.data.MockData
import com.coronado.clinica.data.Cita
import com.coronado.clinica.state.AppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleAppointmentScreen(
    medico: Medico,
    appState: AppState,
    onVolver: () -> Unit,
    onConfirmar: (Cita) -> Unit
) {
    var fechaSeleccionada by remember { mutableIntStateOf(-1) }
    var horaSeleccionada by remember { mutableIntStateOf(-1) }

    val puedeConfirmar = fechaSeleccionada >= 0 && horaSeleccionada >= 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar cita") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Médico: ${medico.nombre}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = medico.especialidad.etiqueta,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Fecha",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MockData.fechasDisponibles.forEachIndexed { index, fecha ->
                        FilterChip(
                            selected = fechaSeleccionada == index,
                            onClick = { fechaSeleccionada = index },
                            label = { Text(fecha) }
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Hora",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MockData.horasDisponibles.forEachIndexed { index, hora ->
                        FilterChip(
                            selected = horaSeleccionada == index,
                            onClick = { horaSeleccionada = index },
                            label = { Text(hora) }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    val cita = appState.agendarCita(
                        medico = medico,
                        fecha = MockData.fechasDisponibles[fechaSeleccionada],
                        hora = MockData.horasDisponibles[horaSeleccionada]
                    )
                    onConfirmar(cita)
                },
                enabled = puedeConfirmar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar cita")
            }
        }
    }
}