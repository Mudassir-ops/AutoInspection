package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "electrical_safety_function_table")
data class ElectricalSafetyFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val battery: String,
    val horn: String,
    val rightHeadlightOperation: String,
    val rightHeadlightCondition: String,
    val rightHeadlightOriginal: String,
    val leftHeadlightOperation: String,
    val leftHeadlightCondition: String,
    val leftHeadlightOriginal: String,
    val foglights: String,
    val leftTailLightsOperation: String,
    val leftTailLightsCondition: String,
    val leftTailLightsOriginal: String,
    val rightTailLightsOperation: String,
    val rightTailLightsCondition: String,
    val rightTailLightsOriginal: String,
    val windshieldWipers: String,
    val airbags: String,
    val checkLights: String
)