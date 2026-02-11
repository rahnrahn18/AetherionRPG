package com.rpg.aetherion.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

object AssetManager {
    var playerSheet: Bitmap? = null
    var grassTiles: Bitmap? = null
    var stoneTiles: Bitmap? = null
    var wallTiles: Bitmap? = null
    var propsTiles: Bitmap? = null

    fun load(context: Context) {
        playerSheet = loadBitmap(context, "PixelArtTopDown/Texture/TX Player.png")
        grassTiles = loadBitmap(context, "PixelArtTopDown/Texture/TX Tileset Grass.png")
        stoneTiles = loadBitmap(context, "PixelArtTopDown/Texture/TX Tileset Stone Ground.png")
        wallTiles = loadBitmap(context, "PixelArtTopDown/Texture/TX Tileset Wall.png")
        propsTiles = loadBitmap(context, "PixelArtTopDown/Texture/TX Props.png")
    }

    private fun loadBitmap(context: Context, path: String): Bitmap? {
        return try {
            val inputStream = context.assets.open(path)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
