package com.rpg.aetherion

import com.soywiz.korge.input.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*

class Joystick(
    val maxRadius: Double = 50.0,
    val knobRadius: Double = 20.0
) : Container() {

    private val base = circle(maxRadius, Colors["#00000077"])
    private val knob = circle(knobRadius, Colors["#ffffffaa"])

    var outputX = 0.0
    var outputY = 0.0

    init {
        // Center the shapes relative to this container's origin (0,0)
        base.anchor(0.5, 0.5)
        base.xy(0, 0)

        knob.anchor(0.5, 0.5)
        knob.xy(0, 0)

        // Enable mouse/touch interaction
        mouse {
            down {
                updateKnob(it.currentPosLocal)
            }
            drag {
                updateKnob(it.currentPosLocal)
            }
            up {
                resetKnob()
            }
            upOutside {
                resetKnob()
            }
        }
    }

    private fun updateKnob(localPos: Point) {
        val distance = localPos.length

        if (distance <= maxRadius) {
            knob.xy(localPos.x, localPos.y)
            outputX = localPos.x / maxRadius
            outputY = localPos.y / maxRadius
        } else {
            // Clamp to maxRadius
            val angle = localPos.angle
            val clampedX = angle.cosine * maxRadius
            val clampedY = angle.sine * maxRadius
            knob.xy(clampedX, clampedY)
            outputX = angle.cosine
            outputY = angle.sine
        }
    }

    private fun resetKnob() {
        knob.xy(0, 0)
        outputX = 0.0
        outputY = 0.0
    }
}
