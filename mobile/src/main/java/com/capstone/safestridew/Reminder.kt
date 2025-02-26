package com.capstone.safestridew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.CalendarView

class Reminder : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RemindersAdapter
    private val remindersList = mutableListOf(
        "Take medicine at 8:00 AM",
        "Doctor appointment at 3:00 PM",
        "Evening walk at 6:00 PM"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        // Calendar Setup
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Log.d("Calendar", "Selected Date: $dayOfMonth/${month+1}/$year")
            // You can use this date for further reminder logic
        }

        // RecyclerView Setup
        recyclerView = findViewById(R.id.recyclerReminders)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = RemindersAdapter(remindersList)
        recyclerView.adapter = adapter

        findViewById<ImageView>(R.id.backArrowIcon).setOnClickListener {
            finish() // Go back to the previous activity
        }

        // Floating Action Button Click Listener
        findViewById<FloatingActionButton>(R.id.fabAddReminder).setOnClickListener {
            val intent = Intent(this, AddReminderActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newReminder = data?.getStringExtra("REMINDER")
            if (!newReminder.isNullOrEmpty()) {
                remindersList.add(newReminder)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
