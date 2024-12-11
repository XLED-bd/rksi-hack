package com.ivan.myapplication.network

import android.media.session.MediaSession.Token
import com.ivan.myapplication.model.AuthResponse
import com.ivan.myapplication.model.Balance
import com.ivan.myapplication.model.BuyStock
import com.ivan.myapplication.model.BuyStockAnswer
import com.ivan.myapplication.model.Portfolio
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.model.StockHistory
import com.ivan.myapplication.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body userDetails: Map<String, String>): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body credentials: Map<String, String>): AuthResponse

    @GET("api/stocks")
    suspend fun getStocks(): List<Stock>

    @GET("api/stocks/{id}")
    suspend fun getStock(@Path("id") postId: String): Stock

    @GET("api/users/me")
    suspend fun getUser(@Header("Authorization") token: String): User

    @GET("api/transactions/history")
    suspend fun getHistory(@Header("Authorization") token: String): List<StockHistory>

    @GET("api/portfolios")
    suspend fun getPortfolios(@Header("Authorization") token: String): List<Portfolio>


    @POST("api/transactions/buy")
    suspend fun buyStocks(@Header("Authorization") token: String, @Body buyDetails: BuyStock): BuyStockAnswer

    @POST("api/transactions/sell")
    suspend fun sellStocks(@Header("Authorization") token: String, @Body buyDetails: BuyStock): BuyStockAnswer

    @GET("api/users/balance")
    suspend fun getBalance(@Header("Authorization") token: String): Balance

    //@POST("api/portfolios")
    //suspend fun createPortfolios
}