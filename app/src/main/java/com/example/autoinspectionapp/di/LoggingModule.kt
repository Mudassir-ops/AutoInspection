package com.example.autoinspectionapp.di

import com.example.autoinspectionapp.domain.LogsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggingModule {

    @Singleton
    @Provides
    fun provideLogs(): LogsHelper = LogsHelper()
}