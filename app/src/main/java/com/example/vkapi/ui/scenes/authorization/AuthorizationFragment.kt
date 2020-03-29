package com.example.vkapi.ui.scenes.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vkapi.R
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        authorizationButton.setOnClickListener {
            VK.login(activity!!, arrayListOf(VKScope.FRIENDS))
        }
    }
}