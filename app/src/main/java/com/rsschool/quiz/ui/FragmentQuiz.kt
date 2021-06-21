package com.rsschool.quiz.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.viewmodel.QuizViewModel


class FragmentQuiz : Fragment(R.layout.fragment_quiz) {

    private val viewBinding: FragmentQuizBinding by viewBinding()
    private val args: FragmentQuizArgs by navArgs()
    private val viewModel: QuizViewModel by activityViewModels()
    lateinit var currentQuestion: Question

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentQuestion = viewModel.getQuestions()[args.questionNumber]

        with(viewBinding) {
            question.text = currentQuestion.question

            radioGroup.forEachIndexed { index, rb ->
                (rb as RadioButton).text = currentQuestion.answer[index].first
                if (rb.text == viewModel.getAnswer(question.text.toString())) {
                    rb.isChecked = true
                    nextButton.isEnabled = true
                }
            }

            when (args.questionNumber) {
                0 -> previousButton.isEnabled = false
                4 -> nextButton.text = getString(R.string.btn_submit)
            }

            radioGroup.setOnCheckedChangeListener { radioGroup, checked ->
                nextButton.isEnabled = true
                val answer = radioGroup.findViewById<RadioButton>(checked).text.toString()
                viewModel.saveAnswer(question.text.toString(), answer)
            }

            nextButton.setOnClickListener {
                if (args.questionNumber < 4) {
                    val action = FragmentQuizDirections.actionFragmentQuizSelf(args.questionNumber + 1)
                    findNavController().navigate(action)
                } else {
                    findNavController().navigate(R.id.resultFragment)
                }
            }

            previousButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}