package com.example.vkapi.ui.utils

import android.view.View

fun View.setVisibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}