package com.debtdestroyer.android.ui.home

import androidx.lifecycle.*
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.WinnersParse
import com.debtdestroyer.android.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TriviaHomeVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<Map<String, *>> {

    fun load() = viewModelScope.launch {
        repository.getQuizData(this@TriviaHomeVM)
    }

    private val _res = MutableLiveData<Resource<Map<String, *>>>()
    val res: LiveData<Resource<Map<String, *>>>
        get() = _res
    override fun onReceive(res: Resource<Map<String, *>>) {
        _res.postValue(res)
    }
}