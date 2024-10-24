package com.example.prueba4._backend

import com.example.prueba4._backend.data.model.UserState
import com.example.prueba4._backend.data.network.SupabaseClient.client
import com.example.prueba4._backend.utils.SharedPreferenceHelper
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseAuthViewModel: ViewModel() {

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
        userNick: String
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject {
                        put("nickname", userNick)
                        put("puntuation", 0)
                    }
                }
                saveToken(context)
                _userState.value = UserState.Success("Registered successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String,
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                _userState.value = UserState.Loading
                client.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    fun logout(context: Context) {
        val sharedPref = SharedPreferenceHelper(context)
        _userState.value = UserState.Loading
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.signOut()
                sharedPref.clearPreferences()
                _userState.value = UserState.Success("Logged out successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun isUserLoggedIn(
        context: Context,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val token = getToken(context)
                if(token.isNullOrEmpty()) {
                    _userState.value = UserState.Success("User not logged in!")
                } else {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in!")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
    }

    fun update(
        totalPunt: Int
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.updateUser {
                    data = buildJsonObject {
                        put("puntuation", totalPunt)
                    }
                }
//                saveToken(context)
                _userState.value = UserState.Success("")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun retrieveUsers(context: Context): List<UserInfo>? {
        var users: List<UserInfo>? = null
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                users = client.auth.admin.retrieveUsers()
                saveToken(context)
                _userState.value = UserState.Success("")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
        return users
    }
}