package com.example.vkapi.di

import com.example.vkapi.di.modules.UseCasesModule
import com.example.vkapi.ui.scenes.friends.FriendsFragment
import com.vk.api.sdk.VK
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCasesModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance vk: VK): AppComponent
    }

    fun inject(friendsFragment: FriendsFragment)
}