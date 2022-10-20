package com.debtdestroyer.android.ui.trivia

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.ActivityTriviaBinding
import com.debtdestroyer.android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaActivity : BaseActivity<ActivityTriviaBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_quiz_main)

        supportActionBar?.hide()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.TriviaFragment, R.id.ScoreFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityTriviaBinding
        get() = ActivityTriviaBinding::inflate
}