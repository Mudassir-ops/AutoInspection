package com.example.autoinspectionapp.presentation.ui.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.data.local.repository.AutoCarInspectionDbRepoImpl
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.sealed.SharedAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(autoCarInspectionDbRepo: AutoCarInspectionDbRepo) :
    ViewModel() {

    private val _appStateFlow = MutableStateFlow<SharedAppState>(SharedAppState.Idle)
    val appStateFlow: StateFlow<SharedAppState> = _appStateFlow.asStateFlow()

    val isDataPresent: StateFlow<Boolean> = autoCarInspectionDbRepo.isAnyDataPresentFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun loadHideShimmer(visibleOrHide: Boolean, buttonId: Int = -1) {
        viewModelScope.launch {
            if (visibleOrHide) {
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId, isShimmer = true
                    )
                )
                delay(timeMillis = 250)
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId, isShimmer = false
                    )
                )
            } else {
                _appStateFlow.emit(
                    SharedAppState.ShimmerVisibility(
                        buttonId = buttonId, isShimmer = false
                    )
                )
            }
        }
    }

}