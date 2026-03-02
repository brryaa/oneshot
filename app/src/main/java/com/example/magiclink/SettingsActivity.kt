package com.example.magiclink

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var editTextX: EditText
    private lateinit var editTextY: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        editTextX = findViewById(R.id.editTextTargetX)
        editTextY = findViewById(R.id.editTextTargetY)
        buttonSave = findViewById(R.id.buttonSaveCoordinates)

        loadSavedCoordinates()

        buttonSave.setOnClickListener {
            saveCoordinates()
        }
    }

    private fun loadSavedCoordinates() {
        val (x, y) = CoordinateManager.loadCoordinates(this)
        editTextX.setText(x.toString())
        editTextY.setText(y.toString())
        Log.d("SettingsActivity", "Loaded for editing: X=$x, Y=$y")
    }

    private fun saveCoordinates() {
        val xStr = editTextX.text.toString()
        val yStr = editTextY.text.toString()

        if (xStr.isEmpty() || yStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.settings_error_empty), Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val x = xStr.toFloat()
            val y = yStr.toFloat()
            CoordinateManager.saveCoordinates(this, x, y)
            Toast.makeText(this, getString(R.string.settings_save_success), Toast.LENGTH_SHORT).show()
            Log.d("SettingsActivity", "Save button clicked: X=$x, Y=$y")
            finish() 
        } catch (e: NumberFormatException) {
            Toast.makeText(this, getString(R.string.settings_error_format), Toast.LENGTH_SHORT).show()
            Log.e("SettingsActivity", "Number format exception: X=$xStr, Y=$yStr", e)
        }
    }
}