package com.example.splashscreen

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signupButton = findViewById<Button>(R.id.button4)  // <-- fixed parentheses
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signupButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val password = etPassword.text.toString()

            val user = User(name, mail, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "user registered successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {  // <-- changed to addOnFailureListener
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
