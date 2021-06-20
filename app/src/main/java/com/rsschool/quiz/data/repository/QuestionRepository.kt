package com.rsschool.quiz.data.repository

import com.rsschool.quiz.DataHolder
import com.rsschool.quiz.Question

object QuestionRepository {
    private val local = DataHolder

    private val answers: MutableMap<Int, String> = mutableMapOf()

    fun getQuestions(): List<Question> = local.questions

    fun setAnswers(id: Int, answer: String) = answers.put(id, answer)

    fun getAnswer(id: Int): String? = answers.get(id)

    private fun getRightAnswers(): List<Question> = local.questions.map { it.copy(answer = it.answer.filter { it.second }) }

    fun checkAnswers(): Double {
        val subset: MutableList<Question> = ArrayList()
        val iterator: Iterator<Question> = getRightAnswers().iterator()
        while (iterator.hasNext()) {
            val q: Question = iterator.next()
            if (answers.containsValue(q.answer.first().first)) {
                subset.add(q)
            }
        }

        return subset.size.toDouble() / 5 * 100
    }

    fun clearAnswers() = answers.clear()
}