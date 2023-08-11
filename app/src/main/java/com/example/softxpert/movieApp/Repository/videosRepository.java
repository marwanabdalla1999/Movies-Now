package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.videosModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.videosResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class videosRepository {
//this class for allMoviesRepository

    private static videosRepository instance;
        private final MutableLiveData<List<videosModel>> videos;

    public videosRepository() {

        videos=new MutableLiveData<>();

    }



    public LiveData<List<videosModel>> getVideos() {
        return videos;
    }


    //method to return all movies using restAPI
        public void videosAPI(int movieId){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallCreditsAPI(movieId));



        }

    private void CallCreditsAPI(int movieId) {
        Call<videosResponse>  Credits=Servicey.getMovieApi().videos(movieId,Credentials.API_KEY);
        Credits.enqueue(new Callback<videosResponse>() {
            @Override
            public void onResponse(@NonNull Call<videosResponse> call, @NonNull Response<videosResponse> response) {

                if (response.isSuccessful()){

                    List<videosModel> videosList = new ArrayList<>(response.body().getResults());
                    videos.postValue(videosList);



                }
                else{
                    videos.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<videosResponse> call, @NonNull Throwable t) {

                Log.d("TAG", "onFailure: "+t.getMessage());
                videos.postValue(null);

            }
        });
    }




}



