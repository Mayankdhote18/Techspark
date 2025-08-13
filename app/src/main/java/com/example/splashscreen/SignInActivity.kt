package com.example.splashscreen

import android.content.Intent
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

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.splashscreen.SignInActivity.name"
        const val KEY2 = "com.example.splashscreen.SignInActivity.email"
        const val KEY3 = "com.example.splashscreen.SignInActivity.password"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signInButton = findViewById<Button>(R.id.btnsignin)
        val etxtName = findViewById<TextInputEditText>(R.id.usernameedittxt)
        val etxtPassword = findViewById<TextInputEditText>(R.id.passwordedittxt)

        signInButton.setOnClickListener {
            val name = etxtName.text.toString()
            val password = etxtPassword.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {
                readData(name, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()

            }
        }

    }
    fun readData(name: String, password: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(name).get().addOnSuccessListener {
            // lets check user exists or not
            if(it.exists())
            {
               //take user to new page, with intent
                val email = it.child("email").value
                val name= it.child("name").value
                val password = it.child("password").value
                Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()

                val intentWelcome = Intent(this, HomeActivity::class.java)
                intentWelcome.putExtra(KEY1, name.toString())
                intentWelcome.putExtra(KEY2, email.toString())
                intentWelcome.putExtra(KEY3, password.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this, "Please signup first before logging in", Toast.LENGTH_SHORT).show()

            }
    }.addOnFailureListener{
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()    }
        }
}