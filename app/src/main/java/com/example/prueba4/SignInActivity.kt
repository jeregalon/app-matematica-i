package com.example.prueba4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba4._backend.SupabaseAuthViewModel
import com.example.prueba4._backend.data.model.UserState
import com.example.prueba4._frontend.components.GradientButtonComponent
import com.example.prueba4._frontend.components.LoadingComponent
import com.example.prueba4._frontend.components.NormalTextComponent
import com.example.prueba4._frontend.components.SimpleDialog
import com.example.prueba4.ui.theme.BgColor
import com.example.prueba4.ui.theme.Primary

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : SupabaseAuthViewModel by viewModels()
        setContent {
            val context = LocalContext.current
            val userState by viewModel.userState

            var userEmail by remember { mutableStateOf("") }
            var userPassword by remember { mutableStateOf("") }
            var currentUserState by remember { mutableStateOf("") }

            val intentSignUp = Intent(this, SignUpActivity::class.java)
            val intentMain = Intent(this, MainActivity::class.java)

            var loading by remember { mutableStateOf(false) }
            var buttonClicked by remember { mutableStateOf(false) }
            var showDialog by remember { mutableStateOf(false) }

            var dialogText by remember { mutableStateOf("") }
            var dialogSuccess by remember { mutableStateOf(true) }

            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(intentMain)
                    finish()
                }
            })

            LaunchedEffect(Unit) {
                viewModel.isUserLoggedIn(
                    context,
                )
            }

            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(28.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {


                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { androidx.compose.material.Text(text = "Correo electrónico") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            focusedLabelColor = Primary,
                            cursorColor = Primary,
                            backgroundColor = BgColor
                        ),
                        keyboardOptions = KeyboardOptions.Default,
                        value = userEmail,
                        onValueChange = {
                            userEmail = it
                        },
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.message), contentDescription = "")
                        }
                    )

                    val passwordVisible = remember {
                        mutableStateOf(false)
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { androidx.compose.material.Text(text = "Contraseña") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            focusedLabelColor = Primary,
                            cursorColor = Primary,
                            backgroundColor = BgColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        value = userPassword,
                        onValueChange = {
                            userPassword = it
                        },
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.ic_lock), contentDescription = "")
                        },
                        trailingIcon = {

                            val iconImage = if(passwordVisible.value) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            }

                            var description = if(passwordVisible.value) {
                                stringResource(id = R.string.hide_password)
                            } else {
                                stringResource(id = R.string.show_password)
                            }

                            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                                Icon(imageVector = iconImage, contentDescription = description)
                            }
                        },
                        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()

                    )

                    Spacer(modifier = Modifier.weight(1f))

                    GradientButtonComponent(
                        onClick = {
                            viewModel.login(
                                context,
                                userEmail,
                                userPassword
                            )
                            buttonClicked = true
                        },
                        textValue = stringResource(id = R.string.login),
                        fontWeight = FontWeight.Bold)

                    NormalTextComponent(
                        value = stringResource(id = R.string.notAccount),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        paddingTop = 10.dp)

                    Spacer(modifier = Modifier.height(1.dp))

                    GradientButtonComponent(
                        onClick = {
                            startActivity(intentSignUp)
                            finish()
                        },
                        textValue = stringResource(id = R.string.register),
                        fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(20.dp))

                }

                when (userState) {
                    is UserState.Loading -> {
                        LoadingComponent()
                        loading = true
                    }

                    is UserState.Success -> {
                        val message = (userState as UserState.Success).message
                        currentUserState = message

                        if (loading && buttonClicked) {
                            showDialog = true

                            if (currentUserState.startsWith("Logged in successfully!")) {
                                dialogText = "¡Sesión iniciada exitosamente!"
                            } else {
                                dialogText = message
                            }

                            dialogSuccess = true
                            loading = false
                            buttonClicked = false
                        }

                    }

                    is UserState.Error -> {
                        val message = (userState as UserState.Error).message
                        currentUserState = message

                        if (loading && buttonClicked) {
                            showDialog = true

                            if (currentUserState.startsWith("Invalid login credentials")) {
                                dialogText = "Credenciales de inicio de sesión incorrectas"
                            } else if (currentUserState.startsWith("missing email or phone")) {
                                dialogText = "Por favor, proporcione un correo electrónico"
                            } else if (currentUserState.startsWith("Request timeout has expired")) {
                                dialogText = "El tiempo de espera de la solicitud ha expirado. Por favor, compruebe su conexión a Internet."
                            } else {
                                dialogText = message
                            }
                            dialogSuccess = false
                            loading = false
                            buttonClicked = false
                        }
                    }
                }

                if (dialogSuccess) {
                    SimpleDialog(
                        titleText = dialogText,
                        show = showDialog,
                        onDissmiss = { showDialog = false },
                        onOkClick = {
                            startActivity(intentMain)
                            finish()
                        },
                        painterResource = painterResource(id = R.drawable.log_success)
                    )
                } else {
                    SimpleDialog(
                        titleText = dialogText,
                        show = showDialog,
                        onDissmiss = { showDialog = false },
                        onOkClick = { showDialog = false },
                        painterResource = painterResource(id = R.drawable.log_error)
                    )
                }
            }
        }
    }
}

