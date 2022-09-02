package com.debtdestroyer.android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.debtdestroyer.android.ui.base.BaseActivity
import com.debtdestroyer.android.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        launch {
         /*   delay(2000)
            views.helloWorldTextView.text = "Hello ActivityUsingBaseActivity"*/
        }
    }

}