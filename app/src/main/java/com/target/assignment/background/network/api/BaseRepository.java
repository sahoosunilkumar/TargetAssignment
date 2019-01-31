package com.target.assignment.background.network.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.target.assignment.background.network.model.IResponse;
import com.target.assignment.background.network.model.ApiResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseRepository<Request, Response> {
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MediatorLiveData<IResponse<Response>> liveDataResponse = new MediatorLiveData<>();

    public LiveData<IResponse<Response>> getResponse() {
        return liveDataResponse;
    }

    private CompositeDisposable getDisposables() {
        return disposables;
    }

    public void execute(final Request request) {
        getDisposables().add(onExecute(request));
    }

    protected abstract Disposable onExecute(Request request);

    protected void onError(Throwable throwable) {
        liveDataResponse.postValue(new ApiResponse<>(throwable));
        onCleared();
    }

    protected void onSuccess(Response response) {
        liveDataResponse.postValue(new ApiResponse<>(response));
        onCleared();
    }

    public final void onCleared() {
        disposables.clear();
    }

    protected void onProgress(Disposable disposable) {
        liveDataResponse.postValue(new ApiResponse<>(Status.IN_PROGRESS));
    }
}
