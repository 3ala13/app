package com.example.clinicreminder.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicreminder.R
import com.example.clinicreminder.data.AppDatabase
import com.example.clinicreminder.utils.AuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(applicationContext)
                val user = db.userDao().authenticate(username, password)

                runOnUiThread {
                    if (user != null) {
                        AuthManager.saveUserSession(applicationContext, user)
                        if (user.isAdmin) {
                            startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
                        } else {
                            startActivity(Intent(this@LoginActivity, UserDashboardActivity::class.java))
                        }
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Identifiants invalides", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
