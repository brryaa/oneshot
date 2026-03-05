package com.example.magiclink

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttonTriggerMagic: Button
    private lateinit var buttonOpenSettings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. 判断启动意图
        // 快捷方式的 Action 定义在 shortcuts.xml 中
        val isShortcutLaunch = intent.action == "com.example.magiclink.ACTION_OPEN_SETTINGS"
        
        if (!isShortcutLaunch) {
            // 直接点击图标启动：尝试静默执行
            if (checkAccessibilityService() && ClashClickService.instance != null) {
                ClashClickService.instance?.doTheMagic()
                Toast.makeText(this, getString(R.string.magic_triggered), Toast.LENGTH_SHORT).show()
                super.onCreate(savedInstanceState)
                finish() // 执行完直接退出，不展示 UI
                return
            }
            // 如果服务未开启或实例未准备好，则继续向下运行以展示界面引导用户
        }

        // 2. 正常 UI 模式：切换回正常主题并显示内容
        setTheme(R.style.Theme_MagicLink)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTriggerMagic = findViewById(R.id.buttonTriggerMagic)
        buttonOpenSettings = findViewById(R.id.buttonOpenSettings)

        buttonTriggerMagic.setOnClickListener {
            if (checkAccessibilityService()) {
                ClashClickService.instance?.doTheMagic()
                Toast.makeText(this, getString(R.string.magic_triggered), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.accessibility_needed_msg), Toast.LENGTH_LONG).show()
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }

        buttonOpenSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkAccessibilityService(): Boolean {
        val service = "$packageName/${ClashClickService::class.java.name}"
        val enabledServices = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ?: ""
        return enabledServices.contains(service)
    }

    override fun onResume() {
        super.onResume()
        // 只有在显示 UI 时（当前不是为了 finish 而存在的实例）才检查权限并提示
        if (!isFinishing && !checkAccessibilityService()) {
            Toast.makeText(this, getString(R.string.accessibility_not_enabled_msg), Toast.LENGTH_LONG).show()
        }
    }
}
