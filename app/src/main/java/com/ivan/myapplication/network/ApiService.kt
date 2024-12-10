package com.ivan.myapplication.network

import com.ivan.myapplication.model.AuthResponse
import com.ivan.myapplication.model.Stock
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body userDetails: Map<String, String>): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body credentials: Map<String, String>): AuthResponse

    @GET("api/stocks")
    suspend fun getStocks(@Header("Authorization") token: String): List<Stock>
}