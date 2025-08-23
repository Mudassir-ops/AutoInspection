package com.example.autoinspectionapp.domain.models

data class TyreFunctionBO(
    val frontPassengerTyreBrand: String?,
    val frontPassengerTyreSize: String?,
    val frontPassengerTyreCondition: String?,
    val frontDriverTyreBrand: String?,
    val frontDriverTyreSize: String?,
    val frontDriverTyreCondition: String?,
    val rearPassengerTyreBrand: String?,
    val rearPassengerTyreSize: String?,
    val rearPassengerTyreCondition: String?,
    val rearDriverTyreBrand: String?,
    val rearDriverTyreSize: String?,
    val rearDriverTyreCondition: String?,
    val alloyRims: String?
)