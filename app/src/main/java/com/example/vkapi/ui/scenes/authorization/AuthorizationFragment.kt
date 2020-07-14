package com.example.vkapi.ui.scenes.authorization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vkapi.R
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.screen_authorization)
    }

    private fun setListeners() {
        authorizationButton.setOnClickListener {
            VK.login(activity!!, arrayListOf(VKScope.FRIENDS))
        }
    }
}