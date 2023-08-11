package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.creditsRepository;
import com.example.softxpert.movieApp.models.personModel;

import java.util.List;

public class creditsViewModel extends ViewModel {

    // this class is used for ViewModel
private final creditsRepository creditsRepositoryInstance;


    public creditsViewModel() {

        creditsRepositoryInstance=  new creditsRepository();


    }


    public LiveData<List<personModel>> getCast(){
        return creditsRepositoryInstance.getCast();
    }
    public LiveData<List<personModel>> getCrew(){

        return creditsRepositoryInstance.getCrew();
    }

//call MoviesAPI in view-model
    public void Credits(int movieId){

        creditsRepositoryInstance.creditsAPI(movieId);
    }


}
