package com.example.autoinspectionapp.ui.home.pagerScreens.exterior

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.data.local.BodyStructureFunctionEntity
import com.example.autoinspectionapp.domain.BodyStructureFunctionBO
import com.example.autoinspectionapp.domain.SuspensionSteeringFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExteriorViewModel @Inject constructor(
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

    fun onNext(bodyStructureFunctionBO: BodyStructureFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $bodyStructureFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertBodyStructureFunctionEntity(
                bodyStructureFunction = bodyStructureFunctionBO.toEntity()
            )
        }
    }

}