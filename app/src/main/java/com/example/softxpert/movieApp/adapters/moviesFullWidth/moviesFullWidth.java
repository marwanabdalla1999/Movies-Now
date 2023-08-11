package com.example.softxpert.movieApp.adapters.moviesFullWidth;

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

public class moviesFullWidth extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<movieModel> movieModels;
    private static final DecimalFormat dfZero = new DecimalFormat("0.0");

    public void setMovieModels(List<movieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_watch_list_item,
                parent,false);
        return new moviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+movieModels.get(position).getPoster_path())
                .apply(new RequestOptions().override(400, 600))
                .into(((moviesViewHolder)holder).poster);

        String vote=dfZero.format(movieModels.get(position).getVote_average());
        ((moviesViewHolder)holder).name.setText(movieModels.get(position).getTitle());
        ((moviesViewHolder)holder).rate.setText(vote);
        ((moviesViewHolder)holder).year.setText(movieModels.get(position).getRelease_date().split("-")[0]);
        ((moviesViewHolder)holder).desc.setText(movieModels.get(position).getMovie_overview());
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
