package com.rpg.aetherion.game.map

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.rpg.aetherion.engine.AssetManager

class MapManager {
    val tileSize = 64
    val mapWidth = 30
    val mapHeight = 30
    val mapData = IntArray(mapWidth * mapHeight)

    // Pixel dimensions
    val width: Int
        get() = mapWidth * tileSize
    val height: Int
        get() = mapHeight * tileSize

    private val srcRect = Rect(0, 0, 32, 32)
    private val dstRect = RectF()

    init {
        // Fill with grass (index 0)
        for (i in mapData.indices) {
            mapData[i] = 0
        }
        // Add walls around the edge
        for (x in 0 until mapWidth) {
            mapData[x] = 1 // Top wall
            mapData[x + (mapHeight - 1) * mapWidth] = 1 // Bottom wall
        }
        for (y in 0 until mapHeight) {
            mapData[y * mapWidth] = 1 // Left wall
            mapData[mapWidth - 1 + y * mapWidth] = 1 // Right wall
        }
    }

    fun draw(canvas: Canvas, offsetX: Float, offsetY: Float, screenWidth: Int, screenHeight: Int) {
        val startX = (offsetX / tileSize).toInt().coerceAtLeast(0)
        val startY = (offsetY / tileSize).toInt().coerceAtLeast(0)
        val endX = ((offsetX + screenWidth) / tileSize).toInt().coerceAtMost(mapWidth - 1)
        val endY = ((offsetY + screenHeight) / tileSize).toInt().coerceAtMost(mapHeight - 1)

        val floorBitmap = AssetManager.floorTiles
        val wallBitmap = AssetManager.wallTiles

        // Adjust source rects if using the new tilesets
        // Floor tileset is likely a grid. Let's pick a random tile (e.g., 2nd tile) or 0,0
        // Wall tileset likewise.

        for (y in startY..endY) {
            for (x in startX..endX) {
                val tileId = mapData[x + y * mapWidth]
                val tileX = (x * tileSize).toFloat() - offsetX
                val tileY = (y * tileSize).toFloat() - offsetY
                dstRect.set(tileX, tileY, tileX + tileSize, tileY + tileSize)

                if (tileId == 0) {
                     // Floor
                     if (floorBitmap != null) {
                         // Most tilesets have some padding or specific layout.
                         // If 0,0 is transparent/black, we need to find a valid tile.
                         // Let's try the center of the bitmap if it's small, or a known offset.
                         // Standard RPG Maker/Unity tilesets often have top-left as valid or transparent.

                         // Attempt 1: Try (0, 0)
                         // Attempt 2: If the user says it's black, maybe 0,0 is empty?
                         // Let's try a bit offset. e.g. 32, 32.

                         val tileW = 6
                         val tileH = 8

                         // Check bounds
                         val sx = if (floorBitmap.width >= 32) 16 else 0
                         val sy = if (floorBitmap.height >= 32) 16 else 0

                         val safeSRect = Rect(sx, sy, sx + tileW, sy + tileH)
                         canvas.drawBitmap(floorBitmap, safeSRect, dstRect, null)
                     }
                } else if (tileId == 1) {
                    // Wall
                    if (wallBitmap != null) {
                         // Use first tile of wall sheet
                         val safeSRect = Rect(0, 0, 16, 16)
                         canvas.drawBitmap(wallBitmap, safeSRect, dstRect, null)
                    }
                }
            }
        }
    }

    fun isSolid(x: Float, y: Float): Boolean {
        val gridX = (x / tileSize).toInt()
        val gridY = (y / tileSize).toInt()

        if (gridX < 0 || gridX >= mapWidth || gridY < 0 || gridY >= mapHeight) return true

        val tileId = mapData[gridX + gridY * mapWidth]
        return tileId == 1 // 1 is wall
    }

    fun getTileRect(col: Int, row: Int): RectF {
        return RectF((col * tileSize).toFloat(), (row * tileSize).toFloat(),
                     (col * tileSize + tileSize).toFloat(), (row * tileSize + tileSize).toFloat())
    }
}
