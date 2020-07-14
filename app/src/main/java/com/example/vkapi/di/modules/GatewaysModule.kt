package com.example.vkapi.di.modules

import com.example.data.vk.gateways.VkFriendsGateway
import com.example.domain.gateways.FriendsGateway
import com.vk.api.sdk.VK
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GatewaysModule {

    @Provides
    @Singleton
    fun provideFriendsGateway(vk: VK): FriendsGateway = VkFriendsGateway(vk)
}