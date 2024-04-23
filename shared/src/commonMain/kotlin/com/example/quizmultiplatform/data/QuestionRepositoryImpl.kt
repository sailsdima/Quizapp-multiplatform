package com.example.quizmultiplatform.data

import com.example.quizmultiplatform.data.db.DatabaseDriverFactory
import com.example.quizmultiplatform.data.db.QuizDatabase
import com.example.quizmultiplatform.domain.model.Answer
import com.example.quizmultiplatform.domain.model.Question
import kotlinx.coroutines.withContext


internal class QuestionRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory,
) : QuestionRepository {

    private val quizDatabase = QuizDatabase(databaseDriverFactory.createQuizDatabase())
    private val questionQueries = quizDatabase.dBQestionQueries
    private val answerQueries = quizDatabase.dBAnswerQueries
    private val categoryQueries = quizDatabase.dBCategoryQueries

    override suspend fun upsertQuestions(questions: List<Question>): Unit = withContext(ioDispatcher) {
        questions.forEach { addQuestion(it) }
    }

    private fun addQuestion(question: Question) {
        val categoryId = categoryQueries.insertCategory(question.category.name).also {
            categoryQueries.findCategoryIdByName(question.category.name)
        }

        questionQueries.upsertQuestion(
            q_type = question.type.type,
            q_category_id = categoryId,
            q_question = question.question,
            q_difficulty = question.difficulty,
            q_createdAt = question.createdAt,
            q_updatedAt = question.updatedAt,
            q_timesShown = null,
        )

        val dbQuestion = question.toDBQuestion(categoryId)
        questionDao.upsertQuestion(dbQuestion)
        val dbAnswers = question.answers.toDBAnswers(dbQuestion.id)
        answerDao.insertAnswers(dbAnswers)
    }

    override suspend fun getSavedQuestionIds(): List<Long> = withContext(ioDispatcher) {
        return@withContext questionDao.getQuestionsList()
    }

    override suspend fun deleteQuestions(questionIds: Set<Long>) = withContext(ioDispatcher) {
        questionDao.deleteQuestions(questionIds.map { DeleteQuestionEntity(it) }.toTypedArray())
    }

    override suspend fun getQuestionById(questionId: Long): Question {
        return questionDao.getQuestionById(questionId).toQuestion()
    }

    override suspend fun getNextRandomQuestion(): Question = withContext(ioDispatcher) {
        return@withContext questionDao.getRandomQuestion().toQuestion()
    }

    override suspend fun incrementQuestionShownTimesCount(questionId: Long): Unit = withContext(ioDispatcher) {
        questionDao.incrementQuestionShownTimesCount(questionId)
    }

    override suspend fun clearQuestions() = withContext(ioDispatcher) {
        questionDao.clear()
    }
}