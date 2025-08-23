package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.acHeate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.ACHeaterFunctionBO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcHeaterViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    val spinnerList = listOf(
        "Yes",
        "Working",
        "Normal",
        "No",
        "Not Working",
        "Low",
        "N/A"
    )

    fun onNext(acHeaterFunctionBO: ACHeaterFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $acHeaterFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertACHeaterFunctionEntity(acHeaterFunction = acHeaterFunctionBO.toEntity())
        }
    }

}