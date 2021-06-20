package com.rsschool.quiz.viewmodel

import androidx.lifecycle.ViewModel
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.data.repository.QuestionRepository
import java.lang.StringBuilder

class QuizViewModel : ViewModel() {
    private val repository = QuestionRepository

    fun getQuestions(): List<Question> = repository.getQuestions()

    fun saveAnswer(question: String, answer: String) = repository.setAnswers(question, answer)

    fun getAnswer(question: String): String? = repository.getAnswer(question)

    fun getAmountOfRightAnswers(): Double = repository.checkAnswers()

    fun clearAnswers() = repository.clearAnswers()

    fun sendAnswers(): String {
        var num = 1
        val answerSB = StringBuilder()
        val answers = repository.getAnswers()
        for (key in answers.keys){
            answerSB.appendLine().append(num).append(")").append(key).appendLine().append("Your answer: ").append(answers.get(key)).appendLine()
            num++
        }

        return answerSB.toString()
    }

}