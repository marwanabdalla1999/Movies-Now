package com.example.softxpert.movieApp.adapters.categoriesForHomeFragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

//Class for ViewHolder of Filters
public class categoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView Name;
    categoriesClickListener filterClickListener;

    public categoriesViewHolder(@NonNull View itemView, categoriesClickListener filterClickListener) {
        super(itemView);
        Name=itemView.findViewById(R.id.category);

this.filterClickListener=filterClickListener;
    itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemView.setEnabled(false);
        view.postDelayed(
                ()-> view.setEnabled(true),
                1000
        );        filterClickListener.onFilterClicked(getAdapterPosition());

    }

}
