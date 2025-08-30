package com.example.autoinspectionapp.presentation.uimodels

import com.example.autoinspectionapp.domain.PartDamageSummary

data class PartUiModel(
    val partName: String,
    val summary: PartDamageSummary?
)

data class PreliminaryInfoUI(
    val clientName: String?,
    val inspectionDate: String?,
    val vehicleMake: String?,
    val vehicleModel: String?,
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

data class AccidentChecklistUI(
    val engineRoomFirewall: String?,
    val rightStrutTower: String?,
    val leftStrutTower: String?,
    val rightFrontRail: String?,
    val leftFrontRail: String?,
    val frontBumperSupport: String?,
    val rearCoreSupport: String?,
    val radiatorCoreSupport: String?,
    val rightAPillar: String?,
    val leftAPillar: String?,
    val rightBPillar: String?,
    val leftBPillar: String?,
    val rightCPillar: String?,
    val leftCPillar: String?,
    val rightDPillar: String?,
    val leftDPillar: String?,
    val bootFloor: String?,
    val frontUnderbody: String?,
    val rearUnderbody: String?
)

data class MechanicalFunctionUI(
    val engineAbnormalNoise: String,
    val enginePick: String,
    val engineVibrations: String,
    val engineSmoke: String,
    val engineSmokeColor: String,
    val engineBlow: String,
    val engineOilLeakage: String,
    val coolantLeakage: String,
    val brakeOilLeakage: String,
    val transmissionOilLeakage: String,
    val catalyticConverter: String,
    val exhaustSound: String,
    val radiator: String,
    val suctionFan: String,
    val gearTransmission: String
)

data class ACHeaterFunctionUI(
    val acInstalled: String,
    val acFan: String,
    val blowerThrow: String,
    val acCooling: String,
    val heater: String
)

data class InteriorControlFunctionUI(
    val steeringWheelWearTear: String,
    val powerSteering: String,
    val steeringWheelButtons: String,
    val lightsLeverSwitch: String,
    val dashboardScratches: String,
    val dashControlButtons: String,
    val interiorLights: String,
    val defogger: String,
    val hazardLights: String,
    val multimedia: String,
    val rearViewCamera: String,
    val frontViewCamera: String,
    val trunkRelease: String,
    val doorSkirts: String,
    val fuelCapReleaseLever: String,
    val bonnetReleaseLever: String,
    val sideViewMirrorAdjustment: String,
    val leftSideViewMirror: String,
    val rightSideViewMirror: String,
    val retractingSideViewMirrors: String,
    val acGrills: String,
    val acceleratorPedal: String,
    val brakePedal: String,
    val clutchPedal: String,
    val sunroof: String,
    val seatsType: String,
    val seatsCondition: String,
    val driverSeatbelt: String,
    val passengerSeatbelt: String,
    val windowsType: String,
    val frontDriverWindow: String,
    val frontPassengerWindow: String,
    val rearDriverSideWindow: String,
    val rearPassengerSideWindow: String,
    val windowSafetyLockButton: String,
    val centralLocking: String,
    val keyButtons: String,
    val floorMats: String,
    val frontDriverDoorSeal: String,
    val frontPassengerDoorSeal: String,
    val rearDriverSideDoorSeal: String,
    val rearPassengerSideDoorSeal: String,
    val bonnetSeal: String,
    val trunkSeal: String
)

data class ElectricalSafetyFunctionUI(
    val battery: String,
    val horn: String,
    val rightHeadlightOperation: String,
    val rightHeadlightCondition: String,
    val rightHeadlightOriginal: String,
    val leftHeadlightOperation: String,
    val leftHeadlightCondition: String,
    val leftHeadlightOriginal: String,
    val foglights: String,
    val leftTailLightsOperation: String,
    val leftTailLightsCondition: String,
    val leftTailLightsOriginal: String,
    val rightTailLightsOperation: String,
    val rightTailLightsCondition: String,
    val rightTailLightsOriginal: String,
    val windshieldWipers: String,
    val airbags: String,
    val checkLights: String
)

data class SuspensionSteeringFunctionUI(
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

data class BodyStructureFunctionUI(
    val trunkLock: String?,
    val frontDriverFender: PartDamageSummary?,
    val bonnet: PartDamageSummary?,
    val frontBumper: PartDamageSummary?,
    val backBumper: PartDamageSummary?,
    val frontWindshield: PartDamageSummary?,
    val frontPassengerFender: PartDamageSummary?,
    val frontPassengerDoor: PartDamageSummary?,
    val rearPassengerDoor: PartDamageSummary?,
    val rearPassengerFender: PartDamageSummary?,
    val trunk: PartDamageSummary?,
    val rearWindshield: PartDamageSummary?,
    val rearDriverFender: PartDamageSummary?,
    val rearDriverDoor: PartDamageSummary?,
    val frontDriverDoor: PartDamageSummary?,
    val roof: PartDamageSummary?,
    val passengerAPillar: PartDamageSummary?,
    val passengerBPillar: PartDamageSummary?,
    val passengerCPillar: PartDamageSummary?,
    val passengerDPillar: PartDamageSummary?,
    val rearDriverTyre: PartDamageSummary?,
    val rearPassengerTyre: PartDamageSummary?,
    val frontDriverTyre: PartDamageSummary?,
    val frontPassengerTyre: PartDamageSummary?,
    val driverPillarA: PartDamageSummary?,
    val driverPillarB: PartDamageSummary?,
    val driverPillarC: PartDamageSummary?,
    val driverPillarD: PartDamageSummary?
)

data class TyreFunctionUI(
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

data class SparePartsFunctionUI(
    val spareWheel: String,
    val toolKit: String,
    val jack: String,
    val punctureRepairKit: String
)

data class TestDriveInspectionUI(
    val enginePick: String,
    val gearShifting: String,
    val differentialNoise: String,
    val driveShaftNoise: String,
    val absActuation: String,
    val brakePedalOperation: String,
    val frontSuspensionNoise: String,
    val rearSuspensionNoise: String,
    val steeringFunction: String,
    val steeringWheelAlignment: String,
    val speedometerInformationCluster: String,
    val testDriveDoneBy: String
)
