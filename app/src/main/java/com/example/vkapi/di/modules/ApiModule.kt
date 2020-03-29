package com.example.vkapi.di.modules

import com.vk.api.sdk.VK
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideVk() = VK
}