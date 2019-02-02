package com.target.assignment.uiwidget.adapter;

import com.target.assignment.uiwidget.adapter.impl.AdapterDelegate;
import com.target.assignment.uiwidget.listener.OnItemClickListener;
import com.target.assignment.uiwidget.listener.OnViewListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AdapterDelegateTest {

    private AdapterDelegate<String> adapterDelegate;
    private int layoutId = 1;
    private int variableId = 2;

    @Before
    public void setUp() {
        adapterDelegate = new AdapterDelegate<>(String.class, layoutId, variableId);
    }

    @Test
    public void testMatches() throws Exception {
        boolean result = adapterDelegate.matches(String.class);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetLayoutId() throws Exception {
        Assert.assertEquals(adapterDelegate.getLayoutId(), layoutId);
    }

    @Test
    public void testGetVariableId() throws Exception {
        Assert.assertEquals(adapterDelegate.getVariableId(), variableId);
    }

    @Test
    public void testSetListener() throws Exception {
        OnItemClickListener listener = mock(OnItemClickListener.class);
        adapterDelegate.setListener(listener);
        Assert.assertEquals(adapterDelegate.getListener(), listener);
    }

    @Test
    public void testSetViewListener() throws Exception {
        OnViewListener listener = mock(OnViewListener.class);
        adapterDelegate.setViewListener(listener);
        Assert.assertEquals(adapterDelegate.getViewListener(), listener);
    }

    public void testGetListener() throws Exception {
        OnItemClickListener listener = mock(OnItemClickListener.class);
        OnItemClickListener listener2 = mock(OnItemClickListener.class);
        adapterDelegate.setListener(listener);
        adapterDelegate.setListener(listener2);
        Assert.assertEquals(adapterDelegate.getListener(), listener2);
    }

    @Test
    public void testGetViewListener() throws Exception {
        OnViewListener listener = mock(OnViewListener.class);
        OnViewListener listener2 = mock(OnViewListener.class);
        adapterDelegate.setViewListener(listener);
        adapterDelegate.setViewListener(listener2);
        Assert.assertEquals(adapterDelegate.getViewListener(), listener2);
    }

    @Test
    public void testAddVariable() throws Exception {
        adapterDelegate.addVariable(0, "object");
        Assert.assertEquals(adapterDelegate.getVariableMap().get(0), "object");

    }
}