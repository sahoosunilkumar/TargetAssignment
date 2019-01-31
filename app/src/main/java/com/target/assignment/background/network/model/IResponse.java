package com.target.assignment.background.network.model;

/**
 * Type for response details
 *
 * @param <T>
 */
public interface IResponse<T> {
    T getData();

    Throwable getError();

    int getStatus();
}
