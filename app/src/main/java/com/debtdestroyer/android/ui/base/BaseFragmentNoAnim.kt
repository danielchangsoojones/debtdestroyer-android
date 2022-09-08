package com.debtdestroyer.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


abstract class BaseFragmentNoAnim<LayoutBinding : ViewBinding> : Fragment(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    protected lateinit var binding: LayoutBinding private set
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        coroutineContext[Job]?.cancel()
    }
}