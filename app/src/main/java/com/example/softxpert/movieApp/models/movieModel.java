package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;



public class movieModel implements Parcelable {
    // model for movies

    private final String title;

private final String poster_path;

private final String release_date;

private final int movie_id;

private final float vote_average;

private final String overview;

private final String backdrop_path;


    protected movieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        backdrop_path=in.readString();
    }

    public static final Creator<movieModel> CREATOR = new Creator<movieModel>() {
        @Override
        public movieModel createFromParcel(Parcel in) {
            return new movieModel(in);
        }

        @Override
        public movieModel[] newArray(int size) {
            return new movieModel[size];
        }
    };

    //Getters
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }


    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
        parcel.writeString(backdrop_path);

    }
}
