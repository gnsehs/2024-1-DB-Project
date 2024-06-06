package com.example.frontend

import com.example.frontend.Data.Article
import com.example.frontend.Data.Devices
import com.example.frontend.Data.Gamecompanys
import com.example.frontend.Data.Games
import com.example.frontend.Data.LoginResponse
import com.example.frontend.Data.User
import java.util.List
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface JsonPlaceHolderApi {
    @GET("gamecompanys")
    fun getGameCompanies(): Call<List<Gamecompanys>>

    @GET("/games")
    fun getGames(): Call<List<Games>>

    @GET("devices")
    fun getDevices(): Call<List<Devices>>

    @POST("/signup")
    fun signup(@Body user: User): Call<Void>

    @POST("/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("/api/articles")
    fun postArticle(@Body article: Article): Call<Void>

}