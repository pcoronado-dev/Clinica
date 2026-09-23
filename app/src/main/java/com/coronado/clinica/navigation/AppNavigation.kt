package com.coronado.clinica.navigation

import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coronado.clinica.state.AppState
import com.coronado.clinica.ui.screens.ConfirmationScreen
import com.coronado.clinica.ui.screens.DoctorProfileScreen
import com.coronado.clinica.ui.screens.HistoryScreen
import com.coronado.clinica.ui.screens.HomeScreen
import com.coronado.clinica.ui.screens.MyAppointmentsScreen
import com.coronado.clinica.ui.screens.ScheduleAppointmentScreen
import com.coronado.clinica.ui.theme.BordeGris
import com.coronado.clinica.ui.theme.GrisTextoPrincipal
import com.coronado.clinica.ui.theme.GrisTextoSecundario
import com.coronado.clinica.ui.theme.MoradoClaro
import com.coronado.clinica.ui.theme.MoradoPrincipal
import kotlinx.coroutines.launch

private data class DrawerDestino(
    val ruta: String,
    val icono: ImageVector,
    val etiqueta: String
)

private val drawerDestinos = listOf(
    DrawerDestino(ruta = "inicio", icono = Icons.Filled.Home, etiqueta = "Inicio"),
    DrawerDestino(ruta = "mis_citas", icono = Icons.Filled.CalendarMonth, etiqueta = "Mis citas"),
    DrawerDestino(ruta = "historial", icono = Icons.Filled.History, etiqueta = "Historial médico")
)

@Composable
fun AppNavigation(appState: AppState) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val abrirMenu: () -> Unit = {
        scope.launch { drawerState.open() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
                drawerContainerColor = Color.White,
                modifier = Modifier
                    .padding(end = 36.dp)
                    .border(
                        width = 2.dp,
                        color = MoradoPrincipal,
                        shape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MoradoClaro,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MoradoPrincipal,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                    Text(
                        text = "Juan Pérez",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = GrisTextoPrincipal
                    )
                    Text(
                        text = "Paciente",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp,
                        color = GrisTextoSecundario
                    )
                }

                HorizontalDivider(color = BordeGris)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    drawerDestinos.forEach { destino ->
                        val isSelected = currentRoute == destino.ruta
                        Surface(
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(destino.ruta) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) MoradoClaro else Color.Transparent,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    imageVector = if (isSelected) Icons.Filled.RadioButtonChecked else Icons.Outlined.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = if (isSelected) MoradoPrincipal else GrisTextoSecundario
                                )
                                Text(
                                    text = destino.etiqueta,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 15.sp,
                                    color = if (isSelected) MoradoPrincipal else GrisTextoPrincipal
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "inicio"
        ) {
            composable("inicio") {
                HomeScreen(
                    appState = appState,
                    onAbrirMenu = abrirMenu,
                    onMedicoClick = { medico ->
                        navController.navigate("perfil/${medico.id}")
                    }
                )
            }

            composable(
                route = "perfil/{medicoId}",
                arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
            ) { entry ->
                val medicoId = entry.arguments?.getInt("medicoId") ?: 0
                val medico = appState.medicos.firstOrNull { it.id == medicoId }
                if (medico != null) {
                    DoctorProfileScreen(
                        medico = medico,
                        onVolver = { navController.popBackStack() },
                        onAgendarCita = {
                            navController.navigate("agendar/$medicoId")
                        }
                    )
                }
            }

            composable(
                route = "agendar/{medicoId}",
                arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
            ) { entry ->
                val medicoId = entry.arguments?.getInt("medicoId") ?: 0
                val medico = appState.medicos.firstOrNull { it.id == medicoId }
                if (medico != null) {
                    ScheduleAppointmentScreen(
                        medico = medico,
                        appState = appState,
                        onVolver = { navController.popBackStack() },
                        onConfirmar = { cita ->
                            navController.navigate("confirmacion/${cita.id}") {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

            composable(
                route = "confirmacion/{citaId}",
                arguments = listOf(navArgument("citaId") { type = NavType.IntType })
            ) { entry ->
                val citaId = entry.arguments?.getInt("citaId") ?: 0
                val cita = appState.citas.firstOrNull { it.id == citaId }
                if (cita != null) {
                    ConfirmationScreen(
                        cita = cita,
                        onVolverInicio = {
                            navController.navigate("inicio") {
                                popUpTo("inicio") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

            composable("mis_citas") {
                MyAppointmentsScreen(
                    citas = appState.citasConfirmadas,
                    onAbrirMenu = abrirMenu,
                    onCancelarCita = { cita -> appState.cancelarCita(cita) }
                )
            }

            composable("historial") {
                HistoryScreen(
                    citasCompletadas = appState.citasCompletadas,
                    onAbrirMenu = abrirMenu
                )
            }
        }
    }
}
