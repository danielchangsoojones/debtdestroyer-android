package com.debtdestroyer.android.di

import com.debtdestroyer.android.model.User
import com.debtdestroyer.android.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(user: User): MainRepository {
        return MainRepository(user)
    }

}