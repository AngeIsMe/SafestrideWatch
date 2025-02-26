package com.capstone.safestridew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RemindersAdapter(
    private val reminders: MutableList<String>
) : RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder>() {

    private var selectedPosition: Int? = null // Track which item is selected

    class ReminderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reminderText: TextView = view.findViewById(R.id.reminderText)
        val doneButton: Button = view.findViewById(R.id.doneButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reminder_item, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.reminderText.text = reminder

        // Show or hide the "Done" button based on selection
        if (selectedPosition == position) {
            holder.doneButton.visibility = View.VISIBLE
        } else {
            holder.doneButton.visibility = View.GONE
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            selectedPosition = if (selectedPosition == position) null else position // Toggle selection
            notifyDataSetChanged() // Refresh the list
        }

        // Handle "Done" button click
        holder.doneButton.setOnClickListener {
            reminders.removeAt(position) // Remove the selected reminder
            selectedPosition = null // Reset selection
            notifyDataSetChanged() // Refresh the list
        }
    }

    override fun getItemCount(): Int {
        return reminders.size
    }
}

