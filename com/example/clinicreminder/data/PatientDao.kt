package com.example.clinicreminder.data

import androidx.room.*
import com.example.clinicreminder.data.entities.Patient

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients")
    suspend fun getAllPatients(): List<Patient>

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Update
    suspend fun updatePatient(patient: Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)
}
