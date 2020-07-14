package com.example.vkapi.ui.scenes.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.vkapi.R
import com.example.vkapi.ui.scenes.authorization.AuthorizationFragment
import com.example.vkapi.ui.scenes.friends.FriendsFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val tokenHandler: VKTokenExpiredHandler by lazy { initVkTokenExpiredHandler() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        initVk()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionLogout -> logout()
        }
        return false
    }

    private fun logout() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.vk_logout))
            .setMessage(getString(R.string.exit_confirmation_question))
            .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                VK.logout()
                supportFragmentManager.navigate(AuthorizationFragment())
            }.setNegativeButton(getString(R.string.negative_button), null)
            .show()
    }

    private fun initVk() {
        VK.addTokenExpiredHandler(tokenHandler)

        if (VK.isLoggedIn()) {
            supportFragmentManager.navigate(FriendsFragment())
        } else {
            supportFragmentManager.navigate(AuthorizationFragment())
        }
    }

    private fun initVkTokenExpiredHandler(): VKTokenExpiredHandler {
        return object : VKTokenExpiredHandler {
            override fun onTokenExpired() {
                supportFragmentManager.navigate(AuthorizationFragment())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                supportFragmentManager.navigate(FriendsFragment())
            }

            override fun onLoginFailed(errorCode: Int) {
                supportFragmentManager.navigate(AuthorizationFragment())
            }
        }
        if (!VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun FragmentManager.navigate(fragment: Fragment) {
        if (previousFragmentSimpleName != fragment.javaClass.simpleName) {//this forces to do not recreate fragment
            val transaction = this.beginTransaction()
            transaction.replace(R.id.mainFragment, fragment)
            transaction.commitAllowingStateLoss()
            previousFragmentSimpleName = fragment.javaClass.simpleName
        }
    }


    companion object {
        private var previousFragmentSimpleName = ""
    }
}
