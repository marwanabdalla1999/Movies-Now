package com.example.softxpert.movieApp.models;

import android.os.Parcel;
import android.os.Parcelable;


public class videosModel implements Parcelable {
    // model for persons

    private final String name;

private final String key;



private final String id;

private final String type;

private final String site;

    private final int size;


    protected videosModel(Parcel in) {
        name = in.readString();
        type = in.readString();
        key = in.readString();
        id = in.readString();
        size = in.readInt();
        site = in.readString();

    }



    public static final Creator<videosModel> CREATOR = new Creator<videosModel>() {
        @Override
        public videosModel createFromParcel(Parcel in) {
            return new videosModel(in);
        }

        @Override
        public videosModel[] newArray(int size) {
            return new videosModel[size];
        }
    };

    //Getters


    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(key);
        parcel.writeString(site);
        parcel.writeString(id);
        parcel.writeInt(size);
        parcel.writeString(type);


    }
}
