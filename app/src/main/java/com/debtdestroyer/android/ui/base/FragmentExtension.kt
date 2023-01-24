package com.debtdestroyer.android.ui.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Params.CONTACT_NUMBER
import com.debtdestroyer.android.ui.base.adapter.ItemSelectionCallback
import com.debtdestroyer.android.ui.auth.AuthActivity
import com.debtdestroyer.android.utils.showCustomToast
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
    (activity as? AuthActivity)?.hideProgressBar()
}

fun BaseFragment<*>.showProgressBar() {
    (activity as? AuthActivity)?.showProgressBar()
}

fun BaseFragment<*>.showError(error: String) {
    (activity as? AuthActivity)?.showError(error)
}

fun BaseFragment<*>.showMessage(message: String) {
    (activity as? AuthActivity)?.showMessage(message)
}

fun BaseFragment<*>.showWarning(warning: String) {
    (activity as? AuthActivity)?.showWarning(warning)
}

fun BaseFragment<*>.showToast(title: String, error: String) {
    (activity as? AuthActivity)?.showToast(title, error)
}

fun BaseFragment<*>.hideKeyBoard(view: View) {
    (activity as? AuthActivity)?.hideKeyBoard(view)
}

fun BaseFragmentNoAnim<*>.showToast(title: String, error: String) {
    (activity as? AuthActivity)?.showToast(title, error)
}

fun BaseFragmentNoAnim<*>.hideProgressBar() {
    (activity as? AuthActivity)?.hideProgressBar()
}

fun BaseFragmentNoAnim<*>.showProgressBar() {
    (activity as? AuthActivity)?.showProgressBar()
}

fun BaseFragmentNoAnim<*>.showError(error: String) {
    (activity as? AuthActivity)?.showError(error)
}

fun BaseFragmentNoAnim<*>.showMessage(message: String) {
    (activity as? AuthActivity)?.showMessage(message)
}

fun BaseFragmentNoAnim<*>.showWarning(warning: String) {
    (activity as? AuthActivity)?.showWarning(warning)
}

fun BaseFragmentNoAnim<*>.hideKeyBoard(view: View) {
    (activity as? AuthActivity)?.hideKeyBoard(view)
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
        Toast(context).showCustomToast("No application can handle this request!", " Please install a web browser.", requireActivity())
        e.printStackTrace()
    }
}

fun Fragment.getSmsIntent() {
    val uri = Uri.parse("smsto:$CONTACT_NUMBER")
    val i = Intent(Intent.ACTION_SENDTO, uri)
    i.putExtra("sms_body", "Please help me reset my password for debtdestroyer")
    startActivity(i)
}