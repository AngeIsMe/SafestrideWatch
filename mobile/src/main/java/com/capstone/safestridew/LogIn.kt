package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance()

        val emailEditText: EditText = findViewById(R.id.emailField)
        val passwordEditText: EditText = findViewById(R.id.passwordField)
        val logInButton: Button = findViewById(R.id.loginButton)
        val forgotPasswordText: TextView = findViewById(R.id.forgotPasswordText)
        val signUpLinkText: TextView = findViewById(R.id.signUpLinkText)
        val eyeIconPassword: ImageView = findViewById(R.id.eyeIconPassword)

        // Log In Button Click Listener
        logInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check if both email and password fields are not empty
            if (email.isEmpty() || password.isEmpty()) {
                // Display a Toast message if either of the fields is empty
                Toast.makeText(this, "Please fill in both email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Use Firebase Authentication to sign in
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign In successful, move to the Dashboard
                            val intent = Intent(this, Dashboard::class.java)
                            startActivity(intent)
                            finish()  // Close the Log In screen to prevent going back to it
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // Sign Up Click Listener
        signUpLinkText.setOnClickListener {
            // Navigate to Sign Up activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // Forgot Password Click Listener
        forgotPasswordText.setOnClickListener {
            // Navigate to Forgot Password Activity
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        // Eye Icon Click Listener to toggle visibility of Password
        eyeIconPassword.setOnClickListener {
            // Check if the current input type is password (hidden)
            if (passwordEditText.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                // Change to visible (normal text)
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                eyeIconPassword.setImageResource(R.drawable.openeye) // Update the icon to 'open eye'
            } else {
                // Change back to hidden (password)
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIconPassword.setImageResource(R.drawable.eyeclosed) // Update the icon to 'closed eye'
            }
            // Move the cursor to the end of the text
            passwordEditText.setSelection(passwordEditText.text.length)
        }

        // Back Arrow Click Listener
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            // Navigate to the Landing Page activity
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
            finish()  // Optional: Close the Log In activity to prevent the user from going back
        }
    }
}


