package com.example.frontend.Data

data class Article(
    val title : String,
    val content : String,
    val game_name : String,
    val game_id : Int,
    val author : String,
    val post_time : String,
    val modified_time : String,
    val comments : List<comments>
)
