package com.example.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.R


object Helper {
    fun ImageView.loadFromUrl(path: String) {
        Glide.with(this).clear(this)
        Glide.with(this)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
            ).load(path).into(this)
    }
}