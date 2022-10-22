package com.example.hw3_eduardo_eric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore


class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        // finding the button
        val showButton = findViewById<Button>(R.id.button)

        // finding the edit text
        val editText = findViewById<EditText>(R.id.Password)
        val editText2 = findViewById<EditText>(R.id.ConfirmPassword)



        // Setting On Click Listener
        showButton.setOnClickListener {

            // Getting the user input
            val text = editText.text.toString()
            val text2 = editText2.text.toString()
            if(text == text2) {
                val email = findViewById<EditText>(R.id.Email).text.toString()
                val password = findViewById<EditText>(R.id.Password).text.toString()
                val fullname = findViewById<EditText>(R.id.FullName).text.toString()

                val db = FirebaseFirestore.getInstance()
                val hw3: MutableMap<String, Any> = HashMap()

                hw3["Email"] = email
                hw3["Password"] = password
                hw3["FullName"] = fullname

                db.collection("hw3")
                    .add(hw3)
                    .addOnSuccessListener {
                        Log.d("dbfirebase", "save: ${hw3}")
                    }
                    .addOnFailureListener {
                        Log.d("dbfirebase Failed", "${hw3}")
                    }
                db.collection("hw3")
                    .get()
                    .addOnCompleteListener {

                        val result: StringBuffer = StringBuffer()

                        if (it.isSuccessful) {
                            for (document in it.result!!) {
                                Log.d("dbfirebase", "retrive: ${document.data.getValue("Email")} " +
                                        "${document.data.getValue("Password")} " +
                                        "${document.data.getValue("FullName")}")
                            }
                        }
                    }
                // Showing the user input
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
            }
            else {
                // Showing the user input
                Toast.makeText(this, "Password must match", Toast.LENGTH_SHORT).show()
            }
        }

        }
    }
