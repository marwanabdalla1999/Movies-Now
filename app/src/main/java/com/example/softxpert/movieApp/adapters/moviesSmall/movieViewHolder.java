package com.example.softxpert.movieApp.adapters.moviesSmall;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class movieViewHolder extends RecyclerView.ViewHolder  {
    ImageView poster;

    public movieViewHolder(@NonNull View itemView) {
        super(itemView);


poster=itemView.findViewById(R.id.moviePoster);


    }


}
