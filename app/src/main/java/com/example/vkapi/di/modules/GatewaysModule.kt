package com.example.vkapi.di.modules

import com.example.data.vk.gateways.VkFriendsGateway
import com.example.domain.gateways.FriendsGateway
import com.vk.api.sdk.VK
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class GatewaysModule {

    @Provides
    @Singleton
    fun provideFriendsGateway(vkApi: VK): FriendsGateway =
        VkFriendsGateway(vkApi)

}