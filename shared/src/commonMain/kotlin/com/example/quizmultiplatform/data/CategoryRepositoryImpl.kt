package com.example.quizmultiplatform.data

import com.example.quizmultiplatform.data.db.DatabaseDriverFactory
import com.example.quizmultiplatform.data.db.QuizDatabase


internal class CategoryRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
): CategoryRepository {

    private val quizDatabase = QuizDatabase(databaseDriverFactory.createQuizDatabase())
    private val categoryQueries = quizDatabase.dBCategoryQueries

    override fun addCategory(name: String): Long {
        return quizDatabase.transactionWithResult {
            categoryQueries.insertCategory(name)
            categoryQueries.lastInsertRowId().executeAsOne()
        }
    }

    override fun findCategoryIdByName(name: String): Long {
        return categoryQueries.findCategoryIdByName(name).executeAsOne()
    }

    override fun deleteCategoryById(categoryId: Long) {
        return categoryQueries.deleteCategoryById(categoryId)
    }

}