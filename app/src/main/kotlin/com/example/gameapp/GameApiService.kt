package com.example.gameapp

import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("play/rock-paper-scissors")
    suspend fun playRockPaperScissors(@Query("playerMove") playerMove: String): GameResult
}
