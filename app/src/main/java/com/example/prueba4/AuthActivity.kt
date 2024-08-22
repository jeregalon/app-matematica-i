package com.example.prueba4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.withCreated
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

//import com.google.firebase.auth.FirebaseAuth


//val supabase = createSupabaseClient(
//    supabaseUrl = "https://rrzfmsekshszjbeqwtyw.supabase.co",
//    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJyemZtc2Vrc2hzempiZXF3dHl3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjI2NjM5NjcsImV4cCI6MjAzODIzOTk2N30.pTUZKiKcrkg3Wq1ybnjSlicVzinsZWKZmKF9Vw7CyQ0"
//) {
//    install(Postgrest)
//}

class AuthActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var accBut: Button
    private lateinit var regBut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        email = findViewById(R.id.emailET)
        password = findViewById(R.id.passET)
        accBut = findViewById(R.id.accButton)
        regBut = findViewById(R.id.regButton)

        regBut.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        setup()
//        namesList()
    }

    private fun setup() {
        accBut.setOnClickListener {
//            if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),
//                    password.text.toString()).addOnCompleteListener {
//
//                        if (it.isSuccessful) {
//
//                        } else {
//                            showAlert()
//                        }
//                }
//            }
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

//    @Serializable
//    data class User (
//        val id: Int,
//        val name: String,
//    )

//    @Composable
//    fun namesList() {
//        val names = remember { mutableStateListOf<User>() }
//        LaunchedEffect(Unit) {
//            withContext(Dispatchers.IO) {
//                val results = supabase.from("users").select().decodeList<User>()
//                names.addAll(results)
//            }
//        }
//        LazyColumn {
//            item(names) {
//                name -> ListView(headline)
//            }
//        }
//    }

}