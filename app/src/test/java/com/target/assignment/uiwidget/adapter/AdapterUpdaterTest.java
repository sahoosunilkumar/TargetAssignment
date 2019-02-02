package com.target.assignment.uiwidget.adapter;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.databinding.ObservableList;
import android.os.Handler;
import android.os.Looper;

import com.target.assignment.uiwidget.adapter.impl.AdapterUpdater;
import com.target.assignment.uiwidget.adapter.impl.RecyclerViewUpdater;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;

public class AdapterUpdaterTest {
    @Mock
    Handler adapterUpdateHandler;
    @Mock
    RecyclerViewUpdater updater;
    @Mock
    Looper looper;
    @Mock
    Handler handler;
    AdapterUpdater<String> adapterUpdater;
    @Mock
    ObservableList<String> observableList;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        adapterUpdater = new AdapterUpdater<>(updater,adapterUpdateHandler);
        willReturn(false).given(adapterUpdateHandler).post(any());
    }

    @Test
    public void testOnChanged() throws Exception {

        adapterUpdater.onChanged(observableList);
        then(adapterUpdateHandler).should(times(1)).post(updater);
    }

    @Test
    public void testOnItemRangeChanged() throws Exception {

        adapterUpdater.onItemRangeChanged(observableList, 0, 1);
        then(adapterUpdateHandler).should(times(1)).post(updater);
    }

    @Test
    public void testOnItemRangeInserted() throws Exception {

        adapterUpdater.onItemRangeInserted(observableList, 0, 1);
        then(adapterUpdateHandler).should(times(1)).post(updater);
    }

    @Test
    public void testOnItemRangeMoved() throws Exception {

        adapterUpdater.onItemRangeMoved(observableList, 0, 1, 1);
        then(adapterUpdateHandler).should(times(1)).post(updater);
    }

    @Test
    public void testOnItemRangeRemoved() throws Exception {

        adapterUpdater.onItemRangeRemoved(observableList, 0, 0);
        then(adapterUpdateHandler).should(times(1)).post(updater);
    }
}