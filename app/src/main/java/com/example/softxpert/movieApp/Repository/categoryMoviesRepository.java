package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.allMoviesResponse;
import com.example.softxpert.movieApp.response.filtersResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryMoviesRepository {

//this class for Filter repository
    private static categoryMoviesRepository instance;
        private final MutableLiveData<List<movieModel>> movies;

    public categoryMoviesRepository() {

        movies=new MutableLiveData<>();

    }



    public LiveData<List<movieModel>> getCategoryMovies() {
        return movies;
    }


    //method to return Filtered movies using restAPI
        public void categoryMovies(int page,String genres){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(() -> CallCategoryMoviesAPI(page,genres));

        }

    private void CallCategoryMoviesAPI(int page,String genres) {
        Call<allMoviesResponse>  allMovies=Servicey.getMovieApi().categoryMovies(Credentials.API_KEY,page,genres,"en");
        allMovies.enqueue(new Callback<allMoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<allMoviesResponse> call, @NonNull Response<allMoviesResponse> response) {
                if (response.isSuccessful()){
                    List<movieModel> allMoviesResponse = new ArrayList<>(((allMoviesResponse)response.body()).getMovies());
                    if (page==1){
                        movies.postValue(allMoviesResponse);
                    }
                    else{
                        List<movieModel> currentMovies=movies.getValue();
                        if (currentMovies != null) {
                            currentMovies.addAll(allMoviesResponse);
                        }
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



