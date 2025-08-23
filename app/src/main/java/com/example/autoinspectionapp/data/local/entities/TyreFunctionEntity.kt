package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tyre_function_table")
data class TyreFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val frontPassengerTyreBrand: String,
    val frontPassengerTyreSize: String,
    val frontPassengerTyreCondition: String,
    val frontDriverTyreBrand: String,
    val frontDriverTyreSize: String,
    val frontDriverTyreCondition: String,
    val rearPassengerTyreBrand: String,
    val rearPassengerTyreSize: String,
    val rearPassengerTyreCondition: String,
    val rearDriverTyreBrand: String,
    val rearDriverTyreSize: String,
    val rearDriverTyreCondition: String,
    val alloyRims: String
)