package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import com.rpg.aetherion.engine.Animation
import com.rpg.aetherion.engine.AssetManager
import com.rpg.aetherion.engine.InputManager
import com.rpg.aetherion.engine.Sprite

class Player(x: Float, y: Float) : Entity(x, y, 64f, 64f) {
    private val speed = 10f

    // Maps state + direction to animation
    // Key format: "state_direction" e.g., "run_down", "idle_side"
    private var animations = mutableMapOf<String, Animation>()
    private var currentAnimation: Animation? = null

    private var facing = "down" // down, up, side
    private var isMoving = false
    private var flipX = false // For side animation (false = right, true = left)

    init {
        loadAnimations()
    }

    private fun loadAnimations() {
        val frameTime = 100L // Fast run
        val frameCount = 6 // Run and Idle sheets usually have 6 frames in this pack

        // --- Run Animations ---
        AssetManager.charRunDown?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["run_down"] = Animation(frames, frameTime)
             animations["run_idle"] = Animation(frames, frameTime) // Fallback
        }
        AssetManager.charRunUp?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["run_up"] = Animation(frames, frameTime)
        }
        AssetManager.charRunSide?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["run_side"] = Animation(frames, frameTime)
        }

        // --- Idle Animations ---
        val idleTime = 150L
        AssetManager.charIdleDown?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["idle_down"] = Animation(frames, idleTime)
        }
        AssetManager.charIdleUp?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["idle_up"] = Animation(frames, idleTime)
        }
        AssetManager.charIdleSide?.let { sheet ->
             val frames = extractFrames(sheet, frameCount)
             animations["idle_side"] = Animation(frames, idleTime)
        }

        // Default
        currentAnimation = animations["idle_down"]
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

    private fun extractFrames(sheet: android.graphics.Bitmap, frameW: Int, frameH: Int, count: Int): List<Sprite> {
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

        // Determine Facing and Flip
        if (isMoving) {
            if (Math.abs(dx) > Math.abs(dy)) {
                facing = "side"
                flipX = (dx < 0) // Flip if moving left
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

        // Select Animation
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
}
