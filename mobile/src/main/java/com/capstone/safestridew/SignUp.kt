package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val emailEditText: EditText = findViewById(R.id.emailField)
        val usernameEditText: EditText = findViewById(R.id.usernameField)
        val passwordEditText: EditText = findViewById(R.id.passwordField)
        val confirmPasswordEditText: EditText = findViewById(R.id.confirmPasswordField)
        val signUpButton: Button = findViewById(R.id.signupButton)
        val alreadyHaveAccountText: TextView = findViewById(R.id.loginText)
        val eyeIconPassword: ImageView = findViewById(R.id.eyeIconPassword)
        val eyeIconConfirmPassword: ImageView = findViewById(R.id.eyeIconConfirmPassword)

        // Initially disable the Sign Up button
        signUpButton.isEnabled = false

        // TextWatcher to monitor form fields
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Check if all fields are filled and valid
                val isFormFilled = emailEditText.text.isNotEmpty() &&
                        usernameEditText.text.isNotEmpty() &&
                        passwordEditText.text.isNotEmpty() &&
                        confirmPasswordEditText.text.isNotEmpty() &&
                        isValidEmail(emailEditText.text.toString()) &&
                        passwordEditText.text.toString() == confirmPasswordEditText.text.toString()  // Ensure passwords match
                signUpButton.isEnabled = isFormFilled
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Attach TextWatcher to each field
        emailEditText.addTextChangedListener(textWatcher)
        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)
        confirmPasswordEditText.addTextChangedListener(textWatcher)

        // Ensure password input is hidden initially
        passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        // Eye Icon for password toggle
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

        // Eye Icon for confirm password toggle
        eyeIconConfirmPassword.setOnClickListener {
            // Check if the current input type is password (hidden)
            if (confirmPasswordEditText.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                // Change to visible (normal text)
                confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                eyeIconConfirmPassword.setImageResource(R.drawable.openeye) // Update the icon to 'open eye'
            } else {
                // Change back to hidden (password)
                confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIconConfirmPassword.setImageResource(R.drawable.eyeclosed) // Update the icon to 'closed eye'
            }
            // Move the cursor to the end of the text
            confirmPasswordEditText.setSelection(confirmPasswordEditText.text.length)
        }

        // Navigate to Dashboard when Sign Up is clicked
        signUpButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password.isEmpty() || confirmPassword.isEmpty() || emailEditText.text.isEmpty() || usernameEditText.text.isEmpty()) {
                // Show a toast if any of the fields are empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                // Show toast message if passwords don't match
                Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed with the sign-up logic
                val intent = Intent(this, Dashboard::class.java)  // Replace with your Dashboard Activity
                startActivity(intent)
                finish()  // Close the SignUp activity to prevent going back
            }
        }

        // Navigate to Log In when "Already have an account?" is clicked
        alreadyHaveAccountText.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        // Back Arrow Click Listener
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            // Navigate to the Landing Page activity
            val intent = Intent(this, LandingPage::class.java)  // Replace with your Landing Page Activity
            startActivity(intent)
            finish()  // Optional: Close the SignUp activity to prevent the user from going back
        }
    }

    // Basic email validation method
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
