package com.example.data.vk.gateways

import com.example.data.vk.requests.VKFriendsRequest
import com.example.domain.entities.VkFriendEntity
import com.example.domain.gateways.FriendsGateway
import com.vk.api.sdk.VK
import io.reactivex.Single

class VkFriendsGateway(private val vkApi: VK) : FriendsGateway {

    override fun getFriends(page: Int, pageSize: Int): Single<List<VkFriendEntity>> {
        return Single.fromCallable {
            vkApi.executeSync(VKFriendsRequest(page, pageSize))
        }
    }
}