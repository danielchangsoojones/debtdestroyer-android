package com.debtdestroyer.android.ui.base

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
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
        navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left)
            .setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right).build()
        findNavController().navigate(directions, navBuilder.build())
    } catch (e: IllegalArgumentException) {
        Timber.e("Fragment Catching potential duplicate navigation event")
    }
}

fun Fragment.getUrlFromIntent(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}

fun Fragment.getSmsIntent(phoneNumber: String) {
    val uri = Uri.parse("smsto:3176905323")
    val i = Intent(Intent.ACTION_SENDTO, uri)
    i.putExtra("sms_body", "Please help me reset my password for debtdestroyer")
    startActivity(i)
}