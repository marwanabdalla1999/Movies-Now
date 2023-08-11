package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.allMoviesResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class trendingMoviesRepository {
//this class for allMoviesRepository

    private static trendingMoviesRepository instance;
        private final MutableLiveData<List<movieModel>> movies;

    public trendingMoviesRepository() {

        movies=new MutableLiveData<>();

    }

    public static trendingMoviesRepository getInstance() {
        if (instance == null){
        instance =new trendingMoviesRepository();
    }
        return instance;
    }

    public LiveData<List<movieModel>> getMovies() {
        return movies;
    }

    //method to return all movies using restAPI
        public void moviesAPI(){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallMovieAPI());



        }

    private void CallMovieAPI() {
        Call<allMoviesResponse>  TrendingMovies=Servicey.getMovieApi().Trending(Credentials.API_KEY);
        TrendingMovies.enqueue(new Callback<allMoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<allMoviesResponse> call, @NonNull Response<allMoviesResponse> response) {
                if (response.isSuccessful()){
                    List<movieModel> allMoviesResponse = new ArrayList<>(((allMoviesResponse)response.body()).getMovies());
                        movies.postValue(allMoviesResponse);


                }
            }

            @Override
            public void onFailure(@NonNull Call<allMoviesResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                movies.postValue(null);
            }
        });
    }




}



