package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.rpg.aetherion.engine.Animation
import com.rpg.aetherion.engine.AssetManager
import com.rpg.aetherion.engine.Sprite

abstract class Villager(x: Float, y: Float, val name: String, val dialogue: String) : Unit(x, y, 64f, 64f, 100, 0, 1000L) {

    protected var idleAnimation: Animation? = null
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 30f
        textAlign = Paint.Align.CENTER
        setShadowLayer(5f, 0f, 0f, Color.BLACK)
    }

    // Show dialogue for a few seconds
    private var showDialogueUntil: Long = 0

    override fun update() {
        idleAnimation?.update()
    }

    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        idleAnimation?.draw(canvas, x - offsetX, y - offsetY, width, height, false)

        if (System.currentTimeMillis() < showDialogueUntil) {
            canvas.drawText(dialogue, x - offsetX + width/2, y - offsetY - 10, textPaint)
        }
        // Draw Name
        canvas.drawText(name, x - offsetX + width/2, y - offsetY + height + 30, textPaint)
    }

    override fun interact(player: Player) {
        showDialogueUntil = System.currentTimeMillis() + 3000 // Show for 3 seconds
    }
}

class Aldric(x: Float, y: Float) : Villager(x, y, "Aldric", "Greetings, traveler! Beware the dungeons.") {
    init {
        AssetManager.knightIdle?.let { sheet ->
             val count = 4 // Assume 4 frames for idle as per standard
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 200L)
        }
    }
}

class Garth(x: Float, y: Float) : Villager(x, y, "Garth", "I have goods if you have coin.") {
    init {
        AssetManager.rogueIdle?.let { sheet ->
             val count = 4
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 200L)
        }
    }
}

class Elvira(x: Float, y: Float) : Villager(x, y, "Elvira", "The magic in these lands is unstable.") {
    init {
        AssetManager.wizzardIdle?.let { sheet ->
             val count = 4
             val frameW = sheet.width / count
             val list = ArrayList<Sprite>()
             for (i in 0 until count) {
                 list.add(Sprite(sheet, i * frameW, 0, frameW, sheet.height))
             }
             idleAnimation = Animation(list, 200L)
        }
    }
}
