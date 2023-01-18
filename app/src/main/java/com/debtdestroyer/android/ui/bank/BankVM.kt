package com.debtdestroyer.android.ui.bank

import androidx.lifecycle.*
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.DebtAccountsParse
import com.debtdestroyer.android.model.TransactionHistoryParse
import com.debtdestroyer.android.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BankVM @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), LifecycleObserver, ResponseCallback<ArrayList<DebtAccountsParse>> {

    fun load() = viewModelScope.launch {
        repository.getConnectedAccount(this@BankVM)
        repository.getTransactions(object : ResponseCallback<ArrayList<TransactionHistoryParse>> {
            override fun onReceive(res: Resource<ArrayList<TransactionHistoryParse>>) {
                //_winnerRes.postValue(res)
            }
        })

    }

    private val _res = MutableLiveData<Resource<ArrayList<DebtAccountsParse>>>()
    val res: LiveData<Resource<ArrayList<DebtAccountsParse>>>
        get() = _res

    override fun onReceive(res: Resource<ArrayList<DebtAccountsParse>>) {
        _res.postValue(res)
    }
}