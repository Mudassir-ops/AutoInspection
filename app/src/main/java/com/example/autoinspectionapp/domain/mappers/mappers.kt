package com.example.autoinspectionapp.domain.mappers

import android.graphics.PointF
import com.example.autoinspectionapp.data.local.entities.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.entities.AccidentChecklistEntity
import com.example.autoinspectionapp.data.local.entities.BodyStructureFunctionEntity
import com.example.autoinspectionapp.data.local.entities.ElectricalSafetyFunctionEntity
import com.example.autoinspectionapp.data.local.entities.InteriorControlFunctionEntity
import com.example.autoinspectionapp.data.local.entities.MechanicalFunctionEntity
import com.example.autoinspectionapp.data.local.entities.PreliminaryInfoEntity
import com.example.autoinspectionapp.data.local.entities.SparePartsFunctionEntity
import com.example.autoinspectionapp.data.local.entities.SuspensionSteeringFunctionEntity
import com.example.autoinspectionapp.data.local.entities.TestDriveInspectionEntity
import com.example.autoinspectionapp.data.local.entities.TyreFunctionEntity
import com.example.autoinspectionapp.data.remote.models.LoginRequest
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.example.autoinspectionapp.domain.PartWithDamage
import com.example.autoinspectionapp.domain.models.ACHeaterFunctionBO
import com.example.autoinspectionapp.domain.models.AccidentChecklistBO
import com.example.autoinspectionapp.domain.models.BodyStructureFunctionBO
import com.example.autoinspectionapp.domain.models.ElectricalSafetyFunctionBO
import com.example.autoinspectionapp.domain.models.InteriorControlFunctionBO
import com.example.autoinspectionapp.domain.models.MechanicalFunctionBO
import com.example.autoinspectionapp.domain.models.PreliminaryInfoBO
import com.example.autoinspectionapp.domain.models.SparePartsFunctionBO
import com.example.autoinspectionapp.domain.models.SuspensionSteeringFunctionBO
import com.example.autoinspectionapp.domain.models.TestDriveInspectionBo
import com.example.autoinspectionapp.domain.models.TyreFunctionBO
import com.example.autoinspectionapp.presentation.uimodels.ACHeaterFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.AccidentChecklistUI
import com.example.autoinspectionapp.presentation.uimodels.ElectricalSafetyFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.InteriorControlFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.LoginUi
import com.example.autoinspectionapp.presentation.uimodels.MechanicalFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.PartUiModel
import com.example.autoinspectionapp.presentation.uimodels.PreliminaryInfoUI
import com.example.autoinspectionapp.presentation.uimodels.SparePartsFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.SuspensionSteeringFunctionUI
import com.example.autoinspectionapp.presentation.uimodels.TestDriveInspectionUI
import com.example.autoinspectionapp.presentation.uimodels.TyreFunctionUI

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
        trunkLock = trunkLock.orEmpty(),
        frontDriverFender = frontDriverFender ?: defaultBo(),
        bonnet = bonnet ?: defaultBo(),
        frontBumper = frontBumper ?: defaultBo(),
        frontWindshield = frontWindshield ?: defaultBo(),
        frontPassengerFender = frontPassengerFender ?: defaultBo(),
        frontPassengerDoor = frontPassengerDoor ?: defaultBo(),
        rearPassengerDoor = rearPassengerDoor ?: defaultBo(),
        rearPassengerFender = rearPassengerFender ?: defaultBo(),
        trunk = trunk ?: defaultBo(),
        rearWindshield = rearWindshield ?: defaultBo(),
        rearDriverFender = rearDriverFender ?: defaultBo(),
        rearDriverDoor = rearDriverDoor ?: defaultBo(),
        frontDriverDoor = frontDriverDoor ?: defaultBo(),
        roof = roof ?: defaultBo(),
        passengerAPillar = passengerAPillar ?: defaultBo(),
        passengerBPillar = passengerBPillar ?: defaultBo(),
        passengerCPillar = passengerCPillar ?: defaultBo(),
        passengerDPillar = passengerDPillar ?: defaultBo(),
        driverPillarA = driverPillarA ?: defaultBo(),
        driverPillarB = driverPillarB ?: defaultBo(),
        driverPillarC = driverPillarC ?: defaultBo(),
        driverPillarD = driverPillarD ?: defaultBo(),
        backBumper = backBumper ?: defaultBo(),
        rearDriverTyre = rearDriverTyre ?: defaultBo(),
        rearPassengerTyre = rearPassengerTyre ?: defaultBo(),
        frontDriverTyre = frontDriverTyre ?: defaultBo(),
        frontPassengerTyre = frontPassengerTyre ?: defaultBo()
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

fun TestDriveInspectionBo.toEntity(): TestDriveInspectionEntity {
    return TestDriveInspectionEntity(
        id = 1,
        enginePick = enginePick,
        gearShifting = gearShifting,
        differentialNoise = differentialNoise,
        driveShaftNoise = driveShaftNoise,
        absActuation = absActuation,
        brakePedalOperation = brakePedalOperation,
        frontSuspensionNoise = frontSuspensionNoise,
        rearSuspensionNoise = rearSuspensionNoise,
        steeringFunction = steeringFunction,
        steeringWheelAlignment = steeringWheelAlignment,
        speedometerInformationCluster = speedometerInformationCluster,
        testDriveDoneBy = testDriveDoneBy
    )
}

fun defaultBo(): PartDamageSummary {
    return PartDamageSummary(
        partName = "",
        damageCodes = listOf(PartWithDamage(PointF(0f, 0f), "N/A", color = -1, partName = "N/A"))
    )
}


// UI MAPPER
fun BodyStructureFunctionEntity.toUI(): List<PartUiModel> {
    return listOf(
        PartUiModel("Front Driver Fender", frontDriverFender),
        PartUiModel("Bonnet", bonnet),
        PartUiModel("Front Bumper", frontBumper),
        PartUiModel("Back Bumper", backBumper),
        PartUiModel("Front Windshield", frontWindshield),
        PartUiModel("Front Passenger Fender", frontPassengerFender),
        PartUiModel("Front Passenger Door", frontPassengerDoor),
        PartUiModel("Rear Passenger Door", rearPassengerDoor),
        PartUiModel("Rear Passenger Fender", rearPassengerFender),
        PartUiModel("Trunk", trunk),
        PartUiModel("Rear Windshield", rearWindshield),
        PartUiModel("Rear Driver Fender", rearDriverFender),
        PartUiModel("Rear Driver Door", rearDriverDoor),
        PartUiModel("Front Driver Door", frontDriverDoor),
        PartUiModel("Roof", roof),

        PartUiModel("Driver A Pillar", driverPillarA),
        PartUiModel("Driver B Pillar", driverPillarB),
        PartUiModel("Driver C Pillar", driverPillarC),
        PartUiModel("Driver D Pillar", driverPillarD),

        PartUiModel("Passenger A Pillar", passengerAPillar),
        PartUiModel("Passenger B Pillar", passengerBPillar),
        PartUiModel("Passenger C Pillar", passengerCPillar),
        PartUiModel("Passenger D Pillar", passengerDPillar),

        PartUiModel("Rear Driver Tyre", rearDriverTyre),
        PartUiModel("Rear Passenger Tyre", rearPassengerTyre),
        PartUiModel("Front Driver Tyre", frontDriverTyre),
        PartUiModel("Front Passenger Tyre", frontPassengerTyre)

    )
}

fun PreliminaryInfoEntity.toUI(): PreliminaryInfoUI =
    PreliminaryInfoUI(
        clientName = clientName.orEmpty(),
        inspectionDate = inspectionDate.orEmpty(),
        vehicleMake = vehicleMake.orEmpty(),
        vehicleModel = vehicleModel.orEmpty(),
        vehicleVariant = vehicleVariant.orEmpty(),
        modelYear = modelYear.orEmpty(),
        transmission = transmission.orEmpty(),
        engineCapacity = engineCapacity.orEmpty(),
        fuelType = fuelType.orEmpty(),
        bodyColor = bodyColor.orEmpty(),
        mileage = mileage.orEmpty(),
        registrationNumber = registrationNumber.orEmpty(),
        registeredRegion = registeredRegion.orEmpty(),
        chassisNumber = chassisNumber.orEmpty(),
        engineNumber = engineNumber.orEmpty(),
        inspectionLocation = inspectionLocation.orEmpty(),
        uploadImageLocalPath = uploadImageLocalPath.orEmpty(),
        uploadImageRemotePath = uploadImageRemotePath.orEmpty()
    )

fun AccidentChecklistEntity.toUI(): AccidentChecklistUI =
    AccidentChecklistUI(
        engineRoomFirewall = engineRoomFirewall.orEmpty(),
        rightStrutTower = rightStrutTower.orEmpty(),
        leftStrutTower = leftStrutTower.orEmpty(),
        rightFrontRail = rightFrontRail.orEmpty(),
        leftFrontRail = leftFrontRail.orEmpty(),
        frontBumperSupport = frontBumperSupport.orEmpty(),
        rearCoreSupport = rearCoreSupport.orEmpty(),
        radiatorCoreSupport = radiatorCoreSupport.orEmpty(),
        rightAPillar = rightAPillar.orEmpty(),
        leftAPillar = leftAPillar.orEmpty(),
        rightBPillar = rightBPillar.orEmpty(),
        leftBPillar = leftBPillar.orEmpty(),
        rightCPillar = rightCPillar.orEmpty(),
        leftCPillar = leftCPillar.orEmpty(),
        rightDPillar = rightDPillar.orEmpty(),
        leftDPillar = leftDPillar.orEmpty(),
        bootFloor = bootFloor.orEmpty(),
        frontUnderbody = frontUnderbody.orEmpty(),
        rearUnderbody = rearUnderbody.orEmpty()
    )

fun MechanicalFunctionEntity.toUI(): MechanicalFunctionUI =
    MechanicalFunctionUI(
        engineAbnormalNoise = engineAbnormalNoise.orEmpty(),
        enginePick = enginePick.orEmpty(),
        engineVibrations = engineVibrations.orEmpty(),
        engineSmoke = engineSmoke.orEmpty(),
        engineSmokeColor = engineSmokeColor.orEmpty(),
        engineBlow = engineBlow.orEmpty(),
        engineOilLeakage = engineOilLeakage.orEmpty(),
        coolantLeakage = coolantLeakage.orEmpty(),
        brakeOilLeakage = brakeOilLeakage.orEmpty(),
        transmissionOilLeakage = transmissionOilLeakage.orEmpty(),
        catalyticConverter = catalyticConverter.orEmpty(),
        exhaustSound = exhaustSound.orEmpty(),
        radiator = radiator.orEmpty(),
        suctionFan = suctionFan.orEmpty(),
        gearTransmission = gearTransmission.orEmpty()
    )

fun ACHeaterFunctionEntity.toUI(): ACHeaterFunctionUI =
    ACHeaterFunctionUI(
        acInstalled = acInstalled.orEmpty(),
        acFan = acFan.orEmpty(),
        blowerThrow = blowerThrow.orEmpty(),
        acCooling = acCooling.orEmpty(),
        heater = heater.orEmpty()
    )

fun InteriorControlFunctionEntity.toUI(): InteriorControlFunctionUI =
    InteriorControlFunctionUI(
        steeringWheelWearTear = steeringWheelWearTear.orEmpty(),
        powerSteering = powerSteering.orEmpty(),
        steeringWheelButtons = steeringWheelButtons.orEmpty(),
        lightsLeverSwitch = lightsLeverSwitch.orEmpty(),
        dashboardScratches = dashboardScratches.orEmpty(),
        dashControlButtons = dashControlButtons.orEmpty(),
        interiorLights = interiorLights.orEmpty(),
        defogger = defogger.orEmpty(),
        hazardLights = hazardLights.orEmpty(),
        multimedia = multimedia.orEmpty(),
        rearViewCamera = rearViewCamera.orEmpty(),
        frontViewCamera = frontViewCamera.orEmpty(),
        trunkRelease = trunkRelease.orEmpty(),
        doorSkirts = doorSkirts.orEmpty(),
        fuelCapReleaseLever = fuelCapReleaseLever.orEmpty(),
        bonnetReleaseLever = bonnetReleaseLever.orEmpty(),
        sideViewMirrorAdjustment = sideViewMirrorAdjustment.orEmpty(),
        leftSideViewMirror = leftSideViewMirror.orEmpty(),
        rightSideViewMirror = rightSideViewMirror.orEmpty(),
        retractingSideViewMirrors = retractingSideViewMirrors.orEmpty(),
        acGrills = acGrills.orEmpty(),
        acceleratorPedal = acceleratorPedal.orEmpty(),
        brakePedal = brakePedal.orEmpty(),
        clutchPedal = clutchPedal.orEmpty(),
        sunroof = sunroof.orEmpty(),
        seatsType = seatsType.orEmpty(),
        seatsCondition = seatsCondition.orEmpty(),
        driverSeatbelt = driverSeatbelt.orEmpty(),
        passengerSeatbelt = passengerSeatbelt.orEmpty(),
        windowsType = windowsType.orEmpty(),
        frontDriverWindow = frontDriverWindow.orEmpty(),
        frontPassengerWindow = frontPassengerWindow.orEmpty(),
        rearDriverSideWindow = rearDriverSideWindow.orEmpty(),
        rearPassengerSideWindow = rearPassengerSideWindow.orEmpty(),
        windowSafetyLockButton = windowSafetyLockButton.orEmpty(),
        centralLocking = centralLocking.orEmpty(),
        keyButtons = keyButtons.orEmpty(),
        floorMats = floorMats.orEmpty(),
        frontDriverDoorSeal = frontDriverDoorSeal.orEmpty(),
        frontPassengerDoorSeal = frontPassengerDoorSeal.orEmpty(),
        rearDriverSideDoorSeal = rearDriverSideDoorSeal.orEmpty(),
        rearPassengerSideDoorSeal = rearPassengerSideDoorSeal.orEmpty(),
        bonnetSeal = bonnetSeal.orEmpty(),
        trunkSeal = trunkSeal.orEmpty()
    )

fun ElectricalSafetyFunctionBO.toUI(): ElectricalSafetyFunctionUI =
    ElectricalSafetyFunctionUI(
        battery = battery.orEmpty(),
        horn = horn.orEmpty(),
        rightHeadlightOperation = rightHeadlightOperation.orEmpty(),
        rightHeadlightCondition = rightHeadlightCondition.orEmpty(),
        rightHeadlightOriginal = rightHeadlightOriginal.orEmpty(),
        leftHeadlightOperation = leftHeadlightOperation.orEmpty(),
        leftHeadlightCondition = leftHeadlightCondition.orEmpty(),
        leftHeadlightOriginal = leftHeadlightOriginal.orEmpty(),
        foglights = foglights.orEmpty(),
        leftTailLightsOperation = leftTailLightsOperation.orEmpty(),
        leftTailLightsCondition = leftTailLightsCondition.orEmpty(),
        leftTailLightsOriginal = leftTailLightsOriginal.orEmpty(),
        rightTailLightsOperation = rightTailLightsOperation.orEmpty(),
        rightTailLightsCondition = rightTailLightsCondition.orEmpty(),
        rightTailLightsOriginal = rightTailLightsOriginal.orEmpty(),
        windshieldWipers = windshieldWipers.orEmpty(),
        airbags = airbags.orEmpty(),
        checkLights = checkLights.orEmpty()
    )

fun SuspensionSteeringFunctionEntity.toUI(): SuspensionSteeringFunctionUI =
    SuspensionSteeringFunctionUI(
        steeringAssemblyPlay = steeringAssemblyPlay.orEmpty(),
        axleBoots = axleBoots.orEmpty(),
        rightBallJoint = rightBallJoint.orEmpty(),
        leftBallJoint = leftBallJoint.orEmpty(),
        tieRodEnd = tieRodEnd.orEmpty(),
        rightBoot = rightBoot.orEmpty(),
        leftBoot = leftBoot.orEmpty(),
        rightBush = rightBush.orEmpty(),
        leftBush = leftBush.orEmpty(),
        rearRightShockAbsorber = rearRightShockAbsorber.orEmpty(),
        rearLeftShockAbsorber = rearLeftShockAbsorber.orEmpty(),
        frontRightShockAbsorber = frontRightShockAbsorber.orEmpty(),
        frontLeftShockAbsorber = frontLeftShockAbsorber.orEmpty()
    )

fun TyreFunctionEntity.toUI(): TyreFunctionUI =
    TyreFunctionUI(
        frontPassengerTyreBrand = frontPassengerTyreBrand.orEmpty(),
        frontPassengerTyreSize = frontPassengerTyreSize.orEmpty(),
        frontPassengerTyreCondition = frontPassengerTyreCondition.orEmpty(),
        frontDriverTyreBrand = frontDriverTyreBrand.orEmpty(),
        frontDriverTyreSize = frontDriverTyreSize.orEmpty(),
        frontDriverTyreCondition = frontDriverTyreCondition.orEmpty(),
        rearPassengerTyreBrand = rearPassengerTyreBrand.orEmpty(),
        rearPassengerTyreSize = rearPassengerTyreSize.orEmpty(),
        rearPassengerTyreCondition = rearPassengerTyreCondition.orEmpty(),
        rearDriverTyreBrand = rearDriverTyreBrand.orEmpty(),
        rearDriverTyreSize = rearDriverTyreSize.orEmpty(),
        rearDriverTyreCondition = rearDriverTyreCondition.orEmpty(),
        alloyRims = alloyRims.orEmpty()
    )

fun TestDriveInspectionEntity.toUI(): TestDriveInspectionUI =
    TestDriveInspectionUI(
        enginePick = enginePick.orEmpty(),
        gearShifting = gearShifting.orEmpty(),
        differentialNoise = differentialNoise.orEmpty(),
        driveShaftNoise = driveShaftNoise.orEmpty(),
        absActuation = absActuation.orEmpty(),
        brakePedalOperation = brakePedalOperation.orEmpty(),
        frontSuspensionNoise = frontSuspensionNoise.orEmpty(),
        rearSuspensionNoise = rearSuspensionNoise.orEmpty(),
        steeringFunction = steeringFunction.orEmpty(),
        steeringWheelAlignment = steeringWheelAlignment.orEmpty(),
        speedometerInformationCluster = speedometerInformationCluster.orEmpty(),
        testDriveDoneBy = testDriveDoneBy.orEmpty()
    )

fun SparePartsFunctionEntity.toUI(): SparePartsFunctionUI = SparePartsFunctionUI(
    spareWheel = spareWheel.orEmpty(),
    toolKit = toolKit.orEmpty(),
    jack = jack.orEmpty(),
    punctureRepairKit = punctureRepairKit.orEmpty()
)
