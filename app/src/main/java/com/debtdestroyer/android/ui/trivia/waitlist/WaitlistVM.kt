package com.debtdestroyer.android.ui.trivia.waitlist

import androidx.lifecycle.*
import com.debtdestroyer.android.BuildConfig
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class WaitlistVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<ArrayList<String>> {

    private val _res = MutableLiveData<Resource<ArrayList<String>>>()
    val res: LiveData<Resource<ArrayList<String>>>
        get() = _res

    private val _showEarnings = MutableLiveData<Resource<Boolean>>()
    val showEarnings: LiveData<Resource<Boolean>>
        get() = _showEarnings

    fun load() = viewModelScope.launch {
        repository.getQuizData(this@WaitlistVM)
    }

    override fun onReceive(res: Resource<ArrayList<String>>) {
        _res.postValue(res)
        val map = HashMap<String, String>()
        map[Params.KEY_APP_VERSION] = BuildConfig.VERSION_NAME
        map[Params.KEY_DEVICE_TYPE] = "Android"
        Timber.e("SHOW Earnigs")
        repository.shouldShowEarning(object : ResponseCallback<Boolean> {
            override fun onReceive(res: Resource<Boolean>) {
                _showEarnings.postValue(res)
            }
        }, map)
    }
}