package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        val reminderMessageInput = findViewById<EditText>(R.id.reminderMessageInput)
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val addReminderButton = findViewById<Button>(R.id.addReminderButton)

        // Set TimePicker to 24-hour format
        timePicker.setIs24HourView(true)

        // Back Arrow Click Listener
        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            finish()
        }

        // Add Reminder Button Click Listener
        addReminderButton.setOnClickListener {
            val reminderMessage = reminderMessageInput.text.toString()
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // Month is 0-based
            val year = datePicker.year
            val hour = timePicker.hour
            val minute = timePicker.minute

            if (reminderMessage.isNotEmpty()) {
                // Format the reminder details
                val reminderDetails = "$reminderMessage on $day/$month/$year at $hour:$minute"

                // Pass the reminder data back to the Reminder activity
                val resultIntent = Intent()
                resultIntent.putExtra("REMINDER", reminderDetails)
                setResult(RESULT_OK, resultIntent)
                finish() // Close this activity
            } else {
                Toast.makeText(this, "Please enter a reminder message.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
