package com.debtdestroyer.android.ui.base

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


abstract class BaseFragment<LayoutBinding : ViewBinding> : Fragment(),//VM : ViewModel
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private var animationWrapper: AnimationSet? = null

    protected lateinit var binding: LayoutBinding private set

    //protected lateinit var viewModel: VM private set
    //private val type = (javaClass.genericSuperclass as ParameterizedType)
    //private val classVB = type.actualTypeArguments[0] as Class<LayoutBinding>
    //private val classVM = type.actualTypeArguments[1] as Class<VM>
    //private val inflateMethod = classVB.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)
        //binding = inflateMethod.invoke(null, inflater, container, false) as LayoutBinding
        //viewModel = createViewModelLazy(classVM.kotlin, { viewModelStore }).value
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
            if (nextAnim == 0)
                return null
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