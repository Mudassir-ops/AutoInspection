package com.example.autoinspectionapp.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LoginRepository
import com.example.autoinspectionapp.domain.sealed.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginState: StateFlow<LoginState> = loginRepository.loginStateFlow
    val serialNumber = MutableStateFlow("")

    fun getDeviceSerialNumber(context: Context) {
        viewModelScope.launch {
            loginRepository.getDeviceSerialNumber(context = context)
        }
    }

}