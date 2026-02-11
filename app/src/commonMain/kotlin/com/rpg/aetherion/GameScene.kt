package com.rpg.aetherion

import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korge.input.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*

class GameScene : Scene() {
    private val mapManager = MapManager()
    private val player = Player()
    private val npc = Npc("Hello Traveler! Welcome to Aetherion.")

    // Layers
    private lateinit var world: Container
    private lateinit var hud: Container

    // UI Elements
    private lateinit var joystick: Joystick
    private lateinit var dialogBox: DialogBox
    private lateinit var interactBtn: Container

    override suspend fun SContainer.sceneMain() {
        // Create layers
        world = container()
        hud = fixedSizeContainer(views.virtualWidth.toDouble(), views.virtualHeight.toDouble())

        // Load Map
        try {
            mapManager.load()
            world.addChild(mapManager.createMap())
        } catch (e: Exception) {
            println("Map load error: $e")
        }

        // Load NPC
        npc.load()
        npc.xy(250, 150)
        world.addChild(npc)

        // Load Player
        player.load()
        player.xy(100, 100)
        world.addChild(player)

        // Setup HUD

        // Joystick
        joystick = Joystick(maxRadius = 40.0, knobRadius = 15.0)
        joystick.xy(60.0, views.virtualHeight - 60.0)
        hud.addChild(joystick)

        // Dialog Box (Centered bottom)
        dialogBox = DialogBox(300.0, 80.0)
        dialogBox.xy((views.virtualWidth - 300.0) / 2, views.virtualHeight - 100.0)
        hud.addChild(dialogBox)

        // Interact Button (Bottom Right)
        interactBtn = container {
            circle(30.0, Colors["#0000AA77"]).anchor(0.5, 0.5)
            text("Talk", 12.0, Colors.WHITE).anchor(0.5, 0.5)
        }
        interactBtn.xy(views.virtualWidth - 60.0, views.virtualHeight - 60.0)
        interactBtn.visible = false
        interactBtn.onClick {
            dialogBox.show(npc.dialogText)
        }
        hud.addChild(interactBtn)

        // Update Loop
        addUpdater {
            // Player Movement
            val speed = 2.0
            val dx = joystick.outputX * speed
            val dy = joystick.outputY * speed
            player.move(dx, dy)

            // Camera Follow
            val targetX = -player.x + views.virtualWidth / 2
            val targetY = -player.y + views.virtualHeight / 2
            world.x += (targetX - world.x) * 0.1
            world.y += (targetY - world.y) * 0.1

            // Interaction Check
            // Simple distance check (squared distance is faster but length is fine here)
            val dist = kotlin.math.sqrt(
                (player.x - npc.x) * (player.x - npc.x) +
                (player.y - npc.y) * (player.y - npc.y)
            )

            if (dist < 50.0) {
                if (!interactBtn.visible) interactBtn.visible = true
            } else {
                if (interactBtn.visible) {
                    interactBtn.visible = false
                    dialogBox.hide()
                }
            }
        }
    }
}
