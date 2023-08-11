package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class reviewModel {
    @Expose
    @SerializedName("id")
    private final String id;

    @Expose
    @SerializedName("author")
    private final String author;

    @Expose
    @SerializedName("content")
    private final String content;

    @Expose
    @SerializedName("updated_at")
    private final String updated_at;
    @Expose
    @SerializedName("created_at")
    private final String created_at;

    @Expose
    @SerializedName("author_details")
    private final authorDetailsModel author_details;

    public reviewModel(String id, String author, String content, String updated_at, String created_at,authorDetailsModel author_details) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.author_details=author_details;
    }





    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public authorDetailsModel getAuthor_details() {
        return author_details;
    }




}