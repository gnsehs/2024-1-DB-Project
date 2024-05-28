package com.example.frontend

import java.util.List
import retrofit2.Call
import retrofit2.http.GET
public interface JsonPlaceHolderApi {
    @GET("gamecompanys")
    fun getGameCompanies(): Call<List<GameCompany>>

    @GET("games")
    fun getGames(): Call<List<Games>>
}