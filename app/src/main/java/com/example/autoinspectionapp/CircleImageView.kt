package com.example.autoinspectionapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.createBitmap
import androidx.core.graphics.get

class CircleImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    interface OnValidTouchListener {
        fun onValidTouch(view: CircleImageView, x: Float, y: Float)
    }

    var onValidTouchListener: OnValidTouchListener? = null

    private val circles = mutableListOf<DamageCircle>()
    private val circlePaint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 32f
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    fun addCircle(x: Float, y: Float, code: String? = null, color: Int = Color.RED) {
        circles.add(DamageCircle(x, y, code, color))
        invalidate()
    }

    fun clearCircles() {
        circles.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val sizePx = 60f
        for (circle in circles) {
            circlePaint.color = circle.color
            canvas.drawCircle(circle.x, circle.y, sizePx / 2, circlePaint)

            if (!circle.code.isNullOrEmpty()) {
                canvas.drawText(
                    circle.code,
                    circle.x,
                    circle.y + textPaint.textSize / 3,
                    textPaint
                )
            }
        }
//
//        val sizePx = 60f
//        for ((x, y, code) in circles) {
//            canvas.drawCircle(x, y, sizePx / 2, circlePaint)
//            if (!code.isNullOrEmpty()) {
//                canvas.drawText(code, x, y + textPaint.textSize / 3, textPaint)
//            }
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            onValidTouchListener?.onValidTouch(this, event.x, event.y)
            return true
            val isTouchOnImage = isTouchOnImage(event.x, event.y)
            Log.e("onTouchEvent", "onTouchEvent:$isTouchOnImage ")
//            if (isTouchOnImage) {
//                Log.e("onTouchEvent", "onTouchEvent:$event ")
//
//                return true // consume event
//            }
            return false // ignore transparent touches
        }
        return super.onTouchEvent(event)
    }


    private fun isTouchOnImage(x: Float, y: Float): Boolean {
        Log.d("TouchCheck", "Raw touch: viewX=$x, viewY=$y")

        val drawable = drawable ?: return false
        val bitmap = drawableToBitmap(drawable)
        Log.d("TouchCheck", "Bitmap size: ${bitmap.width}x${bitmap.height}")

        // Invert matrix to map to bitmap coords
        val inverse = Matrix()
        if (!imageMatrix.invert(inverse)) {
            Log.w("TouchCheck", "Matrix inversion failed")
            return false
        }

        val touchPoint = floatArrayOf(x, y)
        inverse.mapPoints(touchPoint)
        val bitmapX = touchPoint[0].toInt()
        val bitmapY = touchPoint[1].toInt()

        Log.d("TouchCheck", "Mapped to bitmap coords: bitmapX=$bitmapX, bitmapY=$bitmapY")

        if (bitmapX !in 0 until bitmap.width || bitmapY !in 0 until bitmap.height) {
            Log.d("TouchCheck", "Touch outside bitmap bounds")
            return false
        }

        val alpha = Color.alpha(getBitmapPixel(bitmapX, bitmapY))
        Log.d("TouchCheck", "Pixel alpha at touch = $alpha")

        return alpha > 0
    }


    private fun isTouchOnImage2(x: Float, y: Float): Boolean {
        Log.d("TouchCheck", "Raw touch: viewX=$x, viewY=$y")

        val drawable = drawable ?: return false
        val bitmap = drawableToBitmap(drawable)
        Log.d("TouchCheck", "Bitmap size: ${bitmap.width}x${bitmap.height}")

        // Invert the imageMatrix to map view coords → bitmap coords
        val inverse = Matrix()
        if (!imageMatrix.invert(inverse)) {
            Log.w("TouchCheck", "Matrix inversion failed")
            return false
        }

        val touchPoint = floatArrayOf(x, y)
        inverse.mapPoints(touchPoint)
        val bitmapX = touchPoint[0].toInt()
        val bitmapY = touchPoint[1].toInt()

        Log.d(
            "TouchCheck",
            "Mapped to bitmap coords: bitmapX=$bitmapX, bitmapY=$bitmapY"
        )

        // Check bounds
        if (bitmapX !in 0 until bitmap.width || bitmapY !in 0 until bitmap.height) {
            Log.d("TouchCheck", "Touch outside bitmap bounds")
            return false
        }
        // Get pixel alpha
        val pixel = getBitmapPixel(touchX = bitmapX.toFloat(), touchY = bitmapY.toFloat())
        Log.d("TouchCheck", "Pixel alpha at touch =$pixel ")
        // val alpha = Color.alpha(pixel)
        Log.d("TouchCheck", "Pixel alpha at touch = $alpha")

        return alpha > 0
    }

    fun getBitmapPixel(touchX: Float, touchY: Float): Int? {
        val drawable = drawable ?: return null
        val bitmap = (drawable as BitmapDrawable).bitmap

        // ImageView dimensions
        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()

        // Bitmap dimensions
        val bitmapWidth = bitmap.width.toFloat()
        val bitmapHeight = bitmap.height.toFloat()

        // Get the ImageMatrix values
        val matrixValues = FloatArray(9)
        imageMatrix.getValues(matrixValues)

        val scaleX = matrixValues[Matrix.MSCALE_X]
        val scaleY = matrixValues[Matrix.MSCALE_Y]
        val transX = matrixValues[Matrix.MTRANS_X]
        val transY = matrixValues[Matrix.MTRANS_Y]

        // Convert touch to bitmap coords
        val relativeX = (touchX - transX) / scaleX
        val relativeY = (touchY - transY) / scaleY

        // Bounds check
        if (relativeX < 0 || relativeY < 0 || relativeX >= bitmapWidth || relativeY >= bitmapHeight) {
            return null
        }

        return bitmap[relativeX.toInt(), relativeY.toInt()]
    }


    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            return drawable.bitmap
        }
        val bitmap = createBitmap(drawable.intrinsicWidth.takeIf { it > 0 } ?: width,
            drawable.intrinsicHeight.takeIf { it > 0 } ?: height)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun getBitmapPixel(bitmapX: Int, bitmapY: Int): Int {
        val drawable = drawable ?: return 0
        val bitmap = (drawable as BitmapDrawable).bitmap
        if (bitmapX !in 0 until bitmap.width || bitmapY !in 0 until bitmap.height) {
            return 0
        }
        return bitmap.getPixel(bitmapX, bitmapY)
    }

}


class CircleImageView1(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {
    private val circles = mutableListOf<Triple<Float, Float, String?>>()
    private val circlePaint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 32f
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    fun addCircle(x: Float, y: Float, code: String? = null) {
        circles.add(Triple(x, y, code))
        invalidate()
    }


    fun clearCircles() {
        circles.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val sizePx = 60f
        for ((x, y, code) in circles) {
            canvas.drawCircle(x, y, sizePx / 2, circlePaint)
            if (!code.isNullOrEmpty()) {
                canvas.drawText(code, x, y + textPaint.textSize / 3, textPaint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Only process touch if it’s on a non-transparent pixel
        if (isTouchOnImage(event.x, event.y)) {
            Log.e("onTouchEvent", "onTouchEvent:onTouchEvent$event ")
            return super.onTouchEvent(event) // Pass to listener (e.g., setOnTouchListener)
        }
        return false // Ignore touch if on transparent area
    }

    private fun isTouchOnImage(x: Float, y: Float): Boolean {
        val drawable = drawable ?: return false
        val bitmap = drawableToBitmap(drawable)

        val values = FloatArray(9)
        imageMatrix.getValues(values)
        val scaleX = values[Matrix.MSCALE_X]
        val scaleY = values[Matrix.MSCALE_Y]
        val transX = values[Matrix.MTRANS_X]
        val transY = values[Matrix.MTRANS_Y]

        val bitmapX = ((x - transX) / scaleX).toInt()
        val bitmapY = ((y - transY) / scaleY).toInt()

        if (bitmapX !in 0 until bitmap.width || bitmapY !in 0 until bitmap.height) {
            return false
        }

        // Match fully transparent pixels
        return Color.alpha(bitmap[bitmapX, bitmapY]) > 0
    }


    private fun isTouchOnImage12(x: Float, y: Float): Boolean {
        val drawable = drawable ?: return false
        val bitmap = drawableToBitmap(drawable) // always from srcCompat, not background

        val values = FloatArray(9)
        imageMatrix.getValues(values)
        val scaleX = values[Matrix.MSCALE_X]
        val scaleY = values[Matrix.MSCALE_Y]
        val transX = values[Matrix.MTRANS_X]
        val transY = values[Matrix.MTRANS_Y]

        val bitmapX = ((x - transX) / scaleX).toInt()
        val bitmapY = ((y - transY) / scaleY).toInt()

        if (bitmapX !in 0 until bitmap.width || bitmapY !in 0 until bitmap.height) {
            return false
        }

        return Color.alpha(bitmap.getPixel(bitmapX, bitmapY)) > 0
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            return drawable.bitmap
        }
        val bitmap = createBitmap(drawable.intrinsicWidth.takeIf { it > 0 } ?: width,
            drawable.intrinsicHeight.takeIf { it > 0 } ?: height)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }


    private fun isTouchOnImage1(x: Float, y: Float): Boolean {
        // Get the bitmap from the drawable
        val drawable = drawable ?: return false
        val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return false

        // Map touch coordinates to bitmap coordinates
        val matrix = imageMatrix
        val values = FloatArray(9)
        matrix.getValues(values)
        val scaleX = values[Matrix.MSCALE_X]
        val scaleY = values[Matrix.MSCALE_Y]
        val transX = values[Matrix.MTRANS_X]
        val transY = values[Matrix.MTRANS_Y]

        // Calculate bitmap coordinates
        val bitmapX = ((x - transX) / scaleX).toInt()
        val bitmapY = ((y - transY) / scaleY).toInt()

        // Check if coordinates are within bitmap bounds
        if (bitmapX < 0 || bitmapX >= bitmap.width || bitmapY < 0 || bitmapY >= bitmap.height) {
            return false
        }

        // Check if pixel is non-transparent (alpha > 0)
        val pixel = bitmap.getPixel(bitmapX, bitmapY)
        return Color.alpha(pixel) > 0
    }
}

data class DamageCircle(
    val x: Float,
    val y: Float,
    val code: String?,
    val color: Int
)