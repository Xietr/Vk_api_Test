package com.example.vkapi.ui.scenes.friends

import com.example.domain.entities.VkFriendEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FriendsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapterItems(items: ArrayList<VkFriendEntity>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setProgressBarVisibility(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setIsIsRefreshing(isRefreshing: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(throwable: Throwable)
}