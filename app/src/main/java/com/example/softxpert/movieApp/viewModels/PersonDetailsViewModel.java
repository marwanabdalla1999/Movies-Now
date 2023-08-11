package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.PersonDetailsRepository;
import com.example.softxpert.movieApp.Repository.trendingPersonsRepository;
import com.example.softxpert.movieApp.models.personModel;

import java.util.List;

public class PersonDetailsViewModel extends ViewModel {

    // this class is used for ViewModel
private final PersonDetailsRepository personDetailsRepositoryInstance;


    public PersonDetailsViewModel() {

        personDetailsRepositoryInstance= new PersonDetailsRepository();


    }


    public LiveData<personModel> getPersonDetails(){
        return personDetailsRepositoryInstance.getPerson();
    }

//call MoviesAPI in view-model
    public void personDetails(int personId){

        personDetailsRepositoryInstance.personDetailsAPI(personId);
    }


}
