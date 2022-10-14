package com.debtdestroyer.android.di

import android.content.Context
import com.debtdestroyer.android.BuildConfig
import com.debtdestroyer.android.model.SuperParseObject
import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideUser() = User()

    @Provides
    @Singleton
    fun provideParseObject() = SuperParseObject()

    @Provides
    @Singleton
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder =
        if (BuildConfig.DEBUG) { // debug ON
            //Create a new Interceptor.
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)

        } else {
            // Live mode
            OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
        }
}