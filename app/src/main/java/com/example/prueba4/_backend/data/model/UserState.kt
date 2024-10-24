package com.example.prueba4._backend.data.model

import co.touchlab.kermit.Message

sealed class UserState {
    object Loading: UserState()
    data class Success(val message: String): UserState()
    data class Error(val message: String): UserState()

}