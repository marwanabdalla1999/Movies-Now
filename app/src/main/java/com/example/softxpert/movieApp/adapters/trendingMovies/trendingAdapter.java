package com.example.softxpert.movieApp.adapters.trendingMovies;

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
import com.example.softxpert.movieApp.Views.Activities.movieDetailsActivity;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class trendingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<movieModel> movies;

    public void setPosters(List<movieModel> movies) {

        this.movies = movies;
        notifyDataSetChanged();

    }
    public List<movieModel> getPosters() {

      return movies;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_view_pager_item,parent,false);
        return new trendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+movies.get(position).getPoster_path())
                .into(((trendingViewHolder)holder).poster);
        ((trendingViewHolder)holder).position.setText(position+1+"");

        holder.itemView.setOnClickListener(view -> {
            holder.itemView.setEnabled(false);
            view.postDelayed(
                    ()-> view.setEnabled(true),
                    1000
            );
            Intent i =new Intent(holder.itemView.getContext(), movieDetailsActivity.class);

            i.putExtra("movieDetails",movies.get(position));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),((trendingViewHolder)holder).poster , "moviePoster");
            holder.itemView.getContext().startActivity(i);
        });



    }

    @Override
    public int getItemCount() {
        if (movies!=null){
            if(movies.size()>10){
                return 10;
            }
            else{
                return movies.size();
            }

        }
        return 0;
    }

}
