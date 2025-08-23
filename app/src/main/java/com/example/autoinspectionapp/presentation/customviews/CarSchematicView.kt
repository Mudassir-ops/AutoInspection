package com.example.autoinspectionapp.presentation.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.get
import androidx.core.graphics.scale
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PartWithDamage
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

@SuppressLint("ClickableViewAccessibility")
class CarSchematicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val carImage = BitmapFactory.decodeResource(resources, R.drawable.oringal_image)
    private val maskImage = BitmapFactory.decodeResource(resources, R.drawable.dummy_color_image)
    private var scaledCarBitmap: Bitmap? = null
    private var scaledMaskBitmap: Bitmap? = null
    private var imageLeft = 0f
    private var imageTop = 0f
    private var imageScale = 1f
    var onTouchCallback: ((x: Float, y: Float, partName: String) -> Unit)? = null
    private val legendPoints = mutableListOf<PartWithDamage>()
    var eraserMode: Boolean = false
        set(value) {
            field = value
            invalidate()
        }

    val legendWithDamageParts: MutableList<PartWithDamage>
        get() = legendPoints

    val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 30f
        textAlign = Paint.Align.CENTER
    }


    private val strokePaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 2f
        isAntiAlias = true
    }
    private val circleRadius = 25F

    private fun removeCircle(x: Float, y: Float) {
        val iterator = legendPoints.iterator()
        while (iterator.hasNext()) {
            val p = iterator.next()
            val dist = sqrt((p.point.x - x).pow(2) + (p.point.y - y).pow(2))
            if (dist <= circleRadius * 1.5f) {
                iterator.remove()
                break
            }
        }
    }

    fun addDamagePoint(x: Float, y: Float, code: String, colorRes: Int?, partName: String) {
        val color = colorRes?.let { ContextCompat.getColor(context, it) } ?: Color.RED
        legendPoints.add(
            PartWithDamage(
                point = PointF(x, y),
                code = code,
                color = color,
                partName = partName
            )
        )
        invalidate()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                LogsHelper().createLog("CarPart", "User clicked on: ${PointF(event.x, event.y)} 🚗")
                val part = getPartAtTouch(event.x, event.y)
                if (part != null) {
                    if (eraserMode) {
                        removeCircle(event.x, event.y)
                    } else {
                        onTouchCallback?.invoke(event.x, event.y, part)
                    }
                    LogsHelper().createLog("CarPart", "User clicked on: $part 🚗")
                } else {
                    Log.w("CarPart", "No part found at (${event.x}, ${event.y})")
                }
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        scaledCarBitmap?.let {
            canvas.drawBitmap(it, imageLeft, imageTop, null)
        }
        legendPoints.forEach { (point, code, colorCode) ->
            paintCircle.color = colorCode
            canvas.drawCircle(point.x, point.y, circleRadius, paintCircle)
            canvas.drawCircle(point.x, point.y, circleRadius, strokePaint)
            val textY = point.y - (paintText.descent() + paintText.ascent()) / 2
            canvas.drawText(code, point.x, textY, paintText)
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (carImage == null || maskImage == null) {
            Log.e("onSizeChanged", "Images are null")
            return
        }
        imageScale = min(
            w.toFloat() / carImage.width, h.toFloat() / carImage.height
        )
        val newWidth = (carImage.width * imageScale).toInt()
        val newHeight = (carImage.height * imageScale).toInt()
        scaledCarBitmap = carImage.scale(newWidth, newHeight)
        scaledMaskBitmap = maskImage.scale(newWidth, newHeight)
        imageLeft = (w - newWidth) / 2f
        imageTop = (h - newHeight) / 2f

        Log.d("onSizeChanged", "Scaled to: ${newWidth}x${newHeight}")
    }

    private fun getPartAtTouch(x: Float, y: Float): String? {
        scaledMaskBitmap?.let { bmp ->
            val bmpX = (x - imageLeft).toInt()
            val bmpY = (y - imageTop).toInt()

            if (bmpX in 0 until bmp.width && bmpY in 0 until bmp.height) {
                val color = bmp[bmpX, bmpY]
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)

                Log.d("getPartAtTouch", "Touch=($x,$y) -> Mask=($bmpX,$bmpY) RGB=($r,$g,$b)")

                return detectCarPart(r, g, b)
            }
        }
        return null
    }

    private val partColors = mapOf(
        "bonnet" to Triple(218, 131, 249),
        "frontBumper" to Triple(19, 243, 255),
        "frontPassengerDoor" to Triple(20, 17, 17),
        "frontDriverFender" to Triple(0, 166, 200),
        "frontWindShield" to Triple(223, 249, 255),
        "frontPassengerFender" to Triple(243, 17, 17),
        "rearPassengerDoor" to Triple(101, 32, 32),
        "rearPassengerFender" to Triple(250, 0, 146),
        "trunk" to Triple(83, 71, 134),
        "rearWindShield" to Triple(229, 223, 255),
        "rearDriverFender" to Triple(95, 205, 159),
        "rearDriverDoor" to Triple(223, 255, 241),
        "frontDriverDoor" to Triple(74, 82, 32),
        "roof" to Triple(228, 206, 11),
        "backBumper" to Triple(169, 121, 203),
        "passengerFootBoard" to Triple(58, 161, 205),
        "driverFootBoard" to Triple(255, 2, 242)
    )

    private fun detectCarPart(r: Int, g: Int, b: Int): String? {
        return partColors.entries.firstOrNull { (_, rgb) ->
            rgb.first == r && rgb.second == g && rgb.third == b
        }?.key
    }

}

