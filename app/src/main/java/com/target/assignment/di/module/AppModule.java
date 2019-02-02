package com.target.assignment.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.target.assignment.networking.api.APIService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    APIService provideRetrofit(OkHttpClient httpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github-trending-api.now.sh/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(APIService.class);
    }

    @Provides
    @Singleton
    @Named("io")
    Scheduler providesSchedulerIo() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("main")
    Scheduler providesSchedulerMain() {
        return AndroidSchedulers.mainThread();
    }

}
