package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class personCreditsResponse {
    @SerializedName("cast")
    @Expose
    private final List<movieModel> cast;

    @SerializedName("crew")
    @Expose
    private final List<movieModel> crew;

    public personCreditsResponse(List<movieModel> cast, List<movieModel> crew) {
        this.cast = cast;
        this.crew=crew;
    }

    public List<movieModel> getCast() {
        return cast;
    }

    public List<movieModel> getCrew() {
        return crew;
    }
}
