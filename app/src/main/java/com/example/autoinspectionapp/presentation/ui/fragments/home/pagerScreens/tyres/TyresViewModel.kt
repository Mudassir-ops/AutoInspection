package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.TyreFunctionBO
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onNext(tyreFunctionBO: TyreFunctionBO) {
        Log.e("tyreFunctionBO", "onNext: $tyreFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertTyreFunctionEntity(
                tyreFunction = tyreFunctionBO.toEntity()
            )
        }
    }

}