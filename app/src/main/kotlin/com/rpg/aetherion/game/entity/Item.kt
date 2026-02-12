package com.rpg.aetherion.game.entity

import android.graphics.Canvas

abstract class Item(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    var bonusHp: Int,
    var bonusDamage: Int,
    var bonusCooldown: Long
) : Entity(x, y, width, height) {

    var picked: Boolean = false

    abstract fun interact(player: Player)
}
