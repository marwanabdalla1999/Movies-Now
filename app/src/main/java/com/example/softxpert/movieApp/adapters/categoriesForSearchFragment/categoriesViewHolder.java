package com.example.softxpert.movieApp.adapters.categoriesForSearchFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

//Class for ViewHolder of Filters
public class categoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView Name;
    ImageView poster;
    CardView cardView;
  private final categoriesClickListener FilterClickListener;

    public categoriesViewHolder(@NonNull View itemView, categoriesClickListener filterClickListener) {
        super(itemView);
        Name=itemView.findViewById(R.id.category);
        poster=itemView.findViewById(R.id.moviePoster);
        FilterClickListener=filterClickListener;
        cardView=itemView.findViewById(R.id.cardView);
    itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemView.setEnabled(false);
        view.postDelayed(
                ()-> view.setEnabled(true),
                1000
        );        FilterClickListener.onFilterClicked(getAdapterPosition());

    }

}
