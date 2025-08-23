package com.example.autoinspectionapp.presentation.ui.actvities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.databinding.ActivityMainBinding
import com.example.autoinspectionapp.domain.Legend
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.example.autoinspectionapp.domain.models.BodyStructureFunctionBO
import com.example.commons.base.base.BaseActivity
import com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.exterior.ExteriorViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
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
        Legend("T", "Total Genuine", R.color.legend_red),
        Legend("F", "Faded", R.color.legend_blue),
        Legend("P", "Painted", R.color.legend_green),
        Legend("A1", "Minor Scratch", R.color.legend_yellow),
        Legend("A2", "Major Scratch", R.color.legend_orange),
        Legend("E1", "Minor Dent", R.color.legend_purple),
        Legend("E2", "Major Dent", R.color.legend_teal),
        Legend("LS", "Lacquer Shower", R.color.legend_brown),
        Legend("W", "Dry Dented", R.color.legend_gray),
        Legend("G1", "Glass Scratched", R.color.legend_cyan),
        Legend("G2", "Glass Broken", R.color.legend_magenta),
        Legend("G3", "Glass Replaced", R.color.legend_indigo),
        Legend("G4", "Glass Chipped", R.color.legend_lime),
        Legend("B", "Broken", R.color.legend_black),
        Legend("PT", "Pen Touching", R.color.legend_pink),
        Legend("PP", "Partial Paint", R.color.legend_amber),
        Legend("C", "Corrosion", R.color.legend_deep_orange),
        Legend("XX", "Replaced", R.color.legend_light_blue),
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
            frontDriverFender = getDamageFor("frontDriverFender"),
            bonnet = getDamageFor("bonnet"),
            frontWindshield = getDamageFor("frontWindshield"),
            frontPassengerFender = getDamageFor("frontPassengerFender"),
            frontPassengerDoor = getDamageFor("frontPassengerDoor"),
            rearPassengerDoor = getDamageFor("rearPassengerDoor"),
            rearPassengerFender = getDamageFor("rearPassengerFender"),
            trunk = getDamageFor("trunk"),
            rearWindshield = getDamageFor("rearWindshield"),
            rearDriverFender = getDamageFor("rearDriverFender"),
            rearDriverDoor = getDamageFor("rearDriverDoor"),
            frontDriverDoor = getDamageFor("frontDriverDoor"),
            roof = getDamageFor("roof"),
            driverAPillar = getDamageFor("driverAPillar"),
            driverBPillar = getDamageFor("driverBPillar"),
            driverCPillar = getDamageFor("driverCPillar"),
            driverDPillar = getDamageFor("driverDPillar"),
            passengerAPillar = getDamageFor("passengerAPillar"),
            passengerBPillar = getDamageFor("passengerBPillar"),
            passengerCPillar = getDamageFor("passengerCPillar"),
            passengerDPillar = getDamageFor("passengerDPillar"),
            frontBumper = getDamageFor("frontBumper"),
        )
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
}