package com.debtdestroyer.android.callback

interface ResponseCallback<T> {
    fun onReceive(res: Resource<T>)

}