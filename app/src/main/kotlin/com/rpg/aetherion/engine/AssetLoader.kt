package com.rpg.aetherion.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

object AssetLoader {
    // A more generic loader that can be used to load any asset by path
    // and cache it.

    private val cache = mutableMapOf<String, Bitmap>()

    fun loadBitmap(context: Context, path: String): Bitmap? {
        if (cache.containsKey(path)) {
            return cache[path]
        }

        return try {
            val inputStream = context.assets.open(path)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            if (bitmap != null) {
                cache[path] = bitmap
            }
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun clearCache() {
        cache.values.forEach { it.recycle() }
        cache.clear()
    }
}
