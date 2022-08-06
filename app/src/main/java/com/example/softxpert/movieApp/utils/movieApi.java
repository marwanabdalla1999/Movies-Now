package com.example.softxpert.movieApp.utils;

import com.example.softxpert.movieApp.response.allMoviesResponse;
import com.example.softxpert.movieApp.response.filtersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface movieApi {

    @GET("/3/discover/movie?")
    Call<allMoviesResponse> allMovies(
   @Query("api_key") String Key,
   @Query("page") int page,
   @Query("with_genres") String with_genres

    );


    @GET("/3/genre/movie/list?")
    Call<filtersResponse> filters(
            @Query("api_key") String Key
    );

    @GET("/3/search/movie?")
    Call<allMoviesResponse> SearchMovies(
            @Query("api_key") String Key,
            @Query("page") int page,
            @Query("query") String query

    );
}
