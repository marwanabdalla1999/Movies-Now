package com.example.softxpert.movieApp.offlineData;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.softxpert.movieApp.models.movieModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public  class sharedPreference {

    private static sharedPreference sharedPreferenceInstance;



    public  void saveMovie(movieModel movieModel,Context context){
        SharedPreferences  sharedPreferences= context.getSharedPreferences("cache",MODE_PRIVATE);

        Gson gson=new Gson();
       String movies= sharedPreferences.getString("movies","");
        Type type = new TypeToken<List<movieModel>>(){}.getType();
        List<movieModel> moviesList = gson.fromJson(movies, type);
        if(moviesList==null){
            moviesList= new ArrayList<>();
        }
        moviesList.add(movieModel);
        movies=gson.toJson(moviesList);

        sharedPreferences.edit().putString("movies",movies).apply();

    }
    public  void removeMovie(movieModel movieModel,Context context){
        SharedPreferences  sharedPreferences= context.getSharedPreferences("cache",MODE_PRIVATE);

        Gson gson=new Gson();
        String movies= sharedPreferences.getString("movies","");
        Type type = new TypeToken<List<movieModel>>(){}.getType();
        List<movieModel> moviesList = gson.fromJson(movies, type);
        if(moviesList==null){
            moviesList= new ArrayList<>();
        }
        for (int i=0;i<moviesList.size();i++){
            if(movieModel.getMovie_id()==moviesList.get(i).getMovie_id()){
                moviesList.remove(i);
            }
        }
        movies=gson.toJson(moviesList);

        sharedPreferences.edit().putString("movies",movies).apply();

    }

    public  List<movieModel> getMovies(Context context){
        SharedPreferences   sharedPreferences= context.getSharedPreferences("cache",MODE_PRIVATE);

        Gson gson=new Gson();
        String movies= sharedPreferences.getString("movies","");
        Type type = new TypeToken<List<movieModel>>(){}.getType();
        List<movieModel> moviesList = gson.fromJson(movies, type);

        return moviesList;
    }

    public static sharedPreference getInstance(){
        if (sharedPreferenceInstance==null){
sharedPreferenceInstance=new sharedPreference();

        }
        return sharedPreferenceInstance;
    }



}
