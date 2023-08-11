package com.example.softxpert.movieApp.adapters.trendingMovies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class trendingViewHolder extends RecyclerView.ViewHolder  {
    ImageView poster;
    TextView position;
    public trendingViewHolder(@NonNull View itemView) {
        super(itemView);

poster=itemView.findViewById(R.id.moviePosterItem);
        position=itemView.findViewById(R.id.position);

    }


}
