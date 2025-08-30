package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.electronic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.utils.enums.Section
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElectronicViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    val batteryOptions: List<String> = listOf(
        "Normal",
        "Weak",
        "Need to Replace",
        "N/A"
    )

    val hornOptions: List<String> = listOf(
        "Working",
        "Not Working",
        "Missing",
        "N/A"
    )

    val rightHeadlightOperationOptions: List<String> = listOf(
        "Working",
        "Not Working",
        "N/A"
    )

    val rightHeadlightConditionOptions: List<String> = listOf(
        "Normal",
        "Scratched",
        "Faded",
        "Repaired",
        "Broken",
        "Cracked",
        "Chipped",
        "Wrapped",
        "N/A"
    )

    val rightHeadlightOriginalOptions: List<String> = listOf(
        "Original",
        "Not Original",
        "After Market",
        "N/A"
    )

    val fogsLightOptions: List<String> = listOf(
        "Working",
        "Not Working",
        "1 Working",
        "Broken",
        "Faded",
        "N/A"
    )

    val airBagOptions: List<String> = listOf(
        "Present",
        "Deployed",
        "Missing",
        "Replaced",
        "N/A"
    )

    val checkLightOptions: List<String> = listOf(
        "Off",
        "On",
        "Bypassed",
        "N/A"
    )

    fun onNext(electricalSafetyFunctionBO: ElectricalSafetyFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $electricalSafetyFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertElectricalSafetyFunctionEntity(electricalSafetyFunction = electricalSafetyFunctionBO.toEntity())
        }
    }

    init {
        getData()
    }

    private val _dataListDataStateFlow =
        MutableStateFlow<PagesDataState>(PagesDataState.Init)
    val dataListDataStateFlow: StateFlow<PagesDataState> =
        _dataListDataStateFlow.asStateFlow()

    private fun getData() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getElectricalSafetyFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _dataListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.ELECTRONIC_FUNCTION,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }

}