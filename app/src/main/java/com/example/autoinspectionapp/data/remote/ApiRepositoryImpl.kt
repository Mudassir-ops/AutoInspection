package com.example.autoinspectionapp.data.remote

import com.example.autoinspectionapp.data.remote.sealed.ResultNew
import com.example.autoinspectionapp.di.RetrofitApiService

class ApiRepositoryImpl(private val apiService: RetrofitApiService) : NetworkCall(), ApiRepository {

    override suspend fun login(loginRequest: LoginRequest): ResultNew<BaseResponse<AutoCarInspection>> {
        return safeApiCall {
            apiService.login(loginRequest)
        }
    }

}