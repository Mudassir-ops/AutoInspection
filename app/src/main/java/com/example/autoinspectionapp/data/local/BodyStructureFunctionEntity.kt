package com.example.autoinspectionapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.autoinspectionapp.domain.PartDamageSummary

@Entity(tableName = "body_structure_function_table")
data class BodyStructureFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val trunkLock: String,
    val frontDriverFender: PartDamageSummary?,
    val bonnet: PartDamageSummary?,
    val frontBumper: PartDamageSummary?,
    val frontWindshield: PartDamageSummary?,
    val frontPassengerFender: PartDamageSummary?,
    val frontPassengerDoor: PartDamageSummary?,
    val rearPassengerDoor: PartDamageSummary?,
    val rearPassengerFender: PartDamageSummary?,
    val trunk: PartDamageSummary?,
    val rearWindshield: PartDamageSummary?,
    val rearDriverFender: PartDamageSummary?,
    val rearDriverDoor: PartDamageSummary?,
    val frontDriverDoor: PartDamageSummary?,
    val roof: PartDamageSummary?,
    val driverAPillar: PartDamageSummary?,
    val driverBPillar: PartDamageSummary?,
    val driverCPillar: PartDamageSummary?,
    val driverDPillar: PartDamageSummary?,
    val passengerAPillar: PartDamageSummary?,
    val passengerBPillar: PartDamageSummary?,
    val passengerCPillar: PartDamageSummary?,
    val passengerDPillar: PartDamageSummary?
)
