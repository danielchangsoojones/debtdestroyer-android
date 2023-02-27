package com.debtdestroyer.android.di

import android.app.Application
import android.content.Context
import com.debtdestroyer.android.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    companion object {
        @Provides
        @Singleton
        fun provideCoroutineScope() =
            CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext application: Application): Context {
        return application
    }

    /*

    @Singleton
    @Provides
    fun provideDateFormatter(): SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)

    @Singleton
    @Provides
    fun provideCalendar(): Calendar = Calendar.getInstance()


     @Provides
      @Singleton
      fun provideSharedPreferences(
          context: Context
      ): SharedPreferences {
          return context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
      }
  */

}