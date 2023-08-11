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


public class PersonDetailsRepository {
//this class for allMoviesRepository

    private static PersonDetailsRepository instance;
        private final MutableLiveData<personModel> person;

    public PersonDetailsRepository() {

        person=new MutableLiveData<>();

    }



    public LiveData<personModel> getPerson() {
        return person;
    }

    //method to return all movies using restAPI
        public void personDetailsAPI(int person_id){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallPersonsAPI(person_id));



        }

    private void CallPersonsAPI(int person_id) {
        Call<personModel>  personDetails=Servicey.getMovieApi().person(person_id,Credentials.API_KEY);
        personDetails.enqueue(new Callback<personModel>() {
            @Override
            public void onResponse(@NonNull Call<personModel> call, @NonNull Response<personModel> response) {
                if (response.isSuccessful()){
                    person.postValue(response.body());


                }
            }

            @Override
            public void onFailure(@NonNull Call<personModel> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                person.postValue(null);
            }
        });
    }




}



