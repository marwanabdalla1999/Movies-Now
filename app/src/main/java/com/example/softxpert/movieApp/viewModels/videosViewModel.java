package com.example.softxpert.movieApp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.creditsRepository;
import com.example.softxpert.movieApp.Repository.videosRepository;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.models.videosModel;

import java.util.List;

public class videosViewModel extends ViewModel {

    // this class is used for ViewModel
private final videosRepository videosRepositoryInstance;


    public videosViewModel() {

        videosRepositoryInstance= new videosRepository();


    }


    public LiveData<List<videosModel>> getVideos(){

        return videosRepositoryInstance.getVideos();
    }

//call MoviesAPI in view-model
    public void videos(int movieId){

        videosRepositoryInstance.videosAPI(movieId);
    }


}
