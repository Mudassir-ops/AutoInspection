package com.example.autoinspectionapp.domain

data class TestDriveInspectionBo(
    val enginePick: String,
    val gearShifting: String,
    val differentialNoise: String,
    val driveShaftNoise: String,
    val absActuation: String,
    val brakePedalOperation: String,
    val frontSuspensionNoise: String,
    val rearSuspensionNoise: String,
    val steeringFunction: String,
    val steeringWheelAlignment: String,
    val speedometerInformationCluster: String,
    val testDriveDoneBy: String
)
