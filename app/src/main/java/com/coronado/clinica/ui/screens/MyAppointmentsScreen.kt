package com.coronado.clinica.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.clinica.data.Cita
import com.coronado.clinica.ui.components.CitaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppointmentsScreen(
    citas: List<Cita>,
    onAbrirMenu: () -> Unit,
    onCancelarCita: (Cita) -> Unit = {}
) {
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis citas (${citas.size})",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onAbrirMenu) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Abrir menú"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (citas.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Aún no tienes citas agendadas.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(citas, key = { it.id }) { cita ->
                    CitaCard(
                        cita = cita,
                        onCancelarClick = { citaACancelar = cita }
                    )
                }
            }
        }

        citaACancelar?.let { cita ->
            AlertDialog(
                onDismissRequest = { citaACancelar = null },
                title = { Text("Cancelar cita") },
                text = {
                    Text(
                        text = "¿Deseas cancelar la cita con ${cita.medico.nombre} el ${cita.fecha} a las ${cita.hora}?"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onCancelarCita(cita)
                            citaACancelar = null
                        }
                    ) {
                        Text(
                            text = "Sí, cancelar",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { citaACancelar = null }
                    ) {
                        Text(text = "No, conservar")
                    }
                }
            )
        }
    }
}
