package com.example.autoinspectionapp.ui.home.pagerScreens.exterior

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoinspectionapp.data.local.BodyStructureFunctionEntity
import com.example.autoinspectionapp.domain.BodyStructureFunctionBO
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo
import com.example.autoinspectionapp.domain.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExteriorViewModel @Inject constructor(
    private val autoCarInspectionDbRepo: AutoCarInspectionDbRepo
) : ViewModel() {
    val spinnerList = listOf(
        "Not Present",
        "Normal",
        "Smooth ",
        "Working",
        "Not Working",
        "Present",
        "Seepage",
        "Abnormal",
        "Jerky",
        "Noisey",
        "N/A"
    )
    private val partNames = listOf(
        "bonnet",
        "frontBumper",
        "frontPassengerDoor",
        "frontDriverFender",
        "frontWindShield",
        "frontPassengerFender",
        "rearPassengerDoor",
        "rearPassengerFender",
        "trunk",
        "rearWindShield",
        "rearDriverFender",
        "rearDriverDoor",
        "frontDriverDoor",
        "roof",
        "backBumper",
        "passengerFootBoard",
        "driverFootBoard"
    )
    val partUiMap: Map<String, ObservableField<String>> =
        partNames.associateWith { ObservableField("") }


    init {
        viewModelScope.launch {
            getExteriorBodyPart()
        }
    }

    private fun getExteriorBodyPart() {
        viewModelScope.launch {
            autoCarInspectionDbRepo.getBodyExterior().collect { summary ->
                LogsHelper().createLog("getBodyExterior--$summary")
                updateParts(summary)
            }
        }
    }

    private fun updateParts(entity: BodyStructureFunctionEntity?) {
        entity?.let {
            partUiMap["bonnet".lowercase()]?.set(entity.bonnet?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontBumper".lowercase()]?.set(entity.frontBumper?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontPassengerDoor".lowercase()]?.set(entity.frontPassengerDoor?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontDriverFender".lowercase()]?.set(entity.frontDriverFender?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontWindShield".lowercase()]?.set(entity.frontWindshield?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontPassengerFender".lowercase()]?.set(entity.frontPassengerFender?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["rearPassengerDoor".lowercase()]?.set(entity.rearPassengerDoor?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["rearPassengerFender".lowercase()]?.set(entity.rearPassengerFender?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["trunk".lowercase()]?.set(entity.trunk?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["rearWindShield".lowercase()]?.set(entity.rearWindshield?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["rearDriverFender".lowercase()]?.set(entity.rearDriverFender?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["rearDriverDoor".lowercase()]?.set(entity.rearDriverDoor?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["frontDriverDoor".lowercase()]?.set(entity.frontDriverDoor?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["roof".lowercase()]?.set(entity.roof?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["driverAPillar".lowercase()]?.set(entity.driverAPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["driverBPillar".lowercase()]?.set(entity.driverBPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["driverCPillar".lowercase()]?.set(entity.driverCPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["driverDPillar".lowercase()]?.set(entity.driverDPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["passengerAPillar".lowercase()]?.set(entity.passengerAPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["passengerBPillar".lowercase()]?.set(entity.passengerBPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["passengerCPillar".lowercase()]?.set(entity.passengerCPillar?.damageCodes?.joinToString { it.code }
                ?: "")
            partUiMap["passengerDPillar".lowercase()]?.set(entity.passengerDPillar?.damageCodes?.joinToString { it.code }
                ?: "")
        }
    }


    fun onNext(bodyStructureFunctionBO: BodyStructureFunctionBO) {
        Log.e("accidentChecklistBO", "onNext: $bodyStructureFunctionBO")
        viewModelScope.launch {
            autoCarInspectionDbRepo.insertBodyStructureFunctionEntity(
                bodyStructureFunction = bodyStructureFunctionBO.toEntity()
            )
        }
    }

}