package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accident_checklist_table")
data class AccidentChecklistEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val engineRoomFirewall: String,
    val rightStrutTower: String,
    val leftStrutTower: String,
    val rightFrontRail: String,
    val leftFrontRail: String,
    val frontBumperSupport: String,
    val rearCoreSupport: String,
    val radiatorCoreSupport: String,
    val rightAPillar: String,
    val leftAPillar: String,
    val rightBPillar: String,
    val leftBPillar: String,
    val rightCPillar: String,
    val leftCPillar: String,
    val rightDPillar: String,
    val leftDPillar: String,
    val bootFloor: String,
    val frontUnderbody: String,
    val rearUnderbody: String
)