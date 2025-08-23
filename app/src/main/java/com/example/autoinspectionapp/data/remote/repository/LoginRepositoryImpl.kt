package com.example.autoinspectionapp.data.remote.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.example.autoinspectionapp.data.remote.ApiRepository
import com.example.autoinspectionapp.data.remote.models.AutoCarInspection
import com.example.autoinspectionapp.data.remote.sealed.ResultNew
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.mappers.toRequest
import com.example.autoinspectionapp.domain.remote.repository.LoginRepository
import com.example.autoinspectionapp.domain.sealed.LoginState
import com.example.autoinspectionapp.presentation.uimodels.LoginUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginRepositoryImpl(
    private val apiRepository: ApiRepository,
    private val logsHelper: LogsHelper
) : LoginRepository {

    private val _loginStateFlow = MutableStateFlow<LoginState>(LoginState.Init)
    override val loginStateFlow: StateFlow<LoginState> = _loginStateFlow.asStateFlow()

    override suspend fun login(loginUi: LoginUi) {
        _loginStateFlow.emit(LoginState.Loading)
        delay(1500)
        _loginStateFlow.emit(LoginState.Success)
        return
        val result = apiRepository.login(loginRequest = loginUi.toRequest())
        when (result) {
            is ResultNew.Error<*> -> {
                _loginStateFlow.emit(LoginState.Error(errorMessage = result.error?.message.toString()))
            }

            is ResultNew.Success<*> -> {
                val data = result.data?.data ?: AutoCarInspection()
                logsHelper.createLog("login: $data")
                _loginStateFlow.emit(LoginState.Success)
            }
        }
    }


    @SuppressLint("HardwareIds")
    override suspend fun getDeviceSerialNumber(context: Context) {
        val serialNumber = getSerialNumber() ?: Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        _loginStateFlow.emit(LoginState.DeviceSerialNumber(serialNumber = serialNumber ?: ""))
    }


    @SuppressLint("PrivateApi")
    fun getSerialNumber(): String? {
        var serialNumber: String?
        try {
            val c = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)

            serialNumber = get.invoke(c, "gsm.sn1") as String
            if (serialNumber == "") {
                serialNumber = get.invoke(c, "ril.serialnumber") as String
            }
            if (serialNumber == "") {
                serialNumber = get.invoke(c, "ro.serialno") as String
            }
            if (serialNumber == "") {
                serialNumber = get.invoke(c, "sys.serialnumber") as String
            }
            if (serialNumber == "") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    serialNumber = Build.getSerial()
                }
            }
            // If none of the methods above worked
            if (serialNumber == "") {
                serialNumber = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            serialNumber = null
        }
        return serialNumber
    }

}