package com.rpg.aetherion

import com.soywiz.klock.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*

class Npc(val dialogText: String) : Container() {
    suspend fun load() {
        // Load skeleton idle using manual slicing
        val texture = resourcesVfs["PixelPack/Entities/Mobs/Skeleton Crew/Skeleton - Base/Idle/Idle-Sheet.png"].readBitmap()
        val frameSize = texture.height
        val numFrames = texture.width / frameSize
        val frames = (0 until numFrames).map {
            texture.slice(it * frameSize, 0, frameSize, frameSize)
        }
        val anim = SpriteAnimation(frames, 150.milliseconds)

        val sprite = sprite(anim)
        sprite.playAnimationLooped(anim)
        sprite.anchor(0.5, 0.5)
    }
}
