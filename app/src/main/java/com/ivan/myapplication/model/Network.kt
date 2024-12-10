package com.ivan.myapplication.model

data class AuthResponse(
    val token: String
)

data class Stock(
    val id: String,
    val symbol: String,
    val name: String,
    val lastPrice: Double,
    val currency: String
)
