package com.capstone.safestridew


import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ManageContactsActivity : AppCompatActivity() {

    private lateinit var contactsListView: ListView
    private lateinit var addContactButton: Button
    private var contactsList: MutableList<Contact> = mutableListOf()
    private lateinit var contactsAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_contacts)

        contactsListView = findViewById(R.id.contactsListView)
        addContactButton = findViewById(R.id.addContactButton)

        // Load contacts from SharedPreferences
        contactsList = loadContacts()

        // Set up the custom adapter to display contacts
        contactsAdapter = ContactAdapter(this, contactsList)
        contactsListView.adapter = contactsAdapter

        // Handle Add Contact Button
        addContactButton.setOnClickListener {
            showAddContactDialog()
        }

        // Handle Item Click to show contact info
        contactsListView.setOnItemClickListener { _, _, position, _ ->
            val contact = contactsList[position]
            showContactInfoDialog(contact)
        }
        // Back Arrow Click Listener
        findViewById<View>(R.id.backArrowIcon).setOnClickListener {
            finish()
        }

    }

    private fun showAddContactDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_dialog_contact)

        val editTextName: EditText = dialog.findViewById(R.id.editTextName)
        val editTextPhone: EditText = dialog.findViewById(R.id.editTextPhone)
        val editTextRelationship: EditText = dialog.findViewById(R.id.editTextRelationship)
        val buttonSaveContact: Button = dialog.findViewById(R.id.buttonSaveContact)

        // Clear the phone field, allowing the user to type freely
        editTextPhone.setText("")
        editTextPhone.setSelection(0) // Set cursor at the beginning after setting text

        // Add TextWatcher for phone number input (to allow only 11 digits)
        editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No special formatting or restriction, just let the user type freely
                val phone = s.toString()

                // Limit input to 11 digits
                if (phone.length > 10) {
                    editTextPhone.setText(phone.substring(0, 10)) // Limit to 11 digits
                    editTextPhone.setSelection(editTextPhone.text.length) // Move cursor to the end
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonSaveContact.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            val relationship = editTextRelationship.text.toString()

            // Validate phone number to ensure it is exactly 10 digits long
            if (name.isNotEmpty() && phone.isNotEmpty() && relationship.isNotEmpty()) {
                if (phone.length == 10) {
                    addContact(name, phone, relationship)
                    dialog.dismiss() // Dismiss the dialog after saving
                } else {
                    Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }



    private fun showContactInfoDialog(contact: Contact) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.contact_info_dialog)

        val titleContactInfo: TextView = dialog.findViewById(R.id.titleContactInfo)
        val contactName: TextView = dialog.findViewById(R.id.contactName)
        val contactRelationship: TextView = dialog.findViewById(R.id.contactRelationship)
        val contactPhone: TextView = dialog.findViewById(R.id.contactPhone)

        // Set contact information
        contactName.text = contact.name
        contactRelationship.text = contact.relationship
        contactPhone.text = contact.phone

        // Initialize Back Arrow in the dialog
        val backArrow: ImageView = dialog.findViewById(R.id.backArrow)

        // Set the back arrow to close the dialog
        backArrow.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog when the back arrow is clicked
        }

        // Set the delete icon click listener
        val deleteIcon: ImageView = dialog.findViewById(R.id.deleteIcon)
        deleteIcon.setOnClickListener {
            // When the delete icon is clicked, show the confirmation dialog
            showDeleteConfirmationDialog(contact)
            dialog.dismiss() // Dismiss the contact info dialog
        }

        dialog.show()
    }



    private fun showDeleteConfirmationDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Contact")
            .setMessage("Are you sure you want to delete this contact?")
            .setPositiveButton("Delete") { dialog, _ ->
                deleteContact(contact) // Call delete function when "Delete" is clicked
                dialog.dismiss() // Dismiss the dialog after deletion
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss() // Close the dialog if "Cancel" is clicked
            }
            .show()
    }

    private fun deleteContact(contact: Contact) {
        contactsList.remove(contact) // Remove the contact from the list
        saveContacts(contactsList) // Save updated contact list to SharedPreferences
        contactsAdapter.notifyDataSetChanged() // Notify adapter to update the list view
        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show()
    }

    private fun saveContacts(contacts: List<Contact>) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("EmergencyContacts", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(contacts)

        editor.putString("contacts", json)
        editor.apply()
    }


    private fun loadContacts(): MutableList<Contact> {
        val sharedPreferences: SharedPreferences = getSharedPreferences("EmergencyContacts", MODE_PRIVATE)
        val json = sharedPreferences.getString("contacts", "[]") ?: "[]"

        val gson = Gson()
        val type = object : TypeToken<List<Contact>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun addContact(name: String, phone: String, relationship: String) {
        val newContact = Contact(name, phone, relationship)
        contactsList.add(newContact)
        saveContacts(contactsList)
        contactsAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show()
    }
}

