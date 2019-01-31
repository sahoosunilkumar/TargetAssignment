package com.target.uiwidget.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.Map;

public class DatabindingAdapter<T> extends BaseAdapter<T> {

    private AdapterDelegate[] adapterDelegates;

    public DatabindingAdapter(AdapterDelegate... adapterDelegates) {
        super();
        this.adapterDelegates = adapterDelegates;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding itemRowBinding = DataBindingUtil.bind(LayoutInflater
                .from(parent.getContext())
                .inflate(adapterDelegates[viewType].getLayoutId(), parent, false));

        return new ItemHolder(itemRowBinding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        final ViewDataBinding itemRowBinding = holder.getBinding();
        final int viewType = getItemViewType(position);
        itemRowBinding.setVariable(adapterDelegates[viewType].getVariableId(), getItem(position));
        Iterator<Map.Entry<Integer, Object>> iterator = adapterDelegates[viewType].getVariableMap().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Object> entry = iterator.next();
            itemRowBinding.setVariable(entry.getKey(), entry.getValue());
        }

        if(adapterDelegates[viewType].getViewListener() != null){
            adapterDelegates[viewType].getViewListener().onBindView(itemRowBinding.getRoot(), getItem(position));
        }
        itemRowBinding.executePendingBindings();
        if (adapterDelegates[viewType].getListener() != null)
            itemRowBinding.getRoot().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterDelegates[viewType].getListener().onItemClick(getItem(position));
                }
            });
    }


    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < adapterDelegates.length; i++) {
            if (adapterDelegates[i].matches(getItem(position).getClass())) {
                return i;
            }
        }
        throw new IllegalArgumentException("No Valid Adapter Delegate Found for "+getItem(position).getClass());
    }
}
