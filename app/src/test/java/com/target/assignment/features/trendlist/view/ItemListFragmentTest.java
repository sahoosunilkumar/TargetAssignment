package com.target.assignment.features.trendlist.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.target.assignment.R;
import com.target.assignment.databinding.FragmentTrendlistBinding;
import com.target.assignment.features.home.HomeActivity;
import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.features.trendlist.viewmodel.ItemListViewModel;
import com.target.assignment.networking.api.Status;
import com.target.assignment.networking.model.ApiResponse;
import com.target.assignment.networking.model.IResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ReflectionHelpers;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
public class ItemListFragmentTest {
    ItemListFragment itemListFragment;
    @Mock
    List<TrendResponse> response;
    @Mock
    IResponse<List<TrendResponse>> iResponse;
    @Mock
    ItemListViewModel viewModel;
    @Mock
    FragmentTrendlistBinding dataBinding;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        itemListFragment = new ItemListFragment();
        itemListFragment = spy(itemListFragment);
    }

    @Test
    public void testGetViewModel() throws Exception {
        Class<ItemListViewModel> result = itemListFragment.getViewModel();
        Assert.assertEquals(ItemListViewModel.class, result);
    }

    @Test
    public void testGetLayoutRes() throws Exception {
        int result = itemListFragment.getLayoutRes();
        Assert.assertEquals(R.layout.fragment_trendlist, result);
    }

    @Test
    public void testOnBindView() throws Exception {
        View view = mock(View.class);
        TrendResponse response = mock(TrendResponse.class);
        ImageView imageView = mock(ImageView.class);
        willReturn(imageView).given(view).findViewById(anyInt());
        willReturn("avtar").given(response).getAvatar();
        ReflectionHelpers.setField(itemListFragment,"viewModel",viewModel);
        itemListFragment.onBindView(view, response);
        then(viewModel).should(times(1)).loadImage(imageView, "avtar");
    }

    @Test
    public void testOnItemClick() throws Exception {
        itemListFragment.onItemClick(null);
        then(itemListFragment).should(times(1)).getActivity();

    }

    @Test
    public void launchFragment() throws Exception {
        startFragment(itemListFragment, HomeActivity.class);
        assertNotNull(itemListFragment.getViewModel());
        assertNotNull(itemListFragment.getView());
        then(itemListFragment).should(times(1)).onCreate(null);
        ArgumentCaptor<View> viewCaptor = ArgumentCaptor.forClass(View.class);
        ArgumentCaptor<Bundle> bundleCaptor = ArgumentCaptor.forClass(Bundle.class);
        then(itemListFragment).should(times(1)).onViewCreated(viewCaptor.capture(), bundleCaptor.capture());
    }
}