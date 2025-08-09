package com.example.autoinspectionapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class CarSchematicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val recordedShapes = mutableListOf<ShapeData>()
    private var currentPath = Path()
    private val currentPoints = mutableListOf<PointF>()

    private val shapePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = Color.RED
    }

    private val handlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    private val pointerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.MAGENTA
        style = Paint.Style.FILL
    }

    private val carImage = BitmapFactory.decodeResource(resources, R.drawable.car_schemantic)
    private var scaledBitmap: Bitmap? = null
    private var imageLeft = 0f
    private var imageTop = 0f
    private var imageScale = 1f

    // Positions
    private var handlePoint: PointF? = null
    private var pointerPoint: PointF? = null

    // Pointer is 80px ABOVE the handle
    private val pointerOffsetY = -80f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x - imageLeft
        val touchY = event.y - imageTop

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handlePoint = PointF(touchX, touchY)
                pointerPoint = PointF(touchX, touchY + pointerOffsetY)

                currentPath = Path().apply { moveTo(pointerPoint!!.x, pointerPoint!!.y) }
                currentPoints.clear()
                currentPoints.add(PointF(pointerPoint!!.x, pointerPoint!!.y))
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                // Move handle
                handlePoint?.set(touchX, touchY)

                // Pointer stays fixed above handle
                pointerPoint?.set(touchX, touchY + pointerOffsetY)

                // Draw path at pointer position
                currentPath.lineTo(pointerPoint!!.x, pointerPoint!!.y)
                currentPoints.add(PointF(pointerPoint!!.x, pointerPoint!!.y))
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                // Clear old drawings
                recordedShapes.clear()

                val finalPath = Path(currentPath)
                recordedShapes.add(
                    ShapeData(points = currentPoints.toList(), path = finalPath)
                )

                val jsonString = pointsToJson(currentPoints)
                Log.wtf("SavedPointsJSON", "onTouchEvent:$jsonString ")

                currentPath.reset()
                currentPoints.clear()
                invalidate()
            }

        }
        return true
    }


    private fun pointsToJson(points: List<PointF>): String {
        val jsonPoints = points.joinToString(prefix = "[", postfix = "]") {
            """{"x":${it.x},"y":${it.y}}"""
        }
        return jsonPoints
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val offset = 20f  // Adjust offset for a smoother curve

// Configure the Paint object for stroke (outline) only
        val handlePaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 3f
            color = Color.BLACK
            isAntiAlias = true
        }

        val path = Path().apply {
            moveTo(offset, offset)                     // Start near top-left, shifted
            quadTo(w / 4f, 0f, w / 2f, offset)        // Top-left curve toward center
            quadTo(3 * w / 4f, 0f, w - offset, offset) // Top-right curve
            lineTo(w - offset, h - offset)             // Right side straight down
            quadTo(3 * w / 4f, h, w / 2f, h - offset)  // Bottom-right curve
            quadTo(w / 4f, h, offset, h - offset)      // Bottom-left curve
            close()                                    // Close the path
        }

        canvas.save()
        canvas.translate(50f, 50f)  // Move shape as needed
        canvas.drawPath(path, handlePaint)
        canvas.restore()


//        // Draw background car image
//        scaledBitmap?.let {
//            canvas.drawBitmap(it, imageLeft, imageTop, null)
//        }

//        // Draw recorded shapes
//        for (shape in recordedShapes) {
//            canvas.drawPath(shape.path, shapePaint)
//        }
//
//        // Draw current path
//        canvas.drawPath(currentPath, shapePaint)
//
//        // Draw handle
//        handlePoint?.let {
//            val screenX = it.x + imageLeft
//            val screenY = it.y + imageTop
//            canvas.drawCircle(screenX, screenY, 20f, handlePaint)
//        }
//
//        // Draw pointer
//        pointerPoint?.let {
//            val screenX = it.x + imageLeft
//            val screenY = it.y + imageTop
//            canvas.drawCircle(screenX, screenY, 12f, pointerPaint)
//        }
    }

    data class ShapeData(
        val points: List<PointF>,
        val path: Path
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        imageScale = min(
            w.toFloat() / carImage.width,
            h.toFloat() / carImage.height
        )
        val newWidth = (carImage.width * imageScale).toInt()
        val newHeight = (carImage.height * imageScale).toInt()
        scaledBitmap = Bitmap.createScaledBitmap(carImage, newWidth, newHeight, true)
        imageLeft = (w - newWidth) / 2f
        imageTop = (h - newHeight) / 2f
    }
}


//class CarSchematicView @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null
//) : View(context, attrs) {
//    var onMarkerErased: ((Damage) -> Unit)? = null
//    private val carImage = BitmapFactory.decodeResource(resources, R.drawable.car_schemantic)
//    private var scaledBitmap: Bitmap? = null
//
//    private var imageLeft = 0f
//    private var imageTop = 0f
//    private var imageScale = 1f
//
//    private val damageMap = mutableMapOf<String, Damage>()
//
//    var isEraserMode: Boolean = false
//    var onDamageRequested: ((x: Float, y: Float) -> Unit)? = null
//    private val touchPoints = mutableListOf<PointF>()
//    private val drawPath = Path()
//
//    private val gestureDetector =
//        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
//            override fun onLongPress(e: MotionEvent) {
//                val touchX = e.x - imageLeft
//                val touchY = e.y - imageTop
//                if (isEraserMode) {
//                    removeNearestDamage(touchX, touchY)
//                } else {
//                    onDamageRequested?.invoke(touchX, touchY)
//                }
//            }
//        })
//
//    // Eraser pointer coordinates
//    private var eraserPointerX: Float? = null
//    private var eraserPointerY: Float? = null
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val touchX = event.x - imageLeft
//        val touchY = event.y - imageTop
//
//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> {
//                touchPoints.clear()
//                drawPath.reset()
//                drawPath.moveTo(touchX, touchY)
//                touchPoints.add(PointF(touchX, touchY))
//            }
//
//            MotionEvent.ACTION_MOVE -> {
//                drawPath.lineTo(touchX, touchY)
//                touchPoints.add(PointF(touchX, touchY))
//                invalidate()
//            }
//
//            MotionEvent.ACTION_UP -> {
//                // Finalize shape — you can fill it here
//                fillTouchedArea()
//            }
//        }
//        return true
//    }
//
//    private fun fillTouchedArea() {
//        val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//            style = Paint.Style.FILL
//            color = Color.argb(150, 255, 0, 0)
//        }
//        // Optionally close the path so it fills completely
//        drawPath.close()
//        canvas?.drawPath(drawPath, fillPaint)
//    }
//
////    override fun onTouchEvent(event: MotionEvent): Boolean {
////     //   if (isEraserMode) {
////            when (event.action) {
////                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
////                    eraserPointerX = event.x
////                    eraserPointerY = event.y
////                    touchedPoints.clear() // start fresh when finger goes down
////
////
////                    touchedPoints.add(event.x to event.y)
////
////                    Log.e("SattiHereeeee", "onTouchEvent: ${event.x}---${event.y}")
////                    invalidate()
////                }
////
////                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
////                    touchedPoints.add(event.x to event.y)
////                    Log.d("TouchPoints", "Recorded points: $touchedPoints")
////                    eraserPointerX = null
////                    eraserPointerY = null
////                    invalidate()
////                }
////            }
////      //  }
////
////        gestureDetector.onTouchEvent(event)
////        return true
////    }
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//
//        imageScale = min(
//            w.toFloat() / carImage.width,
//            h.toFloat() / carImage.height
//        )
//
//        val newWidth = (carImage.width * imageScale).toInt()
//        val newHeight = (carImage.height * imageScale).toInt()
//
//        scaledBitmap = Bitmap.createScaledBitmap(carImage, newWidth, newHeight, true)
//        imageLeft = (w - newWidth) / 2f
//        imageTop = (h - newHeight) / 2f
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        scaledBitmap?.let {
//            val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//                color = Color.RED
//                textSize = 5f
//                textAlign = Paint.Align.CENTER
//                typeface = Typeface.DEFAULT_BOLD
//            }
//            canvas.drawBitmap(it, imageLeft, imageTop, null)
//            val paint = Paint().apply { color = Color.RED; style = Paint.Style.FILL }
//            for (y in 0 until carImage.height step 50) { // step to avoid millions of points
//                for (x in 0 until carImage.width step 50) {
//                    val screenX = imageLeft + x * imageScale
//                    val screenY = imageTop + y * imageScale
//                    canvas.drawText("${x},${y}", screenX, screenY, numberPaint)
//                    // canvas.drawText()
//                    // canvas.drawCircle(screenX, screenY, 5f, paint)
//
//
//                }
//            }
//            // Draw all damage markers
//            damageMap.values.forEach { damage ->
//                val cx = imageLeft + damage.x
//                val cy = imageTop + damage.y
//                drawDamageCircle(canvas, cx, cy, damage.code)
//            }
//
//            // Draw eraser pointer (if in eraser mode)
//            if (isEraserMode && eraserPointerX != null && eraserPointerY != null) {
//                val x = eraserPointerX!!
//                val y = eraserPointerY!!
//
//                // 1. Small circle at finger touch
//                val fingerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//                    color = Color.RED
//                    style = Paint.Style.FILL
//                }
//
//                canvas.drawCircle(x, y, 12f, fingerPaint)
//
//                // 2. Outer eraser area indicator
//                val targetPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//                    color = Color.argb(100, 255, 0, 0) // translucent red
//                    style = Paint.Style.STROKE
//                    strokeWidth = 4f
//                }
//
//
//                // canvas.drawCircle(x, y, 40f, targetPaint)
//                // canvas.drawRect(x, y, 40f, targetPaint)
//            }
//        }
//    }
//
//    private fun drawDamageCircle(canvas: Canvas, x: Float, y: Float, code: String) {
//        val radius = 25f
//
//        val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//            color = getColorForCode(code)
//            style = Paint.Style.FILL
//        }
//
//        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//            color = Color.WHITE
//            textSize = 24f
//            textAlign = Paint.Align.CENTER
//            this.typeface = Typeface.DEFAULT_BOLD
//        }
//
//        canvas.drawCircle(x, y, radius, fillPaint)
//        canvas.drawText(code, x, y + 8f, textPaint)
//    }
//
//    private fun getColorForCode(code: String): Int = when (code.uppercase()) {
//        "A1", "A2" -> Color.RED
//        "W" -> Color.GREEN
//        "E1", "E2" -> Color.parseColor("#FFA500") // Orange
//        else -> Color.BLUE
//    }
//
//    fun addDamage(code: String, x: Float, y: Float) {
//        val id = "damage_${x}_${y}"
//        damageMap[id] = Damage(id, code, x, y)
//        invalidate()
//    }
//
//    private fun removeNearestDamage(touchX: Float, touchY: Float) {
//        val targetRadius = 40f
//        Log.e("removeNearestDamage", "Touch: $touchX,$touchY")
//        val closest = damageMap.values.find {
//            hypot(it.x - touchX, it.y - touchY) < targetRadius
//        }
//
//        closest?.let {
//            damageMap.remove(it.id)
//            invalidate()
//            onMarkerErased?.invoke(it)
//        }
//    }
//
//    data class Damage(val id: String, val code: String, val x: Float, val y: Float)
//
//
//}
