package com.rsschool.quiz.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.viewmodel.QuizViewModel


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

//        val typedValue = TypedValue()
//        val currentTheme = this.theme
//        currentTheme?.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
//        window?.statusBarColor = typedValue.data

        navController.addOnDestinationChangedListener { _, _, arg ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
            supportActionBar?.title = "Question ${arg?.getInt("question_number")?.plus(1)}"
            when (arg?.getInt("question_number")) {
                0 -> {
                    viewBinding.toolbar.visibility = View.VISIBLE
                    window.statusBarColor = getColor(R.color.deep_orange_100_dark)
                    viewBinding.toolbar.setBackgroundColor(getColor(R.color.deep_orange_100))
                    setTheme(R.style.Theme_Quiz_First)
                }
                1 -> {
                    window.statusBarColor = getColor(R.color.yellow_100_dark)
                    viewBinding.toolbar.setBackgroundColor(getColor(R.color.yellow_100))
                    setTheme(R.style.Theme_Quiz_Second)
                }
                2 -> {
                    window.statusBarColor = getColor(R.color.light_green_100_dark)
                    viewBinding.toolbar.setBackgroundColor(getColor(R.color.light_green_100))
                    setTheme(R.style.Theme_Quiz_Third)
                }
                3 -> {
                    window.statusBarColor = getColor(R.color.cyan_100_dark)
                    viewBinding.toolbar.setBackgroundColor(getColor(R.color.cyan_100))
                    setTheme(R.style.Theme_Quiz_Fourth)
                }
                4 -> {
                    window.statusBarColor = getColor(R.color.deep_purple_100_dark)
                    viewBinding.toolbar.setBackgroundColor(getColor(R.color.deep_purple_100))
                    setTheme(R.style.Theme_Quiz_Fifth)
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

}