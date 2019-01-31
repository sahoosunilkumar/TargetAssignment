package com.target.uiwidget.adapter;

import java.util.List;

public interface IAdapter<T> {
    void addItems(List<T> itemList);

    void clearItems();

    T getItem(int position);
}