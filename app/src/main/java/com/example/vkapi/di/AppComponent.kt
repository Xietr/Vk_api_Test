package com.example.vkapi.di

import com.example.vkapi.di.modules.GatewaysModule
import com.example.vkapi.ui.scenes.friends.FriendsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GatewaysModule::class])
interface AppComponent {

    fun provideFriendsPresenter(): FriendsPresenter
}