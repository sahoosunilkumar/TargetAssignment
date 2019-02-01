package com.target.assignment.features.trenddetail.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.target.assignment.R
import com.target.assignment.databinding.FragmentTrendDetailBinding
import com.target.assignment.features.trenddetail.viewmodel.ItemDetailViewModel
import com.target.assignment.features.trendlist.model.TrendResponse

class ItemDetailFragment : Fragment() {

    companion object {
        val EXTRA_INFO = "extra_info"
        val TAG = "ItemDetailFragment"
    }

    private var mViewModel: ItemDetailViewModel? = null
    private var mBinding: FragmentTrendDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ItemDetailViewModel::class.java)
        val response: TrendResponse? = arguments?.getParcelable(EXTRA_INFO)
        response?.let {
            mViewModel!!.initialize(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_trend_detail,
                container,
                false)
        // bind ViewModel
        mBinding!!.viewModel = mViewModel
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel?.loadImage(mBinding!!.avtarIV)
    }

}
