package com.target.assignment.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.target.assignment.features.trenddetail.viewmodel.ItemDetailViewModel;
import com.target.assignment.features.trendlist.viewmodel.ItemListViewModel;
import com.target.assignment.uiwidget.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsItemListViewModel(ItemListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsItemDetailViewModel(ItemDetailViewModel viewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
