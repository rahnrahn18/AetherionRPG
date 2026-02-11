package com.rpg.aetherion.engine

import android.graphics.Canvas

class Animation(private val frames: List<Sprite>, private val frameDuration: Long) {
    private var currentFrameIndex = 0
    private var lastFrameTime = System.currentTimeMillis()

    fun update() {
        val now = System.currentTimeMillis()
        if (now - lastFrameTime > frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % frames.size
            lastFrameTime = now
        }
    }

    fun getFrame(): Sprite {
        return frames[currentFrameIndex]
    }

    fun draw(canvas: Canvas, x: Float, y: Float, w: Float, h: Float, flipX: Boolean = false) {
        frames[currentFrameIndex].draw(canvas, x, y, w, h, flipX)
    }
}
