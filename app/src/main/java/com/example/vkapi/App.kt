package com.example.vkapi

import android.app.Application
import com.example.vkapi.di.AppComponent
import com.example.vkapi.di.DaggerAppComponent
import com.vk.api.sdk.VK

class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(VK)
    }
}