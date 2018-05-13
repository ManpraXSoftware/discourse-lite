package com.manprax.discourseplugin.model;

import android.support.annotation.NonNull;

import okhttp3.Response;

/**
 * Created by Prateek on 27-11-2017.
 */

public class ResponseError extends Exception {
    @NonNull
    private final Response response;
//    private int code;
//    private String errorType;
//    private String errorDetail;

    public ResponseError(@NonNull Response response) {
        this.response = response;
    }

    public ResponseError(@NonNull final retrofit2.Response<?> response) {
        this(response.raw());
    }

    /**
     * @return The error response.
     */
    @NonNull
    public Response getResponse() {
        return response;
    }

    /**
     * @return The HTTP status error code.
     */
    public int getStatusCode() {
        return response.code();
    }

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getErrorType() {
//        return errorType;
//    }
//
//    public void setErrorType(String errorType) {
//        this.errorType = errorType;
//    }
//
//    public String getErrorDetail() {
//        return errorDetail;
//    }
//
//    public void setErrorDetail(String errorDetail) {
//        this.errorDetail = errorDetail;
//    }
}
