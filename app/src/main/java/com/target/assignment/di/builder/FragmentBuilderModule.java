package com.target.assignment.di.builder;

import com.target.assignment.features.trenddetail.view.ItemDetailFragment;
import com.target.assignment.features.trendlist.view.ItemListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract ItemListFragment contributeItemListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract ItemDetailFragment contributeItemDetailFragment();

}
