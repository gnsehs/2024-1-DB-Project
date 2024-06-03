package com.example.frontend

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder_post {
    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    var api: JsonPlaceHolderApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(JsonPlaceHolderApi::class.java)
}