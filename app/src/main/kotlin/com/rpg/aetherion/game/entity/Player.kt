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
        val frameTime = 100L
        val idleFrameTime = 200L

        // According to common assets in this pack (PixelPack), Run has 6 frames, Idle has 4 or 6.
        // If we force 6 and it only has 4, the slices will be wrong (too narrow).
        // Let's deduce frame count from width if possible, assuming standard 32px or 48px width per frame.
        // OR better: Just assume 6 for Run and 6 for Idle as per user's "folder" hint (Body_A/Animations/...).
        // But if the user says "inconsistent moving around", it means the slicing is misaligned.
        // Many top-down assets use 32x32 or 48x48 grids.

        // Let's try to calculate frame width based on a standard module.
        // If sheet width is 192 (6 * 32), then 6 frames is correct.
        // If sheet width is 128 (4 * 32), then 4 frames.

        // Run Animations
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

        // Idle Animations
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

        // Default
        currentAnimation = animations["idle_down"]
    }

    private fun estimateFrameCount(bitmap: android.graphics.Bitmap): Int {
        // Try to guess based on common power-of-2 sizes or multiples
        // Most sprites are square-ish.
        // If height is 32, and width is 192, frames = 6.
        if (bitmap.height > 0) {
            val ratio = bitmap.width / bitmap.height
            // If the ratio is exact integer, it's likely the frame count (assuming square frames)
            if (bitmap.width % bitmap.height == 0) {
                return ratio
            }
        }
        return 6 // Fallback
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
