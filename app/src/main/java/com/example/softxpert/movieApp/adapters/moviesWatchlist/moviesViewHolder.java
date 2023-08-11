package com.example.softxpert.movieApp.adapters.moviesWatchlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class moviesViewHolder extends RecyclerView.ViewHolder  {
    ImageView poster;

    public moviesViewHolder(@NonNull View itemView) {
        super(itemView);

//yearOfProduction=itemView.findViewById(R.id.movieYearOfProduction);
poster=itemView.findViewById(R.id.moviePoster);

    }


}
