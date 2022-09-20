package com.debtdestroyer.android.api

import com.debtdestroyer.android.callback.Resource
import com.parse.ParseUser

interface ApiService {
    fun loginAuth(email: String, password: String): Resource<ParseUser>
}