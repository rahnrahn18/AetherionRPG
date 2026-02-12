package com.rpg.aetherion.game.map

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.rpg.aetherion.engine.AssetManager

class MapManager {
    companion object {
        const val TILE_GRASS = 0
        const val TILE_WALL = 1
        const val TILE_FLOOR = 2
        const val TILE_ROOF = 3
        const val TILE_PLANT = 4
        const val TILE_PROP = 5

        const val TILE_SIZE_SOURCE = 32
    }

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
        generateVillage()
    }

    private fun generateVillage() {
        // Clear
        for (i in mapData.indices) mapData[i] = TILE_GRASS

        // House 1: Walls and Floor (Enterable)
        val h1x = 5
        val h1y = 5
        val h1w = 8
        val h1h = 6

        for (y in h1y until h1y + h1h) {
            for (x in h1x until h1x + h1w) {
                if (x == h1x || x == h1x + h1w - 1 || y == h1y || y == h1y + h1h - 1) {
                    mapData[x + y * mapWidth] = TILE_WALL
                } else {
                    mapData[x + y * mapWidth] = TILE_FLOOR
                }
            }
        }
        // Door (remove bottom wall center)
        mapData[(h1x + h1w / 2) + (h1y + h1h - 1) * mapWidth] = TILE_FLOOR

        // House 2: Roof (Solid building)
        val h2x = 18
        val h2y = 5
        val h2w = 6
        val h2h = 5
        for (y in h2y until h2y + h2h) {
            for (x in h2x until h2x + h2w) {
                mapData[x + y * mapWidth] = TILE_ROOF
            }
        }

        // Plants and Props
        val rng = java.util.Random()
        repeat(30) {
            val rx = rng.nextInt(mapWidth)
            val ry = rng.nextInt(mapHeight)
            if (mapData[rx + ry * mapWidth] == TILE_GRASS) {
                mapData[rx + ry * mapWidth] = TILE_PLANT
            }
        }
        repeat(15) {
            val rx = rng.nextInt(mapWidth)
            val ry = rng.nextInt(mapHeight)
            if (mapData[rx + ry * mapWidth] == TILE_GRASS) {
                mapData[rx + ry * mapWidth] = TILE_PROP
            }
        }
    }

    fun draw(canvas: Canvas, offsetX: Float, offsetY: Float, screenWidth: Int, screenHeight: Int) {
        val startX = (offsetX / tileSize).toInt().coerceAtLeast(0)
        val startY = (offsetY / tileSize).toInt().coerceAtLeast(0)
        val endX = ((offsetX + screenWidth) / tileSize).toInt().coerceAtMost(mapWidth - 1)
        val endY = ((offsetY + screenHeight) / tileSize).toInt().coerceAtMost(mapHeight - 1)

        val grassBitmap = AssetManager.floorTiles // Using original floorTiles for grass background

        for (y in startY..endY) {
            for (x in startX..endX) {
                val tileId = mapData[x + y * mapWidth]
                val tileX = (x * tileSize).toFloat() - offsetX
                val tileY = (y * tileSize).toFloat() - offsetY
                dstRect.set(tileX, tileY, tileX + tileSize, tileY + tileSize)

                // Helper to draw grass background
                fun drawGrass() {
                    if (grassBitmap != null) {
                        val safeSRect = Rect(0, 0, 32, 32)
                        canvas.drawBitmap(grassBitmap, safeSRect, dstRect, null)
                    }
                }

                when (tileId) {
                    TILE_GRASS -> {
                        drawGrass()
                    }
                    TILE_WALL -> {
                        val bitmask = checkNeighbors(x, y, TILE_WALL)
                        val sRect = getAutoTileRect(bitmask)
                        AssetManager.buildingWalls?.let {
                            canvas.drawBitmap(it, sRect, dstRect, null)
                        }
                    }
                    TILE_FLOOR -> {
                        val bitmask = checkNeighbors(x, y, TILE_FLOOR)
                        val sRect = getAutoTileRect(bitmask)
                        AssetManager.buildingFloors?.let {
                            canvas.drawBitmap(it, sRect, dstRect, null)
                        }
                    }
                    TILE_ROOF -> {
                        val bitmask = checkNeighbors(x, y, TILE_ROOF)
                        val sRect = getAutoTileRect(bitmask)
                        AssetManager.buildingRoofs?.let {
                            canvas.drawBitmap(it, sRect, dstRect, null)
                        }
                    }
                    TILE_PLANT -> {
                        drawGrass()
                        AssetManager.extraPlants?.let {
                            val sRect = Rect(0, 0, it.width.coerceAtMost(32), it.height.coerceAtMost(32))
                            canvas.drawBitmap(it, sRect, dstRect, null)
                        }
                    }
                    TILE_PROP -> {
                        drawGrass()
                        AssetManager.extraProps?.let {
                            val sRect = Rect(0, 0, it.width.coerceAtMost(32), it.height.coerceAtMost(32))
                            canvas.drawBitmap(it, sRect, dstRect, null)
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
        return tileId == TILE_WALL || tileId == TILE_PROP // Wall and Props are solid
    }

    fun getTileRect(col: Int, row: Int): RectF {
        return RectF((col * tileSize).toFloat(), (row * tileSize).toFloat(),
                     (col * tileSize + tileSize).toFloat(), (row * tileSize + tileSize).toFloat())
    }

    private fun checkNeighbors(x: Int, y: Int, type: Int): Int {
        var mask = 0
        // North
        if (isType(x, y - 1, type)) mask = mask or 1
        // West
        if (isType(x - 1, y, type)) mask = mask or 2
        // East
        if (isType(x + 1, y, type)) mask = mask or 4
        // South
        if (isType(x, y + 1, type)) mask = mask or 8
        return mask
    }

    private fun isType(x: Int, y: Int, type: Int): Boolean {
        if (x < 0 || x >= mapWidth || y < 0 || y >= mapHeight) return true // Treat edges as connected
        return mapData[x + y * mapWidth] == type
    }

    private fun getAutoTileRect(bitmask: Int): Rect {
        // Mapping 0-15 to a 4x4 grid (Standard blob assumption)
        // 0  1  2  3
        // 4  5  6  7
        // 8  9  10 11
        // 12 13 14 15

        val col = bitmask % 4
        val row = bitmask / 4

        val sx = col * TILE_SIZE_SOURCE
        val sy = row * TILE_SIZE_SOURCE

        return Rect(sx, sy, sx + TILE_SIZE_SOURCE, sy + TILE_SIZE_SOURCE)
    }
}
