package com.manprax.discourseplugin.net;


import android.text.TextUtils;

import com.manprax.discourseplugin.BuildConfig;
import com.manprax.discourseplugin.domain.DiscourseConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mukesh on 27-11-2017.
 */

public class DiscourseBaseAPI {

    private DiscourseConfig mConfig;
    private static Retrofit retrofit=null;
    private boolean isAuthRequired;

    public DiscourseBaseAPI(DiscourseConfig config) {
        this.mConfig = config;
        this.isAuthRequired = !TextUtils.isEmpty(config.getApiKey());  // in case of API key is empty then don't need to Auth
    }

    protected Map<String,String> getAuthentication(){
        if (isAuthRequired){
            Map<String,String> authMap= new HashMap<>();
            authMap.put(DiscourseConstants.API_KEY,mConfig.getApiKey());
            authMap.put(DiscourseConstants.API_USERNAME,mConfig.getUserName());
            return authMap;
        }else{
            return Collections.EMPTY_MAP;
        }
    }
    public Retrofit getDiscourseRetroClient() {
        if (retrofit == null) {
            final OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(mConfig.getBasUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
