package com.example.clinicreminder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clinicreminder.data.entities.Patient
import com.example.clinicreminder.data.entities.User

@Database(entities = [User::class, Patient::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "clinic_reminder_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
