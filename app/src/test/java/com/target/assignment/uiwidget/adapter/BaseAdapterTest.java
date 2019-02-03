package com.target.assignment.uiwidget.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Looper;

import com.target.assignment.uiwidget.adapter.impl.RecyclerViewUpdater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BaseAdapterTest {
    @Mock
    ObservableList.OnListChangedCallback<ObservableList<String>> callback;
    @Mock
    Handler handler;
    @Mock
    Looper looper;
    @Mock
    RecyclerViewUpdater updater;
    @Mock
    ObservableArrayList<String> list;
    @InjectMocks
    DatabindingAdapter<String> databindingAdapter;
    @Mock
    ViewDataBinding V;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRegister() throws Exception {
        databindingAdapter.register(callback);
    }

    @Test
    public void testGetItemCount() throws Exception {
        databindingAdapter.clearItems();
        ObservableArrayList<String> list = new ObservableArrayList<>();
        list.add("Data");
        list.add("Data");
        databindingAdapter.addItems(list);
        int result = databindingAdapter.getItemCount();
        Assert.assertEquals(2, result);
    }

    @Test
    public void testAddItems() throws Exception {
        ObservableArrayList<String> list = new ObservableArrayList<>();
        list.add("Data");
        list.add("Data");
        databindingAdapter.addItems(list);
        databindingAdapter.addItems(list);
        Assert.assertTrue(databindingAdapter.getItemCount() > 0);
    }

    @Test
    public void testClearItems() throws Exception {
        databindingAdapter.clearItems();
        Assert.assertEquals(0, databindingAdapter.getItemCount());
    }

    @Test
    public void testGetItem() throws Exception {
        ObservableArrayList<String> list = new ObservableArrayList<>();
        list.add("Data");
        databindingAdapter.addItems(list);
        String result = databindingAdapter.getItem(0);
        Assert.assertEquals(result, "Data");
    }

    @Test
    public void testGetItemId() throws Exception {
        long result = databindingAdapter.getItemId(0);
        Assert.assertEquals(0L, result);
    }
}