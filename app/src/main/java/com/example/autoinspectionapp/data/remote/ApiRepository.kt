package com.example.autoinspectionapp.data.remote

import com.example.autoinspectionapp.data.remote.models.AutoCarInspection
import com.example.autoinspectionapp.data.remote.models.LoginRequest
import com.example.autoinspectionapp.data.remote.sealed.ResultNew

interface ApiRepository {
    suspend fun login(loginRequest: LoginRequest): ResultNew<BaseResponse<AutoCarInspection>>
}