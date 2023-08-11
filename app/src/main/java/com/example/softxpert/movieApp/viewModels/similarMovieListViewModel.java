package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.similarMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class similarMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final similarMoviesRepository latestReleaseMoviesRepositoryInstance;


    public similarMovieListViewModel() {

        latestReleaseMoviesRepositoryInstance=  new similarMoviesRepository();


    }


    public LiveData<List<movieModel>> getmovies(){
        return latestReleaseMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void similarMovies(int page,int id){

        latestReleaseMoviesRepositoryInstance.moviesAPI(page,id);
    }


}
