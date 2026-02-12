package com.rpg.aetherion.game.map

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.rpg.aetherion.engine.AssetManager

class MapManager {
    val tileSize = 16
    val mapWidth = 30
    val mapHeight = 30
    val mapData = IntArray(mapWidth * mapHeight)

    // Pixel dimensions
    val width: Int
        get() = mapWidth * tileSize
    val height: Int
        get() = mapHeight * tileSize

    private val srcRect = Rect()
    private val dstRect = RectF()

    init {
        // Fill with grass (index 0)
        for (i in mapData.indices) {
            mapData[i] = 0
        }

        // Add walls around the edge (index 100+)
        for (x in 0 until mapWidth) {
            mapData[x] = 100 // Top wall
            mapData[x + (mapHeight - 1) * mapWidth] = 100 // Bottom wall
        }
        for (y in 0 until mapHeight) {
            mapData[y * mapWidth] = 100 // Left wall
            mapData[mapWidth - 1 + y * mapWidth] = 100 // Right wall
        }
    }

    fun draw(canvas: Canvas, offsetX: Float, offsetY: Float, screenWidth: Int, screenHeight: Int) {
        val startX = (offsetX / tileSize).toInt().coerceAtLeast(0)
        val startY = (offsetY / tileSize).toInt().coerceAtLeast(0)
        // Add 1 to endX/endY to ensure partial tiles are drawn
        val endX = ((offsetX + screenWidth) / tileSize).toInt().coerceAtMost(mapWidth - 1) + 1
        val endY = ((offsetY + screenHeight) / tileSize).toInt().coerceAtMost(mapHeight - 1) + 1

        val floorBitmap = AssetManager.floorTiles
        val wallBitmap = AssetManager.wallTiles

        for (y in startY..endY) {
            if (y >= mapHeight) continue
            for (x in startX..endX) {
                if (x >= mapWidth) continue

                val tileId = mapData[x + y * mapWidth]
                val tileX = (x * tileSize).toFloat() - offsetX
                val tileY = (y * tileSize).toFloat() - offsetY
                dstRect.set(tileX, tileY, tileX + tileSize, tileY + tileSize)

                if (tileId < 100) {
                     // Floor
                     if (floorBitmap != null) {
                         val cols = floorBitmap.width / tileSize
                         if (cols > 0) {
                             val index = tileId
                             val sx = (index % cols) * tileSize
                             val sy = (index / cols) * tileSize
                             srcRect.set(sx, sy, sx + tileSize, sy + tileSize)
                             canvas.drawBitmap(floorBitmap, srcRect, dstRect, null)
                         }
                     }
                } else {
                    // Wall
                    if (wallBitmap != null) {
                         val cols = wallBitmap.width / tileSize
                         if (cols > 0) {
                             val index = tileId - 100
                             val sx = (index % cols) * tileSize
                             val sy = (index / cols) * tileSize
                             srcRect.set(sx, sy, sx + tileSize, sy + tileSize)
                             canvas.drawBitmap(wallBitmap, srcRect, dstRect, null)
                         }
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
        return tileId >= 100 // 100+ is wall
    }

    fun getTileRect(col: Int, row: Int): RectF {
        return RectF((col * tileSize).toFloat(), (row * tileSize).toFloat(),
                     (col * tileSize + tileSize).toFloat(), (row * tileSize + tileSize).toFloat())
    }
}
