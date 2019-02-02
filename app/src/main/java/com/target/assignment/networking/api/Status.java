package com.target.assignment.networking.api;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@IntDef({Status.IN_PROGRESS, Status.SUCCESS, Status.ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface Status {
    int IN_PROGRESS = 1;
    int SUCCESS = 2;
    int ERROR = 3;
}