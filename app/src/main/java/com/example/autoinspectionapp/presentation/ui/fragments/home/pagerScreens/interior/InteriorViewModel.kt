package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.interior

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.InteriorControlFunctionBO
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
class InteriorViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    // Steering Wheel Wear And Tear
    val inputSteeringWheelWearTearSpinner = listOf("Normal", "Slightly Wornout", "Scratched", "N/A")

    // Power Steering
    val inputPowerSteeringSpinner = listOf("Working", "Noisey", "N/A")

    // Steering Wheel Buttons
    val inputSteeringWheelButtonsSpinner =
        listOf("Working", "Scratched", "Faded", "Not Working", "Malfunction", "N/A")

    // Lights Lever / Switch
    val inputLightsLeverSwitchSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Dashboard Scratches
    val inputDashboardScratchesSpinner =
        listOf("Normal", "Faded", "Scratched", "Repaired", "Broken", "Damaged", "N/A")

    // Dash Control Buttons
    val inputDashControlButtonsSpinner =
        listOf("Working", "Not Working", "Faded", "Scratched", "Malfunction", "N/A")

    // Interior Lights
    val inputInteriorLightsSpinner =
        listOf("Working", "Not Working", "Broken", "Malfunction", "N/A")

    // De-fogger
    val inputDefoggerSpinner = listOf("Working", "Not Working", "N/A")

    // Hazard Lights
    val inputHazardLightsSpinner = listOf("Working", "Not Working", "Button Broken", "N/A")

    // Multimedia
    val inputMultimediaSpinner =
        listOf("Working", "Not Working", "N/A", "Screen Broken", "Malfunction", "N/A")

    // Rear View Camera
    val inputRearViewCameraSpinner = listOf("Working", "Not Working", "N/A")

    // Front View Camera
    val inputFrontViewCameraSpinner = listOf("Working", "Not Working", "N/A")

    // Trunk Release
    val inputTrunkReleaseSpinner = listOf("Working", "Not Working", "N/A")

    // Door Skirts
    val inputDoorSkirtsSpinner = listOf("Normal", "Scratched", "Faded", "Broken", "N/A")

    // Fuel Cap Release Lever
    val inputFuelCapReleaseLeverSpinner = listOf("Working", "Not Working", "Broken", "N/A")

    // Bonnet Release Lever
    val inputBonnetReleaseLeverSpinner = listOf("Working", "Not Working", "Broken", "N/A")

    // Side-view Mirror Adjustment
    val inputSideViewMirrorAdjustmentSpinner =
        listOf("Working", "Not Working", "Malfunction", "N/A")

    // Left Side-view Mirror
    val inputLeftSideViewMirrorSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Right Side-view Mirror
    val inputRightSideViewMirrorSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Retracting Side-view Mirrors
    val inputRetractingSideViewMirrorsSpinner =
        listOf("Working", "Not Working", "Malfunction", "N/A")

    // A/C Grills
    val inputACGrillsSpinner = listOf("Normal", "Broken", "Loose", "Noisey", "N/A")

    // Accelerator Pedal
    val inputAcceleratorPedalSpinner =
        listOf("Responsive", "Hard", "Need Adjustment", "Require Service", "N/A")

    // Brake Pedal
    val inputBrakePedalSpinner =
        listOf("Responsive", "Hard", "Need Adjustment", "Require Service", "N/A")

    // Clutch Pedal
    val inputClutchPedalSpinner =
        listOf("Responsive", "Hard", "Need Adjustment", "Require Service", "N/A")

    // Sunroof
    val inputSunroofSpinner =
        listOf("Working", "Not Working", "Malfunction", "Noisey", "N/A")

    // Seats Type
    val inputSeatsTypeSpinner = listOf("Leather", "Fabric", "Partial Leather", "N/A")

    // Seats Condition
    val inputSeatsConditionSpinner =
        listOf("Clean", "Unclean", "Holes", "Wrinkles", "Worn Out", "N/A")

    // Driver Seatbelt
    val inputDriverSeatbeltSpinner = listOf("Working", "Not Working", "Broken", "Missing", "N/A")

    // Passenger Seatbelt
    val inputPassengerSeatbeltSpinner = listOf("Working", "Not Working", "Broken", "Missing", "N/A")

    // Windows Type
    val inputWindowsTypeSpinner = listOf("Automatic", "Manual", "N/A")

    // Front Driver Window
    val inputFrontDriverWindowSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Front Passenger Window
    val inputFrontPassengerWindowSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Rear Driver Side Window
    val inputRearDriverSideWindowSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Rear Passenger Side Window
    val inputRearPassengerSideWindowSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Window Safety Lock Button
    val inputWindowSafetyLockButtonSpinner = listOf("Working", "Not Working", "Malfunction", "N/A")

    // Central Locking
    val inputCentralLockingSpinner = listOf("Working", "Not Working", "N/A")

    // Key Buttons
    val inputKeyButtonsSpinner = listOf("Working", "Not Working", "N/A")

    // Floor Mats
    val inputFloorMatsSpinner = listOf("Present", "Not Present", "N/A")

    // Front Driver Door Seal
    val inputFrontDriverDoorSealSpinner = listOf(
        "Original",
        "Not Original",
        "Missing",
        "Damaged",
        "Repaired",
        "Rusted",
        "Cracked",
        "N/A"
    )

    // Front Passenger Door Seal
    val inputFrontPassengerDoorSealSpinner = listOf(
        "Original",
        "Not Original",
        "Missing",
        "Damaged",
        "Repaired",
        "Rusted",
        "Cracked",
        "N/A"
    )

    // Rear Driver Side Door Seal
    val inputRearDriverSideDoorSealSpinner = listOf(
        "Original",
        "Not Original",
        "Missing",
        "Damaged",
        "Repaired",
        "Rusted",
        "Cracked",
        "N/A"
    )

    // Rear Passenger Side Door Seal
    val inputRearPassengerSideDoorSealSpinner = listOf(
        "Original",
        "Not Original",
        "Missing",
        "Damaged",
        "Repaired",
        "Rusted",
        "Cracked",
        "N/A"
    )

    fun onNext(interiorControlFunctionBO: InteriorControlFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $interiorControlFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertInteriorControlFunctionEntity(interiorControlFunction = interiorControlFunctionBO.toEntity())
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
            autoCarInspectionDbRepo.getInteriorControlFunctionData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _dataListDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.INTERIOR,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }
}