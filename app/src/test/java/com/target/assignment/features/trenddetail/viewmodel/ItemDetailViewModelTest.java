package com.target.assignment.features.trenddetail.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.networking.model.IResponse;
import com.target.assignment.uiwidget.imageloader.ImageLoaderImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ItemDetailViewModelTest {
    @InjectMocks
    ItemDetailViewModel itemDetailViewModel;
    @Mock
    ImageLoaderImpl imageLoader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApiResponse() throws Exception {
        MediatorLiveData<IResponse<TrendResponse>> result = itemDetailViewModel.getApiResponse();
        Assert.assertNotNull(result);
    }

    @Test
    public void testInitialize() throws Exception {
        TrendResponse response = mock(TrendResponse.class);
        itemDetailViewModel.initialize(response);
        Assert.assertNotNull(itemDetailViewModel.getApiResponse());
    }

    @Test
    public void testLoadImage() throws Exception {
        ImageView imageView = mock(ImageView.class);
        Drawable drawable = mock(Drawable.class);
        doAnswer(invocation -> {
            imageView.setImageDrawable(drawable);
            return null;
        }).when(imageLoader).load(anyString(), any(ImageView.class));

        itemDetailViewModel.loadImage(imageView, "path");
        then(imageLoader).should(times(1)).load("path", imageView);
    }
}