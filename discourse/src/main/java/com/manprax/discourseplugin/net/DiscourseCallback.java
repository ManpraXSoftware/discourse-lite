package com.manprax.discourseplugin.net;

import android.support.annotation.NonNull;

import com.manprax.discourseplugin.model.ResponseError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prateek on 27-11-2017.
 */

/**
 * Abstract class used for all Discourse Api Callbacks
 * @param <T>
 */
public abstract class DiscourseCallback<T> implements Callback<T> {
    private ResponseError mError;
    protected abstract void onResponse(@NonNull final T responseBody);

    protected void onFailure(ResponseError responseError,@NonNull final Throwable error) {}

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {  //TODO: Parse response body here and init mError
            mError=new ResponseError(response);
            onFailure(call, mError);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(mError,t);
    }
}
