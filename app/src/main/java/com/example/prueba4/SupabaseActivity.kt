package com.example.prueba4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.prueba4.ui.theme.Prueba4Theme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

val supabase = createSupabaseClient(
    supabaseUrl = "https://jbaycstmoggkesztkgtt.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpiYXljc3Rtb2dna2VzenRrZ3R0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjQzNDM4NzQsImV4cCI6MjAzOTkxOTg3NH0.1jXMV1hee-f5T8ehKpq6U_OcIxlCUt8bjupgWxT_dig"
) {
    install(Auth)
    install(Postgrest)
    //install other modules
}

class SupabaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prueba4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesList()
                }
            }
        }
    }
}

@Serializable
data class Note (
    val id: Int,
    val body: String,
)

@Composable
fun NotesList() {
    val notes = remember { mutableStateListOf<Note>() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val results = supabase.from("tabla1").select().decodeList<Note>()
            notes.addAll(results)
        }
    }
    Column {
        LazyColumn {
            items(notes) { note ->
                ListItem(headlineContent = {Text(text = note.body)})
            }

        }
        var newNote by remember { mutableStateOf("") }
        var composableScope = rememberCoroutineScope()
        Row {
            OutlinedTextField(value = newNote, onValueChange = {newNote = it})
            Button(onClick = {
                composableScope.launch(Dispatchers.IO) {
                    val note = supabase.from("tabla1").insert(mapOf("body" to newNote)) {
                        select()
                        single()
                    }.decodeAs<Note>()
                    notes.add(note)
                    newNote = ""
                }
            }) {
                Text("Save")
            }
        }
    }
}