package com.example.autoinspectionapp.di


import com.example.autoinspectionapp.data.local.AutoCarInspectionDao
import com.example.autoinspectionapp.data.local.AutoCarInspectionDbRepoImpl
import com.example.autoinspectionapp.data.repository.LoginRepositoryImpl
import com.example.autoinspectionapp.domain.AutoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.LoginRepository
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
    fun provideLoginRepository(): LoginRepository = LoginRepositoryImpl()


    @Provides
    @Singleton
    fun provideDbRepository(dao: AutoCarInspectionDao): AutoCarInspectionDbRepo =
        AutoCarInspectionDbRepoImpl(dao = dao)

}