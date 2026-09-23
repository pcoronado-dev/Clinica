package com.coronado.clinica.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coronado.clinica.data.CitaEstado
import com.coronado.clinica.data.Medico
import com.coronado.clinica.ui.theme.AmarilloEstrella
import com.coronado.clinica.ui.theme.BordeGris
import com.coronado.clinica.ui.theme.GrisInactivo
import com.coronado.clinica.ui.theme.GrisTextoPrincipal
import com.coronado.clinica.ui.theme.GrisTextoSecundario
import com.coronado.clinica.ui.theme.MoradoClaro
import com.coronado.clinica.ui.theme.MoradoPrincipal
import com.coronado.clinica.ui.theme.VerdeConfirmadaFondo
import com.coronado.clinica.ui.theme.VerdeConfirmadaTexto

@Composable
fun RatingRow(
    calificacion: Float,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Calificación",
            tint = AmarilloEstrella,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = calificacion.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = GrisTextoPrincipal
        )
    }
}

@Composable
fun MedicoCard(
    medico: Medico,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = MoradoClaro,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.MedicalServices,
                        contentDescription = null,
                        tint = MoradoPrincipal,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = medico.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = GrisTextoPrincipal
                )
                Text(
                    text = medico.especialidad.etiqueta,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = GrisTextoSecundario
                )
            }
            RatingRow(calificacion = medico.calificacion)
        }
    }
}

@Composable
fun CitaEstadoBadge(
    estado: CitaEstado,
    modifier: Modifier = Modifier
) {
    val (container, content) = when (estado) {
        CitaEstado.CONFIRMADA -> VerdeConfirmadaFondo to VerdeConfirmadaTexto
        CitaEstado.COMPLETADA -> GrisInactivo to GrisTextoSecundario
    }
    Surface(
        color = container,
        contentColor = content,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
            text = estado.etiqueta,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}
