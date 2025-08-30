package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.AccidentChecklistBO
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
class AccidentalChecklistViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf(
        "Non-Accidented",
        "Accidented",
        "Rusted",
        "Painted",
        "Minor Hit",
        "Welded",
        "Dent",
        "Replaced",
        "Repaired",
        "N/A"
    )

    fun onNext(accidentChecklistBO: AccidentChecklistBO) {
        Log.e("accidentChecklistBO", "onNext: $accidentChecklistBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertAccidentChecklist(accidentChecklistEntity = accidentChecklistBO.toEntity())
        }
    }
    init {
        getData()
    }


    private val _accidentalCheckListDataStateFlow =
        MutableStateFlow<PagesDataState>(PagesDataState.Init)
    val accidentalCheckListDataStateFlow: StateFlow<PagesDataState> =
        _accidentalCheckListDataStateFlow.asStateFlow()

    private fun getData() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getAccidentChecklistData().collect { data ->
                data?.let {
                    LogsHelper().createLog("data from DB Second = $data")
                    _accidentalCheckListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.ACCIDENTAL_CHECKLIST,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }

}