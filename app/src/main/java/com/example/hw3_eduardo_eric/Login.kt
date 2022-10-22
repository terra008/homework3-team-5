package com.example.hw3_eduardo_eric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonClick3 = findViewById<Button>(R.id.button2)
        val db = FirebaseFirestore.getInstance()
        val hw3: MutableMap<String, Any> = HashMap()


        buttonClick3.setOnClickListener {
            val emailL = findViewById<EditText>(R.id.Email1).text.toString()
            val passwordL = findViewById<EditText>(R.id.Password1).text.toString()


            db.collection("hw3")
                .get()
                .addOnCompleteListener {

                    val result: StringBuffer = StringBuffer()

                   outer@ if (it.isSuccessful) {
                        first@ for (document in it.result!!) {
                            val x = document.data.getValue("Email").toString()
                            val y = document.data.getValue("Password").toString()
                            if(x == emailL && y == passwordL){
                                val intent3 = Intent(this, LoginLanding::class.java)
                                startActivity(intent3)
                                return@addOnCompleteListener
                            }

                            Log.d("dbfirebase",emailL + passwordL)
                            Log.d("dbfirebase", "$x " +
                                    "$y " +
                                    "${document.data.getValue("FullName")}")
                        }
                        Toast.makeText(this, "Either your id or password is incorrect", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
}