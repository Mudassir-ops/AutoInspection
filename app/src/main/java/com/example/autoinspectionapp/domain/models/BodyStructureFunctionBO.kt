package com.example.autoinspectionapp.domain.models

import com.example.autoinspectionapp.domain.PartDamageSummary

data class BodyStructureFunctionBO(
    val trunkLock: String?,
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

    val passengerAPillar: PartDamageSummary?,
    val passengerBPillar: PartDamageSummary?,
    val passengerCPillar: PartDamageSummary?,
    val passengerDPillar: PartDamageSummary?,
    val passengerTyreA: PartDamageSummary?,
    val passengerTyreB: PartDamageSummary?,
    val driverTyreA: PartDamageSummary?,
    val driverTyreB: PartDamageSummary?,
    val driverPillarA: PartDamageSummary?,
    val driverPillarB: PartDamageSummary?,
    val driverPillarC: PartDamageSummary?,
    val driverPillarD: PartDamageSummary?,
)


