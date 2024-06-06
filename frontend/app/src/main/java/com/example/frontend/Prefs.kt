package com.example.frontend

import android.content.Context

class Prefs(context: Context) {
    private val prefNm="mPref"
    private val prefs=context.getSharedPreferences(prefNm,Context.MODE_PRIVATE)

    var token:String?
        get() = prefs.getString("token",null)
        set(value){
            prefs.edit().putString("token",value).apply()
        }

    fun clearToken() {
        prefs.edit().remove("token").apply()
    }
}

