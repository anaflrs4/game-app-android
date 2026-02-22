package com.example.gameapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RockPaperScissorsViewModel : ViewModel() {
    private val _gameResult = MutableStateFlow<GameResult?>(null)
    val gameResult: StateFlow<GameResult?> = _gameResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val apiService: GameApiService = Retrofit.Builder()
        .baseUrl("https://game-app-backend-d88t.onrender.com/") // Production URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GameApiService::class.java)

    fun playGame(playerMove: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = apiService.playRockPaperScissors(playerMove)
                _gameResult.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetGame() {
        _gameResult.value = null
        _errorMessage.value = null
    }
}
