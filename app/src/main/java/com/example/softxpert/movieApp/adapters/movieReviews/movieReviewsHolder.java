package com.example.softxpert.movieApp.adapters.movieReviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class movieReviewsHolder extends RecyclerView.ViewHolder  {
    TextView author,date,content;
    ImageView avatar;
    public movieReviewsHolder(@NonNull View itemView) {
        super(itemView);
avatar=itemView.findViewById(R.id.avatar);
author=itemView.findViewById(R.id.author);
        date=itemView.findViewById(R.id.date);
        content=itemView.findViewById(R.id.content);

    }


}
