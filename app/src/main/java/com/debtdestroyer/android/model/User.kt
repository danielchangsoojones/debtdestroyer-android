package com.debtdestroyer.android.model

import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Resource
import com.parse.ParseClassName
import com.parse.ParseUser
import com.parse.ktx.putOrIgnore
import timber.log.Timber
import javax.inject.Inject


@ParseClassName("User")
class User @Inject constructor() : ParseUser() {

    companion object {
        const val KEY_DEVICE_TYPE = "deviceType"
    }

    var deviceType: String?
        get() = "android"
        set(value) = putOrIgnore(KEY_DEVICE_TYPE, "android")


    fun logIn(mEmail: String, mPassword: String, response: AuthResponseCallback<ParseUser>) =
        logInInBackground(mEmail, mPassword) { user, e ->
            if (user != null) {
                // Hooray! The user is logged in.
                response.onReceive(Resource.success(user))
            } else {
                // SignIn failed. Look at the ParseException to see what happened.
                response.onReceive(Resource.error(e.localizedMessage, null))
            }
        }

    fun signup(parseUser: User, response: AuthResponseCallback<User>) =
        parseUser.signUpInBackground { e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                // Hooray! Let them use the app now.
                response.onReceive(Resource.success(parseUser))
            } else {
                // Sign up didn't succeed. Look at the ParseException to figure out what went wrong
                response.onReceive(Resource.error(e.localizedMessage!!, null))
            }
        }


    fun savePhoneNumber(parseUser: ParseUser, response: AuthResponseCallback<ParseUser>) =
        parseUser.saveInBackground { e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                // Hooray! Let them use the app now.
                response.onReceive(Resource.success(parseUser))
            } else {
                // Save phone number didn't succeed. Look at the ParseException to figure out what went wrong
                response.onReceive(Resource.error(e.localizedMessage!!, null))
            }
        }


    fun logoutUser(parseUser: ParseUser, response: AuthResponseCallback<ParseUser>) =
        logOutInBackground { e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(parseUser))
            } else {
                response.onReceive(Resource.error(e.localizedMessage!!, null))
            }
        }

}