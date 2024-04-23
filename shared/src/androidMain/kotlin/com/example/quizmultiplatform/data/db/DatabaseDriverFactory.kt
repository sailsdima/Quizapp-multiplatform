package com.example.quizmultiplatform.data.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createQuizDatabase(): SqlDriver {
        return AndroidSqliteDriver(QuizDatabase.Schema, context, "quizDatabase.db")
    }
}