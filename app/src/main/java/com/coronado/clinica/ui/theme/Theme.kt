package com.coronado.clinica.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ClinicaLightColors = lightColorScheme(
    primary = Color(0xFF00696D),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF9CF0F4),
    onPrimaryContainer = Color(0xFF002021),
    secondary = Color(0xFF4A6365),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFCCE8EA),
    onSecondaryContainer = Color(0xFF051F21),
    tertiary = Color(0xFF4C6E94),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1E4FF),
    onTertiaryContainer = Color(0xFF001D3A),
    background = Color(0xFFFAFDFC),
    onBackground = Color(0xFF191C1C),
    surface = Color(0xFFFAFDFC),
    onSurface = Color(0xFF191C1C),
    surfaceVariant = Color(0xFFDAE4E5),
    onSurfaceVariant = Color(0xFF3F4849),
    error = Color(0xFFBA1A1A),
    outline = Color(0xFF6F7979)
)

@Composable
fun ClinicaSaludTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ClinicaLightColors,
        typography = Typography(),
        content = content
    )
}