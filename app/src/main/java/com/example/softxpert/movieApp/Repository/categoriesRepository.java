package com.example.softxpert.movieApp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softxpert.movieApp.AppExecutors;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.request.Servicey;
import com.example.softxpert.movieApp.response.filtersResponse;
import com.example.softxpert.movieApp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoriesRepository {

//this class for Filter repository
    private static categoriesRepository instance;
        private final MutableLiveData<List<filtersModel>> filters;

    public categoriesRepository() {

        filters=new MutableLiveData<>();

    }

    public static categoriesRepository getInstance() {
        if (instance == null){
        instance =new categoriesRepository();
    }
        return instance;
    }

    public LiveData<List<filtersModel>> getFilters() {
        return filters;
    }


    //method to return Filtered movies using restAPI
        public void FilterAPI(){

            //Retrieve data from Rest API
            AppExecutors.getInstance().NetworkIO().submit(this::callFilterAPI);

        }

    private void callFilterAPI() {
        Call<filtersResponse>  allMovies=Servicey.getMovieApi().filters(Credentials.API_KEY);
        allMovies.enqueue(new Callback<filtersResponse>() {
            @Override
            public void onResponse(@NonNull Call<filtersResponse> call, @NonNull Response<filtersResponse> response) {
                if (response.isSuccessful()){
                    List<filtersModel> filtersResponse = new ArrayList<>(((filtersResponse)response.body()).getGenres());

                    filters.postValue(filtersResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<filtersResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                filters.postValue(null);
            }
        });
    }


}



