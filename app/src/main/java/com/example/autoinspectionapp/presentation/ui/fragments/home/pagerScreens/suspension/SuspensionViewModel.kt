package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.suspension

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.mappers.toEntity
import com.example.autoinspectionapp.domain.models.SuspensionSteeringFunctionBO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuspensionViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {

    // Steering Assembly Play
    val steeringAssemblyPlayItems = listOf("Not Present", "Present", "Normal", "Abnormal", "Jerky", "N/A")

    // Axle Boots
    val axleBootsItems = listOf("Normal", "Rubber Worn", "Damaged", "Present", "Not Present", "Seepage", "N/A")

    // Right Ball Joint
    val rightBallJointItems = listOf("Normal", "Require Service", "Damaged", "Excessive Wear", "N/A")

    // Left Ball Joint
    val leftBallJointItems = listOf("Normal", "Require Service", "Damaged", "Excessive Wear", "N/A")

    // Tie Rod End
    val tieRodEndItems = listOf("Normal", "Require Service", "Damaged", "Excessive Wear", "N/A")

    // Right Boot
    val rightBootItems = listOf("Normal", "Rubber Worn", "Damaged", "Present", "Not Present", "Seepage", "N/A")

    // Left Boot
    val leftBootItems = listOf("Normal", "Rubber Worn", "Damaged", "Present", "Not Present", "Seepage", "N/A")

    // Right Bush
    val rightBushItems = listOf("Normal", "Require Service", "Damaged", "Excessive Wear", "N/A")

    // Left Bush
    val leftBushItems =  listOf("Normal", "Require Service", "Damaged", "Excessive Wear", "N/A")

    // Rear Right Shock Absorber
    val rearRightShockAbsorberItems = listOf("Normal", "Hard", "Require Service", "Soft", "N/A")

    // Rear Left Shock Absorber
    val rearLeftShockAbsorberItems = listOf("Normal", "Hard", "Require Service", "Soft", "N/A")

    // Front Right Shock Absorber
    val frontRightShockAbsorberItems = listOf("Normal", "Hard", "Require Service", "Soft", "N/A")

    // Front Left Shock Absorber
    val frontLeftShockAbsorberItems = listOf("Normal", "Hard", "Require Service", "Soft", "N/A")



    fun onNext(suspensionSteeringFunctionBO: SuspensionSteeringFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $suspensionSteeringFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertSuspensionSteeringFunctionEntity(
                suspensionSteeringFunction = suspensionSteeringFunctionBO.toEntity()
            )
        }
    }
}