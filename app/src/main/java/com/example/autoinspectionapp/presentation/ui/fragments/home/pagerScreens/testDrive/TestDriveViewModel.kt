package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.testDrive

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.TestDriveInspectionBo
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
class TestDriveViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    val spinnerList = listOf("OK", "Tunning Required ", "Low", "N/A")
    val spinnerGyreShiList = listOf("Smooth", "Jerk ", "N/A")
    val spinnerList3rd = listOf("No", "Noisey ", "N/A")
    val spinnerList4th = listOf("Timely Response", "Not Working", "Delayed Response", "N/A")
    val breakPedalSpinner = listOf("Timely Response", "Service Required", "Noisey", "N/A")
    val spinnerList7th = listOf("Not Present", "Present", "N/A")
    val spinnerSteeringList = listOf("smooth", "Noisey", "Play", "Service Required", "N/A")
    val spinnerList5th = listOf("Centered", "Not Centered", "N/A")
    val spinnerList6th = listOf("Working", "Not Working", "Malfunction", "N/A")

//    last index
//    Inspector G
//    Name xyz G
//    Not Taken R

    fun onNext(testDriveInspectionBo: TestDriveInspectionBo) {
        Log.e("electricalSafetyFunctionBO", "onNext: $testDriveInspectionBo")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertTestDriveInspectionEntity(testDriveInspectionEntity = testDriveInspectionBo.toEntity())
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
            autoCarInspectionDbRepo.getTestDriveInspectionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _dataListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.TEST_DRIVE,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }
}