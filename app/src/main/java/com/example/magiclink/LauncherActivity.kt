package com.example.magiclink

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkAccessibilityService()) {
            ClashClickService.instance?.doTheMagic()
            Toast.makeText(this, getString(R.string.magic_triggered), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.accessibility_disabled), Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
        
        finish()
    }

    private fun checkAccessibilityService(): Boolean {
        val service = "$packageName/$packageName.ClashClickService"
        val enabledServices = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ?: ""
        return enabledServices.contains(service)
    }
}
