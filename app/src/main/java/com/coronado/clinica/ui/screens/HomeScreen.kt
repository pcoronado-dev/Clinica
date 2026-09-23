package com.coronado.clinica.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.clinica.data.Especialidad
import com.coronado.clinica.data.Medico
import com.coronado.clinica.state.AppState
import com.coronado.clinica.ui.components.MedicoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    appState: AppState,
    onAbrirMenu: () -> Unit,
    onMedicoClick: (Medico) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Clínica Salud+",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Reserva de citas médicas",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onAbrirMenu) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Abrir menú"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Elige una especialidad",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = appState.especialidadSeleccionada == null,
                        onClick = { appState.especialidadSeleccionada = null },
                        label = { Text("Todos") }
                    )
                }
                items(Especialidad.entries) { especialidad ->
                    FilterChip(
                        selected = appState.especialidadSeleccionada == especialidad,
                        onClick = { appState.especialidadSeleccionada = especialidad },
                        label = { Text(especialidad.etiqueta) }
                    )
                }
            }

            val medicos = if (appState.especialidadSeleccionada == null) {
                appState.medicos
            } else {
                appState.medicos.filter { it.especialidad == appState.especialidadSeleccionada }
            }

            if (medicos.isEmpty()) {
                EmptyState(
                    mensaje = "No hay médicos para la especialidad seleccionada.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(medicos, key = { it.id }) { medico ->
                        MedicoCard(
                            medico = medico,
                            onClick = { onMedicoClick(medico) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyState(mensaje: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.MedicalServices,
            contentDescription = null
        )
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}