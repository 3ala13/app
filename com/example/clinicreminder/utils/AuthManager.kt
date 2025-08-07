package com.example.clinicreminder.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.clinicreminder.data.entities.User

object AuthManager {
    private const val PREF_NAME = "user_session"
    private const val KEY_USERNAME = "username"
    private const val KEY_IS_ADMIN = "is_admin"

    fun saveUserSession(context: Context, user: User) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString(KEY_USERNAME, user.username)
            putBoolean(KEY_IS_ADMIN, user.isAdmin)
            apply()
        }
    }

    fun clearSession(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    fun isAdmin(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_ADMIN, false)
    }

    fun getUsername(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_USERNAME, null)
    }
}
