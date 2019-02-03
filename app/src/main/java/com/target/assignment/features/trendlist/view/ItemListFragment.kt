package com.target.assignment.features.trendlist.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.target.assignment.BR
import com.target.assignment.R
import com.target.assignment.databinding.FragmentTrendlistBinding
import com.target.assignment.features.trenddetail.view.ItemDetailFragment
import com.target.assignment.features.trendlist.model.TrendResponse
import com.target.assignment.features.trendlist.viewmodel.ItemListViewModel
import com.target.assignment.networking.api.Status
import com.target.assignment.networking.model.IResponse
import com.target.assignment.uiwidget.BaseFragment
import com.target.assignment.uiwidget.adapter.DatabindingAdapter
import com.target.assignment.uiwidget.adapter.impl.AdapterDelegate
import com.target.assignment.uiwidget.adapter.impl.DividerItemDecorationFilter
import com.target.assignment.uiwidget.listener.OnItemClickListener
import com.target.assignment.uiwidget.listener.OnViewListener

class ItemListFragment : BaseFragment<ItemListViewModel, FragmentTrendlistBinding>(), Observer<IResponse<List<TrendResponse>>>, OnItemClickListener<TrendResponse>, OnViewListener<TrendResponse> {

    override fun getViewModel(): Class<ItemListViewModel> {
        return ItemListViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_trendlist
    }

    private val childAdapterDelegate = AdapterDelegate(TrendResponse::class.java, R.layout.item_top_trend_list, BR.trendItem)

    private var adapter: DatabindingAdapter<TrendResponse>? = null
    private var dividerItemDecorationFilter: DividerItemDecorationFilter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        adapter = DatabindingAdapter(childAdapterDelegate)
        dataBinding.recyclerView.adapter = adapter

        viewModel.apiResponse.observe(this, this)
        dividerItemDecorationFilter = getDivider(dataBinding.recyclerView.context)
        dividerItemDecorationFilter!!.setDrawable(ContextCompat.getDrawable(dataBinding.recyclerView.context, R.drawable.divider)!!)
        dataBinding.recyclerView.addItemDecoration(dividerItemDecorationFilter!!)
        childAdapterDelegate.viewListener = this
        childAdapterDelegate.listener = this
        viewModel.execute(savedInstanceState)
    }

    private fun getDivider(context: Context): DividerItemDecorationFilter {
        return object : DividerItemDecorationFilter(context, DividerItemDecorationFilter.VERTICAL) {
            override fun showDivider(child: View, position: Int): Boolean {
                return true
            }
        }
    }

    override fun onChanged(response: IResponse<List<TrendResponse>>?) {
        dataBinding.inProgress = response!!.status == Status.IN_PROGRESS
        if (response.status == Status.ERROR) {
            showMessage(response.error.message!!)
        } else if (response.status == Status.SUCCESS) {
            adapter!!.addItems(response.data)
            if (adapter!!.itemCount == 0) {
                showMessage(getString(R.string.empty_list))
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun launchDetailScreen(response: TrendResponse?) {
        val bundle = Bundle()
        val fragment = ItemDetailFragment()
        bundle.putParcelable(ItemDetailFragment.EXTRA_INFO, response)
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container, fragment, ItemDetailFragment.TAG)?.addToBackStack(ItemDetailFragment.TAG)?.commit()
    }

    override fun onBindView(root: View?, item: TrendResponse?) {
        if(root != null && item != null) {
            val mediaImage: ImageView = root.findViewById(R.id.avtarIV)
            viewModel.loadImage(mediaImage, item.avatar)
        }
    }

    override fun onItemClick(t: TrendResponse?) {
        launchDetailScreen(t)
    }
}
