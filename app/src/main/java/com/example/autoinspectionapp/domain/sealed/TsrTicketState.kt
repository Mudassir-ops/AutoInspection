package com.example.autoinspectionapp.domain.sealed

sealed interface LoginState {
    data object Init : LoginState
    data class DeviceSerialNumber(val serialNumber: String) : LoginState
    data class Login(val useEmail: String, val userPwd: String) : LoginState

}
