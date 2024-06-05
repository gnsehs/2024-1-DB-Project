package com.example.frontend

import com.example.frontend.Data.Article
import com.example.frontend.Data.Devices
import com.example.frontend.Data.Gamecompanys
import com.example.frontend.Data.Games
import com.example.frontend.Data.User
import org.json.JSONObject
import java.util.List
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Query

public interface JsonPlaceHolderApi {
    @GET("gamecompanys")
    fun getGameCompanies(): Call<List<Gamecompanys>>

    @GET("/games")
    fun getGames(): Call<List<Games>>

    @GET("devices")
    fun getDevices(): Call<List<Devices>>

    @FormUrlEncoded
    @POST("/signup")
    fun getPostList(@FieldMap param: JSONObject): Call<User>

    @POST("/login")
    fun login(@Query("username") username: String? = null,
              @Query("password") password: String? = null)

    @POST("/api/articles")
    fun postArticle(@Body article: Article): Call<Void>

}