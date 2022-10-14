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
        const val KEY_PHONE_NUMBER = "phoneNumber"
        const val KEY_IS_DELETED = "isDeleted"
        const val KEY_NAME = "name"
    }

    var deviceType: String?
        get() = "android"
        set(value) = putOrIgnore(KEY_DEVICE_TYPE, "android")

    var phoneNumber: String?
        get() = get(KEY_PHONE_NUMBER).toString()
        set(value) = putOrIgnore(KEY_PHONE_NUMBER, phoneNumber)

    var isDeleted: Boolean?
        get() = get(KEY_IS_DELETED) as Boolean?
        set(value) = putOrIgnore(KEY_IS_DELETED, isDeleted)

    var name: String
        get() = get(KEY_NAME).toString()
        set(value) = putOrIgnore(KEY_NAME, name)

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

    /*func register(email: String, password: String) {
        let newUser = User()
        newUser.username = email.lowercased()
        newUser.password = password
        newUser.email = email.lowercased()
        newUser.deviceType = "iOS"

        newUser.signUpInBackground { (success, error: Error?) -> Void in
                if success {
                    let installation = PFInstallation.current()
                    installation?["user"] = User.current()
                    installation?.saveInBackground()
                    self.delegate?.segueIntoApp()
                }
                else {
                    if let error = error, let code = PFErrorCode(rawValue: error._code) {
                        switch code {
                            case .errorInvalidEmailAddress:
                            self.delegate?.showError(title: "Invalid Email Address", subtitle: "Please enter a valid email address")
                            case .errorUserEmailTaken:
                            self.delegate?.showError(title: "Email Already Taken", subtitle: "Email already exists, please use a different one or log in.")
                            default:
                            self.delegate?.showError(title: "Sign Up Error", subtitle: error.localizedDescription)
                        }
                    }
                }
        }
    }*/

}