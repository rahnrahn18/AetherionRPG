package com.rpg.aetherion.game.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

class Elixir(x: Float, y: Float) : Item(x, y, 32f, 32f, 50, 0, 0) {
    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (picked) return
        val paint = Paint()
        paint.color = Color.RED
        canvas.drawCircle(x - offsetX + 16, y - offsetY + 16, 16f, paint)
    }

    override fun update() {}

    override fun interact(player: Player) {
        if (!picked) {
            player.currentHp += bonusHp
            if (player.currentHp > player.maxHp) player.currentHp = player.maxHp
            picked = true
        }
    }
}

class Amulet(x: Float, y: Float) : Item(x, y, 32f, 32f, 0, 0, -100L) {
    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (picked) return
        val paint = Paint()
        paint.color = Color.BLUE
        canvas.drawRect(x - offsetX, y - offsetY, x - offsetX + 32, y - offsetY + 32, paint)
    }

    override fun update() {}

    override fun interact(player: Player) {
        if (!picked) {
            player.cooldown += bonusCooldown // Reduce cooldown (negative bonus)
            if (player.cooldown < 100) player.cooldown = 100
            picked = true
        }
    }
}

class Tome(x: Float, y: Float) : Item(x, y, 32f, 32f, 20, 2, 0) {
    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (picked) return
        val paint = Paint()
        paint.color = Color.GREEN
        // Triangle
        val path = Path()
        path.moveTo(x - offsetX + 16, y - offsetY)
        path.lineTo(x - offsetX + 32, y - offsetY + 32)
        path.lineTo(x - offsetX, y - offsetY + 32)
        path.close()
        canvas.drawPath(path, paint)
    }

    override fun update() {}

    override fun interact(player: Player) {
        if (!picked) {
            player.maxHp += bonusHp
            player.currentHp += bonusHp
            player.damage += bonusDamage
            picked = true
        }
    }
}

class Sword(x: Float, y: Float) : Item(x, y, 32f, 32f, 0, 10, 0) {
    override fun draw(canvas: Canvas, offsetX: Float, offsetY: Float) {
        if (picked) return
        val paint = Paint()
        paint.color = Color.YELLOW
        canvas.drawRect(x - offsetX + 12, y - offsetY, x - offsetX + 20, y - offsetY + 32, paint)
        canvas.drawRect(x - offsetX, y - offsetY + 24, x - offsetX + 32, y - offsetY + 30, paint)
    }

    override fun update() {}

    override fun interact(player: Player) {
        if (!picked) {
            player.damage += bonusDamage
            picked = true
        }
    }
}
