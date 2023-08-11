package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.popularMoviesRepository;
import com.example.softxpert.movieApp.Repository.topVotedMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class topVotedMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final topVotedMoviesRepository topVotedMoviesRepositoryInstance;


    public topVotedMovieListViewModel() {

        topVotedMoviesRepositoryInstance=  topVotedMoviesRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return topVotedMoviesRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void topVotedMovies(int page,String sortBy){

        topVotedMoviesRepositoryInstance.moviesAPI(page,sortBy);
    }


}
