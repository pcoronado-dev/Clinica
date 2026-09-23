package com.coronado.clinica.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MoradoPrincipal = Color(0xFF6B2FA0)
val MoradoClaro = Color(0xFFF0E5FA)
val VerdeConfirmadaFondo = Color(0xFFE8F5F0)
val VerdeConfirmadaTexto = Color(0xFF1B5E20)
val GrisInactivo = Color(0xFFF2F2F2)
val GrisTextoSecundario = Color(0xFF7A7A7A)
val GrisTextoPrincipal = Color(0xFF1A1A1A)
val BordeGris = Color(0xFFE0E0E0)
val AmarilloEstrella = Color(0xFFFFC107)

private val ClinicaLightColors = lightColorScheme(
    primary = MoradoPrincipal,
    onPrimary = Color.White,
    primaryContainer = MoradoClaro,
    onPrimaryContainer = MoradoPrincipal,
    secondary = MoradoPrincipal,
    onSecondary = Color.White,
    secondaryContainer = MoradoClaro,
    onSecondaryContainer = MoradoPrincipal,
    background = Color.White,
    onBackground = GrisTextoPrincipal,
    surface = Color.White,
    onSurface = GrisTextoPrincipal,
    surfaceVariant = GrisInactivo,
    onSurfaceVariant = GrisTextoSecundario,
    outline = BordeGris,
    error = Color(0xFFD32F2F)
)

@Composable
fun ClinicaSaludTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ClinicaLightColors,
        typography = Typography(),
        content = content
    )
}
