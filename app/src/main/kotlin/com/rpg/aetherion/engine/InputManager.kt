package com.rpg.aetherion.engine

import android.view.MotionEvent

object InputManager {
    var joystickX = 0f
    var joystickY = 0f
    var isTouching = false

    private val baseRadius = 150f

    // For rendering debug or UI
    var joyCenterX = 0f
    var joyCenterY = 0f
    var joyHatX = 0f
    var joyHatY = 0f
    var joyActive = false

    fun onTouchEvent(event: MotionEvent) {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                joyCenterX = x
                joyCenterY = y
                joyHatX = x
                joyHatY = y
                joyActive = true
                isTouching = true
            }
            MotionEvent.ACTION_MOVE -> {
                if (joyActive) {
                    val dx = x - joyCenterX
                    val dy = y - joyCenterY
                    val distance = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()

                    if (distance > baseRadius) {
                        val ratio = baseRadius / distance
                        joyHatX = joyCenterX + dx * ratio
                        joyHatY = joyCenterY + dy * ratio
                    } else {
                        joyHatX = x
                        joyHatY = y
                    }

                    // Normalize output -1 to 1
                    joystickX = (joyHatX - joyCenterX) / baseRadius
                    joystickY = (joyHatY - joyCenterY) / baseRadius
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                joyActive = false
                isTouching = false
                joystickX = 0f
                joystickY = 0f
                // Reset visual position or keep it? Resetting is cleaner for floating joy
                joyCenterX = 0f
                joyCenterY = 0f
                joyHatX = 0f
                joyHatY = 0f
            }
        }
    }
}
