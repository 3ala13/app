package com.example.clinicreminder.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicreminder.R
import com.example.clinicreminder.data.AppDatabase
import com.example.clinicreminder.data.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addUserButton: Button
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var isAdminCheckbox: CheckBox
    private lateinit var statsTextView: TextView

    private val userList = mutableListOf<User>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        listView = findViewById(R.id.listViewUsers)
        addUserButton = findViewById(R.id.buttonAddUser)
        usernameInput = findViewById(R.id.editTextUsername)
        passwordInput = findViewById(R.id.editTextPassword)
        isAdminCheckbox = findViewById(R.id.checkBoxIsAdmin)
        statsTextView = findViewById(R.id.textStats)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        loadUsers()

        addUserButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val isAdmin = isAdminCheckbox.isChecked

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val newUser = User(username = username, password = password, isAdmin = isAdmin)
                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getDatabase(applicationContext)
                    db.userDao().insertUser(newUser)
                    loadUsers()
                }
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val user = userList[position]
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(applicationContext)
                db.userDao().deleteUser(user)
                loadUsers()
            }
            true
        }
    }

    private fun loadUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val users = db.userDao().getAllUsers()
            val patients = db.patientDao().getAllPatients()
            userList.clear()
            userList.addAll(users)

            runOnUiThread {
                adapter.clear()
                adapter.addAll(users.map { "${it.username} (${if (it.isAdmin) "Admin" else "Utilisateur"})" })
                statsTextView.text = "Utilisateurs: ${users.size} | Patients: ${patients.size}"
            }
        }
    }
}
