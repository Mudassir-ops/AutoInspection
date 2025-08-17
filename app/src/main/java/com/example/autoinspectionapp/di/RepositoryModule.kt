package com.example.autoinspectionapp.di


import com.example.autoinspectionapp.data.local.AutoCarInspectionDao
import com.example.autoinspectionapp.data.local.AutoCarInspectionDbRepoImpl
import com.example.autoinspectionapp.data.remote.ApiRepository
import com.example.autoinspectionapp.data.remote.ApiRepositoryImpl
import com.example.autoinspectionapp.data.repository.LoginRepositoryImpl
import com.example.autoinspectionapp.domain.LoginRepository
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
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