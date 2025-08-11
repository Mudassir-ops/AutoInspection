package com.example.autoinspectionapp.domain

import android.content.Context
import com.example.autoinspectionapp.domain.sealed.LoginState
import kotlinx.coroutines.flow.StateFlow

interface LoginRepository {

    val loginStateFlow: StateFlow<LoginState>
    suspend fun login()
    suspend fun getDeviceSerialNumber(context: Context)
}