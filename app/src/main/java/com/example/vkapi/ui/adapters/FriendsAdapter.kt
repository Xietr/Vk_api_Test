package com.example.vkapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.VkFriendEntity
import com.example.vkapi.R
import com.example.vkapi.ui.utils.FriendsDiffUtilCallback
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    private var friends = arrayListOf<VkFriendEntity>()

    fun setNewFriendsList(friends: ArrayList<VkFriendEntity>) {
        val diffResult = DiffUtil.calculateDiff(FriendsDiffUtilCallback(this.friends, friends))
        this.friends = friends
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = friends.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_friend, parent, false)
        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    inner class FriendsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(friend: VkFriendEntity) {
            view.friendTextView.text = String.format(
                view.resources.getString(R.string.friend_name_and_surname),
                friend.firstName,
                friend.lastName
            )
            Glide.with(view)
                .load(friend.photo)
                .into(view.friendImageView)
        }
    }
}
