package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Notification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Sample notification data
        val notifications = listOf(
            Notif(
                "Emergency Alert!",
                "The patient pressed the RED button for an emergency.",
                "5 mins ago"
            ),
            Notif(
                "Need Assistance",
                "The patient pressed the Yellow button for assistance.",
                "10 mins ago"
            ),
            Notif("Track Location", "The patient was outside the safezone!", "40 mins ago"),
            Notif(
                "Need Assistance",
                "The patient pressed the Yellow button for assistance.",
                "1 hour ago"
            )
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerNotifications)
        val adapter = NotificationAdapter(notifications)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Back button functionality
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            onBackPressed()  // Go back to the previous screen
        }
        val settingsIcon: ImageView = findViewById(R.id.settingsIcon)
        settingsIcon.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }
}