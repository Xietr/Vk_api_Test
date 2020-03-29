package com.example.data.vk.requests

import com.example.domain.entities.VkFriendEntity
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import java.util.*

class VKFriendsRequest(private val page: Int, private val pageSize: Int) :
    VKRequest<ArrayList<VkFriendEntity>>("friends.get") {

    private val offset: Int
        get() {
            return page * pageSize
        }

    init {
        addParam("fields", "photo_200")
        addParam("count", pageSize)
        addParam("offset", offset)
    }

    override fun parse(r: JSONObject): ArrayList<VkFriendEntity> {
        val friends = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<VkFriendEntity>()
        for (i in 0 until friends.length()) {
            result.add(parseVkFriendJsonObject(friends.getJSONObject(i)))
        }
        return result
    }

    private fun parseVkFriendJsonObject(jsonObject: JSONObject): VkFriendEntity {
        return VkFriendEntity(
            id = jsonObject.optInt("id", 0),
            firstName = jsonObject.optString("first_name", ""),
            lastName = jsonObject.optString("last_name", ""),
            photo = jsonObject.optString("photo_200", ""),
            deactivated = jsonObject.optBoolean("deactivated", false)
        )
    }
}