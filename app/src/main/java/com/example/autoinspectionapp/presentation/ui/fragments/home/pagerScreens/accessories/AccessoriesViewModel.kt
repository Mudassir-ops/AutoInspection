package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accessories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.SparePartsFunctionBO
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
class AccessoriesViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    val spinnerList = listOf(
        "Present",
        "Not Present",
        "Poor ",
        "N/A"
    )

    fun onNext(sparePartsFunctionBO: SparePartsFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $sparePartsFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertSparePartsFunctionEntity(
                sparePartsFunction = sparePartsFunctionBO.toEntity()
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
            autoCarInspectionDbRepo.getSparePartsFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _dataListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.ACCESSORIES,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }
}