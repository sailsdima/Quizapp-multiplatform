package com.example.quizmultiplatform.data

import com.example.quizmultiplatform.domain.model.Answer
import com.example.quizmultiplatform.domain.model.Question

internal interface QuestionRepository {

    suspend fun upsertQuestions(questions: List<Question>)

    suspend fun getSavedQuestionIds(): List<Long>

    suspend fun deleteQuestions(questionIds: Set<Long>)

    suspend fun getQuestionById(questionId: Long): Question

    suspend fun getNextRandomQuestion(): Question

    suspend fun incrementQuestionShownTimesCount(questionId: Long)

    suspend fun clearQuestions()

}