package com.example.autoinspectionapp.ui.home.pagerScreens.testDrive

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.domain.TestDriveInspectionBo
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestDriveViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf("OK", "Tunning Required ", "Low", "N/A")
    val spinnerGyreShiList = listOf("Smooth", "Jerk ", "N/A")
    val spinnerList3rd = listOf("Noisey", "Noisey ", "N/A")
    val spinnerList4th = listOf("Not Working", "Delayed Response", "Timely Response", "N/A")
    val spinnerList7th = listOf("Not Present", "Present", "N/A")
    val spinnerSteeringList = listOf("smooth", "Noisey", "Play", "Service Required", "N/A")
    val spinnerList5th = listOf("Centered", "Not Centered", "Play", "Service Required", "N/A")
    val spinnerList6th = listOf("Working", "Not Working", "Malfunction", "N/A")

    fun onNext(testDriveInspectionBo: TestDriveInspectionBo) {
        Log.e("electricalSafetyFunctionBO", "onNext: $testDriveInspectionBo")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertTestDriveInspectionEntity(testDriveInspectionEntity = testDriveInspectionBo.toEntity())
        }
    }

}