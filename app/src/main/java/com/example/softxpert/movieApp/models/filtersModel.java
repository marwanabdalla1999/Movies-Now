package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class filtersModel implements Parcelable {

    private final int id;
    private final String name;


    public filtersModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected filtersModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<filtersModel> CREATOR = new Creator<filtersModel>() {
        @Override
        public filtersModel createFromParcel(Parcel in) {
            return new filtersModel(in);
        }

        @Override
        public filtersModel[] newArray(int size) {
            return new filtersModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}