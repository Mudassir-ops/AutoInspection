package com.example.autoinspectionapp.presentation.ui.actvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.ActivityMainBinding
import com.example.autoinspectionapp.domain.Legend
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.example.autoinspectionapp.domain.models.BodyStructureFunctionBO
import com.example.autoinspectionapp.presentation.dialog.showTyreSeekBar
import com.example.commons.base.base.BaseActivity
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.exterior.ExteriorViewModel
import com.example.commons.CarPart
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class CarSchemanticViewActivity : BaseActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ExteriorViewModel>()

    @Inject
    lateinit var logsHelper: LogsHelper

    private var eraserMode = false
    val legends = listOf(
        Legend("T", "Total Genuine", R.color.legend_green),
        Legend("F", "Faded", R.color.legend_gray),
        Legend("P", "Painted", R.color.legend_red),
        Legend("A1", "Minor Scratch", R.color.legend_yellow),
        Legend("A2", "Major Scratch", R.color.legend_orange),
        Legend("E1", "Minor Dent", R.color.legend_purple),
        Legend("E2", "Major Dent", R.color.legend_teal),
        Legend("LS", "Lacquer Shower", R.color.legend_orange),
        Legend("W", "Dry Dented", R.color.legend_blue),
        Legend("G1", "Glass Scratched", R.color.legend_cyan),
        Legend("G2", "Glass Broken", R.color.legend_magenta),
        Legend("G3", "Glass Replaced", R.color.red),
        Legend("G4", "Glass Chipped", R.color.legend_lime),
        Legend("B", "Broken", R.color.red),
        Legend("PT", "Pen Touching", R.color.legend_pink),
        Legend("PP", "Partial Paint", R.color.legend_yellow),
        Legend("C", "Corrosion", R.color.legend_brown),
        Legend("XX", "Replaced", R.color.red),
        Legend("PL", "Policate Repaired", R.color.legend_deep_purple),
    )


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.apply {
            btnEraser.setOnClickListener {
                eraserMode = !eraserMode
                carSchematicView.eraserMode = eraserMode
                binding.btnEraser.text = if (eraserMode) "Eraser: ON" else "Eraser: OFF"
            }
            carSchematicView.onTouchCallback = { x, y, partName ->
                if (partName.isTyreTouched()) {
                    showTyreSeekBar(this@CarSchemanticViewActivity) { value ->
                        carSchematicView.addDamagePoint(
                            x = x,
                            y = y,
                            code = "$value",
                            colorRes = value.getTyreColorCode(),
                            partName = partName
                        )
                    }
                } else {
                    showLegendDialog { selectedLegend ->
                        carSchematicView.addDamagePoint(
                            x = x,
                            y = y,
                            code = selectedLegend.code,
                            colorRes = selectedLegend.legendFilledColor,
                            partName = partName
                        )
                    }
                }
            }
            btnSave.setOnClickListener {
                showLoader()
                carSchematicView.legendWithDamageParts
                    .groupBy { it.partName }
                    .map { (partName, damages) ->
                        PartDamageSummary(
                            partName = partName,
                            damageCodes = damages
                        )
                    }.onSave()
            }
        }
    }

    private fun showLegendDialog(onSelected: (Legend) -> Unit) {
        val items = legends.map { "${it.code} - ${it.description}" }.toTypedArray()
        AlertDialog.Builder(this@CarSchemanticViewActivity)
            .setTitle("Select Legend")
            .setItems(items) { _, which ->
                onSelected(legends[which])
            }
            .show()
    }

    fun List<PartDamageSummary>.onSave() {
        logsHelper.createLog("onSave-->${Gson().toJson(this)}")
        val bodyStructureFunctionBO = BodyStructureFunctionBO(
            trunkLock = "N/A",
            frontDriverFender = getDamageFor(CarPart.FRONT_DRIVER_FENDER.key),
            bonnet = getDamageFor(CarPart.BONNET.key),
            frontWindshield = getDamageFor(CarPart.FRONT_WINDSHIELD.key),
            frontPassengerFender = getDamageFor(CarPart.FRONT_PASSENGER_FENDER.key),
            frontPassengerDoor = getDamageFor(CarPart.FRONT_PASSENGER_DOOR.key),
            rearPassengerDoor = getDamageFor(CarPart.REAR_PASSENGER_DOOR.key),
            rearPassengerFender = getDamageFor(CarPart.REAR_PASSENGER_FENDER.key),
            trunk = getDamageFor(CarPart.TRUNK.key),
            rearWindshield = getDamageFor(CarPart.REAR_WINDSHIELD.key),
            rearDriverFender = getDamageFor(CarPart.REAR_DRIVER_FENDER.key),
            rearDriverDoor = getDamageFor(CarPart.REAR_DRIVER_DOOR.key),
            frontDriverDoor = getDamageFor(CarPart.FRONT_DRIVER_DOOR.key),
            roof = getDamageFor(CarPart.ROOF.key),
            frontBumper = getDamageFor(CarPart.FRONT_BUMPER.key),
            backBumper = getDamageFor(CarPart.BACK_BUMPER.key),
            passengerAPillar = getDamageFor(CarPart.PASSENGER_A_PILLAR.key),
            passengerBPillar = getDamageFor(CarPart.PASSENGER_B_PILLAR.key),
            passengerCPillar = getDamageFor(CarPart.PASSENGER_C_PILLAR.key),
            passengerDPillar = getDamageFor(CarPart.PASSENGER_D_PILLAR.key),

            driverPillarA = getDamageFor(CarPart.DRIVER_A_PILLAR.key),
            driverPillarB = getDamageFor(CarPart.DRIVER_B_PILLAR.key),
            driverPillarC = getDamageFor(CarPart.DRIVER_C_PILLAR.key),
            driverPillarD = getDamageFor(CarPart.DRIVER_D_PILLAR.key),

            rearDriverTyre = getDamageFor(CarPart.REAR_DRIVER_TYRE.key),
            rearPassengerTyre = getDamageFor(CarPart.REAR_PASSENGER_TYRE.key),
            frontDriverTyre = getDamageFor(CarPart.FRONT_DRIVER_TYRE.key),
            frontPassengerTyre = getDamageFor(CarPart.FRONT_PASSENGER_TYRE.key)
        )


        lifecycleScope.launch {
            val file = File(getExternalFilesDir(null), "car_schematic.png")
            binding.carSchematicView.saveToGallery(this@CarSchemanticViewActivity)
            hideLoader()
        }

        viewModel.onNext(bodyStructureFunctionBO) { result ->
            result.onSuccess {
                hideLoader()
                finish()
            }.onFailure { exception ->
                hideLoader()
                exception.printStackTrace()
            }
        }
    }

    fun List<PartDamageSummary>.getDamageFor(part: String): PartDamageSummary? {
        return this.firstOrNull { it.partName == part }
    }

    fun String.isTyreTouched(): Boolean {
        return when (this) {
            CarPart.REAR_DRIVER_TYRE.key,
            CarPart.REAR_PASSENGER_TYRE.key,
            CarPart.FRONT_DRIVER_TYRE.key,
            CarPart.FRONT_PASSENGER_TYRE.key -> true

            else -> false
        }
    }

    fun Int.getTyreColorCode(): Int {
        return when {
            this >= 80 -> R.color.red
            this >= 60 -> R.color.legend_orange
            else -> R.color.legend_green
        }
    }

}