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

class UserDashboardActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addPatientButton: Button
    private lateinit var importSheetButton: Button
    private lateinit var statsTextView: TextView

    private val patientList = mutableListOf<Patient>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        listView = findViewById(R.id.listViewPatients)
        addPatientButton = findViewById(R.id.buttonAddPatient)
        importSheetButton = findViewById(R.id.buttonImportSheet)
        statsTextView = findViewById(R.id.textStatsPatient)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        addPatientButton.setOnClickListener {
            Toast.makeText(this, "Formulaire d'ajout à venir", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, PatientFormActivity::class.java))
        }

        importSheetButton.setOnClickListener {
            Toast.makeText(this, "Import Google Sheet à venir", Toast.LENGTH_SHORT).show()
            // Implémentation future
        }

        loadPatients()

        listView.setOnItemClickListener { _, _, position, _ ->
            val patient = patientList[position]
            Toast.makeText(this, "SMS manuel à venir pour ${patient.firstName}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val patients = db.patientDao().getAllPatients()
            patientList.clear()
            patientList.addAll(patients)

            runOnUiThread {
                adapter.clear()
                adapter.addAll(patients.map { "${it.firstName} ${it.lastName} - ${it.appointmentDate}" })
                statsTextView.text = "Patients : ${patients.size}"
            }
        }
    }
}
