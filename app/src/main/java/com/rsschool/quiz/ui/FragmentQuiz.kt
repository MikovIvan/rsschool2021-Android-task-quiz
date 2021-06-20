package com.rsschool.quiz.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsschool.quiz.Question
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.viewmodel.QuizViewModel


class FragmentQuiz : Fragment(R.layout.fragment_quiz) {

    private val viewBinding: FragmentQuizBinding by viewBinding()
    private val args: FragmentQuizArgs by navArgs()
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question1: Question = viewModel.getQuestions()[args.questionNumber]

        with(viewBinding) {
            question.text = question1.question
            optionOne.text = question1.answer[0].first
            optionTwo.text = question1.answer[1].first
            optionThree.text = question1.answer[2].first
            optionFour.text = question1.answer[3].first
            optionFive.text = question1.answer[4].first

            radioGroup.forEachIndexed { index, viewRB ->
                if ((viewRB as RadioButton).text == viewModel.getAnswer(args.questionNumber)) {
                    viewRB.isChecked = true
                    nextButton.isEnabled = true
                }
            }

            when(args.questionNumber){
                0->previousButton.isEnabled = false
                4->nextButton.text = "Submit"
            }

            radioGroup.setOnCheckedChangeListener { radioGroup, checked ->
                nextButton.isEnabled = true
                val answer = radioGroup.findViewById<RadioButton>(checked).text.toString()
                viewModel.saveAnswer(args.questionNumber, answer)
            }

            nextButton.setOnClickListener {
                if (args.questionNumber<4){
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