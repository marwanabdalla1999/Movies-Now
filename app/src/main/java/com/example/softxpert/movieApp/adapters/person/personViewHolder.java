package com.example.softxpert.movieApp.adapters.person;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class personViewHolder extends RecyclerView.ViewHolder  {
    ImageView photo;
    TextView name,job,trendingRank;
    public personViewHolder(@NonNull View itemView) {
        super(itemView);

        photo=itemView.findViewById(R.id.personPhoto);
        name=itemView.findViewById(R.id.name);
        job=itemView.findViewById(R.id.job);
        trendingRank=itemView.findViewById(R.id.rank);
    }


}
