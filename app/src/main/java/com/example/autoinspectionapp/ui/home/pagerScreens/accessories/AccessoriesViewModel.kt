package com.example.autoinspectionapp.ui.home.pagerScreens.accessories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.SparePartsFunctionBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccessoriesViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) :
    ViewModel() {
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
}