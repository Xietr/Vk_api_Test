package com.example.vkapi

import android.app.Application
import com.example.vkapi.di.AppComponent
import com.example.vkapi.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}