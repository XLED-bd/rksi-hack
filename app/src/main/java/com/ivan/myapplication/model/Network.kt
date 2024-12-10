package com.ivan.myapplication.model

data class AuthResponse(
    val token: String
)

data class Stock(
    val id: String,
    val symbol: String,
    val name: String,
    val last_price: Double,
    val currency: String
)

data class AuthUIState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)
