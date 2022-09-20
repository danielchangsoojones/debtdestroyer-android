package com.debtdestroyer.android.ui.onboarding.signup

import android.view.View
import androidx.lifecycle.*
import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.repository.MainRepository
import com.parse.ParseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignupVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<User> {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _res = MutableLiveData<Resource<User>>()
    val res: LiveData<Resource<User>>
        get() = _res

    fun onSignUpClicked(view: View) {
        if (email.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please enter email!", null))
        } else if (password.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please enter password!", null))
        } else {
            _res.postValue(Resource.loading())
            callSignupAPI()
        }
    }

    private fun callSignupAPI() = viewModelScope.launch {
        val parseUser = User()
        parseUser.username = email.value.toString().trim().lowercase()
        parseUser.email = email.value.toString().trim().lowercase()
        parseUser.setPassword(password.value.toString().trim())

        repository.authCreateNewUser(parseUser, this@SignupVM)
    }

    override fun onReceive(res: Resource<User>) {
        _res.postValue(res)
    }
}