package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.models.videosModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class videosResponse {
    @SerializedName("results")
    @Expose
    private final List<videosModel> results;



    public videosResponse(List<videosModel> results) {
        this.results = results;
    }

    public List<videosModel> getResults() {
        return results;
    }
}
