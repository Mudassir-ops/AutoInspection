package com.example.autoinspectionapp.domain

import com.example.autoinspectionapp.data.local.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.BodyStructureFunctionEntity
import com.example.autoinspectionapp.data.local.ElectricalSafetyFunctionEntity
import com.example.autoinspectionapp.data.local.InteriorControlFunctionEntity
import com.example.autoinspectionapp.data.local.MechanicalFunctionEntity
import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity
import com.example.autoinspectionapp.data.local.SparePartsFunctionEntity
import com.example.autoinspectionapp.data.local.SuspensionSteeringFunctionEntity
import com.example.autoinspectionapp.data.local.TyreFunctionEntity
import com.example.autoinspectionapp.data.remote.LoginRequest
import com.example.autoinspectionapp.domain.uimodels.LoginUi

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

fun InteriorControlFunctionBO.toEntity(): InteriorControlFunctionEntity {
    return InteriorControlFunctionEntity(
        id = 1,
        steeringWheelWearTear = steeringWheelWearTear ?: "",
        powerSteering = powerSteering ?: "",
        steeringWheelButtons = steeringWheelButtons ?: "",
        lightsLeverSwitch = lightsLeverSwitch ?: "",
        dashboardScratches = dashboardScratches ?: "",
        dashControlButtons = dashControlButtons ?: "",
        interiorLights = interiorLights ?: "",
        defogger = defogger ?: "",
        hazardLights = hazardLights ?: "",
        multimedia = multimedia ?: "",
        rearViewCamera = rearViewCamera ?: "",
        frontViewCamera = frontViewCamera ?: "",
        trunkRelease = trunkRelease ?: "",
        doorSkirts = doorSkirts ?: "",
        fuelCapReleaseLever = fuelCapReleaseLever ?: "",
        bonnetReleaseLever = bonnetReleaseLever ?: "",
        sideViewMirrorAdjustment = sideViewMirrorAdjustment ?: "",
        leftSideViewMirror = leftSideViewMirror ?: "",
        rightSideViewMirror = rightSideViewMirror ?: "",
        retractingSideViewMirrors = retractingSideViewMirrors ?: "",
        acGrills = acGrills ?: "",
        acceleratorPedal = acceleratorPedal ?: "",
        brakePedal = brakePedal ?: "",
        clutchPedal = clutchPedal ?: "",
        sunroof = sunroof ?: "",
        seatsType = seatsType ?: "",
        seatsCondition = seatsCondition ?: "",
        driverSeatbelt = driverSeatbelt ?: "",
        passengerSeatbelt = passengerSeatbelt ?: "",
        windowsType = windowsType ?: "",
        frontDriverWindow = frontDriverWindow ?: "",
        frontPassengerWindow = frontPassengerWindow ?: "",
        rearDriverSideWindow = rearDriverSideWindow ?: "",
        rearPassengerSideWindow = rearPassengerSideWindow ?: "",
        windowSafetyLockButton = windowSafetyLockButton ?: "",
        centralLocking = centralLocking ?: "",
        keyButtons = keyButtons ?: "",
        floorMats = floorMats ?: "",
        frontDriverDoorSeal = frontDriverDoorSeal ?: "",
        frontPassengerDoorSeal = frontPassengerDoorSeal ?: "",
        rearDriverSideDoorSeal = rearDriverSideDoorSeal ?: "",
        rearPassengerSideDoorSeal = rearPassengerSideDoorSeal ?: "",
        bonnetSeal = bonnetSeal ?: "",
        trunkSeal = trunkSeal ?: ""
    )
}

fun ElectricalSafetyFunctionBO.toEntity(): ElectricalSafetyFunctionEntity {
    return ElectricalSafetyFunctionEntity(
        id = 1,
        battery = battery ?: "",
        horn = horn ?: "",
        rightHeadlightOperation = rightHeadlightOperation ?: "",
        rightHeadlightCondition = rightHeadlightCondition ?: "",
        rightHeadlightOriginal = rightHeadlightOriginal ?: "",
        leftHeadlightOperation = leftHeadlightOperation ?: "",
        leftHeadlightCondition = leftHeadlightCondition ?: "",
        leftHeadlightOriginal = leftHeadlightOriginal ?: "",
        foglights = foglights ?: "",
        leftTailLightsOperation = leftTailLightsOperation ?: "",
        leftTailLightsCondition = leftTailLightsCondition ?: "",
        leftTailLightsOriginal = leftTailLightsOriginal ?: "",
        rightTailLightsOperation = rightTailLightsOperation ?: "",
        rightTailLightsCondition = rightTailLightsCondition ?: "",
        rightTailLightsOriginal = rightTailLightsOriginal ?: "",
        windshieldWipers = windshieldWipers ?: "",
        airbags = airbags ?: "",
        checkLights = checkLights ?: ""
    )
}

fun SuspensionSteeringFunctionBO.toEntity(): SuspensionSteeringFunctionEntity {
    return SuspensionSteeringFunctionEntity(
        id = 1,
        steeringAssemblyPlay = steeringAssemblyPlay ?: "",
        axleBoots = axleBoots ?: "",
        rightBallJoint = rightBallJoint ?: "",
        leftBallJoint = leftBallJoint ?: "",
        tieRodEnd = tieRodEnd ?: "",
        rightBoot = rightBoot ?: "",
        leftBoot = leftBoot ?: "",
        rightBush = rightBush ?: "",
        leftBush = leftBush ?: "",
        rearRightShockAbsorber = rearRightShockAbsorber ?: "",
        rearLeftShockAbsorber = rearLeftShockAbsorber ?: "",
        frontRightShockAbsorber = frontRightShockAbsorber ?: "",
        frontLeftShockAbsorber = frontLeftShockAbsorber ?: ""
    )
}

fun BodyStructureFunctionBO.toEntity(): BodyStructureFunctionEntity {
    return BodyStructureFunctionEntity(
        id = 1,
        trunkLock = trunkLock ?: "",
        frontDriverFender = frontDriverFender ?: "",
        bonnet = bonnet ?: "",
        frontWindshield = frontWindshield ?: "",
        frontPassengerFender = frontPassengerFender ?: "",
        frontPassengerDoor = frontPassengerDoor ?: "",
        rearPassengerDoor = rearPassengerDoor ?: "",
        rearPassengerFender = rearPassengerFender ?: "",
        trunk = trunk ?: "",
        rearWindshield = rearWindshield ?: "",
        rearDriverFender = rearDriverFender ?: "",
        rearDriverDoor = rearDriverDoor ?: "",
        frontDriverDoor = frontDriverDoor ?: "",
        roof = roof ?: "",
        driverAPillar = driverAPillar ?: "",
        driverBPillar = driverBPillar ?: "",
        driverCPillar = driverCPillar ?: "",
        driverDPillar = driverDPillar ?: "",
        passengerAPillar = passengerAPillar ?: "",
        passengerBPillar = passengerBPillar ?: "",
        passengerCPillar = passengerCPillar ?: "",
        passengerDPillar = passengerDPillar ?: ""
    )
}

fun TyreFunctionBO.toEntity(): TyreFunctionEntity {
    return TyreFunctionEntity(
        id = 1,
        frontPassengerTyreBrand = frontPassengerTyreBrand ?: "",
        frontPassengerTyreSize = frontPassengerTyreSize ?: "",
        frontPassengerTyreCondition = frontPassengerTyreCondition ?: "",
        frontDriverTyreBrand = frontDriverTyreBrand ?: "",
        frontDriverTyreSize = frontDriverTyreSize ?: "",
        frontDriverTyreCondition = frontDriverTyreCondition ?: "",
        rearPassengerTyreBrand = rearPassengerTyreBrand ?: "",
        rearPassengerTyreSize = rearPassengerTyreSize ?: "",
        rearPassengerTyreCondition = rearPassengerTyreCondition ?: "",
        rearDriverTyreBrand = rearDriverTyreBrand ?: "",
        rearDriverTyreSize = rearDriverTyreSize ?: "",
        rearDriverTyreCondition = rearDriverTyreCondition ?: "",
        alloyRims = alloyRims ?: ""
    )
}

fun SparePartsFunctionBO.toEntity(): SparePartsFunctionEntity {
    return SparePartsFunctionEntity(
        id = 1,
        spareWheel = spareWheel ?: "",
        toolKit = toolKit ?: "",
        jack = jack ?: "",
        punctureRepairKit = punctureRepairKit ?: ""
    )
}

fun LoginUi.toRequest(): LoginRequest {
    return LoginRequest(
        loginEmail = this.loginEmail,
        loginPwd = this.loginPwd,
        serialNumber = this.serialNumber
    )
}