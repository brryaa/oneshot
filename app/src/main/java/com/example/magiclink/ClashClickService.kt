package com.example.magiclink

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class ClashClickService : AccessibilityService() {

    companion object {
        var instance: ClashClickService? = null
        private const val TAG = "ClashClickService"
        // API 31+ 引入的收起通知栏动作常量
        private const val GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE = 15
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
        Log.d(TAG, "Accessibility Service Connected")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        instance = null
        Log.d(TAG, "Accessibility Service Unbound")
        return super.onUnbind(intent)
    }

    fun doTheMagic() {
        // 1. 下拉状态栏 (快捷设置)
        Log.d(TAG, "Performing GLOBAL_ACTION_QUICK_SETTINGS")
        performGlobalAction(GLOBAL_ACTION_QUICK_SETTINGS)

        // 2. 延迟等待下拉动画完成并模拟点击
        Handler(Looper.getMainLooper()).postDelayed({
            val (targetX, targetY) = CoordinateManager.loadCoordinates(this) 
            Log.d(TAG, "Using coordinates: X=$targetX, Y=$targetY")
            clickAtPosition(targetX, targetY)
        }, 650) // 略微增加延迟以确保点击到正确层级

        // 3. 强制收起
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d(TAG, "Collapsing status bar...")
            // 优先使用专用的收起指令，这在解锁状态的主屏幕非常有效
            val result = performGlobalAction(GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE)
            
            if (!result) {
                // 兜底方案
                performGlobalAction(GLOBAL_ACTION_BACK)
            }
        }, 1500)
    }

    private fun clickAtPosition(x: Float, y: Float) {
        val path = Path()
        path.moveTo(x, y)
        val gestureDescription = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 50))
            .build()
            
        val result = dispatchGesture(gestureDescription, object : AccessibilityService.GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                super.onCompleted(gestureDescription)
                Log.d(TAG, "Click gesture completed.")
            }
            override fun onCancelled(gestureDescription: GestureDescription?) {
                super.onCancelled(gestureDescription)
                Log.w(TAG, "Click gesture cancelled.")
            }
        }, null)

        if (!result) {
            Log.e(TAG, "Failed to dispatch click gesture.")
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
}
