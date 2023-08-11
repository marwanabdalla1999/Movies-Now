package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.models.reviewModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.filtersResponse;
import com.example.softxpert.movieApp.response.reviewsResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movieReviewsRepository {

//this class for Filter repository
        private final MutableLiveData<List<reviewModel>> Reviews;

    public movieReviewsRepository() {

        Reviews=new MutableLiveData<>();

    }



    public LiveData<List<reviewModel>> getReviews() {
        return Reviews;
    }


    //method to return Filtered movies using restAPI
        public void reviewsAPI(int movieId){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(()->callReviewsAPI(movieId));

        }

    private void callReviewsAPI(int movieId) {
        Call<reviewsResponse>  allMovies=Servicey.getMovieApi().Reviews(movieId+"",Credentials.API_KEY);
        allMovies.enqueue(new Callback<reviewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<reviewsResponse> call, @NonNull Response<reviewsResponse> response) {

                if (response.isSuccessful()){
                    List<reviewModel> reviewsResponse = new ArrayList<>(response.body().getReviews());

                    Reviews.postValue(reviewsResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<reviewsResponse> call, @NonNull Throwable t) {
                System.out.println("fdmsflksdfmksklfmlksdmflksmlkdfmlksdf"+t.getMessage());
                Log.d("TAG", "onFailure: "+t.getMessage());
                Reviews.postValue(null);
            }
        });
    }


}



