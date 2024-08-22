package com.example.prueba4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var nick: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var verifPasswrd: EditText
    private lateinit var accBut: Button
    private lateinit var regBut: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nick = findViewById(R.id.nickET)
        email = findViewById(R.id.emailET)
        password = findViewById(R.id.passET)
        verifPasswrd = findViewById(R.id.verPassET)
        accBut = findViewById(R.id.accButton)
        regBut = findViewById(R.id.regButton)

        accBut.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}