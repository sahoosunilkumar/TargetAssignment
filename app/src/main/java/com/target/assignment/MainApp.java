package com.target.assignment;

import android.app.Activity;
import android.app.Application;

import com.target.assignment.di.components.AppComponent;
import com.target.assignment.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainApp extends Application implements HasActivityInjector {

    private static MainApp sInstance;
    private static AppComponent appComponent;


    public static MainApp getAppContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .application(this)
                    .build();
        }
        return appComponent;
    }

    private static synchronized void setInstance(MainApp app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        getAppComponent().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
