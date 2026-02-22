package com.example.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D1B2A)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen { gameId ->
                                navController.navigate(gameId)
                            }
                        }
                        composable("rps") {
                            RockPaperScissorsScreen(viewModel())
                        }
                        // Puedes agregar los otros juegos aquí cuando los implementes
                        composable("lottery") {
                            // LotteryScreen()
                        }
                        composable("guess") {
                            // GuessNumberScreen()
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
