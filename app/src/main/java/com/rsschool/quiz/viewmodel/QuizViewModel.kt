package com.rsschool.quiz.viewmodel

import androidx.lifecycle.ViewModel
import com.rsschool.quiz.Question
import com.rsschool.quiz.data.repository.QuestionRepository

class QuizViewModel : ViewModel() {
    private val repository = QuestionRepository

    fun getQuestions(): List<Question> = repository.getQuestions()

    fun saveAnswer(id: Int, answer: String) = repository.setAnswers(id, answer)

    fun getAnswer(id: Int): String? =  repository.getAnswer(id)

    fun getAmountOfRightAnswers(): Double = repository.checkAnswers()

    fun clearAnswers() = repository.clearAnswers()

}