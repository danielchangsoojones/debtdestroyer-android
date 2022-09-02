package com.debtdestroyer.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class DebtDestroyerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        } else {
            plant(CrashReportingTree())
        }
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