package com.example.autoinspectionapp.di

import com.example.autoinspectionapp.data.remote.AutoCarInspection
import com.example.autoinspectionapp.data.remote.BaseResponse
import com.example.autoinspectionapp.data.remote.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApiService {

    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResponse<AutoCarInspection>


}