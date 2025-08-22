package com.example.autoinspectionapp.ui.home.pagerScreens.exterior

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.BodyStructureFunctionBO
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.sealed.BodyStrctureState
import com.example.autoinspectionapp.domain.toEntity
import com.example.autoinspectionapp.domain.toPartUiList
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExteriorViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf(
        "Not Present",
        "Normal",
        "Smooth ",
        "Working",
        "Not Working",
        "Present",
        "Seepage",
        "Abnormal",
        "Jerky",
        "Noisey",
        "N/A"
    )
    private val partNames = listOf(
        "bonnet",
        "frontBumper",
        "frontPassengerDoor",
        "frontDriverFender",
        "frontWindShield",
        "frontPassengerFender",
        "rearPassengerDoor",
        "rearPassengerFender",
        "trunk",
        "rearWindShield",
        "rearDriverFender",
        "rearDriverDoor",
        "frontDriverDoor",
        "roof",
        "backBumper",
        "passengerFootBoard",
        "driverFootBoard"
    )
    val partUiMap: Map<String, ObservableField<String>> =
        partNames.associateWith { ObservableField("") }


    init {
        viewModelScope.launch {
            getExteriorBodyPart()
        }
    }

    private val _bodyPartsAdapterStateFlow =
        MutableStateFlow<BodyStrctureState>(BodyStrctureState.Init)
    val bodyPartsAdapterStateFlow: StateFlow<BodyStrctureState> =
        _bodyPartsAdapterStateFlow.asStateFlow()


    private fun getExteriorBodyPart() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getBodyExterior().collect { summary ->
                summary.let {
                    LogsHelper().createLog("getBodyExterior--${Gson().toJson(summary)}")
                    val partsList = summary?.toPartUiList()
                    _bodyPartsAdapterStateFlow.emit(
                        BodyStrctureState.Data(
                            partsData = partsList ?: listOf()
                        )
                    )
                }
            }
        }
    }

    fun onNext(
        bodyStructureFunctionBO: BodyStructureFunctionBO,
        onInsertionComplete: (Result<Unit>) -> Unit
    ) {
        Log.e("accidentChecklistBO", "onNext: $bodyStructureFunctionBO")
        viewModelScope.launch {
            try {
                autoCarInspectionDbRepo.insertBodyStructureFunctionEntity(
                    bodyStructureFunction = bodyStructureFunctionBO.toEntity()
                )
                onInsertionComplete(Result.success(Unit))
            } catch (e: Exception) {
                Log.e("onNext", "Insertion failed", e)
                onInsertionComplete(Result.failure(e))
            }
        }
    }

}