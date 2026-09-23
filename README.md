 PROMPT1: Estás trabajando un proyecto Android con Jetpack Compose y Navigation Compose llamado "Clínica Salud+". Entrega el código completo (sin explicaciones largas) para añadir la función de CANCELAR una cita:
1.
En "Mis citas" (app/src/main/java/com/coronado/clinicasalud/ui/screens/MyAppointmentsScreen.kt) cada CitaCard debe tener un botón "Cancelar".
2.
Al presionarlo, muestra un AlertDialog de confirmación (título "Cancelar cita", mensaje con médico/fecha/hora y botones "No, conservar" y "Sí, cancelar").
3.
Si confirma, llama a appState.cancelarCita(cita) de app/src/main/java/com/coronado/clinicasalud/state/AppState.kt (ya existe) y cierra el diálogo.
4.
Responsivo: si tras cancelar ya no hay citas, se muestra el estado vacío actual.
Restricciones:
•
Solo Jetpack Compose + Material3, SIN ViewModel ni MVVM (se usa remember/mutableStateOf).
•
Mantén el estilo y nombres existentes (CitaCard, CitaEstadoBadge, onAbrirMenu).
•
Antes de codificar, dime qué archivos modificarás y luego entrega el diff o archivos completos.
PROMPT2: Estás trabajando un proyecto Android con Jetpack Compose y Navigation Compose llamado "Clínica Salud+".
Entrega el código completo (sin explicaciones largas) para mejorar la experiencia al agendar:

1. En la pantalla "Confirmación"
   (app/src/main/java/com/coronado/clinicasalud/ui/screens/ConfirmationScreen.kt)
   muestra un Snackbar de éxito al entrar, indicando "Cita confirmada para el {fecha} a las {hora}".
2. Usa ScaffoldState/SnackbarHost del propio Scaffold de Material3 (snackbarHostState en el Scaffold).
3. En "Mis citas", indica cuántas citas hay en la top bar (ej. "Mis citas (2)").

Restricciones:
- Solo Jetpack Compose + Material3, SIN ViewModel ni MVVM (se usa remember/mutableStateOf).
- Mantén el estilo y nombres existentes; usa MaterialTheme.colorScheme.
- Antes de codificar, dime qué archivos modificarás y luego entrega el diff o archivos completos.