package com.example.frontend

import com.example.frontend.Data.GameCompany
import com.example.frontend.Data.Games
import com.example.frontend.Data.User
import org.json.JSONObject
import java.util.List
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Query

public interface JsonPlaceHolderApi {
    @GET("gamecompanys")
    fun getGameCompanies(): Call<List<GameCompany>>

    @GET("games")
    fun getGames(): Call<List<Games>>

    @FormUrlEncoded
    @POST("/signup")
    fun getPostList(@FieldMap param: JSONObject): Call<User>

    @POST("/login")
    fun login(@Query("username") username: String? = null,
              @Query("password") password: String? = null)
}