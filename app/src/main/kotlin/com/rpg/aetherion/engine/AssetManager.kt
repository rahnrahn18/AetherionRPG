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

    // Villagers
    var knightIdle: Bitmap? = null
    var rogueIdle: Bitmap? = null
    var wizzardIdle: Bitmap? = null

    // Monsters
    var skeletonIdle: Bitmap? = null
    var orcIdle: Bitmap? = null

    // Environment
    var worldMap: Bitmap? = null
    // Legacy support for basic tiles
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

        // Villagers (using Npc's folder)
        knightIdle = loadBitmap(context, "PixelPack/Entities/Npc's/Knight/Idle/Idle-Sheet.png")
        rogueIdle = loadBitmap(context, "PixelPack/Entities/Npc's/Rogue/Idle/Idle-Sheet.png")
        wizzardIdle = loadBitmap(context, "PixelPack/Entities/Npc's/Wizzard/Idle/Idle-Sheet.png")

        // Monsters
        skeletonIdle = loadBitmap(context, "PixelPack/Entities/Mobs/Skeleton Crew/Skeleton - Base/Idle/Idle-Sheet.png")
        orcIdle = loadBitmap(context, "PixelPack/Entities/Mobs/Orc Crew/Orc - Warrior/Idle/Idle-Sheet.png")

        // Environment
        worldMap = loadBitmap(context, "PixelArtTopDown/Scene Overview.png")

        // Legacy (might be used for transitions or particles)
        floorTiles = loadBitmap(context, "PixelPack/Environment/Tilesets/Floors_Tiles.png")
        wallTiles = loadBitmap(context, "PixelPack/Environment/Tilesets/Wall_Tiles.png")
    }

    private fun loadBitmap(context: Context, path: String): Bitmap? {
        return AssetLoader.loadBitmap(context, path)
    }
}
