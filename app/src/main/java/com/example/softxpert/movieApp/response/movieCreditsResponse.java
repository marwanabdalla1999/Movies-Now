package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.personModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class movieCreditsResponse {
    @SerializedName("cast")
    @Expose
    private final List<personModel> cast;

    @SerializedName("crew")
    @Expose
    private final List<personModel> crew;

    public movieCreditsResponse(List<personModel> cast, List<personModel> crew) {
        this.cast = cast;
        this.crew=crew;
    }

    public List<personModel> getCast() {
        return cast;
    }

    public List<personModel> getCrew() {
        return crew;
    }
}
