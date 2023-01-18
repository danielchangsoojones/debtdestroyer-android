package com.debtdestroyer.android.ui.base

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.debtdestroyer.android.utils.showCustomToast
import com.google.android.material.snackbar.Snackbar
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

    /**
     * Hide keyboard when user click anywhere on screen
     * @param event contains int value for motion event actions
     * @return boolean value of touch event.
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        try {
            val im = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: NullPointerException) {
        }
        return true
    }

    fun showSnackBar(color: Int, message: String){
        val snackbar: Snackbar
        try {
            val rootView = window.decorView.findViewById<View>(R.id.content)
            snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            val sbView = snackbar.view
            val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(color)
            snackbar.show()
        } catch (ex: Exception) {
        }
    }

    fun showToast(title: String, error: String) {
        Toast(this).showCustomToast(title, error, this)
    }

    fun hideKeyBoard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}