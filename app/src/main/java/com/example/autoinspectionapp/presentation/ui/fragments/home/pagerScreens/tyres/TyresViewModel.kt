package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.TyreFunctionBO
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
class TyresViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    val tyresSpinner = listOf(
        "Good",
        "Average",
        "Poor ",
        "N/A"
    )
    val alloyRimsSpinner = listOf(
        "Yes",
        "No"
    )

    fun onNext(tyreFunctionBO: TyreFunctionBO) {
        Log.e("tyreFunctionBO", "onNext: $tyreFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertTyreFunctionEntity(
                tyreFunction = tyreFunctionBO.toEntity()
            )
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
            autoCarInspectionDbRepo.getTyreFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _dataListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.TYRES,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }

}