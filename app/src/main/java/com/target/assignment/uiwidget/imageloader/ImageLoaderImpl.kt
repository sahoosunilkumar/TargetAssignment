package com.target.assignment.uiwidget.imageloader

import android.widget.ImageView

import com.bumptech.glide.Glide

import javax.inject.Inject

/**
 * ImageLoader implementation for Glide
 */
class ImageLoaderImpl @Inject
constructor() : ImageLoader {

    override fun load(path: String, imageView: ImageView) {
        Glide.with(imageView.context)
                .load(path)
                .into(imageView)
    }

}