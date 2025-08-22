package com.example.autoinspectionapp.domain.uimodels

import com.example.autoinspectionapp.domain.PartDamageSummary

data class PartUiModel(
    val partName: String,
    val summary: PartDamageSummary?
)

