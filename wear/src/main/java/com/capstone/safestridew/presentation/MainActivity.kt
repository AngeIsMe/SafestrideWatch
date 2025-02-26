package com.capstone.safestridew.presentation

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.capstone.safestridew.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Emergency Button Click Handler
        findViewById<FrameLayout>(R.id.emergencyButton).setOnClickListener {
            Toast.makeText(this, "Emergency Button Pressed", Toast.LENGTH_SHORT).show()
        }

        // Assistance Button Click Handler
        findViewById<LinearLayout>(R.id.assistanceButton).setOnClickListener {
            Toast.makeText(this, "Assistance Button Pressed", Toast.LENGTH_SHORT).show()
        }
    }
}