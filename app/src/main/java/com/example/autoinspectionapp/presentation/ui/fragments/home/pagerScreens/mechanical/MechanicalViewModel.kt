package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.mechanical

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.MechanicalFunctionBO
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

    init {
        getData()
    }


    private val _mechanicalListDataStateFlow =
        MutableStateFlow<PagesDataState>(PagesDataState.Init)
    val mechanicalListDataStateFlow: StateFlow<PagesDataState> =
        _mechanicalListDataStateFlow.asStateFlow()

    private fun getData() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getMechanicalFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _mechanicalListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.MECHANICAL_FUNCTION,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }


}