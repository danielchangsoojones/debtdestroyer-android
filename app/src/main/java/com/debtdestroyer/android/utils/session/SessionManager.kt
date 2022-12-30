/*
package com.debtdestroyer.android.utils.session

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.debtdestroyer.android.model.TransactionParse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SessionManager @Inject constructor(@ApplicationContext context: Context) {

    private var mSharedPref: SharedPreferences

    fun clearAll() {
        mSharedPref.edit().clear().apply()
    }

    fun save(key: String?, value: String?) {
        if (!TextUtils.isEmpty(value)) {
            mSharedPref.edit().apply {
                putString(key, value)
                apply()
            }
        }
    }

    fun save(key: String?, value: Long) {
        mSharedPref.edit().apply {
            putLong(key, value)
            apply()
        }
    }

    fun save(key: String?, value: Int) {
        mSharedPref.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    fun delete(key: String?) {
        mSharedPref.edit().apply {
            remove(key)
            apply()
        }
    }

    fun save(key: String?, value: Boolean) {
        mSharedPref.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun getString(key: String?): String? {
        return mSharedPref.getString(key, "")
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return mSharedPref.getString(key, defaultValue)
    }

    fun getInt(key: String?): Int {
        return mSharedPref.getInt(key, -1)
    }

    fun getBoolean(key: String?): Boolean {
        return mSharedPref.getBoolean(key, false)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return mSharedPref.getBoolean(key, defaultValue)
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return mSharedPref.getInt(key, defaultValue)
    }

    fun getLong(key: String?): Long {
        return mSharedPref.getLong(key, -1)
    }

    fun getStringSet(key: String?): Set<String>? {
        return mSharedPref.getStringSet(key, HashSet())
    }


    fun setUserType(token: String) {
        mSharedPref.edit().apply {
            putString(KEY_USER_TYPE, token)
            apply()
        }
    }

    fun getUserType(): String? {
        return mSharedPref.getString(KEY_USER_TYPE, UserType.GUEST.name)
    }

    fun getToken(): String? {
        return mSharedPref.getString(KEY_TOKEN, null)
    }

    fun setToken(token: String) {
        mSharedPref.edit().apply {
            putString(KEY_TOKEN, token)
            apply()
        }
    }

    private fun setUserDetail(prefUser: PrefUser) {
        val gson = Gson()
        val userPrefGson = gson.toJson(prefUser)

        mSharedPref.edit().apply {
            putString(KEY_USER_INFO, userPrefGson)
            apply()
        }
    }

    fun getUserDetail(): TransactionParse? {
        val gson = Gson()
        val userPrefJson = mSharedPref.getString(KEY_USER_INFO, "")
        return gson.fromJson(userPrefJson, TransactionParse::class.java)
    }

    companion object {
        const val KEY_REMEMBER_DRIVER = "div_is_rem_driver"
        const val KEY_REMEMBER_MO = "div_is_rem_mo"

        const val KEY_EMAIL_DRIVER = "div_e_driver"
        const val KEY_EMAIL_MO = "div_e_mo"

        const val KEY_PASSWORD_DRIVER = "div_pwd_driver"
        const val KEY_PASSWORD_MO = "div_pwd_mo"

        private const val KEY_TOKEN = "div_token"
        private const val KEY_USER_TYPE = "div_user_type"
        private const val KEY_USER_INFO = "div_ui"
        const val KEY_LOGGED_IN = "div_is_logged_in"
    }

    init {
        mSharedPref = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

    //MO
    fun saveTransactionParse(
        res: TransactionParse,
        saveInfo: Boolean,
        email: String = "",
        password: String = ""
    ) {
        //val gson = Gson()
        //val userPrefGson = gson.toJson(prefUser)

        mSharedPref.edit().apply {
            putString(KEY_USER_INFO, "userPrefGson")
            apply()
        }
    }

    fun logout() {
        val rememberDriver = getBoolean(KEY_REMEMBER_DRIVER)
        val emailDriver = getString(KEY_EMAIL_DRIVER)
        val pwdDriver = getString(KEY_PASSWORD_DRIVER)

        val rememberMo = getBoolean(KEY_REMEMBER_MO)
        val emailMo = getString(KEY_EMAIL_MO)
        val pwdMo = getString(KEY_PASSWORD_MO)

        mSharedPref.edit().clear().apply()

        if (rememberMo) {
            save(KEY_REMEMBER_MO, rememberMo)
            save(KEY_EMAIL_MO, emailMo)
            save(KEY_PASSWORD_MO, pwdMo)
        }
        if (rememberDriver) {
            save(KEY_REMEMBER_DRIVER, rememberDriver)
            save(KEY_EMAIL_DRIVER, emailDriver)
            save(KEY_PASSWORD_DRIVER, pwdDriver)
        }
    }

}
*/
