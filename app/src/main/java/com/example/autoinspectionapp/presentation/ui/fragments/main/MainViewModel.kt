package com.example.autoinspectionapp.presentation.ui.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _appStateFlow = MutableStateFlow<SharedAppState>(SharedAppState.Idle)
    val appStateFlow: StateFlow<SharedAppState> = _appStateFlow.asStateFlow()

    fun loadHideShimmer(visibleOrHide: Boolean, buttonId: Int = -1) {
        viewModelScope.launch {
            if (visibleOrHide) {
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = true
                    )
                )
                delay(250)
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = false
                    )
                )
            } else {
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId,
                        isShimmer = false
                    )
                )
            }
        }
    }

    fun setPickedImage(uri: String) {
        viewModelScope.launch {
            _appStateFlow.emit(SharedAppState.ImagePickerState(pickedUri = uri))
        }
    }

}