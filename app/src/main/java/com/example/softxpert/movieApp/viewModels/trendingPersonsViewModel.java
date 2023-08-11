package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.trendingMoviesRepository;
import com.example.softxpert.movieApp.Repository.trendingPersonsRepository;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;

import java.util.List;

public class trendingPersonsViewModel extends ViewModel {

    // this class is used for ViewModel
private final trendingPersonsRepository trendingPersonsRepositoryInstance;


    public trendingPersonsViewModel() {

        trendingPersonsRepositoryInstance=  trendingPersonsRepository.getInstance();


    }


    public LiveData<List<personModel>> getTrendingPersons(){
        return trendingPersonsRepositoryInstance.getPersons();
    }

//call MoviesAPI in view-model
    public void trendingPersons(){

        trendingPersonsRepositoryInstance.personsAPI();
    }


}
