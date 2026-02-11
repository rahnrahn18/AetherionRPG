package com.rpg.aetherion.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

object AssetManager {
    // Character Animations (Run)
    var charRunDown: Bitmap? = null
    var charRunUp: Bitmap? = null
    var charRunSide: Bitmap? = null

    // Character Animations (Idle)
    var charIdleDown: Bitmap? = null
    var charIdleUp: Bitmap? = null
    var charIdleSide: Bitmap? = null

    // Environment
    var floorTiles: Bitmap? = null
    var wallTiles: Bitmap? = null

    fun load(context: Context) {
        // Character - Run
        charRunDown = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Run_Base/Run_Down-Sheet.png")
        charRunUp = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Run_Base/Run_Up-Sheet.png")
        charRunSide = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Run_Base/Run_Side-Sheet.png")

        // Character - Idle
        charIdleDown = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Idle_Base/Idle_Down-Sheet.png")
        charIdleUp = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Idle_Base/Idle_Up-Sheet.png")
        charIdleSide = loadBitmap(context, "PixelPack/Entities/Characters/Body_A/Animations/Idle_Base/Idle_Side-Sheet.png")

        // Environment
        floorTiles = loadBitmap(context, "PixelPack/Environment/Tilesets/Floors_Tiles.png")
        wallTiles = loadBitmap(context, "PixelPack/Environment/Tilesets/Wall_Tiles.png")
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
