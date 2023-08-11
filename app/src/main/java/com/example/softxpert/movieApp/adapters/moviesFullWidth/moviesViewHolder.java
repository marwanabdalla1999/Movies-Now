package com.example.softxpert.movieApp.adapters.moviesFullWidth;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class moviesViewHolder extends RecyclerView.ViewHolder  {
    ImageView poster;
    TextView name,rate,year,desc;

    public moviesViewHolder(@NonNull View itemView) {
        super(itemView);

//yearOfProduction=itemView.findViewById(R.id.movieYearOfProduction);
poster=itemView.findViewById(R.id.moviePoster);
name=itemView.findViewById(R.id.name);
rate=itemView.findViewById(R.id.rate);
year=itemView.findViewById(R.id.year);
desc=itemView.findViewById(R.id.desc);
    }


}
