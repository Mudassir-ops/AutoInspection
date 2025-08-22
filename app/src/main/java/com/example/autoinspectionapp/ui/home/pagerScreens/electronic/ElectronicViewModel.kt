package com.example.autoinspectionapp.ui.home.pagerScreens.electronic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElectronicViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf(
        "Normal",
        "Working",
        "Responsive",
        "Fabric",
        "Leather",
        "Partial Leather",
        "Automatic",
        "Manual",
        "Clean",
        "Original",
        "Not Original",
        "Damaged",
        "Repaired",
        "Rusted",
        "Malfunction",
        "Not Working",
        "Scratches",
        "Service Required",
        "Unclean",
        "Holes",
        "Wornout",
        "Wrinkles",
        "Noisey",
        "Hard",
        "N/A"
    )

    fun onNext(electricalSafetyFunctionBO: ElectricalSafetyFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $electricalSafetyFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertElectricalSafetyFunctionEntity(electricalSafetyFunction = electricalSafetyFunctionBO.toEntity())
        }
    }
}