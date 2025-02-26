package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Switches
        val switchEmergencyAlerts: Switch = findViewById(R.id.switchEmergencyAlerts)
        val switchAssistanceAlerts: Switch = findViewById(R.id.switchAssistanceAlerts)
        val switchShowConnectedDevice: Switch = findViewById(R.id.switchShowConnectedDevice)
        val switchGpsTracking: Switch = findViewById(R.id.switchGpsTracking)

        // Buttons
        val buttonReconnectWatch: Button = findViewById(R.id.buttonReconnectWatch)
        val buttonManageEmergencyContacts: Button = findViewById(R.id.buttonManageEmergencyContacts)
        val buttonSetPrimaryContact: Button = findViewById(R.id.buttonSetPrimaryContact)
        val buttonViewLastKnownLocation: Button = findViewById(R.id.buttonViewLastKnownLocation)
        val buttonEditProfile: Button = findViewById(R.id.buttonEditProfile)
        val buttonChangePassword: Button = findViewById(R.id.buttonChangePassword)
        val buttonLogout: Button = findViewById(R.id.buttonLogout)

        // Reconnect Watch (Dialog instead of Activity)
        buttonReconnectWatch.setOnClickListener {
            // Show a dialog to reconnect to the watch
            showReconnectDialog()
        }
        // Handle the "Back Arrow" click
        val backArrowIcon: ImageView = findViewById(R.id.backArrowIcon)
        backArrowIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity to prevent returning here with "back"
        }

        // View Last Location (Google Maps Fragment)
        buttonViewLastKnownLocation.setOnClickListener {
            // Start a new activity to show the last known location
            startActivity(Intent(this, MapsActivity::class.java))
        }

        // Manage Emergency Contacts
        buttonManageEmergencyContacts.setOnClickListener {
            // Navigate to Manage Emergency Contacts Activity
            startActivity(Intent(this, ManageContactsActivity::class.java))
        }

        // Set Primary Contact
        buttonSetPrimaryContact.setOnClickListener {
            // Set a contact as primary (Open contact picker)
        }

        // Edit Profile
        buttonEditProfile.setOnClickListener {
            // Navigate to profile editing page
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        // Change Password
        buttonChangePassword.setOnClickListener {
            // Navigate to change password activity
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        // Logout
        buttonLogout.setOnClickListener {
            // Perform logout logic
            logoutUser()
        }
    }

    private fun showReconnectDialog() {
        // Show a reconnect dialog here, asking the user to reconnect the wearable
    }

    private fun logoutUser() {
        // Perform the logout process
        // E.g., clear session data, navigate to login screen
    }
}
