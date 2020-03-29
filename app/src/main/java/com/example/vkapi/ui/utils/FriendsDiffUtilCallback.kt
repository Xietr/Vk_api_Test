package com.example.vkapi.ui.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.VkFriendEntity

class FriendsDiffUtilCallback(
    private val oldList: ArrayList<VkFriendEntity>,
    private val newList: ArrayList<VkFriendEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].lastName == newList[newItemPosition].lastName &&
                oldList[oldItemPosition].firstName == newList[newItemPosition].firstName &&
                oldList[oldItemPosition].photo == newList[newItemPosition].photo
    }
}