package com.example.hw3_eduardo_eric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonClick = findViewById<Button>(R.id.SignupB)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        val buttonClick2 = findViewById<Button>(R.id.LoginB)
        buttonClick2.setOnClickListener {
            val intent2 = Intent(this, Login::class.java)
            startActivity(intent2)
        }
    }
}