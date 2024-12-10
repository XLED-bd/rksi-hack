package com.ivan.myapplication.model

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
