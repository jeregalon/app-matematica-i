package com.example.prueba4

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba4._backend.SupabaseAuthViewModel
import com.example.prueba4._backend.data.model.UserState
import com.example.prueba4._backend.data.network.SupabaseClient.client
import com.example.prueba4._frontend.components.NormalTextComponent
import com.example.prueba4._frontend.components.SquareTextComponent
import com.example.prueba4._frontend.components.WhiteBorderTransparentButtonComponent
import com.example.prueba4._frontend.components.WhiteBorderTransparentButtonMaxWidthComponent
import com.example.prueba4.ui.theme.WhiteColor
import io.github.jan.supabase.gotrue.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : SupabaseAuthViewModel by viewModels()
        setContent {

            val context = LocalContext.current
            val userState by viewModel.userState
            var currentUserState by remember { mutableStateOf("") }

            val intentSignUp = Intent(this, SignUpActivity::class.java)
            val intentSignIn = Intent(this, SignInActivity::class.java)

            val intentLevelsDerivates = Intent(this, LevelsDerivatesActivity::class.java)
            val intentLevelsLimits = Intent(this, LevelsLimitsActivity::class.java)
            val intentRanking = Intent(this, RankingActivity::class.java)

            var welcomeText = ""
            var pointsText = ""

            var logged by remember { mutableStateOf(true) }
            var notLogged by remember { mutableStateOf(true) }

            var connected by remember { mutableStateOf(false) }

            val thisContext : Context = this

            connected = checkForInternet(thisContext)

            if (connected) {

                val nick = client.auth.currentUserOrNull()?.userMetadata?.get("nickname")
                val points =
                    client.auth.currentUserOrNull()?.userMetadata?.get("puntuation").toString()

                LaunchedEffect(Unit) {
                    viewModel.isUserLoggedIn(
                        context,
                    )
                }

                when (userState) {
                    is UserState.Loading -> {
                        welcomeText = "Cargando..."
                        pointsText = ""
                        logged = false
                        notLogged = false
                    }

                    is UserState.Success -> {
                        if (nick != null) {
                            var nickText = nick.toString().replace("\"", "")
                            welcomeText = "¡Hola, $nickText!"
                            pointsText = "XP: $points"
                            logged = true
                        } else {
                            welcomeText = "¡Hola!"
                            pointsText = ""
                            logged = false
                        }
                        notLogged = !logged
                    }

                    is UserState.Error -> {
                        welcomeText = "¡Hola!"
                        logged = false
                        notLogged = true
                    }
                }
            } else {
                logged = false
                notLogged = false
                welcomeText = "Sin conexión"
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                val image: Painter = painterResource(id = R.drawable.fondo_main)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    SquareTextComponent(
                        value = pointsText,
                        color = WhiteColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        paddingTop = 10.dp,
                        paddingStart = 10.dp)

                    Spacer(modifier = Modifier.weight(1f))

                    NormalTextComponent(
                        value = welcomeText,
                        color = WhiteColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp)

                    if (!connected) {

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            WhiteBorderTransparentButtonComponent(
                                onClick = {
                                    connected = checkForInternet(thisContext)
                                },
                                textValue = "Refrescar",
                                fontColor = WhiteColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                borderStroke = 5.dp,
                                paddingTop = 5.dp,
                                paddingBottom = 5.dp,
                                paddingStart = 10.dp,
                                paddingEnd = 10.dp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (notLogged) {

                        NormalTextComponent(
                            value = "Oh, parece que no te has registrado",
                            color = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            paddingTop = 10.dp)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            WhiteBorderTransparentButtonComponent(
                                onClick = {
                                    startActivity(intentSignUp)
                                    finish()
                                },
                                textValue = "Crear cuenta",
                                fontColor = WhiteColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                borderStroke = 5.dp,
                                paddingTop = 5.dp,
                                paddingBottom = 5.dp,
                                paddingStart = 10.dp,
                                paddingEnd = 10.dp,
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        NormalTextComponent(
                            value = "¿Ya tienes una cuenta?",
                            color = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            paddingTop = 10.dp)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            WhiteBorderTransparentButtonComponent(
                                onClick = {
                                    startActivity(intentSignIn)
                                    finish()
                                },
                                textValue = "Iniciar sesión",
                                fontColor = WhiteColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                borderStroke = 5.dp,
                                paddingTop = 5.dp,
                                paddingBottom = 5.dp,
                                paddingStart = 10.dp,
                                paddingEnd = 10.dp
                            )
                        }

                    } else if (logged) {
                        NormalTextComponent(
                            value = "¿No eres tú?",
                            color = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            WhiteBorderTransparentButtonComponent(
                                onClick = {
                                    viewModel.logout(
                                        context
                                    )
                                },
                                textValue = "Cerrar sesión",
                                fontColor = WhiteColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                borderStroke = 5.dp,
                                paddingTop = 5.dp,
                                paddingBottom = 5.dp,
                                paddingStart = 10.dp,
                                paddingEnd = 10.dp,
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.weight(1f))

                        NormalTextComponent(
                            value = "JUGAR",
                            color = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            paddingTop = 10.dp)

                        if (!logged) {
                            NormalTextComponent(
                                value = "No es necesario estar registrado o conectado a Internet",
                                color = WhiteColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                paddingTop = 10.dp)
                        } else {
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        WhiteBorderTransparentButtonMaxWidthComponent(
                            onClick = {
                                startActivity(intentLevelsLimits)
                                finish()
                            },
                            textValue = "Límites",
                            fontColor = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            borderStroke = 5.dp,
                            paddingTop = 5.dp,
                            paddingBottom = 5.dp,
                            paddingStart = 10.dp,
                            paddingEnd = 10.dp
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        WhiteBorderTransparentButtonMaxWidthComponent(
                            onClick = {
                                startActivity(intentLevelsDerivates)
                                finish()
                            },
                            textValue = "Derivadas",
                            fontColor = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            borderStroke = 5.dp,
                            paddingTop = 5.dp,
                            paddingBottom = 5.dp,
                            paddingStart = 10.dp,
                            paddingEnd = 10.dp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        WhiteBorderTransparentButtonMaxWidthComponent(
                            onClick = {
                                startActivity(intentRanking)
                                finish()
                            },
                            textValue = "Ránking",
                            fontColor = WhiteColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            borderStroke = 5.dp,
                            paddingTop = 5.dp,
                            paddingBottom = 5.dp,
                            paddingStart = 10.dp,
                            paddingEnd = 10.dp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                    }


                }
            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}
