package com.example.autoinspectionapp.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectionInterceptor @Inject constructor(@ApplicationContext val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context)) {
            val dummyResponse = Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(503)
                .message("No Internet Connection")
                .body("{\"error\":\"No Internet Connection\",\"statusCode\":503}".toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
            return dummyResponse
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return try {
            // For interceptors, use a fast simple check - this runs on EVERY HTTP request
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            // Simple, fast connectivity check - let the complex multi-network logic stay in ConnectivityHelper
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } catch (e: Exception) {
            false
        }
    }
}
