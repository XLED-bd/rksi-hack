package com.ivan.myapplication.network

import android.media.session.MediaSession.Token
import com.ivan.myapplication.model.AuthResponse
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.model.User
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
    suspend fun getStocks(): List<Stock>

    @GET("api/users/me")
    suspend fun getUser(@Header("Authorization") token: String): User

}