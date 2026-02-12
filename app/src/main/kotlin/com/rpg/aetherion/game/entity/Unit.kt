package com.rpg.aetherion.game.entity

import android.graphics.Canvas

abstract class Unit(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    var maxHp: Int,
    var damage: Int,
    var cooldown: Long
) : Entity(x, y, width, height) {

    var currentHp: Int = maxHp
    var lastAttackTime: Long = 0

    // Interaction with the player (e.g., Monster attacks Player, Villager talks to Player)
    // We use Any here to avoid circular dependency issues during compilation if Player isn't fully ready,
    // but we will cast it or use the specific type once Player is updated.
    // Actually, in the same package, it should be fine.
    abstract fun interact(player: Player)

    fun isAlive(): Boolean {
        return currentHp > 0
    }

    fun takeDamage(amount: Int) {
        currentHp -= amount
        if (currentHp < 0) currentHp = 0
    }
}
