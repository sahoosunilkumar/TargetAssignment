package com.target.uiwidget.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ItemHolder> implements IAdapter<T> {

    private ObservableList<T> list = new ObservableArrayList<>();
    private Handler adapterUpdateHandler = new Handler(Looper.getMainLooper());
    private RecyclerViewUpdater updater;

    private ObservableList.OnListChangedCallback<ObservableList<T>> callback = new
            ObservableList.OnListChangedCallback<ObservableList<T>>() {
                @Override
                public void onChanged(ObservableList<T> sender) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeChanged(ObservableList<T> sender, int
                        positionStart, int itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeInserted(ObservableList<T> sender, int
                        positionStart, int itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeMoved(ObservableList<T> sender, int
                        fromPosition, int toPosition, int itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeRemoved(ObservableList<T> sender, int
                        positionStart, int itemCount) {
                    adapterUpdateHandler.post(updater);
                }
            };

    public BaseAdapter() {
        this.list.addOnListChangedCallback
                (callback);
        this.updater = new RecyclerViewUpdater(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<T> itemList) {
        list.addAll(itemList);
    }

    public void clearItems() {
        list.clear();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public static class ItemHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private V itemRowBinding;

        public ItemHolder(V itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public V getBinding() {
            return itemRowBinding;
        }
    }

    private class RecyclerViewUpdater implements Runnable {
        RecyclerView.Adapter adapter;

        RecyclerViewUpdater(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void run() {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}