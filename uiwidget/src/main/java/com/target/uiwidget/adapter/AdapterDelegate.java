package com.target.uiwidget.adapter;

import android.support.annotation.LayoutRes;

import com.target.uiwidget.listener.OnItemClickListener;
import com.target.uiwidget.listener.OnViewListener;

import java.util.HashMap;


public class AdapterDelegate<T> {
    private @LayoutRes
    final int layoutId;
    private final int variableId;
    private final Class<T> typeParameterClass;

    private OnItemClickListener<T> listener;
    private OnViewListener<T> bindViewListener;

    private final HashMap<Integer, Object> variableMap = new HashMap<>();

    public boolean matches(Class<?> requiredType){
        return requiredType == getTypeParameterClass();
    }

    public AdapterDelegate(Class<T> typeParameterClass, @LayoutRes int layoutId, int variableId) {
        this.typeParameterClass = typeParameterClass;
        this.variableId = variableId;
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public int getVariableId() {
        return variableId;
    }


    public void setListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    public void setViewListener(OnViewListener<T> listener) {
        this.bindViewListener = listener;
    }
    public OnItemClickListener<T> getListener() {
        return listener;
    }

    public OnViewListener<T> getViewListener() {
        return bindViewListener;
    }

    private Class<T> getTypeParameterClass() {
        return typeParameterClass;
    }

    public HashMap<Integer, Object> getVariableMap() {
        return variableMap;
    }

    public void addVariable(int variableId, Object object) {
        variableMap.put(variableId, object);
    }
}
