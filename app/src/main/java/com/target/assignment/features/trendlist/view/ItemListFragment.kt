package com.target.assignment.features.trendlist.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import com.target.assignment.BR
import com.target.assignment.R
import com.target.assignment.background.network.api.Status
import com.target.assignment.background.network.model.IResponse
import com.target.assignment.databinding.FragmentTrendlistBinding
import com.target.assignment.features.trenddetail.view.ItemDetailFragment
import com.target.assignment.features.trendlist.model.TrendResponse
import com.target.assignment.features.trendlist.viewmodel.PullsViewModel
import com.target.uiwidget.adapter.AdapterDelegate
import com.target.uiwidget.adapter.DatabindingAdapter
import com.target.uiwidget.adapter.DividerItemDecorationFilter
import com.target.uiwidget.listener.OnItemClickListener
import com.target.uiwidget.listener.OnViewListener

class ItemListFragment : Fragment(), Observer<IResponse<List<TrendResponse>>> {

    private var mViewModel: PullsViewModel? = null
    private var mBinding: FragmentTrendlistBinding? = null
    private val childAdapterDelegate = AdapterDelegate(TrendResponse::class.java, R.layout.item_top_trend_list, BR.trendItem)

    private var adapter: DatabindingAdapter<TrendResponse>? = null
    private var dividerItemDecorationFilter: DividerItemDecorationFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(PullsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // inflate layout, bind fields and etc
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_trendlist,
                container,
                false)
        // bind ViewModel
        mBinding!!.viewModel = mViewModel
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        adapter = DatabindingAdapter(childAdapterDelegate)
        mBinding!!.recyclerView.adapter = adapter

        mViewModel!!.apiResponse.observe(this, this)
        dividerItemDecorationFilter = getDivider(mBinding!!.recyclerView.context)
        dividerItemDecorationFilter!!.setDrawable(ContextCompat.getDrawable(mBinding!!.recyclerView.context, R.drawable.divider)!!)
        mBinding!!.recyclerView.addItemDecoration(dividerItemDecorationFilter!!)
        childAdapterDelegate.viewListener = mediaSnapViewListener
        childAdapterDelegate.listener = repoItemClickListener
        mViewModel!!.execute(savedInstanceState)
    }

    private fun getDivider(context: Context): DividerItemDecorationFilter {
        return object : DividerItemDecorationFilter(context, DividerItemDecorationFilter.VERTICAL) {
            override fun showDivider(child: View, position: Int): Boolean {
                return true
            }
        }
    }

    override fun onChanged(response: IResponse<List<TrendResponse>>?) {
        mBinding!!.inProgress = response!!.status == Status.IN_PROGRESS
        if (response.status == Status.ERROR) {
            showMessage(response.error.message!!)
        } else if (response.status == Status.SUCCESS) {
            adapter!!.addItems(response.getData())
            if (adapter!!.itemCount == 0) {
                showMessage(getString(R.string.empty_list))
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private val mediaSnapViewListener: OnViewListener<TrendResponse> = OnViewListener { root: View, response: TrendResponse ->
        val mediaImage: ImageView = root.findViewById(R.id.avtarIV)
        mViewModel?.loadImage(mediaImage, response.avatar)
    }
    private val repoItemClickListener: OnItemClickListener<TrendResponse> = OnItemClickListener {
        launchDetailScreen(it)
    }

    private fun launchDetailScreen(response: TrendResponse?) {
        val bundle = Bundle()
        val fragment = ItemDetailFragment()
        bundle.putParcelable(ItemDetailFragment.EXTRA_INFO, response)
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container, fragment, ItemDetailFragment.TAG)?.addToBackStack(ItemDetailFragment.TAG)?.commit()
    }
}
