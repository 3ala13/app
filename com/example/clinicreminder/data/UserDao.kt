package com.example.clinicreminder.data

import androidx.room.*
import com.example.clinicreminder.data.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun authenticate(username: String, password: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
