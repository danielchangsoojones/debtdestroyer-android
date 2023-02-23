package com.debtdestroyer.android.ui.task

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
class TaskVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<ParseUser> {

    val promoCode = MutableLiveData("")
    val fName = MutableLiveData("")
    val lName = MutableLiveData("")

    private val _res = MutableLiveData<Resource<ParseUser>>()
    val res: LiveData<Resource<ParseUser>>
        get() = _res

    fun onNextClicked(view: View) {
        Timber.e("promoCode ${promoCode.value.toString().trim()}")
        _res.postValue(Resource.loading())
        callUpdateAPI()
    }

    private fun callUpdateAPI() = viewModelScope.launch {
        val parseUser = ParseUser.getCurrentUser()
        parseUser.put(User.KEY_F_NAME, fName.value.toString().trim())
        parseUser.put(User.KEY_L_NAME, lName.value.toString().trim())
        parseUser.put(User.KEY_PROMO_CODE, promoCode.value.toString().trim())

        repository.updateUserDetails(parseUser, this@TaskVM)
    }

    override fun onReceive(res: Resource<ParseUser>) {
        _res.postValue(res)
    }
}