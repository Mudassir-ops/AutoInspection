package com.example.autoinspectionapp.domain

data class PreliminaryInfoBO(
    val clientName: String?,
    val vehicleMake: String?,
    val vehicleModel: String?,
    val vehicleVariant: String?,
    val modelYear: String?,
    val transmission: String?,
    val engineCapacity: String?,
    val fuelType: String?,
    val bodyColor: String?,
    val mileage: String?,
    val registrationNumber: String?,
    val registeredRegion: String?,
    val chassisNumber: String?,
    val engineNumber: String?,
    val inspectionLocation: String?,
    val uploadImageLocalPath: String? = null,
    val uploadImageRemotePath: String? = null,
)
