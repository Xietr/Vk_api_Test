package com.example.vkapi.ui.scenes.friends

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.VkFriendEntity
import com.example.vkapi.App
import com.example.vkapi.R
import com.example.vkapi.ui.adapters.FriendsAdapter
import com.example.vkapi.ui.listeners.PaginationScrollListener
import com.example.vkapi.ui.utils.setVisibility
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FriendsFragment : MvpAppCompatFragment(R.layout.fragment_friends), FriendsView {

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    @ProvidePresenter
    fun providePresenter() = App.appComponent.provideFriendsPresenter()

    private lateinit var adapter: FriendsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Friends"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.actionLogout).isVisible = true
    }

    override fun setAdapterItems(items: ArrayList<VkFriendEntity>) {
        errorTextView.setVisibility(false)
        adapter.setNewFriendsList(items)
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        friendsProgressBar.setVisibility(isVisible)
    }

    override fun setIsIsRefreshing(isRefreshing: Boolean) {
        friendRefreshLayout.isRefreshing = isRefreshing
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

        if (adapter.itemCount == 0) {
            with(errorTextView) {
                setVisibility(true)
                text = String.format(
                    this@FriendsFragment.resources.getString(R.string.error_text),
                    errorMessage
                )
            }
        }
    }

    private fun setupView() {
        friendRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        adapter = FriendsAdapter()
        val paginationScrollListener =
            PaginationScrollListener(linearLayoutManager, presenter::getFriends)

        friendRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@FriendsFragment.adapter
            addOnScrollListener(paginationScrollListener)
        }
    }
}