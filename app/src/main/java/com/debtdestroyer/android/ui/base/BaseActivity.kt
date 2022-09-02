package com.debtdestroyer.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    protected lateinit var binding: B
        private set

    abstract val bindingInflater: (LayoutInflater) -> B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater).apply { setContentView(root) }
        onViewBindingCreated(savedInstanceState)
    }

    open fun onViewBindingCreated(savedInstanceState: Bundle?) {}

    @CallSuper
    override fun onDestroy() {
        coroutineContext[Job]?.cancel()
        super.onDestroy()
    }
}