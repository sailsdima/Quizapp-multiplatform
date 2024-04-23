package com.example.quizmultiplatform.data


internal interface CategoryRepository {

    fun addCategory(name: String): Long
    fun findCategoryIdByName(name: String): Long
    fun deleteCategoryById(categoryId: Long)

}