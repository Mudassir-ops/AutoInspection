package com.example.autoinspectionapp.domain.remote.repository

import android.content.Context
import com.example.autoinspectionapp.domain.sealed.LoginState
import com.example.autoinspectionapp.presentation.uimodels.LoginUi
import kotlinx.coroutines.flow.StateFlow

interface LoginRepository {

    val loginStateFlow: StateFlow<LoginState>
    suspend fun login(loginUi: LoginUi)
    suspend fun getDeviceSerialNumber(context: Context)

}