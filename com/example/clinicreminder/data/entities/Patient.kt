package com.example.clinicreminder.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val city: String,
    val birthDate: String,
    val appointmentDate: String
)
