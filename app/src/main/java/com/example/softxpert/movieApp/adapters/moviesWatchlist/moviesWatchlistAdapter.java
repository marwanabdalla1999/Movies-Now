package com.example.softxpert.movieApp.adapters.moviesWatchlist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.Activities.movieDetailsActivity;
import com.example.softxpert.movieApp.models.movieModel;

import java.text.DecimalFormat;
import java.util.List;

public class moviesWatchlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<movieModel> movieModels;

    public void setMovieModels(List<movieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_full_size,
                parent,false);
        return new moviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+movieModels.get(position).getPoster_path())
                .into(((moviesViewHolder)holder).poster);


        holder.itemView.setOnClickListener(view -> {
            holder.itemView.setEnabled(false);
            view.postDelayed(
                    ()-> view.setEnabled(true),
                    1000
            );
            Intent i =new Intent(holder.itemView.getContext(), movieDetailsActivity.class);

            i.putExtra("movieDetails",movieModels.get(position));
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if (movieModels!=null){
        return movieModels.size();
    }
    return 0;
    }



}
