package com.example.quizmultiplatform.domain.model

data class NewGameSession(
    val timePerGameMs: Long,
    val questionsCount: Int,
    val mistakesAllowedCount: Int,
    val gameStatus: GameStatus,
    val createdAt: Long,
)