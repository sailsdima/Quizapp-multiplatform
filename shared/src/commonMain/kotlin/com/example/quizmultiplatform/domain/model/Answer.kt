package com.example.quizmultiplatform.domain.model

data class Answer(
    val id: Long,
    val value: String,
    val isCorrect: Boolean,
)