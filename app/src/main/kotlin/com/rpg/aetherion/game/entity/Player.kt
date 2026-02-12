package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import com.rpg.aetherion.engine.Animation
import com.rpg.aetherion.engine.AssetManager
import com.rpg.aetherion.engine.InputManager
import com.rpg.aetherion.engine.Sprite

// Player extends Unit (which extends Entity)
// Default stats: 100 HP, 10 Damage, 500ms Cooldown
class Player(x: Float, y: Float) : Unit(x, y, 64f, 64f, 100, 10, 500L) {
    private val speed = 10f

    // Maps state + direction to animation
    private var animations = mutableMapOf<String, Animation>()
    private var currentAnimation: Animation? = null

    private var facing = "down" // down, up, side
    private var isMoving = false
    private var flipX = false // For side animation (false = right, true = left)

    init {
        loadAnimations()
    }

    private fun loadAnimations() {
        val frameTime = 100L
        val idleFrameTime = 200L

        AssetManager.charRunDown?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["run_down"] = Animation(frames, frameTime)
        }
        AssetManager.charRunUp?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["run_up"] = Animation(frames, frameTime)
        }
        AssetManager.charRunSide?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["run_side"] = Animation(frames, frameTime)
        }

        AssetManager.charIdleDown?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["idle_down"] = Animation(frames, idleFrameTime)
        }
        AssetManager.charIdleUp?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["idle_up"] = Animation(frames, idleFrameTime)
        }
        AssetManager.charIdleSide?.let { sheet ->
             val count = estimateFrameCount(sheet)
             val frames = extractFrames(sheet, count)
             animations["idle_side"] = Animation(frames, idleFrameTime)
        }

        currentAnimation = animations["idle_down"]
    }

    private fun estimateFrameCount(bitmap: android.graphics.Bitmap): Int {
        if (bitmap.height > 0) {
            val ratio = bitmap.width / bitmap.height
            if (bitmap.width % bitmap.height == 0) {
                return ratio
            }
        }
        return 6
    }

    private fun extractFrames(sheet: android.graphics.Bitmap, count: Int): List<Sprite> {
        val frameW = sheet.width / count
        val frameH = sheet.height
        val list = ArrayList<Sprite>()
        for (i in 0 until count) {
            list.add(Sprite(sheet, i * frameW, 0, frameW, frameH))
        }
        return list
    }

    override fun update() {
        val dx = InputManager.joystickX * speed
        val dy = InputManager.joystickY * speed

        x += dx
        y += dy

        isMoving = dx != 0f || dy != 0f

        if (isMoving) {
            if (Math.abs(dx) > Math.abs(dy)) {
                facing = "side"
                flipX = (dx < 0)
            } else {
                if (dy > 0) {
                    facing = "down"
                    flipX = false
                } else {
                    facing = "up"
                    flipX = false
                }
            }
        }

        val state = if (isMoving) "run" else "idle"
        val animKey = "${state}_${facing}"

        val newAnim = animations[animKey]
        if (newAnim != null && newAnim != currentAnimation) {
            currentAnimation = newAnim
        }

        currentAnimation?.update()
    }

    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        currentAnimation?.draw(canvas, x - offsetX, y - offsetY, width, height, flipX)
    }

    // --- Interaction Methods ---

    override fun interact(player: Player) {
        // Self interaction, do nothing
    }

    fun attack(target: Unit) {
        val now = System.currentTimeMillis()
        if (now - lastAttackTime > cooldown) {
            target.takeDamage(damage)
            lastAttackTime = now
        }
    }

    fun talk(villager: Unit) {
         villager.interact(this)
    }

    fun pick(item: Item) {
        item.interact(this)
    }
}
