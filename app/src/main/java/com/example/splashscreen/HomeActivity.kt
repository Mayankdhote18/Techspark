package com.example.splashscreen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra(SignInActivity.KEY1)
        val mail = intent.getStringExtra(SignInActivity.KEY2)
        val password = intent.getStringExtra(SignInActivity.KEY3)

        val welcometxt = findViewById<TextView>(R.id.textwelcome)
        val mailtxt = findViewById<TextView>(R.id.txtnmail)
        val nametxt = findViewById<TextView>(R.id.txtname)

        welcometxt.text = "Welcome $name"
        mailtxt.text = " mail: $mail"
        nametxt.text = " name: $name"


    }
}