package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class personModel implements Parcelable {
    // model for persons

    private final String name;

private final String profile_path;



private final int id;

private final String known_for_department;

private final int gender;

    private final String job;

    private final String character;

    private final String biography;

    private final String birthday;

    private final String place_of_birth;

    private final String deathday;




    protected personModel(Parcel in) {
        name = in.readString();
        profile_path = in.readString();
        known_for_department = in.readString();
        id = in.readInt();
        gender = in.readInt();
        job = in.readString();
        character = in.readString();
        biography=in.readString();
        birthday=in.readString();
        place_of_birth=in.readString();
        deathday=in.readString();

    }




    public String getJob() {
        return job;
    }

    public String getCharacter() {
        return character;
    }

    public int getMovie_id() {
        return id;
    }

    public static final Creator<personModel> CREATOR = new Creator<personModel>() {
        @Override
        public personModel createFromParcel(Parcel in) {
            return new personModel(in);
        }

        @Override
        public personModel[] newArray(int size) {
            return new personModel[size];
        }
    };

    //Getters


    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public int getId() {
        return id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public int getGender() {
        return gender;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(profile_path);
        parcel.writeString(known_for_department);
        parcel.writeInt(id);
        parcel.writeInt(gender);
        parcel.writeString(job);
        parcel.writeString(character);

        parcel.writeString(biography);
        parcel.writeString(deathday);
        parcel.writeString(place_of_birth);
        parcel.writeString(birthday);

    }
}
