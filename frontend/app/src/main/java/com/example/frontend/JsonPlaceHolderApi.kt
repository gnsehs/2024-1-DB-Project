package com.example.frontend

import com.example.frontend.Data.Article
import com.example.frontend.Data.Devices
import com.example.frontend.Data.Gamecompanys
import com.example.frontend.Data.Games
import com.example.frontend.Data.LoginResponse
import com.example.frontend.Data.User
import com.example.frontend.Data.Write
import com.example.frontend.Data.comment_write
import java.util.List
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

public interface JsonPlaceHolderApi {
    @GET("gamecompanys")
    fun getGameCompanies(): Call<List<Gamecompanys>>

    @GET("/games")
    fun getGames(): Call<List<Games>>

    @GET("devices")
    fun getDevices(): Call<List<Devices>>

    @GET("/api/articles")
    fun getArticle(): Call<List<Article>>

    @GET("/api/articles/{id}")
    fun getArticleById(@Path("id") id: Int): Call<Article>

    @GET("/games/{id}")
    fun getGameById(@Path("id") id: Int): Call<Games>

    @GET("/gamecompanys/{id}")
    fun getCompanyById(@Path("id") id: Int): Call<Gamecompanys>

    @GET("/devices/{id}")
    fun getDeviceById(@Path("id") id: Int): Call<Devices>

    @POST("/signup")
    fun signup(@Body user: User): Call<Void>

    @POST("/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("/api/articles")
    fun postArticle(@Body article: Write): Call<Void>

    @POST("/api/comments")
    fun postComment(@Body comment: comment_write): Call<Void>

    @GET("/api/article")
    fun getOneArticle(@Query("game_id") id: Int) : Call<List<Article>>

}