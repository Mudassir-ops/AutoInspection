package com.example.autoinspectionapp.ui.home.pagerScreens.interior

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.InteriorControlFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InteriorViewModel @Inject constructor(
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

    fun onNext(interiorControlFunctionBO: InteriorControlFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $interiorControlFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertInteriorControlFunctionEntity(interiorControlFunction = interiorControlFunctionBO.toEntity())
        }
    }
}