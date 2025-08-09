package com.example.autoinspectionapp

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.autoinspectionapp.databinding.ActivityMainBinding
import androidx.core.graphics.createBitmap

class TestActivity2 : AppCompatActivity() {
    private fun showCircleAt(parent: FrameLayout, x: Float, y: Float) {
        val sizePx = 100
        val circleView = View(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(sizePx, sizePx)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.RED)
            }
        }
        circleView.x = x - sizePx / 2
        circleView.y = y - sizePx / 2

        parent.addView(circleView)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //  private lateinit var carSchematicView: CarSchematicView
    private var eraserMode = false
    private fun drawCircleAtView1(targetView: View) {
        // Get location of the view
        val location = IntArray(2)
        targetView.getLocationOnScreen(location)
        val centerX = location[0] + targetView.width / 2f
        val centerY = location[1] + targetView.height / 2f
        val radius = 10F

        // Create bitmap with transparent background
        val bitmap = createBitmap(targetView.rootView.width, targetView.rootView.height)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        // Draw the circle
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Show the bitmap in an overlay ImageView
        val overlayImageView = ImageView(this).apply {
            setImageBitmap(bitmap)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        (targetView.rootView as ViewGroup).addView(overlayImageView)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //carSchematicView = binding.carSchematicView

        binding.apply {
            carRoofArea.setOnTouchListener { v, event ->
                onAction(v, event,carRoofArea)
                true
            }


            carRoofBack1Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofBack1Area)
                Log.e("setOnLongClickListener", "carRoofBack1Area: ")
                true
            }

            carRoofBack2Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofBack2Area)
                Log.e("setOnLongClickListener", "carRoofBack2Area: ")
                true
            }

            carRoofBack3Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofBack3Area)
                Log.e("setOnLongClickListener", "carRoofBack3Area: ")
                true
            }

            carRoofFront1Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofFront1Area)
                Log.e("setOnLongClickListener", "carRoofFront1Area: ")
                true
            }

            carRoofFront2Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofFront2Area)
                Log.e("setOnLongClickListener", "carRoofFront2Area: ")
                true
            }

            carRoofFront3Area.setOnTouchListener { v, event ->
                onAction(v, event,carRoofFront3Area)
                Log.e("setOnLongClickListener", "carRoofFront3Area: ")
                true
            }

            carTyre1.setOnTouchListener { v, event ->
                onAction(v, event,carTyre1)
                Log.e("setOnLongClickListener", "carTyre1: ")
                true
            }

            carTyre2.setOnTouchListener { v, event ->
                onAction(v, event,carTyre2)
                Log.e("setOnLongClickListener", "carTyre2: ")
                true
            }

            carTyre3.setOnTouchListener { v, event ->
                onAction(v, event,carTyre3)
                Log.e("setOnLongClickListener", "carTyre3: ")
                true
            }

            carTyre4.setOnTouchListener { v, event ->
                onAction(v, event,carTyre4)
                Log.e("setOnLongClickListener", "carTyre3: ")
                true
            }


        }
        //        carSchematicView.onDamageRequested = { x, y ->
//            showDamageSelectionDialog(x, y)
//        }
//        binding.btnEraser.setOnClickListener {
//            eraserMode = !eraserMode
//            carSchematicView.isEraserMode = eraserMode
//            binding.btnEraser.text = if (eraserMode) "Eraser: ON" else "Eraser: OFF"
//        }
//
//        carSchematicView.onMarkerErased = { erased ->
//            Snackbar.make(binding.root, "Removed: ${erased.code}", Snackbar.LENGTH_SHORT).show()
//        }
//    }
//

    }

    private fun showCircleAt(parent: ViewGroup, x: Float, y: Float, selectedCode: String? = null) {
        val sizePx = 60
        val circleView = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(sizePx, sizePx)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.RED)
            }
            setTextColor(Color.WHITE)
            textSize = 16f
            gravity = Gravity.CENTER
            text = selectedCode ?: ""
        }

        // Position exactly at touch point
        circleView.translationX = x - (sizePx / 2)
        circleView.translationY = y - (sizePx / 2)

        parent.addView(circleView)
    }

    private fun showDamageSelectionDialog(x: Float, y: Float,viewGroup: ViewGroup) {
        val damageCodes = arrayOf("T", "A1", "A2", "E1", "E2", "W", "B", "PT")

        AlertDialog.Builder(this)
            .setTitle("Select Damage Code")
            .setItems(damageCodes) { _, which ->
                val selectedCode = damageCodes[which]
                // Place circle with code text
                showCircleAt(viewGroup, x, y, selectedCode)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onAction(v: View, event: MotionEvent,viewGroup: ViewGroup) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            v.performClick() // accessibility
        }
        if (event.action == MotionEvent.ACTION_MOVE || event.action == MotionEvent.ACTION_UP) {
            // Ignore here, just showing DOWN for simplicity
        }
        if (event.action == MotionEvent.ACTION_DOWN) {
            showDamageSelectionDialog(event.x, event.y, viewGroup =viewGroup )
        }
    }


}