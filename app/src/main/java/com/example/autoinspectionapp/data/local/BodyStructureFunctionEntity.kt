package com.example.autoinspectionapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_structure_function_table")
data class BodyStructureFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val trunkLock: String,
    val frontDriverFender: String,
    val bonnet: String,
    val frontWindshield: String,
    val frontPassengerFender: String,
    val frontPassengerDoor: String,
    val rearPassengerDoor: String,
    val rearPassengerFender: String,
    val trunk: String,
    val rearWindshield: String,
    val rearDriverFender: String,
    val rearDriverDoor: String,
    val frontDriverDoor: String,
    val roof: String,
    val driverAPillar: String,
    val driverBPillar: String,
    val driverCPillar: String,
    val driverDPillar: String,
    val passengerAPillar: String,
    val passengerBPillar: String,
    val passengerCPillar: String,
    val passengerDPillar: String
)