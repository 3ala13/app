package com.example.clinicreminder.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicreminder.R
import com.example.clinicreminder.data.AppDatabase
import com.example.clinicreminder.data.entities.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientFormActivity : AppCompatActivity() {

    private lateinit var firstNameEdit: EditText
    private lateinit var lastNameEdit: EditText
    private lateinit var phoneEdit: EditText
    private lateinit var cityEdit: EditText
    private lateinit var birthDateEdit: EditText
    private lateinit var appointmentDateEdit: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_form)

        firstNameEdit = findViewById(R.id.editTextFirstName)
        lastNameEdit = findViewById(R.id.editTextLastName)
        phoneEdit = findViewById(R.id.editTextPhone)
        cityEdit = findViewById(R.id.editTextCity)
        birthDateEdit = findViewById(R.id.editTextBirthDate)
        appointmentDateEdit = findViewById(R.id.editTextAppointmentDate)
        saveButton = findViewById(R.id.buttonSavePatient)

        saveButton.setOnClickListener {
            val patient = Patient(
                firstName = firstNameEdit.text.toString(),
                lastName = lastNameEdit.text.toString(),
                phoneNumber = phoneEdit.text.toString(),
                city = cityEdit.text.toString(),
                birthDate = birthDateEdit.text.toString(),
                appointmentDate = appointmentDateEdit.text.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(applicationContext).patientDao().insertPatient(patient)
                runOnUiThread {
                    Toast.makeText(this@PatientFormActivity, "Patient enregistré", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
