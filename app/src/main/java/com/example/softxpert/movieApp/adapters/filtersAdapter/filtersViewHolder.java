package com.example.softxpert.movieApp.adapters.filtersAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

//Class for ViewHolder of Filters
public class filtersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView Name;
    filterClickListener filterClickListener;

    public filtersViewHolder(@NonNull View itemView, filterClickListener filterClickListener) {
        super(itemView);
        Name=itemView.findViewById(R.id.filterName);

this.filterClickListener=filterClickListener;
    itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        filterClickListener.onFilterClicked(getAdapterPosition());
    }
}
