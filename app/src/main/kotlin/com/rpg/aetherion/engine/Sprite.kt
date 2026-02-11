package com.rpg.aetherion.engine

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

class Sprite(private val bitmap: Bitmap, x: Int, y: Int, width: Int, height: Int) {
    private val srcRect = Rect(x, y, x + width, y + height)

    fun draw(canvas: Canvas, dstRect: RectF) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null)
    }

    fun draw(canvas: Canvas, x: Float, y: Float, w: Float, h: Float) {
        val dst = RectF(x, y, x + w, y + h)
        canvas.drawBitmap(bitmap, srcRect, dst, null)
    }
}
