package com.example.quizmultiplatform

import com.example.quizmultiplatform.data.CategoryRepositoryImpl
import com.example.quizmultiplatform.data.db.DatabaseDriverFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val categoryDataSource = CategoryRepositoryImpl(databaseDriverFactory)

    suspend fun addCategory() = withContext(Dispatchers.Default) {
        categoryDataSource.addCategory("CategoryName ${Random.nextInt()}")
    }
}