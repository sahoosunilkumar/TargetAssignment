package com.target.assignment.features.trendlist.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.target.assignment.features.trendlist.model.TrendRequest;
import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.features.trendlist.repository.TrendItemsRepository;
import com.target.assignment.networking.model.ApiResponse;
import com.target.assignment.networking.model.IResponse;
import com.target.assignment.uiwidget.imageloader.ImageLoaderImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ItemListViewModelTest {
    @Mock
    MediatorLiveData<IResponse<List<TrendResponse>>> apiResponse;
    @Mock
    MediatorLiveData<IResponse<List<TrendResponse>>> repositoryResponse;
    @Mock
    TrendRequest request;
    @Mock
    ImageLoaderImpl imageLoader;
    @Mock
    TrendItemsRepository repository;
    @InjectMocks
    ItemListViewModel itemListViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApiResponse() throws Exception {
        Assert.assertNotNull(itemListViewModel.getApiResponse());
    }

    @Test
    public void testOnChanged() throws Exception {
        MediatorLiveData<IResponse<List<TrendResponse>>> apiResponse = mock(MediatorLiveData.class);
        ItemListViewModel itemListViewModel = mock(ItemListViewModel.class);
        ApiResponse<List<TrendResponse>> listIResponse = mock(ApiResponse.class);
        willReturn(apiResponse).given(itemListViewModel).getApiResponse();
        willDoNothing().given(apiResponse).postValue(listIResponse);

        itemListViewModel.onChanged(listIResponse);

        then(apiResponse).should(times(0)).postValue(listIResponse);
    }

    @Test
    public void testExecute() throws Exception {
        ArgumentCaptor<TrendRequest> requestArgumentCaptor = ArgumentCaptor.forClass(TrendRequest.class);
        doNothing().when(repository).execute(any());
        itemListViewModel.execute(null);
        then(repository).should(times(1)).execute(requestArgumentCaptor.capture());
        Assert.assertEquals(requestArgumentCaptor.getValue().getLanguage(), "java");
        Assert.assertEquals(requestArgumentCaptor.getValue().getSince(), "weekly");
    }

    @Test
    public void testLoadImage() throws Exception {
        ImageView imageView = mock(ImageView.class);
        Drawable drawable = mock(Drawable.class);
        doAnswer(invocation -> {
            imageView.setImageDrawable(drawable);
            return null;
        }).when(imageLoader).load(anyString(), any(ImageView.class));

        itemListViewModel.loadImage(imageView, "path");
        then(imageLoader).should(times(1)).load("path", imageView);
    }

    @Test
    public void testGetRepository() throws Exception {
        TrendItemsRepository result = itemListViewModel.getRepository();
        Assert.assertEquals(repository, result);
    }
}