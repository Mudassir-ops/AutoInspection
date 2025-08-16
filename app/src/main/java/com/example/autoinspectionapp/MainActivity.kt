package com.example.autoinspectionapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.example.autoinspectionapp.databinding.CarCenterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        CarCenterBinding.inflate(layoutInflater)
    }
    private val damageColors = mapOf(
        "T" to "#FFA500".toColorInt(), // Orange instead of Yellow
        "A1" to Color.RED,
        "A2" to Color.MAGENTA,
        "E1" to Color.BLUE,
        "E2" to Color.CYAN,
        "W" to "#8B4513".toColorInt(),
        "B" to "#800080".toColorInt(), // Purple
        "PT" to "#00CED1".toColorInt() // Dark Turquoise
    )


    private val genericTouchListener = object : CircleImageView.OnValidTouchListener {
        override fun onValidTouch(view: CircleImageView, x: Float, y: Float) {
            showDamageSelectionDialog(x, y, view)
            Log.d("CircleTouch", "Touched  at ($x, $y)")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.apply {
            listOf(
                carTyre2,
                carMudGuardBack,
                carSide1,
                carTyre1,
                carMudGuard,
                carBackDoor,
                carFrontDoor,
                carRoofArea,
                carRoofArea1,
                carRoofArea2,
                carRoofArea3,
                carRoofFront1,
                carBonut,
                carFrontBumper,
                carBottomBackDoor,
                carBottomFrontDoor,
                carBottomFrontBumper,
                carTyre3,
                carBottomBackBonut,
                tyre4,
                carBottomMud
            ).forEach { imageView ->
                imageView.onValidTouchListener = genericTouchListener
            }
        }
    }


    private fun showDamageSelectionDialog(x: Float, y: Float, viewGroup: CircleImageView) {
        val damageCodes = arrayOf("T", "A1", "A2", "E1", "E2", "W", "B", "PT")
        AlertDialog.Builder(this)
            .setTitle("Select Damage Code")
            .setItems(damageCodes) { _, which ->
                val selectedCode = damageCodes[which]
                val selectedColor = damageColors[selectedCode] ?: Color.RED
                showCircleAt(viewGroup, x, y, selectedCode, selectedColor)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun showCircleAt(
        imageView: CircleImageView,
        x: Float,
        y: Float,
        selectedCode: String? = null,
        selectedColor: Int = Color.RED
    ) {
        imageView.addCircle(x, y, selectedCode, selectedColor)
    }

}