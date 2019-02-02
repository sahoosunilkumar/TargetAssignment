package com.target.assignment.features.trendlist.repository;

import com.target.assignment.features.trendlist.model.TrendRequest;
import com.target.assignment.features.trendlist.model.TrendResponse;
import com.target.assignment.networking.api.APIService;
import com.target.assignment.networking.api.BaseRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public final class PullsRepository extends BaseRepository<TrendRequest, List<TrendResponse>> {

    private Scheduler schedulerIo;
    private Scheduler schedulerMain;
    private APIService apiService;

    @Inject
    PullsRepository(
            @Named("io")
                    Scheduler schedulerIo,
            @Named("main")
                    Scheduler schedulerMain,
            APIService apiService
    ) {
        this.schedulerIo = schedulerIo;
        this.schedulerMain = schedulerMain;
        this.apiService = apiService;

    }

    protected Disposable onExecute(final TrendRequest requestModel) {
        return apiService
                .getPulls(requestModel.getLanguage(), requestModel.getSince())
                .subscribeOn(schedulerIo)
                .observeOn(schedulerMain)
                .doOnSubscribe(this::onProgress)
                .subscribe(this::onComplete, this::onError);
    }

    private void onComplete(Response<List<TrendResponse>> listResponse) {
        if (listResponse.isSuccessful()) {
            onSuccess(listResponse.body());
        } else {
            onError(new Exception(listResponse.message()));
        }
    }
}
