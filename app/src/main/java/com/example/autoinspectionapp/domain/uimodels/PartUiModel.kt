package com.example.autoinspectionapp.domain.uimodels

import androidx.databinding.ObservableField

data class PartUiModel(
    val name: String,
    val color: Triple<Int, Int, Int>,
    val value: ObservableField<String> = ObservableField("")
)
