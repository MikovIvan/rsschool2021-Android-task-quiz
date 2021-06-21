package com.rsschool.quiz.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.viewmodel.QuizViewModel


class ResultFragment : Fragment(R.layout.fragment_result) {

    private val viewBinding: FragmentResultBinding by viewBinding()
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(viewBinding) {
            score.text = getString(R.string.score, viewModel.getAmountOfRightAnswers())

            share.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, """${score.text}
                    ${viewModel.sendAnswers()}        
                """.trimIndent()
                )
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.quiz_results))
                intent.type = "text/plain"
                startActivity(intent)
            }

            restart.setOnClickListener {
                viewModel.clearAnswers()
                val action = ResultFragmentDirections.actionResultFragmentToFragmentQuiz()
                findNavController().navigate(action)
            }

            quit.setOnClickListener {
                requireActivity().finish()
            }
        }
    }

}