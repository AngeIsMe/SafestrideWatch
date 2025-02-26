package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // Back Arrow Click Listener
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            // Navigate to the Log In activity
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()  // Optional: Close the Forgot Password activity to prevent going back to it
        }

        // Initialize the EditText for the email field
        val emailEditText: EditText = findViewById(R.id.emailFieldForReset)
        val confirmEmailButton: Button = findViewById(R.id.confirmEmailButton)

        // Set click listener for the "Confirm Email" button
        confirmEmailButton.setOnClickListener {
            val email = emailEditText.text.toString()

            // Check if the email field is empty
            if (email.isEmpty()) {
                // Show a toast message if the email field is empty
                Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show()
            } else {
                // If the email field is not empty, navigate to the Verification page and pass the email
                val intent = Intent(this, Verification::class.java)
                intent.putExtra("user_email", email)  // Pass the email to the Verification Activity
                startActivity(intent)
                finish()  // Close the Forgot Password screen to prevent going back
            }
        }
    }
}
