package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_drive_inspection")
data class TestDriveInspectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
