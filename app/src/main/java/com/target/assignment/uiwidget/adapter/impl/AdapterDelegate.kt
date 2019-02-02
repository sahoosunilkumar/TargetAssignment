package com.target.assignment.uiwidget.adapter.impl

import android.annotation.SuppressLint
import android.support.annotation.LayoutRes

import com.target.assignment.uiwidget.listener.OnItemClickListener
import com.target.assignment.uiwidget.listener.OnViewListener

import java.util.HashMap


class AdapterDelegate<T>(private val typeParameterClass: Class<T>, @param:LayoutRes @field:LayoutRes
val layoutId: Int, val variableId: Int) {

    var listener: OnItemClickListener<T>? = null
    var viewListener: OnViewListener<T>? = null

    @SuppressLint("UseSparseArrays")
    val variableMap = HashMap<Int, Any>()

    fun matches(requiredType: Class<*>): Boolean {
        return requiredType == typeParameterClass
    }

    fun addVariable(variableId: Int, `object`: Any) {
        variableMap[variableId] = `object`
    }
}
