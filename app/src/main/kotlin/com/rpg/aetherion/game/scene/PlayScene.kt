package com.rpg.aetherion.game.scene

import android.graphics.Canvas
import com.rpg.aetherion.game.World
import com.rpg.aetherion.game.entity.*

class PlayScene(private val realScreenWidth: Int, private val realScreenHeight: Int) : Scene() {

    private val density = android.content.res.Resources.getSystem().displayMetrics.density
    private val scale = if (density < 2) 2f else if (density < 3) 3f else 4f

    private val logicalWidth = (realScreenWidth / scale).toInt()
    private val logicalHeight = (realScreenHeight / scale).toInt()

    private val world = World(logicalWidth, logicalHeight)

    init {
        // --- Initialize World Entities ---

        // Villagers
        world.addUnit(Aldric(200f, 200f))
        world.addUnit(Garth(350f, 150f))
        world.addUnit(Elvira(500f, 250f))

        // Monsters (Passive)
        world.addUnit(Bat(600f, 600f))
        world.addUnit(Bat(650f, 650f))

        // Monsters (Aggressive)
        world.addUnit(Skeleton(800f, 800f))
        world.addUnit(Orc(900f, 900f))
        world.addUnit(Skeleton(100f, 800f)) // One far away

        // Items
        world.addItem(Elixir(300f, 300f))
        world.addItem(Sword(400f, 400f))
        world.addItem(Amulet(500f, 500f))
        world.addItem(Tome(600f, 300f))
    }

    override fun update() {
        world.update()
    }

    override fun draw(canvas: Canvas) {
        world.draw(canvas)
    }
}
