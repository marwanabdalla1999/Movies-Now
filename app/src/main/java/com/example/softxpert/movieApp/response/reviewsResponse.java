package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.reviewModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class reviewsResponse {
    @SerializedName("results")
    @Expose
    private final List<reviewModel> reviews;

    public reviewsResponse(List<reviewModel> reviews) {
        this.reviews = reviews;
    }

    public List<reviewModel> getReviews() {
        return reviews;
    }
}
