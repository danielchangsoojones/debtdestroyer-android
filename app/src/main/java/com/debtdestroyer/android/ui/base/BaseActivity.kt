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
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.debtdestroyer.android.utils.showCustomToast
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    protected lateinit var binding: B
        private set

    abstract val bindingInflater: (LayoutInflater) -> B

    lateinit var appUpdateManager: AppUpdateManager
    private val updateAvailable = MutableLiveData<Boolean>().apply { value = false }
    private var updateInfo: AppUpdateInfo? = null
    var updateListener = InstallStateUpdatedListener { state: InstallState ->
        Timber.e("update01:$state")
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            showUpdateSnackbar()
        }
    }

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

    fun showSnackBar(color: Int, message: String) {
        val snackbar: Snackbar
        try {
            val rootView = window.decorView.findViewById<View>(R.id.content)
            snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            val sbView = snackbar.view
            val textView =
                sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
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

    fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                it.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                updateInfo = it
                updateAvailable.value = true
                startForInAppUpdate(updateInfo)
            } else {
                updateAvailable.value = false
                Timber.e("update01:Update not available")
            }
        }
    }

    private fun startForInAppUpdate(it: AppUpdateInfo?) {
        //AppUpdateType.IMMEDIATE for force
        appUpdateManager.startUpdateFlowForResult(it!!, AppUpdateType.IMMEDIATE, this, 1101)
    }

    private fun showUpdateSnackbar() {
        try {
            val snackbar = Snackbar.make(
                binding.root,
                "An update has just been downloaded.",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("RESTART") { appUpdateManager.completeUpdate() }
            snackbar.setActionTextColor(Color.parseColor("#ffff4444"))
            snackbar.show()
        } catch (e: java.lang.Exception) {
        }
    }
}