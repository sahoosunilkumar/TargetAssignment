package com.target.assignment.uiwidget.listener;

import android.view.View;

public interface OnViewListener<T> {
    void onBindView(View root, T item);
}
