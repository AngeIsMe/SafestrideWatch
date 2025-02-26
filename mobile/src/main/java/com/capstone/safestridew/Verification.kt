package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Verification : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var resendCodeText: TextView
    private lateinit var verificationCodeField: EditText
    private lateinit var confirmCodeButton: Button
    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 60000  // 1 minute

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        // Back Arrow Click Listener
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            // Navigate to the Forgot Password activity
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()  // Optional: Close the Verification activity to prevent going back
        }

        // Retrieve the email passed from ForgotPasswordActivity
        val userEmail = intent.getStringExtra("user_email")

        // Set the email text dynamically in the TextView
        val verificationSentText: TextView = findViewById(R.id.verificationSentText)
        verificationSentText.text = "Verification sent to: $userEmail"  // Display the email dynamically

        // Initialize views for verification code input and button
        verificationCodeField = findViewById(R.id.verificationCodeField)
        confirmCodeButton = findViewById(R.id.confirmCodeButton)
        timerText = findViewById(R.id.timerText)
        resendCodeText = findViewById(R.id.resendCodeText)

        // Start the timer
        startTimer()

        // Resend code click listener
        resendCodeText.setOnClickListener {
            // Implement logic to resend the verification code here
            Toast.makeText(this, "Resend code logic goes here", Toast.LENGTH_SHORT).show()
            restartTimer()
        }

        // Confirm Code Button click listener
        confirmCodeButton.setOnClickListener {
            val code = verificationCodeField.text.toString()
            if (code.isEmpty()) {
                Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show()
            } else {
                // If the code is entered, proceed to the New Password page
                val intent = Intent(this, NewPassword::class.java)
                startActivity(intent)
                finish()  // Close the Verification activity to prevent going back
            }
        }
    }

    // Start the countdown timer
    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                timerText.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                timerText.text = "00:00"
                resendCodeText.isClickable = true  // Enable the "Resend" text when timer finishes
            }
        }.start()
    }

    // Restart the timer for resend functionality
    private fun restartTimer() {
        timeLeftInMillis = 60000  // Reset timer to 1 minute
        timer?.cancel()
        startTimer()  // Start the timer again
    }
}
