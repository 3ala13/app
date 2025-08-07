package com.example.clinicreminder.sms

import android.app.Activity
import android.telephony.SmsManager
import android.widget.Toast

object SmsSender {
    fun sendSMS(activity: Activity, phoneNumber: String, message: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(activity, "SMS envoyé à $phoneNumber", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(activity, "Erreur SMS: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
