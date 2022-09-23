package com.debtdestroyer.android.callback

interface AuthResponseCallback<T> {
    fun onReceive(res : Resource<T>)
}