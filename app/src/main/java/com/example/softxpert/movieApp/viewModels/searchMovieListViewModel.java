package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.softxpert.movieApp.Repository.searchmovieRepository;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class searchMovieListViewModel extends ViewModel {

    // this class is used for ViewModel
private final searchmovieRepository searchMovieRepositoryInstance;


    public searchMovieListViewModel() {

        searchMovieRepositoryInstance=  searchmovieRepository.getInstance();


    }


    public LiveData<List<movieModel>> getmovies(){
        return searchMovieRepositoryInstance.getMovies();
    }

//call MoviesAPI in view-model
    public void searchMovies(int page,String query){

        searchMovieRepositoryInstance.searchMoviesAPI(page,query);
    }
}
