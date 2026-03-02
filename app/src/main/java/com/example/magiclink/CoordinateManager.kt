package com.example.magiclink

import android.content.Context
import android.util.Log

object CoordinateManager {
    private const val PREFS_NAME = "MagicLinkPrefs"
    private const val KEY_TARGET_X = "targetX"
    private const val KEY_TARGET_Y = "targetY"

    private const val DEFAULT_TARGET_X = 660f
    private const val DEFAULT_TARGET_Y = 940f

    private const val TAG = "CoordinateManager"

    fun saveCoordinates(context: Context, x: Float, y: Float) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putFloat(KEY_TARGET_X, x)
            putFloat(KEY_TARGET_Y, y)
            apply()
            Log.d(TAG, "Coordinates saved: X=$x, Y=$y")
        }
    }

    fun loadCoordinates(context: Context): Pair<Float, Float> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val x = prefs.getFloat(KEY_TARGET_X, DEFAULT_TARGET_X)
        val y = prefs.getFloat(KEY_TARGET_Y, DEFAULT_TARGET_Y)
        Log.d(TAG, "Coordinates loaded: X=$x, Y=$y")
        return Pair(x, y)
    }

    fun hasCustomCoordinates(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.contains(KEY_TARGET_X)
    }
}
