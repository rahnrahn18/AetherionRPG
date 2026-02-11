package com.rpg.aetherion

import com.soywiz.klock.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*

class Player : Container() {
    private lateinit var sprite: Sprite

    // Animations
    private lateinit var animIdleDown: SpriteAnimation
    private lateinit var animWalkDown: SpriteAnimation
    private lateinit var animWalkUp: SpriteAnimation
    private lateinit var animWalkSide: SpriteAnimation

    // State
    private var currentAnim: SpriteAnimation? = null
    private var isMoving = false
    private var facingRight = true

    suspend fun load() {
        // Helper to load animation from strip manually slicing
        suspend fun loadAnim(path: String): SpriteAnimation {
            val tex = resourcesVfs[path].readBitmap()
            val frameSize = tex.height // Assume square frames
            val numFrames = tex.width / frameSize
            val frames = (0 until numFrames).map {
                tex.slice(it * frameSize, 0, frameSize, frameSize)
            }
            return SpriteAnimation(frames, 150.milliseconds)
        }

        animIdleDown = loadAnim("PixelPack/Entities/Characters/Body_A/Animations/Idle_Base/Idle_Down-Sheet.png")
        animWalkDown = loadAnim("PixelPack/Entities/Characters/Body_A/Animations/Walk_Base/Walk_Down-Sheet.png")
        animWalkUp = loadAnim("PixelPack/Entities/Characters/Body_A/Animations/Walk_Base/Walk_Up-Sheet.png")
        animWalkSide = loadAnim("PixelPack/Entities/Characters/Body_A/Animations/Walk_Base/Walk_Side-Sheet.png")

        // Initial sprite
        sprite = sprite(animIdleDown)
        currentAnim = animIdleDown
        sprite.playAnimationLooped(animIdleDown)

        // Center anchor
        sprite.anchor(0.5, 0.5)
    }

    fun move(dx: Double, dy: Double) {
        if (dx == 0.0 && dy == 0.0) {
            if (isMoving) {
                isMoving = false
                playAnim(animIdleDown)
            }
            return
        }

        isMoving = true

        // Determine animation
        val newAnim = when {
            dy > 0 -> animWalkDown
            dy < 0 -> animWalkUp
            dx != 0.0 -> animWalkSide
            else -> animWalkDown
        }

        // Handle flipping
        if (dx > 0) {
            sprite.scaleX = 1.0
            facingRight = true
        } else if (dx < 0) {
            sprite.scaleX = -1.0
            facingRight = false
        } else {
            // Keep facing direction if moving vertically
            sprite.scaleX = if (facingRight) 1.0 else -1.0
        }

        playAnim(newAnim)

        x += dx
        y += dy
    }

    private fun playAnim(anim: SpriteAnimation) {
        if (currentAnim != anim) {
            currentAnim = anim
            sprite.playAnimationLooped(anim)
        }
    }
}
