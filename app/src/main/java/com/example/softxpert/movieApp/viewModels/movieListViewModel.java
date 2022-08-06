package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.movieRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class movieListViewModel  extends ViewModel {

    // this class is used for ViewModel
private final movieRepository movieRepositoryInstance;


    public movieListViewModel() {

        movieRepositoryInstance=  movieRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return movieRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void allMovies(int page,String genres){

        movieRepositoryInstance.moviesAPI(page,genres);
    }


}
