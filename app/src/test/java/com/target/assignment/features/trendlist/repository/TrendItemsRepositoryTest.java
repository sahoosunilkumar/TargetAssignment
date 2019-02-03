package com.target.assignment.features.trendlist.repository;

import android.arch.lifecycle.MediatorLiveData;

import com.target.assignment.features.trendlist.model.TrendRequest;
import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.networking.api.APIService;
import com.target.assignment.networking.model.IResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import retrofit2.Response;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;

public class TrendItemsRepositoryTest {
    @Mock
    Scheduler schedulerIo;
    @Mock
    Scheduler schedulerMain;
    @Mock
    APIService apiService;
    @Mock
    MediatorLiveData<IResponse<Response>> liveDataResponse;
    @InjectMocks
    TrendItemsRepository trendItemsRepository;
    @Mock
    Single<Response<List<TrendResponse>>> list;
    private final TrendRequest request = new TrendRequest("language", "since");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testOnExecute() throws Exception {
        List<TrendResponse> list = new ArrayList<>();
        Mockito.when(apiService.getPulls(anyString(), anyString())).thenReturn(Single.just(Response.success(list)));
        trendItemsRepository.onExecute(request);
        then(apiService).should(times(1)).getPulls(request.getLanguage(), request.getSince());

    }

    @Test
    public void testGetResponse() throws Exception {
        Assert.assertNotNull(trendItemsRepository.getResponse());
    }

    @Test
    public void testExecute() throws Exception {
        List<TrendResponse> list = new ArrayList<>();
        Mockito.when(apiService.getPulls(anyString(), anyString())).thenReturn(Single.just(Response.success(list)));
        trendItemsRepository.execute(request);
        then(apiService).should(times(1)).getPulls(request.getLanguage(), request.getSince());
    }

    @Test
    public void testOnCleared() throws Exception {
        trendItemsRepository.onCleared();
    }
}