package com.example.softxpert.movieApp.adapters.moviesLarge;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class MoviesViewHolder extends RecyclerView.ViewHolder  {
    ImageView poster;

    public MoviesViewHolder(@NonNull View itemView) {
        super(itemView);

//yearOfProduction=itemView.findViewById(R.id.movieYearOfProduction);
poster=itemView.findViewById(R.id.moviePoster);

    }


}
