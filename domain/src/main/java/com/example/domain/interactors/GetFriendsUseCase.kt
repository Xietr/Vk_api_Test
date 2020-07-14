package com.example.domain.interactors

import com.example.domain.entities.VkFriendEntity
import io.reactivex.Single

interface GetFriendsUseCase {
    operator fun invoke(page: Int, pageSize: Int): Single<List<VkFriendEntity>>
}