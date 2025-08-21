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

    private fun updateParts(entity: BodyStructureFunctionEntity) {
        partUiMap["bonnet"]?.set(entity.bonnet.damageCodes.joinToString { it.code })
        partUiMap["frontbumper"]?.set(entity.frontBumper.damageCodes.joinToString { it.code })
        partUiMap["frontpassengerdoor"]?.set(entity.frontPassengerDoor.damageCodes.joinToString { it.code })
        partUiMap["frontdriverfender"]?.set(entity.frontDriverFender.damageCodes.joinToString { it.code })
        partUiMap["frontwindshield"]?.set(entity.frontWindshield.damageCodes.joinToString { it.code })
        partUiMap["frontpassengerfender"]?.set(entity.frontPassengerFender.damageCodes.joinToString { it.code })
        partUiMap["rearpassengerdoor"]?.set(entity.rearPassengerDoor.damageCodes.joinToString { it.code })
        partUiMap["rearpassengerfender"]?.set(entity.rearPassengerFender.damageCodes.joinToString { it.code })
        partUiMap["trunk"]?.set(entity.trunk.damageCodes.joinToString { it.code })
        partUiMap["rearwindshield"]?.set(entity.rearWindshield.damageCodes.joinToString { it.code })
        partUiMap["reardriverfender"]?.set(entity.rearDriverFender.damageCodes.joinToString { it.code })
        partUiMap["reardriverdoor"]?.set(entity.rearDriverDoor.damageCodes.joinToString { it.code })
        partUiMap["frontdriverdoor"]?.set(entity.frontDriverDoor.damageCodes.joinToString { it.code })
        partUiMap["roof"]?.set(entity.roof?.damageCodes?.joinToString { it.code }
            ?: "")
        partUiMap["driverapillar"]?.set(entity.driverAPillar.damageCodes.joinToString { it.code })
        partUiMap["driverbpillar"]?.set(entity.driverBPillar.damageCodes.joinToString { it.code })
        partUiMap["drivercpillar"]?.set(entity.driverCPillar.damageCodes.joinToString { it.code })
        partUiMap["driverdpillar"]?.set(entity.driverDPillar.damageCodes.joinToString { it.code })
        partUiMap["passengerapillar"]?.set(entity.passengerAPillar.damageCodes.joinToString { it.code })
        partUiMap["passengerbpillar"]?.set(entity.passengerBPillar.damageCodes.joinToString { it.code })
        partUiMap["passengercpillar"]?.set(entity.passengerCPillar.damageCodes.joinToString { it.code })
        partUiMap["passengerdpillar"]?.set(entity.passengerDPillar.damageCodes.joinToString { it.code })
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