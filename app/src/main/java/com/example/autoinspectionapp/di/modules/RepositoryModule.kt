package com.example.autoinspectionapp.di.modules


import com.example.autoinspectionapp.data.local.dao.AutoCarInspectionDao
import com.example.autoinspectionapp.data.local.repository.AutoCarInspectionDbRepoImpl
import com.example.autoinspectionapp.data.remote.ApiRepository
import com.example.autoinspectionapp.data.remote.apiService.RetrofitApiService
import com.example.autoinspectionapp.data.remote.repository.ApiRepositoryImpl
import com.example.autoinspectionapp.data.remote.repository.LoginRepositoryImpl
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.remote.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiRepository: ApiRepository,
        logsHelper: LogsHelper
    ): LoginRepository =
        LoginRepositoryImpl(apiRepository = apiRepository, logsHelper = logsHelper)


    @Provides
    @Singleton
    fun provideDbRepository(dao: AutoCarInspectionDao): AutoCarInspectionDbRepo =
        AutoCarInspectionDbRepoImpl(dao = dao)

    @Provides
    @Singleton
    fun provideApiRepository(retrofitApiService: RetrofitApiService): ApiRepository {
        return ApiRepositoryImpl(retrofitApiService)
    }

}