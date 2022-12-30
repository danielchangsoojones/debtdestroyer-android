package com.debtdestroyer.android.ui.phonenumber

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
class PhoneNumberVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<ParseUser> {

    val phoneNumber = MutableLiveData("")

    private val _res = MutableLiveData<Resource<ParseUser>>()
    val res: LiveData<Resource<ParseUser>>
        get() = _res

    fun onNextClicked(view: View) {
        if (phoneNumber.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please enter phone number!", null))
        } else {
            _res.postValue(Resource.loading())
            callUpdateAPI()
        }
    }

    private fun callUpdateAPI() = viewModelScope.launch {
        val parseUser = ParseUser.getCurrentUser()
        parseUser.put(User.KEY_PHONE_NUMBER, phoneNumber.value.toString().trim())

        repository.updateUserDetails(parseUser, this@PhoneNumberVM)
    }

    override fun onReceive(res: Resource<ParseUser>) {
        _res.postValue(res)
    }
}