package com.example.quizmultiplatform.domain.model

data class NewUserAnswer(
    val gameId: Long,
    val questionId: Long,
    val answerId: Long,
    val answeredAt: Long,
    val isCorrect: Boolean,
)