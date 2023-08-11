package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.movieCreditsResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class creditsRepository {
//this class for allMoviesRepository

    private static creditsRepository instance;
        private final MutableLiveData<List<personModel>> cast;
    private final MutableLiveData<List<personModel>> crew;

    public creditsRepository() {

        cast=new MutableLiveData<>();
        crew=new MutableLiveData<>();

    }



    public LiveData<List<personModel>> getCast() {
        return cast;
    }

    public LiveData<List<personModel>> getCrew() {
        return crew;
    }

    //method to return all movies using restAPI
        public void creditsAPI(int movieId){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallCreditsAPI(movieId));



        }

    private void CallCreditsAPI(int movieId) {
        Call<movieCreditsResponse>  Credits=Servicey.getMovieApi().credits(movieId,Credentials.API_KEY);
        Credits.enqueue(new Callback<movieCreditsResponse>() {
            @Override
            public void onResponse(@NonNull Call<movieCreditsResponse> call, @NonNull Response<movieCreditsResponse> response) {

                if (response.isSuccessful()){

                    List<personModel> castList = new ArrayList<>(response.body().getCast());
                    cast.postValue(castList);

                    List<personModel> crewList = new ArrayList<>(response.body().getCrew());
                    crew.postValue(crewList);

                }
            }

            @Override
            public void onFailure(@NonNull Call<movieCreditsResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                cast.postValue(null);
                crew.postValue(null);
            }
        });
    }




}



