package com.debtdestroyer.android.ui.dob

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
class DobVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<ParseUser> {

    val month = MutableLiveData("")
    val day = MutableLiveData("")
    val year = MutableLiveData("")

    private val _res = MutableLiveData<Resource<ParseUser>>()
    val res: LiveData<Resource<ParseUser>>
        get() = _res

    fun onNextClicked(view: View) {
        Timber.e("date ${month.value.toString().trim()} ${day.value.toString().trim()} ${year.value.toString().trim()}")
        if (month.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please input month!", null))
        } else if (day.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please input day!", null))
        } else if (day.value.toString().trim().isEmpty()) {
            _res.postValue(Resource.error("Please input year!", null))
        } else {
            _res.postValue(Resource.loading())
            val dobString =
                month.value.toString() + "/" + day.value.toString() + "/" + year.value.toString()
            callUpdateAPI(dobString)
        }
    }

    private fun callUpdateAPI(dobString: String) = viewModelScope.launch {
        val parseUser = ParseUser.getCurrentUser()
        parseUser.put(User.KEY_DOB, dobString)

        repository.updateUserDetails(parseUser, this@DobVM)
    }

    override fun onReceive(res: Resource<ParseUser>) {
        _res.postValue(res)
    }
}