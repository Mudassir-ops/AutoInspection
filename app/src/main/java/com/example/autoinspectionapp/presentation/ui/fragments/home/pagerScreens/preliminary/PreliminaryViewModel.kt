package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.preliminary

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.PreliminaryInfoBO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreliminaryViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    var uploadImage = ObservableField<String?>()
    var imagePath: String = ""

    fun onNext(preliminaryInfoBO: PreliminaryInfoBO) {
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertPreliminaryInfo(info = preliminaryInfoBO.toEntity())
        }
    }

}