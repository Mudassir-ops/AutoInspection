package com.example.autoinspectionapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preliminary_info_table")
data class PreliminaryInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val clientName: String,
    val vehicleMake: String,
    val vehicleModel: String,
    val vehicleVariant: String,
    val modelYear: String,
    val transmission: String,
    val engineCapacity: String,
    val fuelType: String,
    val bodyColor: String,
    val mileage: String,
    val registrationNumber: String,
    val registeredRegion: String,
    val chassisNumber: String,
    val engineNumber: String,
    val inspectionLocation: String,
    val uploadImageLocalPath: String?,
    val uploadImageRemotePath: String?,
)
