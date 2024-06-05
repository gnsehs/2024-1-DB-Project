package com.example.frontend.Data

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("game_id") val game_id : Int,
    @SerializedName("game_name") val game_name : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("made_by") val made_by : String,
    @SerializedName("age_rating") val age_rating : Int,
    @SerializedName("exclusive") val exclusive : Boolean,
    @SerializedName("num_device") val num_device : Int
) {
    override fun toString(): String{
        return game_name
    }
}
