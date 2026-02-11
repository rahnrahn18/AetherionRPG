package com.rpg.aetherion

import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*

suspend fun main() = Korge(
    width = 480,
    height = 270,
    virtualWidth = 480,
    virtualHeight = 270,
    backgroundColor = Colors["#2b2b2b"],
    title = "Aetherion RPG",
    scaleMode = ScaleMode.SHOW_ALL,
    scaleAnchor = Anchor.CENTER,
    clipBorders = false,
) {
    val sceneContainer = sceneContainer()
    sceneContainer.changeTo { GameScene() }
}
