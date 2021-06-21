package com.rsschool.quiz.ui

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.viewmodel.QuizViewModel

const val ARG_QUESTION_NUMBER = "question_number"

class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by viewBinding()
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(viewBinding.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arg ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
            supportActionBar?.title = "Question ${arg?.getInt(ARG_QUESTION_NUMBER)?.plus(1)}"
            when (arg?.getInt(ARG_QUESTION_NUMBER)) {
                0 -> {
                    viewBinding.toolbar.visibility = View.VISIBLE
                    setTheme(R.style.Theme_Quiz_First)
                    setToolbarAndStatusBarColor()
                }
                1 -> {
                    setTheme(R.style.Theme_Quiz_Second)
                    setToolbarAndStatusBarColor()
                }
                2 -> {
                    setTheme(R.style.Theme_Quiz_Third)
                    setToolbarAndStatusBarColor()
                }
                3 -> {
                    setTheme(R.style.Theme_Quiz_Fourth)
                    setToolbarAndStatusBarColor()
                }
                4 -> {
                    setTheme(R.style.Theme_Quiz_Fifth)
                    setToolbarAndStatusBarColor()
                }
                else -> {
                    viewBinding.toolbar.visibility = View.GONE
                    setTheme(R.style.Theme_Quiz)
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return when (navController.currentDestination?.id) {
            R.id.resultFragment -> {
                viewModel.clearAnswers()
                val action = ResultFragmentDirections.actionResultFragmentToFragmentQuiz()
                navController.navigate(action)
                true
            }
            else -> navController.navigateUp() || super.onSupportNavigateUp()
        }
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    private fun setToolbarAndStatusBarColor() {
        val typedValue = TypedValue()
        val currentTheme = this.theme
        currentTheme?.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
        window?.statusBarColor = typedValue.data
        currentTheme?.resolveAttribute(android.R.attr.colorPrimary, typedValue, true)
        viewBinding.toolbar.setBackgroundColor(typedValue.data)
    }

}