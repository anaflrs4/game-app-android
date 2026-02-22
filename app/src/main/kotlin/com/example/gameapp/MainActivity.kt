package com.example.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentScreen = remember { mutableStateOf("home") }

                    when (currentScreen.value) {
                        "home" -> HomeScreen { game ->
                            currentScreen.value = game
                        }
                        "rps" -> RockPaperScissorsScreen(viewModel())
                        else -> HomeScreen { game ->
                            currentScreen.value = game
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}
