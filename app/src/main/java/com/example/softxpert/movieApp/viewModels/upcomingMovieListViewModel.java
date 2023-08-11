package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.topVotedMoviesRepository;
import com.example.softxpert.movieApp.Repository.upcomingMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class upcomingMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final upcomingMoviesRepository upcomingMoviesRepositoryInstance;


    public upcomingMovieListViewModel() {

        upcomingMoviesRepositoryInstance=  upcomingMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return upcomingMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void upcomingMovies(int page){

        upcomingMoviesRepositoryInstance.moviesAPI(page);
    }


}
