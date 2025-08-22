package com.example.autoinspectionapp.ui.home.pagerScreens.mechanical

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.MechanicalFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicalViewModel @Inject constructor(
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

    fun onNext(mechanicalFunctionBO: MechanicalFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $mechanicalFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertMechanicalFunction(mechanicalFunction = mechanicalFunctionBO.toEntity())
        }
    }

}