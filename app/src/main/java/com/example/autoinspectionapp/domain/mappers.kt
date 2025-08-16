package com.example.autoinspectionapp.domain

import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity

fun PreliminaryInfoBO.toEntity(): PreliminaryInfoEntity {
    return PreliminaryInfoEntity(
        id = 1,
        clientName = clientName ?: "",
        inspectionDate = inspectionDate ?: "",
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

fun AccidentChecklistBO.toEntity(): AccidentChecklistEntity {
    return AccidentChecklistEntity(
        id = 1,
        engineRoomFirewall = engineRoomFirewall ?: "",
        rightStrutTower = rightStrutTower ?: "",
        leftStrutTower = leftStrutTower ?: "",
        rightFrontRail = rightFrontRail ?: "",
        leftFrontRail = leftFrontRail ?: "",
        frontBumperSupport = frontBumperSupport ?: "",
        rearCoreSupport = rearCoreSupport ?: "",
        radiatorCoreSupport = radiatorCoreSupport ?: "",
        rightAPillar = rightAPillar ?: "",
        leftAPillar = leftAPillar ?: "",
        rightBPillar = rightBPillar ?: "",
        leftBPillar = leftBPillar ?: "",
        rightCPillar = rightCPillar ?: "",
        leftCPillar = leftCPillar ?: "",
        rightDPillar = rightDPillar ?: "",
        leftDPillar = leftDPillar ?: "",
        bootFloor = bootFloor ?: "",
        frontUnderbody = frontUnderbody ?: "",
        rearUnderbody = rearUnderbody ?: ""
    )
}
