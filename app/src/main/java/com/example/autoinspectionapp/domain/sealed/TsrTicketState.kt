package com.example.autoinspectionapp.domain.sealed

sealed interface LoginState {
    data object Init : LoginState
    data object Loading : LoginState
    data class DeviceSerialNumber(val serialNumber: String) : LoginState
    data class Error(val errorMessage: String) : LoginState
    data object Success : LoginState
}
