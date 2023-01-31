package com.debtdestroyer.android.ui.trivia.score

import androidx.lifecycle.*
import com.debtdestroyer.android.BuildConfig
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.model.QuizScoreParse
import com.debtdestroyer.android.repository.MainRepository
import com.parse.ParseObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ScoreVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver {

    fun getLeaderboardData() = viewModelScope.launch {
        repository.getLeaderboard(object : ResponseCallback<Map<String, *>> {
            override fun onReceive(res: Resource<Map<String, *>>) {
                _quiz.postValue(res)
            }
        })
    }

    private val _quiz = MutableLiveData<Resource<Map<String, *>>>()
    val quiz: LiveData<Resource<Map<String, *>>>
        get() = _quiz

}