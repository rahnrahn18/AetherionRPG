package com.rpg.aetherion.engine

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.RectF

class Sprite(private val bitmap: Bitmap, x: Int, y: Int, width: Int, height: Int) {
    private val srcRect = Rect(x, y, x + width, y + height)

    fun draw(canvas: Canvas, dstRect: RectF, flipX: Boolean = false) {
        if (flipX) {
            val matrix = Matrix()
            matrix.preScale(-1f, 1f, dstRect.centerX(), dstRect.centerY())

            // Save current canvas state
            canvas.save()
            canvas.concat(matrix)
            canvas.drawBitmap(bitmap, srcRect, dstRect, null)
            canvas.restore()
        } else {
            canvas.drawBitmap(bitmap, srcRect, dstRect, null)
        }
    }

    fun draw(canvas: Canvas, x: Float, y: Float, w: Float, h: Float, flipX: Boolean = false) {
        val dst = RectF(x, y, x + w, y + h)
        draw(canvas, dst, flipX)
    }
}
