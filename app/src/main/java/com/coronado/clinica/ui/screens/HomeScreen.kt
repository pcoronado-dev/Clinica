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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coronado.clinica.data.Especialidad
import com.coronado.clinica.data.Medico
import com.coronado.clinica.state.AppState
import com.coronado.clinica.ui.components.MedicoCard
import com.coronado.clinica.ui.theme.GrisInactivo
import com.coronado.clinica.ui.theme.GrisTextoPrincipal
import com.coronado.clinica.ui.theme.GrisTextoSecundario
import com.coronado.clinica.ui.theme.MoradoPrincipal

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
                            text = "Hola, Juan Pérez",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Reserva de citas médicas",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onAbrirMenu) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Abrir menú",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MoradoPrincipal
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
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = GrisTextoPrincipal,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    SpecialtyTabPill(
                        selected = appState.especialidadSeleccionada == null,
                        label = "Todos",
                        onClick = { appState.especialidadSeleccionada = null }
                    )
                }
                items(Especialidad.entries) { especialidad ->
                    SpecialtyTabPill(
                        selected = appState.especialidadSeleccionada == especialidad,
                        label = especialidad.etiqueta,
                        onClick = { appState.especialidadSeleccionada = especialidad }
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
private fun SpecialtyTabPill(
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = if (selected) MoradoPrincipal else GrisInactivo,
        contentColor = if (selected) Color.White else GrisTextoSecundario
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
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
            contentDescription = null,
            tint = GrisTextoSecundario
        )
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyMedium,
            color = GrisTextoSecundario
        )
    }
}
