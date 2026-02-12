package com.rpg.aetherion.game.scene

import android.graphics.Canvas
import android.view.MotionEvent

abstract class Scene {
    abstract fun update()
    abstract fun draw(canvas: Canvas)
    open fun onTouchEvent(event: MotionEvent) {}
}
