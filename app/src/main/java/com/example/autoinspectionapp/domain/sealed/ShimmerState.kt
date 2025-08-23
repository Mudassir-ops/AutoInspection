package com.example.autoinspectionapp.domain.sealed

sealed interface ShimmerState {
    val buttonId: Int
    val isShimmer: Boolean

    data class ShimmerVisibility(
        override val buttonId: Int,
        override val isShimmer: Boolean
    ) : ShimmerState
}
