package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accidentalChecklist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.AccidentChecklistBO
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