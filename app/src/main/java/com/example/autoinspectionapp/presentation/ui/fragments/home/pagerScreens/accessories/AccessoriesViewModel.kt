package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.accessories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.SparePartsFunctionBO
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