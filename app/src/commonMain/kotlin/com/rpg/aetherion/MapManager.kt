package com.rpg.aetherion

import com.soywiz.korge.view.*
import com.soywiz.korge.view.tiles.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.kds.*

class MapManager {
    lateinit var tileSet: TileSet
    lateinit var map: TileMap

    suspend fun load() {
        val texture = resourcesVfs["PixelPack/Environment/Tilesets/Dungeon_Tiles.png"].readBitmap()
        // Assuming 16x16 tiles
        tileSet = TileSet.fromBitmap(texture, 16, 16)
    }

    fun createMap(): TileMap {
        val width = 30
        val height = 20

        val mapInts = IntArray2(width, height, 0)

        for (x in 0 until width) {
            for (y in 0 until height) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    mapInts[x, y] = 1 // Wall
                } else {
                    mapInts[x, y] = 0 // Floor
                }
            }
        }

        map = TileMap(mapInts, tileSet)
        return map
    }
}
