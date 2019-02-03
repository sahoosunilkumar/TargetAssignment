package com.target.assignment.features.trenddetail.view;

import android.os.Bundle;
import android.view.View;

import com.target.assignment.R;
import com.target.assignment.databinding.FragmentTrendDetailBinding;
import com.target.assignment.features.home.HomeActivity;
import com.target.assignment.features.trenddetail.viewmodel.ItemDetailViewModel;
import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.networking.api.Status;
import com.target.assignment.networking.model.IResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
public class ItemDetailFragmentTest {
    ItemDetailFragment itemDetailFragment;
    @Mock
    TrendResponse response;
    @Mock
    IResponse<TrendResponse> iResponse;
    @Mock
    ItemDetailViewModel viewModel;
    @Mock
    FragmentTrendDetailBinding dataBinding;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        itemDetailFragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_info", response);
        itemDetailFragment.setArguments(bundle);
        itemDetailFragment = spy(itemDetailFragment);
    }

    @Test
    public void testOnChangedOnError() throws Exception {
        willReturn(response).given(iResponse).getData();
        willReturn(Status.ERROR).given(iResponse).getStatus();
        itemDetailFragment.onChanged(iResponse);
        then(dataBinding).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testGetViewModel() throws Exception {
        Class<ItemDetailViewModel> result = itemDetailFragment.getViewModel();
        Assert.assertEquals(ItemDetailViewModel.class, result);
    }

    @Test
    public void testGetLayoutRes() throws Exception {
        int result = itemDetailFragment.getLayoutRes();
        Assert.assertEquals(R.layout.fragment_trend_detail, result);
    }

    @Test
    public void launchFragment() throws Exception {
        startFragment(itemDetailFragment, HomeActivity.class);
        assertNotNull(itemDetailFragment.getViewModel());
        assertNotNull(itemDetailFragment.getView());
        then(itemDetailFragment).should(times(1)).onCreate(null);
        ArgumentCaptor<View> viewCaptor = ArgumentCaptor.forClass(View.class);
        ArgumentCaptor<Bundle> bundleCaptor = ArgumentCaptor.forClass(Bundle.class);
        then(itemDetailFragment).should(times(1)).onViewCreated(viewCaptor.capture(), bundleCaptor.capture());
    }

}
