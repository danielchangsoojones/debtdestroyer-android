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

    var deviceType: String
        get() = "android"
        set(value) = putOrIgnore(KEY_DEVICE_TYPE, deviceType)

    var createdAt: String
        get() = "android"
        set(value) = putOrIgnore(KEY_CREATED_AT, createdAt)

    var updatedAt: String
        get() = get(KEY_UPDATED_AT).toString()
        set(value) = putOrIgnore(KEY_UPDATED_AT, updatedAt)

    var phoneNumber: String?
        get() = get(KEY_PHONE_NUMBER).toString()
        set(value) = putOrIgnore(KEY_PHONE_NUMBER, phoneNumber)

    var isDeleted: Boolean?
        get() = get(KEY_IS_DELETED) as Boolean?
        set(value) = putOrIgnore(KEY_IS_DELETED, isDeleted)

    var name: String
        get() = get(KEY_NAME).toString()
        set(value) = putOrIgnore(KEY_NAME, name)

    var fname: String
        get() = get(KEY_F_NAME).toString()
        set(value) = putOrIgnore(KEY_F_NAME, fname)

    var lname: String
        get() = get(KEY_L_NAME).toString()
        set(value) = putOrIgnore(KEY_L_NAME, lname)

/*
    var shouldShowEarning: Boolean
        get() = get(KEY_SHOULD_SHOW_EARNINGS) as Boolean
        set(value) = putOrIgnore(KEY_SHOULD_SHOW_EARNINGS, shouldShowEarning)
*/

    fun fullName(): String {
        return "$fname $lname"
    }

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

    fun updateUserDetails(parseUser: ParseUser, response: AuthResponseCallback<ParseUser>) =
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

    //reset user
    fun resetPassword(mEmail: String, response: AuthResponseCallback<ParseUser>) =
        requestPasswordReset(mEmail)

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

    companion object {
        const val KEY_DEVICE_TYPE = "deviceType"
        const val KEY_PHONE_NUMBER = "phoneNumber"
        const val KEY_IS_DELETED = "isDeleted"
        const val KEY_NAME = "name"
        const val KEY_F_NAME = "firstName"
        const val KEY_L_NAME = "lastName"
        const val KEY_SESSION_TOKEN = "sessionToken"
        //const val KEY_SHOULD_SHOW_EARNINGS = "shouldShowEarning"
        const val KEY_CREATED_AT = "createdAt"
        const val KEY_UPDATED_AT = "updatedAt"
        const val KEY_DOB = "dateOfBirth"

        var KEY_SHOULD_SHOW_EARNINGS = true
    }

    //LOGIN
    //{"objectId":"niloAQmmF3",
    // "email":"annabelle@gmail.com",
    // "username":"annabelle@gmail.com",
    // "deviceType":"iOS",
    // "createdAt":"2022-11-11T07:55:55.681Z",
    // "updatedAt":"2022-11-29T06:17:33.171Z",
    // "phoneNumber":"15121231111",
    // "firstName":"anna",
    // "lastName":"belle",
    // "sessionToken":"r:1a53d7115e54667b33db7cb7606f039e"}

}