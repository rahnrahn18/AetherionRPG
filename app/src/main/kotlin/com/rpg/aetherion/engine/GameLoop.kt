package com.rpg.aetherion.engine

import android.graphics.Canvas

class GameLoop(private val gameSurface: GameSurface) : Thread() {
    private var isRunning = false
    private val targetFPS = 60
    private var averageFPS = 0.0

    fun setRunning(run: Boolean) {
        isRunning = run
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        val targetTime = (1000 / targetFPS).toLong()

        while (isRunning) {
            startTime = System.nanoTime()
            var canvas: Canvas? = null

            try {
                canvas = gameSurface.holder.lockCanvas()
                synchronized(gameSurface.holder) {
                    if (canvas != null) {
                        gameSurface.update()
                        gameSurface.draw(canvas)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        gameSurface.holder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                if (waitTime > 0) {
                    sleep(waitTime)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            totalTime += System.nanoTime() - startTime
            frameCount++
            if (frameCount == targetFPS) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000)
                frameCount = 0
                totalTime = 0
            }
        }
    }
}
