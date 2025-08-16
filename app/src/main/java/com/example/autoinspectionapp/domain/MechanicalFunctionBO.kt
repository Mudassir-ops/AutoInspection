package com.example.autoinspectionapp.domain

data class MechanicalFunctionBO(
    val engineAbnormalNoise: String?,
    val enginePick: String?,
    val engineVibrations: String?,
    val engineSmoke: String?,
    val engineSmokeColor: String?,
    val engineBlow: String?,
    val engineOilLeakage: String?,
    val coolantLeakage: String?,
    val brakeOilLeakage: String?,
    val transmissionOilLeakage: String?,
    val catalyticConverter: String?,
    val exhaustSound: String?,
    val radiator: String?,
    val suctionFan: String?,
    val gearTransmission: String?
)