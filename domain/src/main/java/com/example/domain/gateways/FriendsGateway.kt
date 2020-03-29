package com.example.domain.gateways

import com.example.domain.entities.VkFriendEntity
import io.reactivex.Single
import java.util.ArrayList

interface FriendsGateway {

    fun getFriends(page: Int, pageSize: Int): Single<ArrayList<VkFriendEntity>>
}