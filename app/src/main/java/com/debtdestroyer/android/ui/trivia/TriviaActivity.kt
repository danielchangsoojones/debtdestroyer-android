package com.debtdestroyer.android.ui.trivia

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
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
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TriviaActivity : BaseActivity<ActivityTriviaBinding>(),
    NavController.OnDestinationChangedListener {

    //@Inject
    //lateinit var networkConnectionManager: NetworkConnectionManager

    private val viewModel: TriviaVM by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override val bindingInflater: (LayoutInflater) -> ActivityTriviaBinding
        get() = ActivityTriviaBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        connectionRegister()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_trivia_main)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewModel.load()
        checkUpdates()
    }

    private fun connectionRegister() {
        /*networkConnectionManager.startListenNetworkState()
        networkConnectionManager.isNetworkConnectedFlow
            .onEach {
                @StringRes val res = if (it) {
                    R.string.network_is_connected
                } else {
                    R.string.network_is_disconnected
                }
                showToast("Connection Error","$it")
            }
            .launchIn(lifecycleScope)*/
    }

    private fun checkUpdates() {
        try {
            appUpdateManager = AppUpdateManagerFactory.create(this)
            appUpdateManager.registerListener(updateListener)
            checkForUpdate()
        } catch (e: Exception) {
            Timber.e("update01:Update e1 ${e.message}")
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            try {
                appUpdateManager.unregisterListener(updateListener)
            } catch (e: Exception) {
                Timber.e("update01:Update e2 ${e.message}")
            }
        }
    }

    override fun onDestroy() {
        //networkConnectionManager.stopListenNetworkState()
        super.onDestroy()
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_trivia_main)
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

    private fun showActionBar() {
        supportActionBar?.show()
    }
}