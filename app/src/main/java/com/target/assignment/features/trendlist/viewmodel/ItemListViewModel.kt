package com.target.assignment.features.trendlist.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.widget.ImageView
import com.target.assignment.features.trendlist.model.TrendRequest
import com.target.assignment.features.trendlist.model.TrendResponse
import com.target.assignment.features.trendlist.repository.TrendItemsRepository
import com.target.assignment.networking.model.IResponse
import com.target.assignment.uiwidget.imageloader.ImageLoaderImpl
import javax.inject.Inject

class ItemListViewModel @Inject
internal constructor(val repository: TrendItemsRepository, private val imageLoader: ImageLoaderImpl) : ViewModel(), Observer<IResponse<List<TrendResponse>>> {
    val apiResponse = MediatorLiveData<IResponse<List<TrendResponse>>>()
    private val repositoryResponse = MediatorLiveData<IResponse<List<TrendResponse>>>()
    private var request: TrendRequest? = null
    private val language = "java"
    private val since = "weekly"

    init {
        repositoryResponse.addSource(repository.response, this)
        apiResponse.addSource(repositoryResponse, this)
    }

    override fun onChanged(listIResponse: IResponse<List<TrendResponse>>?) {
        this.apiResponse.postValue(listIResponse)
    }

    fun execute(bundle: Bundle?) {
        if (bundle == null) {
            this.request = TrendRequest(language, since)
            repository.execute(request)
        }
    }

    fun loadImage(imageView: ImageView, path: String) {
        imageLoader.load(path, imageView)
    }
}
