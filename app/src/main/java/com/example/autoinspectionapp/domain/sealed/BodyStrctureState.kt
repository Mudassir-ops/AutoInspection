package com.example.autoinspectionapp.domain.sealed

import com.example.autoinspectionapp.domain.uimodels.PartUiModel

sealed interface BodyStrctureState {
    data object Init : BodyStrctureState
    data class Data(val partsData: List<PartUiModel>) : BodyStrctureState
}