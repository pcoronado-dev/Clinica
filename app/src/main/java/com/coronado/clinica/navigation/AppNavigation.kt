package com.coronado.clinica.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
            ModalDrawerSheet {
                Text(
                    text = "Clínica Salud+",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                drawerDestinos.forEach { destino ->
                    val destinoId = remember(destino.ruta) { destino.ruta }
                    NavigationDrawerItem(
                        label = { Text(destino.etiqueta) },
                        icon = {
                            Icon(
                                imageVector = destino.icono,
                                contentDescription = null
                            )
                        },
                        selected = currentRoute == destinoId,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(destino.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
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