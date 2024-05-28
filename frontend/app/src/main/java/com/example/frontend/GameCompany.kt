package com.example.frontend

import java.time.LocalDate

public class GameCompany {
    private lateinit var name : String
    private lateinit var nation : String
    private lateinit var founding_date : String
    private lateinit var ceo : String
    private var numGames : Int = 0

    public fun getName() : String {
        return name
    }
    public fun getNation() : String {
        return nation
    }
    public fun getDate() : String {
        return founding_date
    }
    public fun getCeo() : String {
        return ceo
    }
    public fun getNum() : Int {
        return numGames
    }
}