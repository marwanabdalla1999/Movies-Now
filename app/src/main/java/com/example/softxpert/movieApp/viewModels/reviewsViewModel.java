package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.creditsRepository;
import com.example.softxpert.movieApp.Repository.movieReviewsRepository;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.models.reviewModel;

import java.util.List;

public class reviewsViewModel extends ViewModel {

    // this class is used for ViewModel
private final movieReviewsRepository movieReviewsRepositoryInstance;


    public reviewsViewModel() {

        movieReviewsRepositoryInstance=  new movieReviewsRepository();


    }


    public LiveData<List<reviewModel>> getReviews(){
        return movieReviewsRepositoryInstance.getReviews();
    }


//call MoviesAPI in view-model
    public void Reviews(int movieId){

        movieReviewsRepositoryInstance.reviewsAPI(movieId);
    }


}
