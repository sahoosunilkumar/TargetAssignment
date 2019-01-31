package com.target.assignment.background.network.model;

import com.target.assignment.background.network.api.Status;

/**
 * model for network response data with event
 *
 * @param <T>
 */
public class ApiResponse<T> implements IResponse<T> {
    private T retrieveMatches;
    private Throwable error;
    private @Status
    int status;

    public ApiResponse(T retrieveMatches) {
        this.retrieveMatches = retrieveMatches;
        this.status = Status.SUCCESS;
    }

    public ApiResponse(Throwable error) {
        this.status = Status.ERROR;
        this.error = error;
        this.retrieveMatches = null;
    }

    public ApiResponse(@Status int status) {
        this.status = status;
        this.error = null;
        this.retrieveMatches = null;
    }

    @Override
    public T getData() {
        return retrieveMatches;
    }

    @Override
    public Throwable getError() {
        return error;
    }

    @Override
    public int getStatus() {
        return status;
    }
}



