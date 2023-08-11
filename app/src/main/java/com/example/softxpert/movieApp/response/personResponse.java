package com.example.softxpert.movieApp.response;

import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.personModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class personResponse {
    @SerializedName("results")
    @Expose
    private final List<personModel> persons;

    public personResponse(List<personModel> persons) {
        this.persons = persons;
    }

    public List<personModel> getPersons() {
        return persons;
    }
}
