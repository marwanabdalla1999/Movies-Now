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


public class searchmovieRepository {
//this class for SearchRepository

    private static searchmovieRepository instance;
        private final MutableLiveData<List<movieModel>> movies;

    public searchmovieRepository() {

        movies=new MutableLiveData<>();

    }

    public static searchmovieRepository getInstance() {
        if (instance == null){
        instance =new searchmovieRepository();
    }
        return instance;
    }

    public LiveData<List<movieModel>> getMovies() {
        return movies;
    }

    //method to return the movies that Contain the Entered Query using restAPI
    public void searchMoviesAPI(int page,String query){

        //Retrieve data from Rest API
        AppExecutors.getInstance().NetworkIO().submit(() -> CallSearchMovieAPI(page,query));
    }

    private void CallSearchMovieAPI(int page, String query) {
        Call<allMoviesResponse>  searchMovies=Servicey.getMovieApi().SearchMovies(Credentials.API_KEY,page,query);
        searchMovies.enqueue(new Callback<allMoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<allMoviesResponse> call, @NonNull Response<allMoviesResponse> response) {
                if (response.isSuccessful()){

                    List<movieModel> allMoviesResponse = new ArrayList<>(((allMoviesResponse)response.body()).getMovies());
                    if (page==1){
                        movies.postValue(allMoviesResponse);
                    }
                    else{
                        List<movieModel> currentMovies=movies.getValue();
                        currentMovies.addAll(allMoviesResponse);
                        movies.postValue(currentMovies);
                    }
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



