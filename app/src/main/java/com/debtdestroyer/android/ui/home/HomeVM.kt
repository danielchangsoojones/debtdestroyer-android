package com.debtdestroyer.android.ui.home

import androidx.lifecycle.*
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.WinnersParse
import com.debtdestroyer.android.repository.MainRepository
import com.parse.ParseCloud
import com.parse.ParseObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<Map<String, *>> {

    fun load() = viewModelScope.launch {
        repository.getHomeSavingsInfo(this@HomeVM)

        repository.getHomeSweepStakeInfo(object : ResponseCallback<Map<String, *>> {
            override fun onReceive(res: Resource<Map<String, *>>) {
                _sweepRes.postValue(res)
            }
        })
        repository.getPastWinners(object : ResponseCallback<ArrayList<WinnersParse>> {
            override fun onReceive(res: Resource<ArrayList<WinnersParse>>) {
                _winnerRes.postValue(res)
            }
        })
    }

    private val _res = MutableLiveData<Resource<Map<String, *>>>()
    val res: LiveData<Resource<Map<String, *>>>
        get() = _res

    private val _sweepRes = MutableLiveData<Resource<Map<String, *>>>()
    val sweepRes: LiveData<Resource<Map<String, *>>>
        get() = _sweepRes

    private val _winnerRes = MutableLiveData<Resource<ArrayList<WinnersParse>>>()
    val winners: LiveData<Resource<ArrayList<WinnersParse>>>
        get() = _winnerRes

    override fun onReceive(res: Resource<Map<String, *>>) {
        _res.postValue(res)
    }
}