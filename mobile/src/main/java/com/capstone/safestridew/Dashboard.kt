package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Handle Menu Icon Click
        val menuIcon: ImageView = findViewById(R.id.menuIcon)
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START) // Open the drawer
        }

        // Handle Navigation Drawer Item Clicks
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleMenuSelection(menuItem)
            drawerLayout.closeDrawers() // Close drawer after selection
            true
        }

        // Handle Add Device Card Click
        val addDeviceCard: LinearLayout = findViewById(R.id.addDeviceCard)
        addDeviceCard.setOnClickListener {
            val intent = Intent(this, SetUp::class.java)
            startActivity(intent)
        }

        // Handle Notification Bell Click
        val notificationBell: ImageView = findViewById(R.id.notificationBell)
        notificationBell.setOnClickListener {
            val intent = Intent(this, Notification::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for the Patient Card
        val patientCard: LinearLayout = findViewById(R.id.patientCard)
        patientCard.setOnClickListener {
            val intent = Intent(this, Patient::class.java)
            startActivity(intent)
        }
        val cardRecords = findViewById<View>(R.id.cardRecords)
        cardRecords.setOnClickListener {
            // Example: Navigate to Records Feature Activity
            val intent = Intent(this, Records::class.java)
            startActivity(intent)
        }

        // Handle Reminder Card Click
        val reminderCard: LinearLayout = findViewById(R.id.cardReminder)
        reminderCard.setOnClickListener {
            val intent = Intent(this, Reminder::class.java)
            startActivity(intent)
        }


    }

    private fun handleMenuSelection(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                // Handle Home action
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_settings -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)

            }
            R.id.nav_about -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)

            }
            R.id.nav_sign_out -> {
                val intent = Intent(this, LandingPage::class.java)
                startActivity(intent)
            }
        }
    }


    // Method to handle sign-out
    private fun signOut() {
        // Add your sign-out logic here
        // For example, clear user data and navigate to the Login page
        val intent = Intent(this, LogIn::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}
