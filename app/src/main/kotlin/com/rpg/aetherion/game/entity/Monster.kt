package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.rpg.aetherion.engine.Animation
import com.rpg.aetherion.engine.AssetManager
import com.rpg.aetherion.engine.Sprite
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

abstract class Monster(x: Float, y: Float, val name: String, hp: Int, damage: Int, val speed: Float) : Unit(x, y, 64f, 64f, hp, damage, 1000L) {
    protected var idleAnimation: Animation? = null
    protected val detectionRange = 400f

    // Abstract AI method
    abstract fun updateAI(player: Player)

    override fun update() {
        idleAnimation?.update()
    }

    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (!isAlive()) return

        idleAnimation?.draw(canvas, x - offsetX, y - offsetY, width, height, false)

        // Draw HP Bar
        val hpPercent = currentHp.toFloat() / maxHp
        val barWidth = width
        val barHeight = 10f
        val paint = Paint()
        paint.color = Color.RED
        canvas.drawRect(x - offsetX, y - offsetY - 15, x - offsetX + barWidth, y - offsetY - 5, paint)
        paint.color = Color.GREEN
        canvas.drawRect(x - offsetX, y - offsetY - 15, x - offsetX + barWidth * hpPercent, y - offsetY - 5, paint)
    }

    override fun interact(player: Player) {
        // Attack player logic (called when colliding)
        // Check cooldown
        val now = System.currentTimeMillis()
        if (now - lastAttackTime > cooldown) {
            player.takeDamage(damage)
            lastAttackTime = now
        }
    }
}

class Skeleton(x: Float, y: Float) : Monster(x, y, "Skeleton", 60, 8, 3f) {
    init {
        AssetManager.skeletonIdle?.let { sheet ->
             val count = 4
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 150L)
        }
    }

    override fun updateAI(player: Player) {
        // Aggressive: Chase player
        val dx = player.x - x
        val dy = player.y - y
        val dist = sqrt(dx*dx + dy*dy)

        if (dist < detectionRange && dist > 10f) { // Don't overlap perfectly
            val angle = atan2(dy, dx)
            x += (cos(angle) * speed).toFloat()
            y += (sin(angle) * speed).toFloat()
        }
    }
}

class Orc(x: Float, y: Float) : Monster(x, y, "Orc", 100, 15, 2f) {
    init {
        AssetManager.orcIdle?.let { sheet ->
             val count = 4
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 150L)
        }
    }

    override fun updateAI(player: Player) {
        // Aggressive: Chase player
        val dx = player.x - x
        val dy = player.y - y
        val dist = sqrt(dx*dx + dy*dy)

        if (dist < detectionRange && dist > 10f) {
            val angle = atan2(dy, dx)
            x += (cos(angle) * speed).toFloat()
            y += (sin(angle) * speed).toFloat()
        }
    }
}

class Bat(x: Float, y: Float) : Monster(x, y, "Bat", 20, 2, 5f) {
    // Passive / Random movement
    private var moveDirX = 0f
    private var moveDirY = 0f
    private var lastChange = 0L

    init {
        // Re-use skeleton sprite as placeholder if no bat sprite
        AssetManager.skeletonIdle?.let { sheet ->
             val count = 4
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 100L)
        }
    }

    override fun updateAI(player: Player) {
        // Random movement
        val now = System.currentTimeMillis()
        if (now - lastChange > 1000) {
            moveDirX = (Math.random() * 2 - 1).toFloat()
            moveDirY = (Math.random() * 2 - 1).toFloat()
            lastChange = now
        }
        x += moveDirX * speed
        y += moveDirY * speed
    }
}
