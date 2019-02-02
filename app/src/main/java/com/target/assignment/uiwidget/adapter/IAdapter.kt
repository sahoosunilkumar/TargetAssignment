package com.target.assignment.uiwidget.adapter

interface IAdapter<T> {
    fun addItems(itemList: List<T>)

    fun clearItems()

    fun getItem(position: Int): T
}