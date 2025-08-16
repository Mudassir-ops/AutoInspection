package com.example.autoinspectionapp.ui.home.pagerScreens.preliminary

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.PreliminaryInfoBO
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreliminaryViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    var uploadImage = ObservableField<Uri?>()
    var imagePath: String = ""

    fun onNext(preliminaryInfoBO: PreliminaryInfoBO) {
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertPreliminaryInfo(info = preliminaryInfoBO.toEntity())
        }
    }

}