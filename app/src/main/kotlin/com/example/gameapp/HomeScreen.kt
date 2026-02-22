package com.example.gameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Game(val id: String, val title: String, val emoji: String, val description: String)

val gameList = listOf(
    Game(
        id = "rps",
        title = "Piedra, Papel o Tijera",
        emoji = "🗿📄✂️",
        description = "Juega contra el servidor y pon a prueba tu suerte."
    ),
    Game(
        id = "lottery",
        title = "Lotería",
        emoji = "🎟️",
        description = "Genera tus números de la suerte para el próximo sorteo."
    ),
    Game(
        id = "guess",
        title = "Adivina el Número",
        emoji = "🔢",
        description = "Intenta adivinar el número secreto que piensa el servidor."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onGameSelected: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game App", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1B263B))
            )
        },
        containerColor = Color(0xFF0D1B2A)
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
            item {
                Text(
                    text = "Bienvenido",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Selecciona un juego para empezar",
                    fontSize = 18.sp,
                    color = Color(0xFF98C1D9),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            items(gameList) { game ->
                GameCard(game = game, onClick = { onGameSelected(game.id) })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun GameCard(game: Game, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B263B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = game.emoji, fontSize = 40.sp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = game.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = game.description,
                    color = Color(0xFF98C1D9),
                    fontSize = 14.sp
                )
            }
        }
    }
}
