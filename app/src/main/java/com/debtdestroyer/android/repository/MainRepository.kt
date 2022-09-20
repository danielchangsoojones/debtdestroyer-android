package com.debtdestroyer.android.repository

import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.model.User
import com.parse.ParseUser
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(private val user: User) {

    fun authLogin(
        emailStr: String,
        passwordStr: String,
        authResponseCallback: AuthResponseCallback<ParseUser>
    ) = user.apply {
        logIn(emailStr, passwordStr, authResponseCallback)
    }

    fun authCreateNewUser(parseUser: User, authResponseCallback: AuthResponseCallback<User>) =
        user.apply {
            signup(parseUser, authResponseCallback)
        }

    fun savePhoneNumber(parseUser: ParseUser, authResponseCallback: AuthResponseCallback<ParseUser>) =
        user.savePhoneNumber(parseUser, authResponseCallback)

}