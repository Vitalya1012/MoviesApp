package com.vitaliy.moviesapp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactoryTMDB {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public static final ApiServiceTMDB apiServiceTMDB = retrofit.create(ApiServiceTMDB.class);
}
