package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.autoinspectionapp.domain.PartDamageSummary

@Entity(tableName = "body_structure_function_table")
data class BodyStructureFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val trunkLock: String?,
    val frontDriverFender: PartDamageSummary?,
    val bonnet: PartDamageSummary?,
    val frontBumper: PartDamageSummary?,
    val backBumper: PartDamageSummary?,
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

    val passengerAPillar: PartDamageSummary?,
    val passengerBPillar: PartDamageSummary?,
    val passengerCPillar: PartDamageSummary?,
    val passengerDPillar: PartDamageSummary?,
    val rearDriverTyre: PartDamageSummary?,
    val rearPassengerTyre: PartDamageSummary?,
    val frontDriverTyre: PartDamageSummary?,
    val frontPassengerTyre: PartDamageSummary?,
    val driverPillarA: PartDamageSummary?,
    val driverPillarB: PartDamageSummary?,
    val driverPillarC: PartDamageSummary?,
    val driverPillarD: PartDamageSummary?,
)
