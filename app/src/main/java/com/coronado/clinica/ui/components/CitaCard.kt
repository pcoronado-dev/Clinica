package com.coronado.clinica.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coronado.clinica.data.Cita
import com.coronado.clinica.ui.theme.BordeGris
import com.coronado.clinica.ui.theme.GrisTextoPrincipal
import com.coronado.clinica.ui.theme.GrisTextoSecundario
import com.coronado.clinica.ui.theme.MoradoPrincipal

@Composable
fun CitaCard(
    cita: Cita,
    modifier: Modifier = Modifier,
    onCancelarClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = GrisTextoPrincipal
        ),
        border = BorderStroke(1.dp, BordeGris),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(MoradoPrincipal)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = cita.medico.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = GrisTextoPrincipal
                    )
                    CitaEstadoBadge(estado = cita.estado)
                }
                Text(
                    text = cita.medico.especialidad.etiqueta,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 13.sp,
                    color = GrisTextoSecundario
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = GrisTextoSecundario,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = cita.fecha,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = GrisTextoSecundario
                    )
                    Icon(
                        imageVector = Icons.Outlined.Schedule,
                        contentDescription = null,
                        tint = GrisTextoSecundario,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = cita.hora,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = GrisTextoSecundario
                    )
                }
                if (onCancelarClick != null) {
                    OutlinedButton(
                        onClick = onCancelarClick,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 4.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
