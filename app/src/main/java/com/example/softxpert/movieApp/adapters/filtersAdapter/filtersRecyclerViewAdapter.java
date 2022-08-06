package com.example.softxpert.movieApp.adapters.filtersAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.models.filtersModel;

import java.util.List;

//Adapter for Filters Recycler View
public class filtersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<filtersModel> filtersModels;
        private final filterClickListener filterClickListener;

    public filtersRecyclerViewAdapter(filterClickListener filterClickListener) {
        this.filterClickListener = filterClickListener;
    }

    public void setMovieModels(List<filtersModel> filtersModels) {
        this.filtersModels = filtersModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filters_item,
                parent,false);
        return new filtersViewHolder(view,filterClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((filtersViewHolder)holder).Name.setText(filtersModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (filtersModels!=null){
        return filtersModels.size();
    }
    return 0;
    }


}
