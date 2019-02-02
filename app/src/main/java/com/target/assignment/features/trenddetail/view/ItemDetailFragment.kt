package com.target.assignment.features.trenddetail.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.target.assignment.R
import com.target.assignment.databinding.FragmentTrendDetailBinding
import com.target.assignment.features.trenddetail.viewmodel.ItemDetailViewModel
import com.target.assignment.features.trendlist.model.TrendResponse
import com.target.assignment.networking.api.Status
import com.target.assignment.networking.model.IResponse
import com.target.assignment.uiwidget.BaseFragment

class ItemDetailFragment : BaseFragment<ItemDetailViewModel, FragmentTrendDetailBinding>(), Observer<IResponse<TrendResponse>> {
    override fun onChanged(response: IResponse<TrendResponse>?) {
        response?.let {
            if (it.status == Status.SUCCESS && it.data != null) {
                dataBinding.response = it.data
                viewModel.loadImage(dataBinding.avtarIV, it.data.avatar)
            }
        }
    }

    companion object {
        val EXTRA_INFO = "extra_info"
        val TAG = "ItemDetailFragment"
    }

    override fun getViewModel(): Class<ItemDetailViewModel> {
        return ItemDetailViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_trend_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apiResponse.observe(this, this)
        val response: TrendResponse? = arguments?.getParcelable(EXTRA_INFO)
        response?.let {
            viewModel.initialize(it)
        }
    }

}
