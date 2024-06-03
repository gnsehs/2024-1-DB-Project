package com.example.frontend.Data

class Games {
    private lateinit var game_name : String
    private lateinit var release_date : String
    private lateinit var made_by : String
    private var age_rating : Int = 0
    private var exclusive : Boolean = false

    fun getName() : String{
        return game_name
    }
    fun getDate() : String{
        return release_date
    }
    fun getMade() : String{
        return made_by
    }
    fun getAge() : Int{
        return age_rating
    }
    fun getExclusive() : Boolean{
        return exclusive
    }
}