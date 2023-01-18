package com.debtdestroyer.android.ui.trivia

import androidx.lifecycle.*
import com.debtdestroyer.android.BuildConfig
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.repository.MainRepository
import com.parse.ParseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TriviaVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<ArrayList<String>> {

    private val _res = MutableLiveData<Resource<ArrayList<String>>>()
    val res: LiveData<Resource<ArrayList<String>>>
        get() = _res

    private val _showEarnings = MutableLiveData<Resource<Boolean>>()
    val showEarnings: LiveData<Resource<Boolean>>
        get() = _showEarnings

    fun load() = viewModelScope.launch {
        //repository.getQuizData(this@TriviaVM)
    }

    override fun onReceive(res: Resource<ArrayList<String>>) {
        _res.postValue(res)
        //checkShowQuizPopUp
        repository.checkShowQuizPopUp(object : ResponseCallback<Boolean> {
            override fun onReceive(res: Resource<Boolean>) {
                val parseUser = ParseUser.getCurrentUser()
                parseUser.put("checkShowQuizPopUp", res)
                _showEarnings.postValue(res)
            }
        })

        //Should Show Earnings
        /*
        val map = HashMap<String, String>()
        map[Params.KEY_APP_VERSION] = BuildConfig.VERSION_NAME
        map[Params.KEY_DEVICE_TYPE] = "Android"
        Timber.e("Show Earnings :: ${map.keys}")

        repository.shouldShowEarning(object : ResponseCallback<Boolean> {
            override fun onReceive(res: Resource<Boolean>) {
                val parseUser = ParseUser.getCurrentUser()
                parseUser.put("shouldShowEarning", res)
                _showEarnings.postValue(res)
            }
        }, map)*/
    }
}