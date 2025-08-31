package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.acHeate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.ACHeaterFunctionBO
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
class AcHeaterViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    private val _acHeaterListDataStateFlow =
        MutableStateFlow<PagesDataState>(PagesDataState.Init)
    val acHeaterListDataStateFlow: StateFlow<PagesDataState> =
        _acHeaterListDataStateFlow.asStateFlow()

    val spinnerAcInstalled = listOf(
        "Yes",
        "No",
        "N/A"
    )
    val spinnerAcFan = listOf(
        "Working",
        " Not Working",
        "N/A"
    )

    val spinnerBlowThrow = listOf(
        "Normal",
        "Low",
        "Not Working",
        "N/A"
    )

    val spinnerAcCooling = listOf(
        "Normal",
        "Low",
        "Not Working",
        "N/A"
    )

    val spinnerHeater = listOf(
        "Working",
        "Not Working",
        "N/A"
    )

    fun onNext(acHeaterFunctionBO: ACHeaterFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $acHeaterFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertACHeaterFunctionEntity(acHeaterFunction = acHeaterFunctionBO.toEntity())
        }
    }


    init {
        getData()
    }


    private fun getData() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getACHeaterFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _acHeaterListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.AC_HEATER_OPERATION,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }
}