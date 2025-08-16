package com.example.autoinspectionapp.domain

import com.example.autoinspectionapp.data.local.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.MechanicalFunctionEntity
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

fun MechanicalFunctionBO.toEntity(): MechanicalFunctionEntity {
    return MechanicalFunctionEntity(
        id = 1,
        engineAbnormalNoise = engineAbnormalNoise ?: "",
        enginePick = enginePick ?: "",
        engineVibrations = engineVibrations ?: "",
        engineSmoke = engineSmoke ?: "",
        engineSmokeColor = engineSmokeColor ?: "",
        engineBlow = engineBlow ?: "",
        engineOilLeakage = engineOilLeakage ?: "",
        coolantLeakage = coolantLeakage ?: "",
        brakeOilLeakage = brakeOilLeakage ?: "",
        transmissionOilLeakage = transmissionOilLeakage ?: "",
        catalyticConverter = catalyticConverter ?: "",
        exhaustSound = exhaustSound ?: "",
        radiator = radiator ?: "",
        suctionFan = suctionFan ?: "",
        gearTransmission = gearTransmission ?: ""
    )
}

fun ACHeaterFunctionBO.toEntity(): ACHeaterFunctionEntity {
    return ACHeaterFunctionEntity(
        id = 1,
        acInstalled = acInstalled ?: "",
        acFan = acFan ?: "",
        blowerThrow = blowerThrow ?: "",
        acCooling = acCooling ?: "",
        heater = heater ?: ""
    )
}
