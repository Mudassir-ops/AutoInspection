package com.example.autoinspectionapp.data.remote.repository

import com.example.autoinspectionapp.data.remote.ApiRepository
import com.example.autoinspectionapp.data.remote.BaseResponse
import com.example.autoinspectionapp.data.remote.NetworkCall
import com.example.autoinspectionapp.data.remote.apiService.RetrofitApiService
import com.example.autoinspectionapp.data.remote.models.AutoCarInspection
import com.example.autoinspectionapp.data.remote.models.LoginRequest
import com.example.autoinspectionapp.data.remote.sealed.ResultNew

class ApiRepositoryImpl(private val apiService: RetrofitApiService) : NetworkCall(), ApiRepository {

    override suspend fun login(loginRequest: LoginRequest): ResultNew<BaseResponse<AutoCarInspection>> {
        return safeApiCall {
            apiService.login(loginRequest)
        }
    }

}