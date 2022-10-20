package com.debtdestroyer.android.ui.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.ui.base.adapter.ItemSelectionCallback
import com.debtdestroyer.android.ui.main.MainActivity
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

fun BaseFragment<*>.hideProgressBar() {
    (activity as? MainActivity)?.hideProgressBar()
}

fun BaseFragment<*>.showProgressBar() {
    (activity as? MainActivity)?.showProgressBar()
}

fun BaseFragment<*>.showError(error: String) {
    (activity as? MainActivity)?.showError(error)
}

fun BaseFragment<*>.showMessage(message: String) {
    (activity as? MainActivity)?.showMessage(message)
}

fun BaseFragment<*>.showWarning(warning: String) {
    (activity as? MainActivity)?.showWarning(warning)
}


fun BaseFragmentNoAnim<*>.hideProgressBar() {
    (activity as? MainActivity)?.hideProgressBar()
}

fun BaseFragmentNoAnim<*>.showProgressBar() {
    (activity as? MainActivity)?.showProgressBar()
}

fun BaseFragmentNoAnim<*>.showError(error: String) {
    (activity as? MainActivity)?.showError(error)
}

fun BaseFragmentNoAnim<*>.showMessage(message: String) {
    (activity as? MainActivity)?.showMessage(message)
}

fun BaseFragmentNoAnim<*>.showWarning(warning: String) {
    (activity as? MainActivity)?.showWarning(warning)
}

fun DialogFragment.navigateTo(directions: NavDirections) {
    try {
        val navBuilder = NavOptions.Builder()
        findNavController().navigate(directions, navBuilder.build())
    } catch (e: IllegalArgumentException) {
        Timber.e("Fragment Catching potential duplicate navigation event")
    }
}

fun Fragment.getUrlFromIntent(url: String) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "No application can handle this request." + " Please install a webbrowser",
            Toast.LENGTH_LONG
        ).show()
        e.printStackTrace()
    }
}

fun Fragment.getSmsIntent(phoneNumber: String) {
    val uri = Uri.parse("smsto:3176905323")
    val i = Intent(Intent.ACTION_SENDTO, uri)
    i.putExtra("sms_body", "Please help me reset my password for debtdestroyer")
    startActivity(i)
}