package com.example.vkapi.ui.scenes.friends

import com.example.domain.entities.VkFriendEntity
import com.example.domain.gateways.FriendsGateway
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FriendsPresenter @Inject constructor(private val friendsGateway: FriendsGateway) :
    MvpPresenter<FriendsView>() {

    private val vkFriendsList = arrayListOf<VkFriendEntity>()
    private val compositeDisposable = CompositeDisposable()
    private var currentPage = 0
    private var isLoading: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFriends()
    }

    fun getFriends() {
        val countOfItemsShouldBe = currentPage * PAGE_SIZE
        if (isLoading or (vkFriendsList.size < countOfItemsShouldBe)) return

        friendsGateway.getFriends(currentPage, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading = true
                viewState.setProgressBarVisibility(true)
            }
            .doFinally {
                isLoading = false
                with(viewState) {
                    setIsIsRefreshing(false)
                    setProgressBarVisibility(false)
                }
            }
            .doOnSuccess { currentPage++ }
            .subscribe({
                vkFriendsList.addAll(it)
                viewState.setAdapterItems(vkFriendsList)
            }, {
                it.printStackTrace()
                viewState.showError("Ошибка подключения")
            })
            .addTo(compositeDisposable)
    }

    fun onRefresh() {
        currentPage = 0
        vkFriendsList.clear()
        getFriends()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    companion object {
        private const val PAGE_SIZE = 15
    }
}