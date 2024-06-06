package com.example.frontend

import android.app.Application
import com.example.frontend.Prefs
class App :Application(){
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        prefs=Prefs(applicationContext)
        super.onCreate()
    }
}