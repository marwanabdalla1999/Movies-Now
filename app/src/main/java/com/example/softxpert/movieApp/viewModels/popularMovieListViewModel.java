package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.popularMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class popularMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final popularMoviesRepository movieRepositoryInstance;


    public popularMovieListViewModel() {

        movieRepositoryInstance=  popularMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return movieRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void popularMovies(int page){

        movieRepositoryInstance.moviesAPI(page);
    }


}
