package com.debtdestroyer.android.callback

import com.parse.ParseObject

interface ResponseCallback<T> {
    fun onReceive(res: Resource<T>)

}