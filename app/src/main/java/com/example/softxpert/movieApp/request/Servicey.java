package com.example.softxpert.movieApp.request;

import com.example.softxpert.movieApp.utils.Credentials;
import com.example.softxpert.movieApp.utils.movieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {


    private static final Retrofit.Builder retrofitBuilder=new Retrofit.Builder()
            .baseUrl(Credentials.Base_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit=retrofitBuilder.build();

    private static final movieApi movieApi=retrofit.create(movieApi.class);



    public static movieApi getMovieApi() {
        return movieApi;
    }



}
