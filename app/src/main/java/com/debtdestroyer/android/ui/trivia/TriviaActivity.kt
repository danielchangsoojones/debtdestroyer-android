package com.debtdestroyer.android.ui.trivia

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.ActivityTriviaBinding
import com.debtdestroyer.android.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaActivity : BaseActivity<ActivityTriviaBinding>(),
    NavController.OnDestinationChangedListener {

    private val viewModel: TriviaVM by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override val bindingInflater: (LayoutInflater) -> ActivityTriviaBinding
        get() = ActivityTriviaBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideActionBar()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_quiz_main)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        viewModel.load()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        //binding.toolbarTitle.text = binding.toolbar.title

        when (controller.currentDestination?.id) {
            R.id.SettingsFragment -> {
                showActionBar()
            }
            else -> {
                hideActionBar()
            }
        }
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    fun showActionBar() {
        supportActionBar?.hide()
    }
}