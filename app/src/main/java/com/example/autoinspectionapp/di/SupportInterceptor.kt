package com.example.autoinspectionapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SupportInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("X-App-Version", "1.0.0")
            .build()
        return chain.proceed(requestWithHeaders)
    }
}
