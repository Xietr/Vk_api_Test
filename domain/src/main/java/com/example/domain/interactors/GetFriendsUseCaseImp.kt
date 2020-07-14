package com.example.domain.interactors

import com.example.domain.entities.VkFriendEntity
import com.example.domain.gateways.FriendsGateway
import io.reactivex.Single

class GetFriendsUseCaseImp(private val friendsGateway: FriendsGateway) : GetFriendsUseCase {

    override operator fun invoke(page: Int, pageSize: Int): Single<List<VkFriendEntity>> {
        return friendsGateway.getFriends(page, pageSize)
    }
}