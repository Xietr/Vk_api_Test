package com.example.domain.entities

data class VkFriendEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo: String,
    val deactivated: Boolean
)