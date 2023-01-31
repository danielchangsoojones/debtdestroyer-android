package com.debtdestroyer.android

import android.app.Application
import com.debtdestroyer.android.model.*
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
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
    val DAYS_FOR_FLEXIBLE_UPDATE = 5

    lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate() {
        super.onCreate()
        //inAppUpdate()
        setUpParse()
        setUpTimber()
    }

    private fun inAppUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this) // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            // This example applies an immediate update. To apply a flexible update // instead, pass in AppUpdateType.FLEXIBLE
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

            }
            //Use clientVersionStalenessDays() to check the number of days since the update became available on the Play Store:
            /*if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= DAYS_FOR_FLEXIBLE_UPDATE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                // Request the update.
            }*/
        }
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
        ParseObject.registerSubclass(QuizDataParse::class.java)
        ParseObject.registerSubclass(QuizTopicParse::class.java)

        ParseObject.registerSubclass(QuizScoreParse::class.java)
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
                .applicationId(BuildConfig.P_APP_ID) // if defined
                .clientKey(BuildConfig.P_CLIENT_ID)
                .server(BuildConfig.SERVER_ENDPOINT)
                .clientBuilder(httpsClient)
                .build()
        )
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