package com.debtdestroyer.android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.ActivityMainBinding
import com.debtdestroyer.android.ui.base.BaseActivity
import com.debtdestroyer.android.ui.base.getSmsIntent
import com.debtdestroyer.android.utils.GradientTextView
import kotlinx.coroutines.launch
import timber.log.Timber


class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        launch {
            setSupportActionBar(binding.toolbar)
            binding.toolbar.setNavigationOnClickListener {
            }

            val navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navController.addOnDestinationChangedListener(this@MainActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_help)
        val mButton: GradientTextView = item.actionView.findViewById(R.id.title_help_textview)

        mButton.setOnClickListener { view ->
            getSmsIntent()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun hideTitleBar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun homeAsUpEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    fun getSupportBar(): ActionBar? {
        return supportActionBar
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        binding.toolbarTitle.setText(binding.toolbar.title)
    }
}