package com.example.quizmultiplatform.data.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createQuizDatabase(): SqlDriver
}