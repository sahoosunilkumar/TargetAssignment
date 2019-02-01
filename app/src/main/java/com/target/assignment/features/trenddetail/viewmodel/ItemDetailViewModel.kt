package com.target.assignment.features.trenddetail.viewmodel

import android.arch.lifecycle.ViewModel
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.target.assignment.features.trendlist.model.TrendResponse

class ItemDetailViewModel : ViewModel() {
    var response: TrendResponse? = null
        private set

    fun initialize(response: TrendResponse) {
        this.response = response
    }

    fun loadImage(imageView: ImageView) {
        response?.let {
            it.avatar?.let {
                Glide.with(imageView.context).load(it)
                        .into(imageView)
            }
        }
    }
}
