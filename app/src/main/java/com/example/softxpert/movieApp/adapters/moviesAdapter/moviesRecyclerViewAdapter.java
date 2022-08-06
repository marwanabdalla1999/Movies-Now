package com.example.softxpert.movieApp.adapters.moviesAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.movieDetailsActivity;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class moviesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<movieModel> movieModels;


    public void setMovieModels(List<movieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                parent,false);
        return new movieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((movieViewHolder)holder).title.setText(movieModels.get(position).getTitle());
        ((movieViewHolder)holder).yearOfProduction.setText(movieModels.get(position).getRelease_date());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+movieModels.get(position).getPoster_path())
                .into(((movieViewHolder)holder).poster);

        holder.itemView.setOnClickListener(view -> {
            Intent i =new Intent(holder.itemView.getContext(), movieDetailsActivity.class);

            i.putExtra("movieDetails",movieModels.get(position));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),((movieViewHolder)holder).poster , "Poster");
            holder.itemView.getContext().startActivity(i,options.toBundle());
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
