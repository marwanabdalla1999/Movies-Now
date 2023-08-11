package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.PersonDetailsRepository;
import com.example.softxpert.movieApp.Repository.PersonMoviesRepository;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;

import java.util.List;

public class personMoviesViewModel extends ViewModel {

    // this class is used for ViewModel
private final PersonMoviesRepository personMoviesRepositoryInstance;


    public personMoviesViewModel() {

        personMoviesRepositoryInstance= new PersonMoviesRepository();


    }


    public LiveData<List<movieModel>> getPersonMovies(){
        return personMoviesRepositoryInstance.getPersonMovies();
    }

//call MoviesAPI in view-model
    public void personMovies(int personId){

        personMoviesRepositoryInstance.personMovies(personId);
    }


}
