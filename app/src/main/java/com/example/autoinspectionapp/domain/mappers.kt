package com.example.autoinspectionapp.domain

import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity

fun PreliminaryInfoBO.toEntity(): PreliminaryInfoEntity {
    return PreliminaryInfoEntity(
        id = 1,
        clientName = clientName ?: "",
        vehicleMake = vehicleMake ?: "",
        vehicleModel = vehicleModel ?: "",
        vehicleVariant = vehicleVariant ?: "",
        modelYear = modelYear ?: "",
        transmission = transmission ?: "",
        engineCapacity = engineCapacity ?: "",
        fuelType = fuelType ?: "",
        bodyColor = bodyColor ?: "",
        mileage = mileage ?: "",
        registrationNumber = registrationNumber ?: "",
        registeredRegion = registeredRegion ?: "",
        chassisNumber = chassisNumber ?: "",
        engineNumber = engineNumber ?: "",
        inspectionLocation = inspectionLocation ?: "",
        uploadImageLocalPath = uploadImageLocalPath ?: "",
        uploadImageRemotePath = uploadImageRemotePath ?: ""
    )
}