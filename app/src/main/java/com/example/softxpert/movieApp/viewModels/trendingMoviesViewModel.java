package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.trendingMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class trendingMoviesViewModel extends ViewModel {

    // this class is used for ViewModel
private final trendingMoviesRepository trendingMoviesRepositoryInstance;


    public trendingMoviesViewModel() {

        trendingMoviesRepositoryInstance=  trendingMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getTrendingmovies(){
        return trendingMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void trendingMovies(){

        trendingMoviesRepositoryInstance.moviesAPI();
    }


}
