package com.example.autoinspectionapp.domain.models

data class ElectricalSafetyFunctionBO(
    val battery: String?,
    val horn: String?,
    val rightHeadlightOperation: String?,
    val rightHeadlightCondition: String?,
    val rightHeadlightOriginal: String?,
    val leftHeadlightOperation: String?,
    val leftHeadlightCondition: String?,
    val leftHeadlightOriginal: String?,
    val foglights: String?,
    val leftTailLightsOperation: String?,
    val leftTailLightsCondition: String?,
    val leftTailLightsOriginal: String?,
    val rightTailLightsOperation: String?,
    val rightTailLightsCondition: String?,
    val rightTailLightsOriginal: String?,
    val windshieldWipers: String?,
    val airbags: String?,
    val checkLights: String?
)