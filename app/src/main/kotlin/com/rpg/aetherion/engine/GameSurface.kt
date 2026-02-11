package com.rpg.aetherion.engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.rpg.aetherion.game.scene.PlayScene
import com.rpg.aetherion.game.scene.Scene

class GameSurface(context: Context, attrs: AttributeSet? = null) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private var gameLoop: GameLoop? = null
    private var currentScene: Scene? = null

    init {
        holder.addCallback(this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        AssetManager.load(context)
        // Initialize the scene. Width and Height might be available now.
        // If 0, we rely on surfaceChanged, but usually surfaceCreated happens after layout.
        // However, technically surfaceChanged is safer for dimensions.
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (currentScene == null) {
            currentScene = PlayScene(width, height)
        }

        if (gameLoop == null) {
            gameLoop = GameLoop(this)
            gameLoop?.setRunning(true)
            gameLoop?.start()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        gameLoop?.setRunning(false)
        while (retry) {
            try {
                gameLoop?.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun update() {
        currentScene?.update()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        InputManager.onTouchEvent(event)
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        // Clear screen with a background color
        canvas.drawColor(Color.BLACK)
        currentScene?.draw(canvas)
    }
}
