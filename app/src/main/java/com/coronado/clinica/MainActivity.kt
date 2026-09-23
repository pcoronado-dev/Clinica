package com.coronado.clinica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.coronado.clinica.navigation.AppNavigation
import com.coronado.clinica.state.AppState
import com.coronado.clinica.ui.theme.ClinicaSaludTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClinicaSaludTheme {
                val appState = remember { AppState() }
                AppNavigation(appState = appState)
            }
        }
    }
}
