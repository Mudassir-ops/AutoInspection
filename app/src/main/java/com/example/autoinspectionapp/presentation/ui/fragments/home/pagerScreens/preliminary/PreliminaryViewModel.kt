package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.preliminary

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.mappers.toUI
import com.example.autoinspectionapp.domain.models.PreliminaryInfoBO
import com.example.autoinspectionapp.domain.sealed.PagesDataState
import com.example.autoinspectionapp.utils.enums.Section
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreliminaryViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    var uploadImage = ObservableField<String?>()
    var imagePath: String = ""

    init {
        getData()
    }


    fun onNext(preliminaryInfoBO: PreliminaryInfoBO) {
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertPreliminaryInfo(info = preliminaryInfoBO.toEntity())
        }
    }

    private val _preliminaryDataStateFlow =
        MutableStateFlow<PagesDataState>(PagesDataState.Init)
    val preliminaryDataStateFlow: StateFlow<PagesDataState> =
        _preliminaryDataStateFlow.asStateFlow()

    private fun getData() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getPreliminaryData().collect { data ->
                data?.let {
                    LogsHelper().createLog("getData--${Gson().toJson(data)}")
                    _preliminaryDataStateFlow.emit(
                        value = PagesDataState.Data(
                            section = Section.PRELIMINARY_INFO,
                            data = data.toUI()
                        )
                    )
                }
            }
        }
    }

}