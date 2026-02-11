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

        // Scale the canvas for pixel art look (3x or 4x)
        // Calculate scale based on density to look good on all screens
        val density = context.resources.displayMetrics.density
        val scale = if (density < 2) 2f else if (density < 3) 3f else 4f

        canvas.save()
        canvas.scale(scale, scale)

        // We need to pass the INVERSE scale to the scene/camera so it knows the "logical" screen size
        // Currently the scene assumes full screen width.
        // We should probably just scale the drawing, but the camera logic uses screenWidth.
        // Let's keep it simple: Camera centers on player.
        // If we scale the canvas, the viewable area becomes smaller in logical pixels.

        currentScene?.draw(canvas)

        canvas.restore()
    }
}
