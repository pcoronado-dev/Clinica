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
PROMPT3:Ajusta el diseño visual de mi app (Clínica Salud+) para que coincida exactamente con este sistema de diseño:

PALETA DE COLORES
- Morado principal (headers, botones activos, iconos activos): #6B2FA0 aprox (púrpura oscuro)
- Morado claro de fondo (badges, tarjetas seleccionadas, avatares): #F0E5FA / #EDE0F7
- Verde de estado "Confirmada": texto verde sobre fondo verde clarito #E8F5F0
- Fondo general de pantalla: blanco (#FFFFFF)
- Fondo de tarjetas/pills inactivas: gris muy claro #F2F2F2
- Texto principal: negro/gris oscuro #1A1A1A
- Texto secundario (especialidad, fecha, hora): gris medio #7A7A7A
- Bordes de tarjetas: gris claro #E0E0E0, radio de esquina grande (16px aprox)
- Borde del drawer/menú lateral: morado (#6B2FA0), grosor 2px, esquinas muy redondeadas (24px+)

TIPOGRAFÍA
- Sans-serif estilo system font (San Francisco / Inter / Roboto)
- Títulos de pantalla: bold, 18-20px
- Nombres (médicos, paciente): semibold, 15-16px
- Texto secundario (especialidad, fecha/hora): regular, 12-13px, color gris
- Rating con estrella: texto pequeño bold junto a ícono ★ amarillo/dorado

COMPONENTES CLAVE

1. Header superior (morado sólido, esquinas inferiores redondeadas, texto blanco):
   - Saludo "Hola, [Nombre]"

2. Tabs tipo "pill" (especialidades: Cardiología, Pediatría):
   - Activo: fondo morado oscuro, texto blanco, bordes redondeados completos
   - Inactivo: fondo gris claro, texto gris

3. Tarjetas de lista (médicos, citas):
   - Fondo blanco con borde sutil o gris muy claro
   - Ícono circular a la izquierda (fondo morado claro, ícono morado oscuro, símbolo "+")
   - Nombre en negrita + especialidad en gris debajo
   - Rating con estrella alineado a la derecha
   - Esquinas redondeadas (12-16px)

4. Selector de fecha/hora (chips):
   - Fondo gris claro por defecto, texto gris oscuro
   - Seleccionado: fondo morado sólido, texto blanco
   - Forma de píldora/rectángulo redondeado

5. Estado/badge (ej. "Confirmada", "Completada"):
   - "Confirmada": texto verde sobre fondo verde clarito, forma de píldora
   - "Completada": texto gris sobre fondo gris clarito, forma de píldora

6. Botón principal (ej. "Agendar cita", "Confirmar cita"):
   - Ancho completo, fondo morado oscuro sólido, texto blanco, bold
   - Esquinas redondeadas (12px), altura generosa (48-52px)

7. Botón secundario (ej. "Ver mis citas"):
   - Fondo gris claro, texto negro, mismas esquinas redondeadas

8. Pantalla de confirmación:
   - Ícono circular grande con check verde sobre fondo verde clarito, centrado
   - Título bold debajo, subtítulo gris con detalle de la cita (médico, fecha, hora)

9. Avatar circular (perfil médico o paciente):
   - Círculo morado claro con ícono/iniciales en morado oscuro, centrado
   - Nombre bold debajo, rol/especialidad en gris

10. Menú lateral (Drawer):
   - Contenedor con borde morado grueso y esquinas muy redondeadas
   - Header con avatar circular + nombre + rol ("Paciente")
   - Línea divisoria debajo del header
   - Items de navegación con radio button/círculo a la izquierda:
      - Activo: fondo morado clarito, texto morado oscuro bold, radio relleno
      - Inactivo: texto gris oscuro, radio vacío
   - Espaciado vertical generoso entre items (16-20px)

11. Sección "Mis citas":
   - Título bold arriba
   - Tarjetas con borde izquierdo grueso morado (4px) como indicador
   - Nombre del médico bold, fecha/hora en gris, badge de estado debajo

ESPACIADO Y LAYOUT
- Padding lateral consistente de 16-20px en toda la pantalla
- Separación entre tarjetas de 10-12px
- Jerarquía visual clara: header/drawer → contenido con scroll → botón de acción fijo abajo (cuando aplica)

Aplica estos estilos manteniendo la estructura y funcionalidad de mi código actual, solo actualizando colores, tipografía, espaciados, bordes y componentes visuales para que coincidan con este diseño.