package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.DataHolder
import com.rsschool.quiz.data.Question

object QuestionRepository {
    private val local = DataHolder

    private val answers: MutableMap<String, String> = mutableMapOf()

    fun getQuestions(): List<Question> = local.questions

    fun setAnswers(question: String, answer: String) = answers.put(question, answer)

    fun getAnswer(question: String): String? = answers.get(question)

    fun getAnswers(): Map<String, String> = answers

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