package com.example.softxpert.movieApp.adapters.moviesSmall;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.Activities.movieDetailsActivity;
import com.example.softxpert.movieApp.models.movieModel;

import java.util.List;

public class moviesAdapterSmallLayout extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<movieModel> movieModels;


    public void setMovieModels(List<movieModel> movieModels) {
        for(int i=0;i<movieModels.size();i++){
            if(movieModels.get(i).getPoster_path() == null || movieModels.get(i).getPoster_path().equals("")){
                movieModels.remove(i);
            }
        }
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_search,
                parent,false);
        return new movieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(movieModels.get(position).getPoster_path() == null || movieModels.get(position).getPoster_path().equals("null")
        || movieModels.get(position).getPoster_path().equals("")){

            ((movieViewHolder)holder).poster.setVisibility(View.GONE);

        }



       // ((movieViewHolder)holder).title.setText(movieModels.get(position).getTitle());
    //    ((movieViewHolder)holder).yearOfProduction.setText(movieModels.get(position).getRelease_date());


        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+movieModels.get(position).getPoster_path())
                .apply(new RequestOptions().override(400, 600))
                .into(((movieViewHolder)holder).poster);

        holder.itemView.setOnClickListener(view -> {
            holder.itemView.setEnabled(false);
            view.postDelayed(
                    ()-> view.setEnabled(true),
                    1000
            );
            Intent i =new Intent(holder.itemView.getContext(), movieDetailsActivity.class);

            i.putExtra("movieDetails",movieModels.get(position));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),((movieViewHolder)holder).poster , "moviePoster");
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
