package com.example.autoinspectionapp.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitApiService(@Named("defaultOkHttp") okHttpClient: OkHttpClient): RetrofitApiService {
        val baseUrl = "https://example.com/"
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build()
            .create(RetrofitApiService::class.java)
    }


    @Singleton
    @Provides
    @Named("defaultOkHttp")
    fun provideOkHttp(
        supportInterceptor: SupportInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            callTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            addInterceptor(networkConnectionInterceptor)
            addNetworkInterceptor { chain ->
                chain.proceed(chain.request())
            }.addInterceptor(supportInterceptor)
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            addInterceptor(loggingInterceptor)
        }.build()
    }


}
