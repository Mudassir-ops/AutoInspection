package com.example.autoinspectionapp.domain

import android.content.Context
import com.example.autoinspectionapp.domain.sealed.LoginState
import com.example.autoinspectionapp.domain.uimodels.LoginUi
import kotlinx.coroutines.flow.StateFlow

interface LoginRepository {

    val loginStateFlow: StateFlow<LoginState>
    suspend fun login(loginUi: LoginUi)
    suspend fun getDeviceSerialNumber(context: Context)

}