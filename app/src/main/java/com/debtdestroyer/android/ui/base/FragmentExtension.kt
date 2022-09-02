package com.debtdestroyer.android.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.ui.base.adapter.ItemSelectionCallback
import timber.log.Timber

fun <T> createItemSelectionCallback(block: (T) -> Unit): ItemSelectionCallback<T> {
    return object : ItemSelectionCallback<T> {
        override fun onItemSelected(item: T) {
            block.invoke(item)
        }
    }
}

fun Fragment.navigateTo(directions: NavDirections) {
    try {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left).setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right).build()
        findNavController().navigate(directions, navBuilder.build())
    } catch (e: IllegalArgumentException) {
        Timber.e("Fragment Catching potential duplicate navigation event")
    }
}