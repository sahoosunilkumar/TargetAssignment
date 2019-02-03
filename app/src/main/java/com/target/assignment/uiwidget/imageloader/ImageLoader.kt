package com.target.assignment.uiwidget.imageloader

import android.widget.ImageView

/**
 * Fetches image from path nd loads into the target object
 */
interface ImageLoader {

    /**
     * Loads image from path into image view
     */
    fun load(path: String, imageView: ImageView)
}
