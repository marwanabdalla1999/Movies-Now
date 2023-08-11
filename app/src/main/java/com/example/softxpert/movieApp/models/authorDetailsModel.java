package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class authorDetailsModel implements Parcelable {

    private final String name;
    private final String username;
    private final String rating;
    private final String avatar_path;

    public authorDetailsModel(String name, String username, String content, String avatar_path) {
        this.name = name;
        this.username = username;
        this.rating = content;

        this.avatar_path=avatar_path;
    }

    protected authorDetailsModel(Parcel in) {
        name = in.readString();
        username = in.readString();
        rating = in.readString();

        avatar_path=in.readString();
    }

    public static final Creator<authorDetailsModel> CREATOR = new Creator<authorDetailsModel>() {
        @Override
        public authorDetailsModel createFromParcel(Parcel in) {
            return new authorDetailsModel(in);
        }

        @Override
        public authorDetailsModel[] newArray(int size) {
            return new authorDetailsModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getRating() {
        return rating;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(rating);

        parcel.writeString(avatar_path);
    }
}