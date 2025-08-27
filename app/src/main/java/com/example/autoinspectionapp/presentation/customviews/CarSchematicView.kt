package com.example.autoinspectionapp.presentation.customviews

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Environment
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import androidx.core.graphics.get
import androidx.core.graphics.scale
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.domain.LogsHelper
import com.example.autoinspectionapp.domain.PartWithDamage
import com.example.commons.CarPart
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

private fun decodeSampledBitmapFromResource(
    res: Resources,
    resId: Int
): Bitmap {
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeResource(res, resId, options)

    options.inSampleSize = calculateInSampleSize(options, 1080, 1920)
    options.inJustDecodeBounds = false

    return BitmapFactory.decodeResource(res, resId, options)
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val (height, width) = options.outHeight to options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2
        while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}


@SuppressLint("ClickableViewAccessibility")
class CarSchematicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val carImage =
        decodeSampledBitmapFromResource(resources, R.drawable.ooot1)
    private val maskImage =
        decodeSampledBitmapFromResource(resources, R.drawable.maskt1)

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
        canvas.drawColor(Color.WHITE)
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
        CarPart.BONNET.key to Triple(218, 131, 249),
        CarPart.FRONT_BUMPER.key to Triple(19, 243, 255),
        CarPart.FRONT_PASSENGER_DOOR.key to Triple(20, 17, 17),
        CarPart.FRONT_DRIVER_FENDER.key to Triple(0, 166, 200),
        CarPart.FRONT_WINDSHIELD.key to Triple(223, 249, 255),
        CarPart.FRONT_PASSENGER_FENDER.key to Triple(243, 17, 17),
        CarPart.REAR_PASSENGER_DOOR.key to Triple(101, 32, 32),
        CarPart.REAR_PASSENGER_FENDER.key to Triple(250, 0, 146),
        CarPart.TRUNK.key to Triple(83, 71, 134),
        CarPart.REAR_WINDSHIELD.key to Triple(229, 223, 255),
        CarPart.REAR_DRIVER_FENDER.key to Triple(95, 205, 159),
        CarPart.REAR_DRIVER_DOOR.key to Triple(223, 255, 241),
        CarPart.FRONT_DRIVER_DOOR.key to Triple(74, 82, 32),
        CarPart.ROOF.key to Triple(228, 206, 11),

        // Optional parts (if you support them)
        CarPart.DRIVER_A_PILLAR.key to Triple(196, 59, 177),
        CarPart.DRIVER_B_PILLAR.key to Triple(194, 153, 228),
        CarPart.DRIVER_C_PILLAR.key to Triple(255, 25, 25),
        CarPart.DRIVER_D_PILLAR.key to Triple(82, 238, 30),
        CarPart.PASSENGER_A_PILLAR.key to Triple(120, 151, 201),
        CarPart.PASSENGER_B_PILLAR.key to Triple(1, 177, 183),
        CarPart.PASSENGER_C_PILLAR.key to Triple(197, 38, 132),
        CarPart.PASSENGER_D_PILLAR.key to Triple(105, 234, 80),

        CarPart.REAR_DRIVER_TYRE.key to Triple(251, 180, 217),
        CarPart.REAR_PASSENGER_TYRE.key to Triple(3, 53, 122),
        CarPart.FRONT_DRIVER_TYRE.key to Triple(23, 101, 128),
        CarPart.FRONT_PASSENGER_TYRE.key to Triple(219, 126, 202),
        CarPart.BACK_BUMPER.key to Triple(169, 121, 203),
        "passengerFootBoard" to Triple(58, 161, 205),
        "driverFootBoard" to Triple(255, 2, 242)
    )


    private fun detectCarPart(r: Int, g: Int, b: Int): String? {
        return partColors.entries.firstOrNull { (_, rgb) ->
            rgb.first == r && rgb.second == g && rgb.third == b
        }?.key
    }

    fun saveToGallery(context: Context, scaleFactor: Float = 4f) {
        // Create a larger bitmap for high resolution
        val bitmap = createBitmap((width * scaleFactor).toInt(), (height * scaleFactor).toInt())
        val canvas = Canvas(bitmap)

        // Scale the canvas so drawings expand proportionally
        canvas.scale(scaleFactor, scaleFactor)

        // Set background color
        canvas.drawColor(Color.WHITE)

        // Draw your view's content at higher scale
        draw(canvas)

        // Save with MediaStore
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "car_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + "/CarSchematics"
            )
        }
        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
        }
        Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
    }

}

