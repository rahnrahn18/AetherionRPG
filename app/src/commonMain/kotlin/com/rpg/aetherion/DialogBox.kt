package com.rpg.aetherion

import com.soywiz.korge.view.*
import com.soywiz.korim.color.*

class DialogBox(w: Double, h: Double) : Container() {
    init {
        solidRect(w, h, Colors.DARKGRAY).alpha(0.8)
        visible = false
    }

    private val textField = text("", 14.0, Colors.WHITE).xy(10, 10)

    fun show(message: String) {
        textField.text = message
        visible = true
        // Bring to front
        parent?.addChild(this)
    }

    fun hide() {
        visible = false
    }
}
