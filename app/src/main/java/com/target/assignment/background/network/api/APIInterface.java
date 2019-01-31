package com.target.assignment.background.network.api;

import com.target.assignment.features.trendlist.model.TrendResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/developers?")
    Single<Response<List<TrendResponse>>> getPulls(@Query("language") String language, @Query("since") String since);
}