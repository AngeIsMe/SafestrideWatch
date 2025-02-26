package com.capstone.safestridew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.capstone.safestridew.Contact


class ContactAdapter(context: Context, private val contacts: List<Contact>) : ArrayAdapter<Contact>(context, 0, contacts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)

        val contact = getItem(position)

        val contactName: TextView = view.findViewById(R.id.contactName)
        val contactRelationship: TextView = view.findViewById(R.id.contactRelationship)
        val contactPhone: TextView = view.findViewById(R.id.contactPhone)

        contact?.let {
            contactName.text = it.name
            contactRelationship.text = it.relationship
            contactPhone.text = it.phone // Ensure this is displayed as-is, with +63 included
        }

        return view
    }
}

