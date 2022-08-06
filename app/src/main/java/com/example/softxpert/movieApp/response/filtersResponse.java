package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.filtersModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class filtersResponse {
    @SerializedName("genres")
    @Expose
    private final List<filtersModel> genres;

    public filtersResponse(List<filtersModel> genres) {

        this.genres = genres;
    }

    public List<filtersModel> getGenres() {
        return genres;
    }
}
