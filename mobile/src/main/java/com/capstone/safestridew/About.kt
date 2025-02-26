package com.capstone.safestridew

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Back button to go to Dashboard
        val backArrowIcon: ImageView = findViewById(R.id.backArrowIcon)
        backArrowIcon.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        // Terms of Service Click Listener
        val termsOfUse: LinearLayout = findViewById(R.id.termsOfUse)
        termsOfUse.setOnClickListener {
            showFeatureDialog("Terms of Service", getTermsOfServiceContent())
        }

        // Privacy and Security Click Listener
        val privacySecurity: LinearLayout = findViewById(R.id.privacyAndSecurity)
        privacySecurity.setOnClickListener {
            showFeatureDialog("Privacy and Security", getPrivacySecurityContent())
        }

        // Contact and Support Click Listener
        val contactSupport: LinearLayout = findViewById(R.id.contactAndSupport)
        contactSupport.setOnClickListener {
            showFeatureDialog("Contact and Support", getContactSupportContent())
        }

    }

    // Function to display the pop-up dialog
    private fun showFeatureDialog(title: String, content: String) {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_terms, null)
        dialog.setContentView(dialogView)

        val dialogTitle: TextView = dialogView.findViewById(R.id.dialogTitle)
        val dialogContent: TextView = dialogView.findViewById(R.id.dialogContent)
        val closeButton: Button = dialogView.findViewById(R.id.closeButton)

        dialogTitle.text = title
        dialogContent.text = content

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // Function to return full "Terms of Service" content
    private fun getTermsOfServiceContent(): String {
        return """
        **Introduction**
        Welcome to SAFE STRIDE. Our app and wearable device are designed to assist Persons with Disabilities (PWDs) and their caregivers.

        **User Responsibilities**
        • Users must provide accurate information when creating an account.
        • The app should only be used for assisting PWDs and caregivers.
        • Unauthorized access, misuse, or tampering is strictly prohibited.

        **Account & Security**
        • Users must maintain confidentiality of account credentials.
        • SAFE STRIDE is not liable for unauthorized access due to weak or shared passwords.
        • Security breaches must be reported immediately to support.

        **Limitations of Liability**
        • SAFE STRIDE provides real-time tracking and notifications but does not prevent all emergencies.
        • Service performance depends on internet and GPS availability.
        • We are not responsible for data loss due to hardware failures or third-party service disruptions.

        **Updates & Modifications**
        • SAFE STRIDE reserves the right to update these terms.
        • Continued use of the app means acceptance of updated terms.
        """.trimIndent()
    }

    // Function to return full "Privacy and Security" content
    private fun getPrivacySecurityContent(): String {
        return """
        **Data Collection**
        We collect the following data to ensure the safety of PWDs:
        • Location Data: Used for real-time tracking and emergency assistance.
        • Emergency Logs: Stored when emergency buttons are pressed.
        • Device Information: Used for app compatibility and security monitoring.

        **How Data is Used**
        • Caregivers can access PWDs' real-time location when necessary.
        • Emergency logs help caregivers respond quickly.
        • Data is never sold or used for marketing purposes.

        **Data Protection**
        • All data is encrypted to prevent unauthorized access.
        • Secure servers store logs and sensitive information.
        • Regular security updates maintain system integrity.

        **User Control**
        • Users can request data deletion by contacting support.
        • Location tracking can be turned off (may limit functionality).

        **Third-Party Sharing**
        • SAFE STRIDE does not share personal data with advertisers.
        • Data is only shared with authorized caregivers and emergency responders.
        """.trimIndent()
    }

    // Function to return full "Contact and Support" content
    private fun getContactSupportContent(): String {
        return """
        **Customer Support**
        • Email Support: [Safestride@gmail.com]
        • FAQs: A self-help section in the app for common issues.

        **Report Issues**
        • App malfunctions can be reported via the Help Center in the app.
        • Critical bugs can be reported directly to support.

        **Feedback & Suggestions**
        • Users can send feature requests through the Feedback Form in the app.
        • Regular updates improve accessibility and functionality.

        **Emergency Contacts**
        • Quick access to caregiver contact details.
        • Optional integration with local emergency services (depending on location).
        """.trimIndent()
    }
}

