package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.MoviesPopularInEgyptRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class moviesPopularInEgyptViewModel extends ViewModel {

    // this class is used for ViewModel
private final MoviesPopularInEgyptRepository upcomingMoviesRepositoryInstance;


    public moviesPopularInEgyptViewModel() {

        upcomingMoviesRepositoryInstance=  MoviesPopularInEgyptRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return upcomingMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void upcomingMovies(int page,String region){

        upcomingMoviesRepositoryInstance.moviesAPI(page,region);
    }


}
