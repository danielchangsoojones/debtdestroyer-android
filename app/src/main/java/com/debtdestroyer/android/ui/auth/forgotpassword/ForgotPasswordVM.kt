package com.debtdestroyer.android.ui.auth.forgotpassword

import android.view.View
import androidx.lifecycle.*
import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.repository.MainRepository
import com.debtdestroyer.android.utils.isEmailValid
import com.parse.ParseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<ParseUser> {

    val email = MutableLiveData("")

    private val _res = MutableLiveData<Resource<ParseUser>>()
    val res: LiveData<Resource<ParseUser>>
        get() = _res

    fun onNextClicked(view: View) {
        if (email.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please enter valid email!", null))
        } else {
            _res.postValue(Resource.loading())
            callResetPasswordAPI()
        }
    }

    private fun callResetPasswordAPI() = viewModelScope.launch {
        repository.resetPassword(email.value.toString(), this@ForgotPasswordVM)
    }

    override fun onReceive(res: Resource<ParseUser>) {
        _res.postValue(res)
    }
}