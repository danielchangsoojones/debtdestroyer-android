package com.debtdestroyer.android.repository

import com.debtdestroyer.android.callback.AuthResponseCallback
import com.debtdestroyer.android.callback.Params
import com.debtdestroyer.android.callback.Resource
import com.debtdestroyer.android.callback.ResponseCallback
import com.debtdestroyer.android.model.*
import com.parse.ParseCloud
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val user: User
) {
    val emptyParams: HashMap<String, Any> = HashMap()

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

    fun updateUserDetails(
        parseUser: ParseUser,
        authResponseCallback: AuthResponseCallback<ParseUser>
    ) = user.updateUserDetails(parseUser, authResponseCallback)

    fun resetPassword(email: String, authResponseCallback: AuthResponseCallback<ParseUser>) =
        user.resetPassword(email, authResponseCallback)

    fun logoutUser(parseUser: ParseUser, authResponseCallback: AuthResponseCallback<ParseUser>) =
        user.logoutUser(parseUser, authResponseCallback)

    fun getSavingInfo(authResponseCallback: ResponseCallback<ParseObject>) =
        query(ParseQuery.getQuery("getSavingsInfo"), authResponseCallback)

    fun query(parseQuery: ParseQuery<ParseObject>, response: ResponseCallback<ParseObject>) =
        parseQuery.findInBackground { data, e ->
            if (e == null) {
                Timber.e("RESSSS ${data}")
            } else {
                Timber.e("RESSSS eeee : ${e.localizedMessage}")
            }
        }


    //Home Screen Apis
    fun checkIfMethodAuthed(response: ResponseCallback<Boolean>) =
        ParseCloud.callFunctionInBackground<Boolean>(
            Params.API_CHECK_IF_METHOD_AUTHED,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("checkIfMethodAuthed $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    fun checkWaitlist(response: ResponseCallback<Map<String, *>>) =
        ParseCloud.callFunctionInBackground<Map<String, *>>(
            Params.API_CHECK_WAITLIST,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("checkWaitlist:: $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    fun shouldShowEarning(response: ResponseCallback<Boolean>, map: HashMap<String, String>) =
        ParseCloud.callFunctionInBackground<Boolean>(
            Params.API_SHOULD_SHOW_EARNINGS, map
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("shouldShowEarning:: $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
            }
        }

    fun getDemoQuizData(response: ResponseCallback<ArrayList<QuizDataParse>>) =
        ParseCloud.callFunctionInBackground<ArrayList<QuizDataParse>>(
            Params.API_GET_DEMO_QUIZ_DATA,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("getDemoQuizData:: $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    fun getLeaderboard(response: ResponseCallback<Map<String, *>>) =
        ParseCloud.callFunctionInBackground<Map<String, *>>(
            Params.API_GET_LEADERSHIP,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("leadership $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    //Not in use
    fun getHomeSweepStakeInfo(response: ResponseCallback<Map<String, *>>) =
        ParseCloud.callFunctionInBackground<Map<String, *>>(
            Params.API_SWEEP_STAKES,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("sweep $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    fun getHomeSavingsInfo(response: ResponseCallback<Map<String, *>>) =
        ParseCloud.callFunctionInBackground<Map<String, *>>(
            Params.API_SAVING_INFO,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("hoooo $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }


    fun getPastWinners(response: ResponseCallback<ArrayList<WinnersParse>>) =
        ParseCloud.callFunctionInBackground<ArrayList<WinnersParse>>(
            Params.API_PAST_WINNERS,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                Timber.e("winners $result")
                response.onReceive(Resource.success(result))
            } else {
                //response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("winners exception ${e.localizedMessage}")
            }
        }

    //Bank Screen Apis
    fun getConnectedAccount(response: ResponseCallback<ArrayList<DebtAccountsParse>>) =
        ParseCloud.callFunctionInBackground<ArrayList<DebtAccountsParse>>(
            Params.API_GET_DEBT_ACCOUNTS,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                Timber.e("accounts  $result")
                response.onReceive(Resource.success(result))
            } else {
                //response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("accounts exception ${e.localizedMessage}")
            }
        }

    fun getTransactions(response: ResponseCallback<ArrayList<TransactionHistoryParse>>) =
        ParseCloud.callFunctionInBackground<ArrayList<TransactionHistoryParse>>(
            Params.API_GET_TRANSACTION,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                Timber.e("transaction history exception $result")
                response.onReceive(Resource.success(result))
            } else {
                Timber.e("transaction history exception ${e.localizedMessage}")
            }
        }

    //Trivia

    //Home Screen Apis
    fun getQuizData(response: ResponseCallback<ArrayList<String>>) =
        ParseCloud.callFunctionInBackground<ArrayList<String>>(
            Params.API_GET_QUIZ_DATA,
            emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("Yoooohhhoo!! $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    fun checkShowQuizPopUp(response: ResponseCallback<Boolean>) =
        ParseCloud.callFunctionInBackground<Boolean>(
            Params.API_CHECK_SHOW_QUIZ_POPUP, emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("Yoooohhhoo!! checkShowQuizPopUp :: $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

    //Scholarship
    fun getScholarshipData(response: ResponseCallback<Boolean>) =
        ParseCloud.callFunctionInBackground<Boolean>(
            Params.API_CHECK_SHOW_QUIZ_POPUP, emptyParams
        ) { result, e ->
            response.onReceive(Resource.loading())
            if (e == null) {
                response.onReceive(Resource.success(result))
                Timber.e("Yoooohhhoo!! checkShowQuizPopUp :: $result")
            } else {
                response.onReceive(Resource.error(e.localizedMessage, null))
                Timber.e("exception ${e.localizedMessage}")
            }
        }

}