package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.popularMoviesRepository;
import com.example.softxpert.movieApp.Repository.topRevenueMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class topRevenueMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final topRevenueMoviesRepository topRevenueMoviesRepositoryInstance;


    public topRevenueMovieListViewModel() {

        topRevenueMoviesRepositoryInstance=  topRevenueMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return topRevenueMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void topRevenueMovies(int page,String sortBy){

        topRevenueMoviesRepositoryInstance.moviesAPI(page,sortBy);
    }


}
