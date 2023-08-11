package com.example.softxpert.movieApp.viewModels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert.movieApp.Repository.categoriesRepository;
import com.example.softxpert.movieApp.models.filtersModel;
import java.util.List;

public class categoriesViewModel extends ViewModel {

    // this class is used for ViewModel
private final categoriesRepository filtersRepositoryInstance;

    public categoriesViewModel() {

        filtersRepositoryInstance=  categoriesRepository.getInstance();


    }


    public LiveData<List<filtersModel>> getFilters(){
        return filtersRepositoryInstance.getFilters();
    }

//call MoviesAPI in view-model

    public void filters(){
        filtersRepositoryInstance.FilterAPI();
    }


}
