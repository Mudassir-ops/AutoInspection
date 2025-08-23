package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interior_control_function_table")
data class InteriorControlFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val steeringWheelWearTear: String,
    val powerSteering: String,
    val steeringWheelButtons: String,
    val lightsLeverSwitch: String,
    val dashboardScratches: String,
    val dashControlButtons: String,
    val interiorLights: String,
    val defogger: String,
    val hazardLights: String,
    val multimedia: String,
    val rearViewCamera: String,
    val frontViewCamera: String,
    val trunkRelease: String,
    val doorSkirts: String,
    val fuelCapReleaseLever: String,
    val bonnetReleaseLever: String,
    val sideViewMirrorAdjustment: String,
    val leftSideViewMirror: String,
    val rightSideViewMirror: String,
    val retractingSideViewMirrors: String,
    val acGrills: String,
    val acceleratorPedal: String,
    val brakePedal: String,
    val clutchPedal: String,
    val sunroof: String,
    val seatsType: String,
    val seatsCondition: String,
    val driverSeatbelt: String,
    val passengerSeatbelt: String,
    val windowsType: String,
    val frontDriverWindow: String,
    val frontPassengerWindow: String,
    val rearDriverSideWindow: String,
    val rearPassengerSideWindow: String,
    val windowSafetyLockButton: String,
    val centralLocking: String,
    val keyButtons: String,
    val floorMats: String,
    val frontDriverDoorSeal: String,
    val frontPassengerDoorSeal: String,
    val rearDriverSideDoorSeal: String,
    val rearPassengerSideDoorSeal: String,
    val bonnetSeal: String,
    val trunkSeal: String
)