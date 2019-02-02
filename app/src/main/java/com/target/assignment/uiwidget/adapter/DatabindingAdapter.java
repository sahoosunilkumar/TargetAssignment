package com.target.assignment.uiwidget.adapter;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.target.assignment.uiwidget.adapter.impl.AdapterDelegate;
import com.target.assignment.uiwidget.adapter.impl.AdapterUpdater;
import com.target.assignment.uiwidget.adapter.impl.RecyclerViewUpdater;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class DatabindingAdapter<T> extends BaseAdapter<T> {

    private AdapterDelegate[] adapterDelegates;
    private final ObservableList.OnListChangedCallback<ObservableList<T>> callback;
    private final Handler handler;
    private RecyclerViewUpdater updater;

    public DatabindingAdapter(AdapterDelegate... adapterDelegates) {
        super();
        this.handler = new Handler(Looper.getMainLooper());
        this.updater = new RecyclerViewUpdater(this);
        callback = new AdapterUpdater<>(this.updater, this.handler);
        register(callback);
        this.adapterDelegates = adapterDelegates;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding itemRowBinding = DataBindingUtil.bind(LayoutInflater
                .from(parent.getContext())
                .inflate(adapterDelegates[viewType].getLayoutId(), parent, false));

        return new ItemHolder<>(Objects.requireNonNull(itemRowBinding));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ViewDataBinding itemRowBinding = holder.getBinding();
        final int viewType = getItemViewType(position);
        itemRowBinding.setVariable(adapterDelegates[viewType].getVariableId(), getItem(position));
        Iterator<Map.Entry<Integer, Object>> iterator = adapterDelegates[viewType].getVariableMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> entry = iterator.next();
            itemRowBinding.setVariable(entry.getKey(), entry.getValue());
        }

        if (adapterDelegates[viewType].getViewListener() != null) {
            adapterDelegates[viewType].getViewListener().onBindView(itemRowBinding.getRoot(), getItem(position));
        }
        itemRowBinding.executePendingBindings();
        if (adapterDelegates[viewType].getListener() != null)
            itemRowBinding.getRoot().setOnClickListener(v -> adapterDelegates[viewType].getListener().onItemClick(getItem(position)));
    }


    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < adapterDelegates.length; i++) {
            if (adapterDelegates[i].matches(getItem(position).getClass())) {
                return i;
            }
        }
        throw new IllegalArgumentException("No Valid Adapter Delegate Found for " + getItem(position).getClass());
    }
}
