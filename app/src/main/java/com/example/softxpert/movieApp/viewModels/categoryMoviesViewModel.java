package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.categoriesRepository;
import com.example.softxpert.movieApp.Repository.categoryMoviesRepository;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class categoryMoviesViewModel extends ViewModel {

    // this class is used for ViewModel
private final categoryMoviesRepository categoryMoviesRepositoryInstance;

    public categoryMoviesViewModel() {

        categoryMoviesRepositoryInstance= new categoryMoviesRepository();


    }


    public LiveData<List<movieModel>> getCategoryMovies(){
        return categoryMoviesRepositoryInstance.getCategoryMovies();
    }

//call MoviesAPI in view-model

    public void categoryMovies(int page,String genres){
        categoryMoviesRepositoryInstance.categoryMovies(page,genres);
    }


}
