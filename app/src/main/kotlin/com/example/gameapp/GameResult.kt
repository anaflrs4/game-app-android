package com.example.gameapp

import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    val playerMove: String,
    val serverMove: String,
    val result: String
)
