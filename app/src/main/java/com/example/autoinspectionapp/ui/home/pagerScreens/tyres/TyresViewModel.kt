package com.example.autoinspectionapp.ui.home.pagerScreens.tyres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.TyreFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
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