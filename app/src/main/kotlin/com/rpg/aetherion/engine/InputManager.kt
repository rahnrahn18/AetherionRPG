package com.rpg.aetherion.engine

import android.view.MotionEvent

object InputManager {
    var joystickX = 0f
    var joystickY = 0f
    var isTouching = false
    var isAttack = false // Flag for attack action (or interact)

    private val baseRadius = 150f

    // For rendering debug or UI
    var joyCenterX = 0f
    var joyCenterY = 0f
    var joyHatX = 0f
    var joyHatY = 0f
    var joyActive = false

    private var downTime = 0L
    private var downX = 0f
    private var downY = 0f

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

                downTime = System.currentTimeMillis()
                downX = x
                downY = y
                isAttack = false
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

                joyCenterX = 0f
                joyCenterY = 0f
                joyHatX = 0f
                joyHatY = 0f

                // Check for Tap (Short duration and small movement)
                val upTime = System.currentTimeMillis()
                val dx = x - downX
                val dy = y - downY
                val dist = Math.sqrt((dx * dx + dy * dy).toDouble())

                if (upTime - downTime < 250 && dist < 50) {
                    isAttack = true
                }
            }
        }
    }

    fun consumeAttack(): Boolean {
        if (isAttack) {
            isAttack = false
            return true
        }
        return false
    }
}
