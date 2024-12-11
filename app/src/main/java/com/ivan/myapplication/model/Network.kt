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

data class StockHistory(
    val id: String,
    val stock_name: String,
    val stock_symbol: String,
    val stock_last_price: Float,
    val amount: Int,
    val price: Double,
    val type: String,
    val created_at: String
)

data class Portfolio(
    val portfolio_id: String,
    val stock_name: String,
    val stock_symbol: String,
    val stock_last_price: Float,
    val amount: Int,
    val current_price: Double,
    val average_purchase_price: String,
)

data class AuthUIState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)
