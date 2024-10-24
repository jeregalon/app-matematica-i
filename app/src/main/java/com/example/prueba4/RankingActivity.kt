package com.example.prueba4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba4._backend.SupabaseAuthViewModel
import com.example.prueba4._backend.data.network.SupabaseClient.client
import com.example.prueba4._frontend.components.TableRow
import com.example.prueba4.ui.theme.BronzeBass
import com.example.prueba4.ui.theme.BronzeGradient
import com.example.prueba4.ui.theme.GoldBass
import com.example.prueba4.ui.theme.GoldGradient
import com.example.prueba4.ui.theme.Primary
import com.example.prueba4.ui.theme.Prueba4Theme
import com.example.prueba4.ui.theme.Secondary
import com.example.prueba4.ui.theme.SilverBass
import com.example.prueba4.ui.theme.SilverGradient
import com.example.prueba4.ui.theme.WhiteColor
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

class RankingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val intentMain = Intent(this, MainActivity::class.java)

            Ranking()

            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(intentMain)
                    finish()
                }
            })
        }
    }
}

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}

@Serializable
data class Player (
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val nickname: String,
    val puntuation: Int,
)

@Composable
fun Ranking() {
    val players = remember { mutableStateListOf<Player>() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val results = client.from("profiles").select().decodeList<Player>()
            val rank = results.sortedByDescending { it.puntuation }.take(10)
            players.addAll(rank)
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                if (players.isNotEmpty()) {

                    if (players.size >= 10) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            GoldBass,
                                            GoldGradient
                                        )
                                    )
                                )
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = players.get(0).nickname,
                                fontSize = 25.sp)
                            Text(text = players.get(0).puntuation.toString(),
                                fontSize = 25.sp)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            SilverBass,
                                            SilverGradient
                                        )
                                    )
                                )
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = players.get(1).nickname,
                                fontSize = 25.sp)
                            Text(text = players.get(1).puntuation.toString(),
                                fontSize = 25.sp)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            BronzeBass,
                                            BronzeGradient
                                        )
                                    )
                                )
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = players.get(2).nickname,
                                fontSize = 25.sp)
                            Text(text = players.get(2).puntuation.toString(),
                                fontSize = 25.sp)
                        }

                        for (i in 3..9) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = players.get(i).nickname,
                                    fontSize = 20.sp)
                                Text(text = players.get(i).puntuation.toString(),
                                    fontSize = 20.sp)
                            }
                        }
                        
                    } else {
                        Text(text = "No hay suficientes jugadores para hacer un ranking")
                    }

                }

            }

        }
    }
    
}