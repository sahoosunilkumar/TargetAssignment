package com.target.assignment.features.home;

import android.os.Bundle;
import android.view.MenuItem;

import com.target.assignment.R;
import com.target.assignment.databinding.ActivityMainBinding;
import com.target.assignment.features.trendlist.view.ItemListFragment;
import com.target.assignment.uiwidget.BaseActivity;

import java.util.Objects;

public class HomeActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(dataBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ItemListFragment(), "ItemListFragment")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
