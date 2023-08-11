package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.arabicMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class arabicMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final arabicMoviesRepository latestArabicMoviesRepositoryInstance;


    public arabicMovieListViewModel() {

        latestArabicMoviesRepositoryInstance=  arabicMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return latestArabicMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void ArabicMovies(int page){

        latestArabicMoviesRepositoryInstance.moviesAPI(page);
    }


}
