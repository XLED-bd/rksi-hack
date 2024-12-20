package com.ivan.myapplication.model

import kotlinx.coroutines.flow.MutableStateFlow

data class User(
    val id: String,
    val email: String,
    val created_at: String
)

data class RegisterUser(
    val email: String,
    val password: String,
)

data class LoginUser(
    val email: String,
    val password: String,
)
