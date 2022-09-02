package com.debtdestroyer.android.ui.base

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


abstract class BaseFragment<LayoutBinding : ViewBinding> :
    Fragment(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private var animationWrapper: AnimationSet? = null

    protected lateinit var binding: LayoutBinding
        private set

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

    open fun interruptAnimation() {
        if (animationWrapper != null) {
            animationWrapper!!.duration = 0
            animationWrapper!!.cancel()
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): AnimationSet? {
        val anim: Animation = try {
            AnimationUtils.loadAnimation(activity, nextAnim)
        } catch (e: Resources.NotFoundException) {
            return null
        }
        val animationWrapper = AnimationSet(false)
        animationWrapper.addAnimation(anim)
        this.animationWrapper = animationWrapper
        return this.animationWrapper
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}