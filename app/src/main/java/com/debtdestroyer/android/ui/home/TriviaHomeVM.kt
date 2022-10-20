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
) : ViewModel(), LifecycleObserver, ResponseCallback<ArrayList<String>> {

    fun load() = viewModelScope.launch {
        repository.shouldShowEarning(this@TriviaHomeVM)
        repository.getQuizData(this@TriviaHomeVM)
    }

    private val _res = MutableLiveData<Resource<ArrayList<String>>>()
    val res: LiveData<Resource<ArrayList<String>>>
        get() = _res

    override fun onReceive(res: Resource<ArrayList<String>>) {
        _res.postValue(res)
    }
}