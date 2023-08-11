package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.personResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class trendingPersonsRepository {
//this class for allMoviesRepository

    private static trendingPersonsRepository instance;
        private final MutableLiveData<List<personModel>> persons;

    public trendingPersonsRepository() {

        persons=new MutableLiveData<>();

    }

    public static trendingPersonsRepository getInstance() {
        if (instance == null){
        instance =new trendingPersonsRepository();
    }
        return instance;
    }

    public LiveData<List<personModel>> getPersons() {
        return persons;
    }

    //method to return all movies using restAPI
        public void personsAPI(){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallPersonsAPI());



        }

    private void CallPersonsAPI() {
        Call<personResponse>  TrendingMovies=Servicey.getMovieApi().trendingPersons(Credentials.API_KEY);
        TrendingMovies.enqueue(new Callback<personResponse>() {
            @Override
            public void onResponse(@NonNull Call<personResponse> call, @NonNull Response<personResponse> response) {
                if (response.isSuccessful()){
                    List<personModel> allPersonsResponse = new ArrayList<>(((personResponse)response.body()).getPersons());
                    persons.postValue(allPersonsResponse);


                }
            }

            @Override
            public void onFailure(@NonNull Call<personResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                persons.postValue(null);
            }
        });
    }




}



