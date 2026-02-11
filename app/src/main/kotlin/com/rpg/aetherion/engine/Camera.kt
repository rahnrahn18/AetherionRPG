package com.rpg.aetherion.engine

import com.rpg.aetherion.game.entity.Entity

class Camera(private val screenWidth: Int, private val screenHeight: Int) {
    var offsetX = 0f
    var offsetY = 0f

    fun update(target: Entity, mapWidth: Int, mapHeight: Int) {
        // Center camera on target
        offsetX = target.x - screenWidth / 2 + target.width / 2
        offsetY = target.y - screenHeight / 2 + target.height / 2

        // Clamp to map bounds
        if (offsetX < 0) offsetX = 0f
        if (offsetY < 0) offsetY = 0f

        val maxOffsetX = mapWidth - screenWidth
        val maxOffsetY = mapHeight - screenHeight

        if (maxOffsetX > 0 && offsetX > maxOffsetX) offsetX = maxOffsetX.toFloat()
        if (maxOffsetY > 0 && offsetY > maxOffsetY) offsetY = maxOffsetY.toFloat()

        // Handle case where map is smaller than screen
        if (mapWidth < screenWidth) offsetX = -(screenWidth - mapWidth) / 2f
        if (mapHeight < screenHeight) offsetY = -(screenHeight - mapHeight) / 2f
    }
}
