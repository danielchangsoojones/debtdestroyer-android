package com.debtdestroyer.android.ui.trivia.daily

import androidx.lifecycle.*
import com.debtdestroyer.android.BuildConfig
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<Boolean> {

    fun load() = viewModelScope.launch {
        _res.postValue(Resource.loading())
        repository.checkIfMethodAuthed(object : ResponseCallback<Boolean> {
            override fun onReceive(res: Resource<Boolean>) {
                _res.postValue(res)
            }
        })
    }

    fun loadShouldShowEarnings() = viewModelScope.launch {
        val map = HashMap<String, String>()
        map[Params.KEY_APP_VERSION] = BuildConfig.VERSION_NAME
        map[Params.KEY_DEVICE_TYPE] = "android"
        repository.shouldShowEarning(object : ResponseCallback<Boolean> {
            override fun onReceive(res: Resource<Boolean>) {
                _res.postValue(res)
            }
        }, map)
    }


    fun getDemoQuizData() = viewModelScope.launch {
        repository.getDemoQuizData(object : ResponseCallback<Map<String, *>> {
            override fun onReceive(res: Resource<Map<String, *>>) {
                _quiz.postValue(res)
            }
        })
    }

    private val _res = MutableLiveData<Resource<Boolean>>()
    val res: LiveData<Resource<Boolean>>
        get() = _res

    private val _quiz = MutableLiveData<Resource<Map<String, *>>>()
    val quiz: LiveData<Resource<Map<String, *>>>
        get() = _quiz

    override fun onReceive(res: Resource<Boolean>) {
        _res.postValue(res)
    }
}