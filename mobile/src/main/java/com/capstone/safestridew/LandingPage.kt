package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LandingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        // Log In Button Click Listener
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            // Navigate to Log In activity
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        // Sign Up Button Click Listener
        findViewById<Button>(R.id.signupButton).setOnClickListener {
            // Navigate to Sign Up activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}
