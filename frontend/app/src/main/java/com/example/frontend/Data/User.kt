package com.example.frontend.Data

import com.google.gson.annotations.SerializedName
data class User(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)