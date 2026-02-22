package com.example.gameapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1B2A)), // Dark Navy Blue
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title
            Text(
                text = "Piedra, Papel o Tijera",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 32.dp)
            )

            // Result Display or Loading
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color(0xFFE0FBFC), modifier = Modifier.size(64.dp))
                } else if (errorMessage != null) {
                    Text(text = errorMessage!!, color = Color.Red, fontSize = 18.sp)
                } else {
                    AnimatedVisibility(
                        visible = gameResult != null,
                        enter = fadeIn(animationSpec = tween(500)) + scaleIn(animationSpec = tween(500)),
                        exit = fadeOut(animationSpec = tween(500))
                    ) {
                        gameResult?.let { ResultCard(it) }
                    }
                }
            }

            // Player actions
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Elige tu jugada",
                    color = Color(0xFF98C1D9),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GameButton(emoji = "🗿", onClick = { viewModel.playGame("ROCK") }, enabled = !isLoading)
                    GameButton(emoji = "📄", onClick = { viewModel.playGame("PAPER") }, enabled = !isLoading)
                    GameButton(emoji = "✂️", onClick = { viewModel.playGame("SCISSORS") }, enabled = !isLoading)
                }
                Spacer(modifier = Modifier.height(24.dp))
                if (gameResult != null) {
                    Button(
                        onClick = { viewModel.resetGame() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D5A80))
                    ) {
                        Text("Jugar de Nuevo", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun GameButton(emoji: String, onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        modifier = Modifier.size(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF98C1D9),
            disabledContainerColor = Color(0xFF5A6A7A)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ) {
        Text(text = emoji, fontSize = 40.sp)
    }
}

@Composable
fun ResultCard(result: GameResult) {
    val resultText = when (result.result) {
        "WIN" -> "¡Ganaste!"
        "LOSE" -> "¡Perdiste!"
        else -> "¡Empate!"
    }
    val resultColor = when (result.result) {
        "WIN" -> Color(0xFF57CC99)
        "LOSE" -> Color(0xFFE63946)
        else -> Color(0xFFFCA311)
    }

    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B263B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = resultText,
                color = resultColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                PlayerChoice(emoji = getEmojiForMove(result.playerMove), label = "Tu Jugada")
                PlayerChoice(emoji = getEmojiForMove(result.serverMove), label = "Servidor")
            }
        }
    }
}

@Composable
fun PlayerChoice(emoji: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = emoji, fontSize = 60.sp)
        Text(text = label, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

fun getEmojiForMove(move: String): String {
    return when (move.uppercase()) {
        "ROCK" -> "🗿"
        "PAPER" -> "📄"
        "SCISSORS" -> "✂️"
        else -> "❓"
    }
}
