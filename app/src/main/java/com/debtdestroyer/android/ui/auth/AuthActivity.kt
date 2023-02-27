package com.debtdestroyer.android.ui.auth

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.ActivityMainBinding
import com.debtdestroyer.android.ui.base.BaseActivity
import com.debtdestroyer.android.ui.base.getSmsIntent
import com.debtdestroyer.android.utils.GradientTextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(this@AuthActivity)

        val navView: BottomNavigationView = binding.content.navView
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_help)
        val mButton: GradientTextView = item.actionView!!.findViewById(R.id.title_help_textview)

        mButton.setOnClickListener {
            getSmsIntent()
        }
        return true
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
        binding.toolbarTitle.text = binding.toolbar.title

        when (controller.currentDestination?.id) {
            R.id.OnBoardingFragment, R.id.LoginFragment, R.id.SignupFragment, R.id.PhoneNumberFragment -> {
                binding.isLoggedIn = false
            }
            else -> {
                binding.isLoggedIn = true
            }
        }
    }

    fun hideProgressBar() {
        binding.content.showProgress = false
    }

    fun showProgressBar() {
        binding.content.showProgress = true
    }

    fun showError(error: String) {
        showSnackBar(Color.parseColor("#efd539"), error)
    }

    fun showWarning(warning: String) {
        showSnackBar(Color.parseColor("#efd539"), warning)
    }

    fun showMessage(message: String) {
        showSnackBar(Color.parseColor("#efd539"), message)
    }

    companion object {
        const val SHOW_PROMO_CODE = true
    }
}