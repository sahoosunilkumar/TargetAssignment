package com.target.assignment.uiwidget.adapter.impl

import android.databinding.ObservableList
import android.os.Handler

class AdapterUpdater<T>(private val updater: RecyclerViewUpdater, private val adapterUpdateHandler: Handler) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

    override fun onChanged(sender: ObservableList<T>) {
        adapterUpdateHandler.post(updater)
    }

    override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapterUpdateHandler.post(updater)
    }

    override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapterUpdateHandler.post(updater)
    }

    override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        adapterUpdateHandler.post(updater)
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        adapterUpdateHandler.post(updater)
    }
}
