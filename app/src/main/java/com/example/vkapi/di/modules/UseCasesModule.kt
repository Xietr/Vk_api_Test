package com.example.vkapi.di.modules

import com.example.domain.gateways.FriendsGateway
import com.example.domain.interactors.GetFriendsUseCase
import com.example.domain.interactors.GetFriendsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [GatewaysModule::class])
class UseCasesModule {

    @Provides
    @Reusable
    fun provideFriendsUseCase(friendsGateway: FriendsGateway): GetFriendsUseCase =
        GetFriendsUseCaseImp(friendsGateway)
}