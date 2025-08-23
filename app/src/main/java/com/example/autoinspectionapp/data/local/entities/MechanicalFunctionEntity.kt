package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mechanical_function_table")
data class MechanicalFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val engineAbnormalNoise: String,
    val enginePick: String,
    val engineVibrations: String,
    val engineSmoke: String,
    val engineSmokeColor: String,
    val engineBlow: String,
    val engineOilLeakage: String,
    val coolantLeakage: String,
    val brakeOilLeakage: String,
    val transmissionOilLeakage: String,
    val catalyticConverter: String,
    val exhaustSound: String,
    val radiator: String,
    val suctionFan: String,
    val gearTransmission: String
)