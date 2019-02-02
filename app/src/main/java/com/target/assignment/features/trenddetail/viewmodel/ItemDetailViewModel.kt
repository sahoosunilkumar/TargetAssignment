package com.target.assignment.features.trenddetail.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.target.assignment.features.trendlist.model.TrendResponse
import com.target.assignment.networking.model.ApiResponse
import com.target.assignment.networking.model.IResponse
import javax.inject.Inject

class ItemDetailViewModel @Inject
internal constructor() : ViewModel() {
    val apiResponse = MediatorLiveData<IResponse<TrendResponse>>()

    fun initialize(response: TrendResponse) {
        apiResponse.postValue(ApiResponse<TrendResponse>(response))
    }

    fun loadImage(imageView: ImageView, path: String?) {
        path?.let {
            Glide.with(imageView.context).load(it)
                    .into(imageView)

        }
    }
}
