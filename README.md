# Game App - Android Frontend

Aplicación Android desarrollada en **Kotlin** con **Jetpack Compose** para jugar varios juegos conectándose a un backend REST API.

## Descripción

Esta es la interfaz móvil para una aplicación de juegos que se conecta a un backend desarrollado en Spring Boot. La aplicación incluye:

- **Lotería**: Genera números aleatorios
- **Adivina el Número**: Intenta adivinar un número secreto
- **Piedra, Papel o Tijeras**: Juega contra el servidor

## Tecnologías

- **Kotlin**: Lenguaje de programación
- **Jetpack Compose**: Framework de UI declarativa
- **Android Studio**: IDE de desarrollo
- **Retrofit**: Cliente HTTP para consumir APIs
- **Coroutines**: Programación asincrónica

## Requisitos previos

- Android Studio Arctic Fox o superior
- Android SDK 24 o superior
- Kotlin 1.9.0 o superior
- Gradle 8.1.0 o superior

## Instalación

### Pasos para compilar y ejecutar

1. Clonar el repositorio:
```bash
git clone https://github.com/anaflrs4/game-app-android.git
cd game-app-android
```

2. Abrir el proyecto en Android Studio:
   - Abre Android Studio
   - Selecciona "Open an existing Android Studio project"
   - Navega a la carpeta del proyecto y selecciona

3. Configurar el backend:
   - En `RockPaperScissorsViewModel.kt`, actualiza la URL base del API
   - Para emulador: `http://10.0.2.2:8080/`
   - Para dispositivo físico: `http://[TU_IP]:8080/`

4. Compilar la aplicación:
   - Click en "Build" > "Make Project"
   - O usa: `./gradlew build`

5. Ejecutar la aplicación:
   - Click en "Run" > "Run 'app'"
   - O selecciona un emulador/dispositivo y presiona el botón de play

## Estructura del Proyecto

```
game-app-android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/example/gameapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── RockPaperScissorsScreen.kt
│   │   │   │   ├── RockPaperScissorsViewModel.kt
│   │   │   │   ├── GameApiService.kt
│   │   │   │   └── GameResult.kt
│   │   │   ├── res/
│   │   │   │   └── values/
│   │   │   │       └── strings.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Características principales

### Pantalla de inicio
- Menú con tres opciones de juegos
- Interfaz limpia y fácil de usar

### Juego: Piedra, Papel o Tijeras
- Tres botones para seleccionar tu movimiento
- Comunicación con el backend mediante REST API
- Muestra el resultado inmediatamente
- Indicador de carga mientras se procesa la solicitud
- Manejo de errores

## Configuración del API

### URL del Backend

**Desarrollo (Emulador):**
```
http://10.0.2.2:8080
```

**Producción:**
```
https://game-app-backend-d88t.onrender.com
```

### Ejemplo de solicitud

```kotlin
// El ViewModel hace automáticamente esta solicitud
val result = apiService.playRockPaperScissors("ROCK")
```

## Compilación y Generación del APK

Para generar un APK listo para distribución:

```bash
./gradlew assembleRelease
```

El APK se generará en: `app/build/outputs/apk/release/app-release.apk`

## Desarrollo

### Agregar un nuevo juego

1. Crear un nuevo archivo de pantalla (ej: `NewGameScreen.kt`)
2. Crear un ViewModel correspondiente (ej: `NewGameViewModel.kt`)
3. Agregar el endpoint en `GameApiService.kt`
4. Actualizar `MainActivity.kt` para incluir la nueva pantalla
5. Agregar un botón en `HomeScreen.kt`

## Troubleshooting

### Error: "Unable to connect to backend"
- Verifica que el backend esté ejecutándose
- Comprueba la URL base en `RockPaperScissorsViewModel.kt`
- Asegúrate de que el emulador/dispositivo pueda acceder a la red

### Error: "Gradle sync failed"
- Invalida cachés: File > Invalidate Caches / Restart
- Sincroniza el proyecto: File > Sync Now

## Licencia

Este proyecto es de código abierto bajo la licencia MIT.

## Autor

Desarrollado por Ana Paula Flores Escobar
