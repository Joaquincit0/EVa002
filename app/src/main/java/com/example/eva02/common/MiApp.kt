package com.example.eva02.common
import android.app.Application

class MiApp : Application(){
    companion object {
        lateinit var instancia: MiApp
    }

    override fun onCreate() {
        super.onCreate()
        instancia = this
    }
}