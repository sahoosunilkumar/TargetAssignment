package com.target.assignment.uiwidget.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.ItemHolder<ViewDataBinding>>(), IAdapter<T> {

    private val list = ObservableArrayList<T>()

    fun register(callback: ObservableList.OnListChangedCallback<ObservableList<T>>) {
        this.list.addOnListChangedCallback(callback)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun addItems(itemList: List<T>) {
        list.addAll(itemList)
    }

    override fun clearItems() {
        list.clear()
    }

    override fun getItem(position: Int): T {
        return list[position]
    }

    class ItemHolder<V : ViewDataBinding>(val binding: V) : RecyclerView.ViewHolder(binding.getRoot())

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}