package com.example.autoinspectionapp.presentation.ui.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.sealed.ShimmerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _shimmerSharedFlow = MutableStateFlow(
        ShimmerState.ShimmerVisibility(
            buttonId = 0,
            isShimmer = false
        )
    )
    val shimmerSharedFlow: StateFlow<ShimmerState> = _shimmerSharedFlow.asStateFlow()

    fun loadHideShimmer(visibleOrHide: Boolean, buttonId: Int = -1) {
        viewModelScope.launch {
            if (visibleOrHide) {
                _shimmerSharedFlow.emit(
                    ShimmerState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = true
                    )
                )
                delay(250)
                _shimmerSharedFlow.emit(
                    ShimmerState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = false
                    )
                )
            } else {
                _shimmerSharedFlow.emit(
                    ShimmerState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = false
                    )
                )
            }
        }
    }
}