package com.rpg.aetherion.game.scene

import android.graphics.Canvas
import android.view.MotionEvent
import com.rpg.aetherion.engine.Camera
import com.rpg.aetherion.game.entity.Player
import com.rpg.aetherion.game.map.MapManager

class PlayScene(private val screenWidth: Int, private val screenHeight: Int) : Scene() {
    private val player = Player(100f, 100f)
    private val mapManager = MapManager()
    private val camera = Camera(screenWidth, screenHeight)

    override fun update() {
        val oldX = player.x
        val oldY = player.y

        player.update()
        val newX = player.x
        val newY = player.y

        // Resolve X Collision
        player.x = newX
        player.y = oldY
        if (isColliding(player)) {
            player.x = oldX
        }

        // Resolve Y Collision
        player.y = newY
        if (isColliding(player)) {
            player.y = oldY
        }

        // Basic map boundary check (clamping)
        if (player.x < 0) player.x = 0f
        if (player.y < 0) player.y = 0f
        if (player.x > mapManager.width.toFloat() - player.width) player.x = mapManager.width.toFloat() - player.width
        if (player.y > mapManager.height.toFloat() - player.height) player.y = mapManager.height.toFloat() - player.height

        camera.update(player, mapManager.width, mapManager.height)
    }

    private fun isColliding(entity: com.rpg.aetherion.game.entity.Entity): Boolean {
        // Shrink collision box slightly to allow fitting through tight spaces
        val margin = 4f
        val left = entity.x + margin
        val right = entity.x + entity.width - margin
        val top = entity.y + margin
        val bottom = entity.y + entity.height - margin

        return mapManager.isSolid(left, top) ||
               mapManager.isSolid(right, top) ||
               mapManager.isSolid(left, bottom) ||
               mapManager.isSolid(right, bottom)
    }

    override fun draw(canvas: Canvas) {
        mapManager.draw(canvas, camera.offsetX, camera.offsetY, screenWidth, screenHeight)
        player.draw(canvas, camera.offsetX, camera.offsetY)
    }
}
