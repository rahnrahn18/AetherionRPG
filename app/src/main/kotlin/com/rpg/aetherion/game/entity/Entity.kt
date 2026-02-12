package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import android.graphics.RectF

abstract class Entity(var x: Float, var y: Float, var width: Float, var height: Float) {
    // Basic properties for collision and rendering
    val bounds: RectF
        get() = RectF(x, y, x + width, y + height)

    abstract fun update()
    abstract fun draw(canvas: Canvas, offsetX: Float, offsetY: Float)
}
