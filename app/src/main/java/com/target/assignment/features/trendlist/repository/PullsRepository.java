package com.target.assignment.features.trendlist.repository;

import com.target.assignment.background.network.NetworkClient;
import com.target.assignment.background.network.api.BaseRepository;
import com.target.assignment.features.trendlist.model.TrendRequest;
import com.target.assignment.features.trendlist.model.TrendResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public final class PullsRepository extends BaseRepository<TrendRequest, List<TrendResponse>> {

    protected Disposable onExecute(final TrendRequest requestModel) {
        return NetworkClient.getInstance()
                .getPulls(requestModel.getLanguage(), requestModel.getSince())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
