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

    // New Environment Assets
    var buildingWalls: Bitmap? = null
    var buildingFloors: Bitmap? = null
    var buildingRoofs: Bitmap? = null
    var extraPlants: Bitmap? = null
    var extraProps: Bitmap? = null

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

        // New Environment
        buildingWalls = loadBitmap(context, "PixelPack/Environment/Structures/Buildings/Walls.png")
        buildingFloors = loadBitmap(context, "PixelPack/Environment/Structures/Buildings/Floors.png")
        buildingRoofs = loadBitmap(context, "PixelPack/Environment/Structures/Buildings/Roofs.png")
        extraPlants = loadBitmap(context, "PixelArtTopDown/Texture/Extra/TX Plant with Shadow.png")
        extraProps = loadBitmap(context, "PixelArtTopDown/Texture/Extra/TX Props with Shadow.png")
    }

    private fun loadBitmap(context: Context, path: String): Bitmap? {
        return AssetLoader.loadBitmap(context, path)
    }
}
