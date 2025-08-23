package com.example.autoinspectionapp.data.remote.apiService

import com.example.autoinspectionapp.data.remote.BaseResponse
import com.example.autoinspectionapp.data.remote.models.AutoCarInspection
import com.example.autoinspectionapp.data.remote.models.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApiService {

    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResponse<AutoCarInspection>

}