package com.debtdestroyer.android.ui.trivia.quiz

import androidx.lifecycle.*
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.QuizDataParse
import com.debtdestroyer.android.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class QuizVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver {

    fun load() = viewModelScope.launch {
        Timber.e("LOAD QUIZ")
        repository.getDemoQuizData(object : ResponseCallback<ArrayList<QuizDataParse>> {
            override fun onReceive(res: Resource<ArrayList<QuizDataParse>>) {
                _res.postValue(res)
            }
        })
    }

    private val _res = MutableLiveData<Resource<ArrayList<QuizDataParse>>>()
    val res: LiveData<Resource<ArrayList<QuizDataParse>>>
        get() = _res

}