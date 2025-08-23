package com.example.autoinspectionapp.presentation.uimodels

import com.example.autoinspectionapp.domain.PartDamageSummary

data class PartUiModel(
    val partName: String,
    val summary: PartDamageSummary?
)

