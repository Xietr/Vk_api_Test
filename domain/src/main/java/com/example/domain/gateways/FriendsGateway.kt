package com.example.domain.gateways

import com.example.domain.entities.VkFriendEntity
import io.reactivex.Single

interface FriendsGateway {

    fun getFriends(page: Int, pageSize: Int): Single<List<VkFriendEntity>>
}