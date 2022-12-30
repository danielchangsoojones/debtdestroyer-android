package com.debtdestroyer.android.ui.trivia.settings

import android.view.View
import androidx.lifecycle.*
import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.repository.MainRepository
import com.parse.ParseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, AuthResponseCallback<ParseUser> {

    private val _res = MutableLiveData<Resource<ParseUser>>()
    val res: LiveData<Resource<ParseUser>>
        get() = _res

    fun onLogoutClicked() {
        _res.postValue(Resource.loading())
        callLogoutAPI()
    }

    private fun callLogoutAPI() = viewModelScope.launch {
        repository.logoutUser(ParseUser.getCurrentUser(), this@SettingsVM)
    }

    override fun onReceive(res: Resource<ParseUser>) {
        _res.postValue(res)
    }
}