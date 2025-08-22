package com.example.autoinspectionapp.ui.home.pagerScreens.suspension

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.SuspensionSteeringFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuspensionViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf(
        "Not Present",
        "Normal",
        "Smooth ",
        "Working",
        "Not Working",
        "Present",
        "Seepage",
        "Abnormal",
        "Jerky",
        "Noisey",
        "N/A"
    )

    fun onNext(suspensionSteeringFunctionBO: SuspensionSteeringFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $suspensionSteeringFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertSuspensionSteeringFunctionEntity(
                suspensionSteeringFunction = suspensionSteeringFunctionBO.toEntity()
            )
        }
    }
}