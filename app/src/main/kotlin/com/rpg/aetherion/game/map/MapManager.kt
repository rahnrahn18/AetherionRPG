package com.rpg.aetherion.game.map

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.rpg.aetherion.engine.AssetManager

class MapManager {
    val tileSize = 64 // Kept for player movement compatibility, though map is not tiled

    // Pixel dimensions
    val width: Int
        get() = AssetManager.worldMap?.width ?: 1000
    val height: Int
        get() = AssetManager.worldMap?.height ?: 1000

    private val dstRect = RectF()

    init {
        // No procedural generation. Using single image.
    }

    fun draw(canvas: Canvas, offsetX: Float, offsetY: Float, screenWidth: Int, screenHeight: Int) {
        val map = AssetManager.worldMap ?: return

        // Draw the entire map at (0,0) minus the camera offset
        // In a real optimized engine, we would source-rect clip this,
        // but for a single background image, drawing it with an offset is fine
        // as Android Canvas handles off-screen clipping efficiently.

        // However, to be safe with large bitmaps, let's calculate the visible portion.

        val left = offsetX
        val top = offsetY
        val right = left + screenWidth
        val bottom = top + screenHeight

        val srcLeft = left.toInt().coerceAtLeast(0)
        val srcTop = top.toInt().coerceAtLeast(0)
        val srcRight = right.toInt().coerceAtMost(map.width)
        val srcBottom = bottom.toInt().coerceAtMost(map.height)

        if (srcLeft < srcRight && srcTop < srcBottom) {
            val src = Rect(srcLeft, srcTop, srcRight, srcBottom)
            val dst = RectF(
                (srcLeft - offsetX),
                (srcTop - offsetY),
                (srcRight - offsetX),
                (srcBottom - offsetY)
            )
            canvas.drawBitmap(map, src, dst, null)
        }
    }

    fun isSolid(x: Float, y: Float): Boolean {
        // Simple bounds check. The map image is the playable area.
        if (x < 0 || x >= width || y < 0 || y >= height) return true

        // Since we are using a flat image without collision data,
        // we treat everything inside the image bounds as walkable.
        // To make it more interesting, we could reject pixels that are transparent,
        // but for a "Scene Overview", likely everything is opaque.

        return false
    }
}
