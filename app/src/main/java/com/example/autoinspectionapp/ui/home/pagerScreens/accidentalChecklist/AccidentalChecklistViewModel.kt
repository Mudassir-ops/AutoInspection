package com.example.autoinspectionapp.ui.home.pagerScreens.accidentalChecklist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.AccidentChecklistBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
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
}