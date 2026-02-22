package com.example.gameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RockPaperScissorsScreen(viewModel: RockPaperScissorsViewModel = viewModel()) {
    val gameResult by viewModel.gameResult.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A2E))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Title
        Text(
            text = "Rock Paper Scissors",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Game Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameButton(
                text = "Rock",
                onClick = { viewModel.playGame("ROCK") },
                enabled = !isLoading
            )
            GameButton(
                text = "Paper",
                onClick = { viewModel.playGame("PAPER") },
                enabled = !isLoading
            )
            GameButton(
                text = "Scissors",
                onClick = { viewModel.playGame("SCISSORS") },
                enabled = !isLoading
            )
        }

        // Loading Indicator
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        }

        // Error Message
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Result Display
        if (gameResult != null) {
            ResultCard(gameResult!!)
        }

        // Reset Button
        if (gameResult != null) {
            Button(
                onClick = { viewModel.resetGame() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF16213E)
                )
            ) {
                Text("Play Again", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun GameButton(text: String, onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .width(80.dp)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0F3460),
            disabledContainerColor = Color(0xFF555555)
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ResultCard(result: GameResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF16213E)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Choice: ${result.playerMove}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Server Choice: ${result.serverMove}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            val resultColor = when (result.result) {
                "WIN" -> Color.Green
                "LOSE" -> Color.Red
                else -> Color.Yellow
            }

            Text(
                text = result.result,
                color = resultColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
