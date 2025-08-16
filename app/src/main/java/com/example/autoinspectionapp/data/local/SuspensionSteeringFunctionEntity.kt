package com.example.autoinspectionapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suspension_steering_function_table")
data class SuspensionSteeringFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val steeringAssemblyPlay: String,
    val axleBoots: String,
    val rightBallJoint: String,
    val leftBallJoint: String,
    val tieRodEnd: String,
    val rightBoot: String,
    val leftBoot: String,
    val rightBush: String,
    val leftBush: String,
    val rearRightShockAbsorber: String,
    val rearLeftShockAbsorber: String,
    val frontRightShockAbsorber: String,
    val frontLeftShockAbsorber: String
)