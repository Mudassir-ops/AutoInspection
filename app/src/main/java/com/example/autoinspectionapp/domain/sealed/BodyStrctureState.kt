package com.example.autoinspectionapp.domain.sealed

import com.example.autoinspectionapp.presentation.uimodels.PartUiModel
import com.example.autoinspectionapp.utils.enums.Section

sealed interface BodyStrctureState {
    data object Init : BodyStrctureState
    data class Data(val partsData: List<PartUiModel>) : BodyStrctureState
}

sealed interface PagesDataState {
    data object Init : PagesDataState
    data class Data<T>(val section: Section, val data: T) : PagesDataState
}
