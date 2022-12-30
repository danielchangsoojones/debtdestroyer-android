package com.debtdestroyer.android

import android.app.Application
import com.debtdestroyer.android.model.*
import com.parse.Parse
import com.parse.ParseObject
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant
import timber.log.Timber.Tree
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class DebtDestroyerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpParse()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)
            plant(DebugTree())
        } else {
            //plant(CrashReportingTree())
        }
    }

    private fun setUpParse() {
        ParseObject.registerSubclass(User::class.java)
        ParseObject.registerSubclass(SweepParse::class.java)
        ParseObject.registerSubclass(TransactionParse::class.java)

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val httpsClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)


        Parse.initialize(
            Parse.Configuration.Builder(this)
                .clientBuilder(httpsClient)
                .applicationId(BuildConfig.P_APP_ID) // if defined
                .clientKey(BuildConfig.P_CLIENT_ID)
                .server(BuildConfig.SERVER_ENDPOINT)
                .build()
        )
    }

    private fun setUpParseQueryClient() {

    }

    private class CrashReportingTree : Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            /* if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                 return
             }
             FakeCrashLibrary.log(priority, tag, message)
             if (t != null) {
                 if (priority == Log.ERROR) {
                     FakeCrashLibrary.logError(t)
                 } else if (priority == Log.WARN) {
                     FakeCrashLibrary.logWarning(t)
                 }
             }*/
        }
    }
}