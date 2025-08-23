package com.example.autoinspectionapp.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.autoinspectionapp.data.local.repository.SessionManagerRepoImpl
import com.example.autoinspectionapp.utils.AppConstants
import com.example.autoinspectionapp.domain.local.repository.SessionManagerRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSessionManagerRepository(sharedPreferences: SharedPreferences): SessionManagerRepo {
        return SessionManagerRepoImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
}
