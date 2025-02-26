package com.capstone.safestridew

data class Contact(
    val name: String,
    val phone: String,
    val relationship: String
) {
    override fun toString(): String {
        return "$name ($relationship) - $phone"
    }
}

