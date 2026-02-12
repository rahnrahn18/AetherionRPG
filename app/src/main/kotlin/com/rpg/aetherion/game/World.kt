package com.rpg.aetherion.game

import android.graphics.Canvas
import com.rpg.aetherion.engine.Camera
import com.rpg.aetherion.engine.InputManager
import com.rpg.aetherion.game.entity.Item
import com.rpg.aetherion.game.entity.Monster
import com.rpg.aetherion.game.entity.Player
import com.rpg.aetherion.game.entity.Unit
import com.rpg.aetherion.game.entity.Villager
import com.rpg.aetherion.game.entity.Entity
import com.rpg.aetherion.game.map.MapManager
import kotlin.math.sqrt

class World(private val screenWidth: Int, private val screenHeight: Int) {
    val mapManager = MapManager()
    val player = Player(100f, 100f) // Initial spawn point
    val camera = Camera(screenWidth, screenHeight)

    val units = ArrayList<Unit>()
    val items = ArrayList<Item>()

    fun addUnit(unit: Unit) {
        units.add(unit)
    }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun update() {
        // 0. Handle Player Input (Attack/Interact)
        if (InputManager.consumeAttack()) {
            val target = getClosestUnit(player.x, player.y, 100f) // 100px range
            if (target != null) {
                if (target is Monster) {
                    player.attack(target)
                } else if (target is Villager) {
                    player.talk(target)
                }
            }
        }

        // 1. Update Player Movement & Collision
        val oldX = player.x
        val oldY = player.y
        player.update()

        val desiredX = player.x
        val desiredY = player.y

        // Test X Collision
        player.x = desiredX
        player.y = oldY
        if (isCollidingMap(player)) {
            player.x = oldX
        }

        // Test Y Collision
        // player.x is now either desiredX or oldX (safe)
        player.y = desiredY
        if (isCollidingMap(player)) {
            player.y = oldY
        }

        // Clamp to map
        if (player.x < 0) player.x = 0f
        if (player.y < 0) player.y = 0f
        if (player.x > mapManager.width - player.width) player.x = mapManager.width - player.width
        if (player.y > mapManager.height - player.height) player.y = mapManager.height - player.height

        // 2. Update Units (Monsters/Villagers)
        val unitsToRemove = ArrayList<Unit>()
        for (unit in units) {
            if (!unit.isAlive()) {
                unitsToRemove.add(unit)
                continue
            }

            // AI Update
            if (unit is Monster) {
                unit.updateAI(player)
            }
            unit.update()

            // Unit Map Collision (Simple Clamp)
            if (unit.x < 0) unit.x = 0f
            if (unit.y < 0) unit.y = 0f
            if (unit.x > mapManager.width - unit.width) unit.x = mapManager.width - unit.width
            if (unit.y > mapManager.height - unit.height) unit.y = mapManager.height - unit.height

            // Unit vs Player Collision (Monster Attack)
            if (checkCollision(player, unit)) {
                if (unit is Monster) {
                    unit.interact(player) // Monster attacks Player on contact
                }
            }
        }
        units.removeAll(unitsToRemove)

        // 3. Update Items
        val itemsToRemove = ArrayList<Item>()
        for (item in items) {
            item.update()
            if (!item.picked && checkCollision(player, item)) {
                player.pick(item) // Pick item
                itemsToRemove.add(item)
            }
        }
        items.removeAll(itemsToRemove)

        // 4. Update Camera
        camera.update(player, mapManager.width, mapManager.height)
    }

    private fun isCollidingMap(entity: Entity): Boolean {
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

    private fun checkCollision(a: Entity, b: Entity): Boolean {
        return a.bounds.intersect(b.bounds)
    }

    fun draw(canvas: Canvas) {
        mapManager.draw(canvas, camera.offsetX, camera.offsetY, screenWidth, screenHeight)

        for (item in items) {
            item.draw(canvas, camera.offsetX, camera.offsetY)
        }

        // Sort units by Y for depth sorting
        units.sortBy { it.y }

        for (unit in units) {
            unit.draw(canvas, camera.offsetX, camera.offsetY)
        }

        player.draw(canvas, camera.offsetX, camera.offsetY)
    }

    // Helper to find closest Unit for interaction (e.g. Talk/Attack)
    fun getClosestUnit(x: Float, y: Float, range: Float): Unit? {
        var closest: Unit? = null
        var minDist = range
        for (unit in units) {
            val dx = unit.x - x
            val dy = unit.y - y
            val dist = sqrt(dx*dx + dy*dy)
            if (dist < minDist) {
                minDist = dist
                closest = unit
            }
        }
        return closest
    }
}
