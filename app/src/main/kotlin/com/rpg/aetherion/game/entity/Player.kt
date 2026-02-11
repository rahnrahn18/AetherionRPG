package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import com.rpg.aetherion.engine.Animation
import com.rpg.aetherion.engine.AssetManager
import com.rpg.aetherion.engine.InputManager
import com.rpg.aetherion.engine.Sprite

class Player(x: Float, y: Float) : Entity(x, y, 64f, 64f) {
    private val speed = 10f
    private var animations = mutableMapOf<String, Animation>()
    private var currentAnimation: Animation? = null
    private var facing = "down"
    private var isMoving = false
    private var fallbackSprite: Sprite? = null

    init {
        val sheet = AssetManager.playerSheet
        if (sheet != null) {
            // Assume 4x4 sprite sheet.
            // If the sheet is very small, fallback to single sprite.
            if (sheet.width >= 64 && sheet.height >= 64) {
                val frameW = sheet.width / 4
                val frameH = sheet.height / 4

                val downFrames = ArrayList<Sprite>()
                val leftFrames = ArrayList<Sprite>()
                val rightFrames = ArrayList<Sprite>()
                val upFrames = ArrayList<Sprite>()

                // Assuming Row 0: Down, Row 1: Left, Row 2: Right, Row 3: Up (Standard RPG)
                // Or Row 1: Left, Row 2: Right, Row 3: Up
                // We'll trust standard 4-dir layout.
                for (i in 0 until 4) {
                    downFrames.add(Sprite(sheet, i * frameW, 0 * frameH, frameW, frameH))
                    leftFrames.add(Sprite(sheet, i * frameW, 1 * frameH, frameW, frameH))
                    rightFrames.add(Sprite(sheet, i * frameW, 2 * frameH, frameW, frameH))
                    upFrames.add(Sprite(sheet, i * frameW, 3 * frameH, frameW, frameH))
                }

                animations["down"] = Animation(downFrames, 150)
                animations["left"] = Animation(leftFrames, 150)
                animations["right"] = Animation(rightFrames, 150)
                animations["up"] = Animation(upFrames, 150)

                currentAnimation = animations["down"]
            } else {
                 fallbackSprite = Sprite(sheet, 0, 0, sheet.width, sheet.height)
            }
        }
    }

    override fun update() {
        val dx = InputManager.joystickX * speed
        val dy = InputManager.joystickY * speed

        x += dx
        y += dy

        isMoving = dx != 0f || dy != 0f

        if (isMoving) {
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) facing = "right" else facing = "left"
            } else {
                if (dy > 0) facing = "down" else facing = "up"
            }
            currentAnimation = animations[facing]
            currentAnimation?.update()
        } else {
             // Reset to first frame (idle)
             // currentAnimation = animations[facing]
             // But don't update to keep it static or loop idle if implemented
        }
    }

    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (currentAnimation != null) {
            currentAnimation?.draw(canvas, x - offsetX, y - offsetY, width, height)
        } else {
            fallbackSprite?.draw(canvas, x - offsetX, y - offsetY, width, height)
        }
    }
}
