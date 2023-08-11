package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.personCreditsResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonMoviesRepository {
//this class for allMoviesRepository

    private static PersonMoviesRepository instance;
        private final MutableLiveData<List<movieModel>> movies;

    public PersonMoviesRepository() {

        movies=new MutableLiveData<>();

    }



    public LiveData<List<movieModel>> getPersonMovies() {
        return movies;
    }

    //method to return all movies using restAPI
        public void personMovies(int person_id){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> callPersonMoviesAPI(person_id));



        }

    private void callPersonMoviesAPI(int person_id) {

        Call<personCreditsResponse>  personMovie=Servicey.getMovieApi().personMovies(person_id,Credentials.API_KEY);
        personMovie.enqueue(new Callback<personCreditsResponse>() {
            @Override
            public void onResponse(@NonNull Call<personCreditsResponse> call, @NonNull Response<personCreditsResponse> response) {
                if (response.isSuccessful()){

                    List<movieModel> list=new ArrayList<>(response.body().getCast());
                    list.addAll(response.body().getCrew());
                    movies.postValue(list);


                }
            }

            @Override
            public void onFailure(@NonNull Call<personCreditsResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                movies.postValue(null);
            }
        });
    }




}



