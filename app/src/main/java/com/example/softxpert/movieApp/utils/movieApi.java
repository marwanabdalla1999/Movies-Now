package com.example.softxpert.movieApp.utils;

import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.response.allMoviesResponse;
import com.example.softxpert.movieApp.response.movieCreditsResponse;
import com.example.softxpert.movieApp.response.filtersResponse;
import com.example.softxpert.movieApp.response.personCreditsResponse;
import com.example.softxpert.movieApp.response.personResponse;
import com.example.softxpert.movieApp.response.reviewsResponse;
import com.example.softxpert.movieApp.response.videosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface movieApi {


   @GET("/3/discover/movie?")
   Call<allMoviesResponse> TopRevenue(
        @Query("api_key") String Key,
        @Query("page") int page,
        @Query("sort_by") String sort_by,
        @Query("with_original_language") String with_original_language
   );
    @GET("/3/person/{person_id}/movie_credits?")
    Call<personCreditsResponse> personMovies(
            @Path("person_id") int person_id,
            @Query("api_key") String Key

    );


    @GET("/3/discover/movie?")
    Call<allMoviesResponse> categoryMovies(
            @Query("api_key") String Key,
            @Query("page") int page,
            @Query("with_genres") String with_genres,
            @Query("with_original_language") String with_original_language
    );


    @GET("/3/trending/person/week?")
    Call<personResponse> trendingPersons(
            @Query("api_key") String Key
    );

    @GET("/3/movie/{movie_id}/videos?")
    Call<videosResponse> videos(
            @Path("movie_id") int movie_id,
            @Query("api_key") String Key

    );
        @GET("/3/movie/{movie_id}/reviews?")
        Call<reviewsResponse> Reviews(
                @Path("movie_id") String movie_id,
                @Query("api_key") String Key
        );




    @GET("/3/movie/{movie_id}/credits?")
    Call<movieCreditsResponse> credits(
            @Path("movie_id") int movie_id,
            @Query("api_key") String Key

    );

    @GET("/3/movie/top_rated?")
    Call<allMoviesResponse> topRated(
            @Query("api_key") String Key,
            @Query("page") int page,
            @Query("language") String language
    );

 @GET("/3/movie/{id}/similar?")
 Call<allMoviesResponse> similar(
         @Path("id") int id,
         @Query("api_key") String Key,
         @Query("page") int page,
         @Query("language") String language
 );

    @GET("/3/movie/upcoming?")
    Call<allMoviesResponse> upcoming(
            @Query("api_key") String Key,
            @Query("page") int page,
            @Query("language") String language
    );


    @GET("/3/movie/popular?")
    Call<allMoviesResponse> popular(
          @Query("api_key") String Key,
      @Query("page") int page,
 @Query("language") String language,
                      @Query("region") String region

    );


    @GET("/3/person/{person_id}?")
    Call<personModel> person(
            @Path("person_id") int person_id,
            @Query("api_key") String Key

    );

    @GET("/3/genre/movie/list?")
    Call<filtersResponse> filters(
        @Query("api_key") String Key);

    @GET("/3/discover/movie?")
    Call<allMoviesResponse> ArabicMovies(
            @Query("api_key") String Key,
            @Query("with_origin_country") String with_origin_country,
            @Query("sort_by") String sort_by,
            @Query("page") int page

    );

    @GET("/3/trending/movie/week?")
    Call<allMoviesResponse> Trending(
            @Query("api_key") String Key);

    @GET("/3/search/movie?")
    Call<allMoviesResponse> SearchMovies(
        @Query("api_key") String Key,
        @Query("page") int page,
        @Query("query") String query,
        @Query("with_original_language") String with_original_language

    );


}
