package com.example.autoinspectionapp.domain.sealed

sealed interface SharedAppState {

    data object Idle : SharedAppState
    data class ShimmerVisibility(
        val buttonId: Int,
        val isShimmer: Boolean
    ) : SharedAppState

    data class ImagePickerState(val pickedUri: String) : SharedAppState

}
