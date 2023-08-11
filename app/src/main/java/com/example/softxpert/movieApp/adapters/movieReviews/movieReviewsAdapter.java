package com.example.softxpert.movieApp.adapters.movieReviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.models.reviewModel;

import java.util.List;

public class movieReviewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<reviewModel> reviewModels;


    public void setReviewModels(List<reviewModel> reviewModels) {
        this.reviewModels = reviewModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_review_item,
                parent,false);
        return new movieReviewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((movieReviewsHolder)holder).author.setText(reviewModels.get(position).getAuthor());

        ((movieReviewsHolder)holder).date.setText(reviewModels.get(position).getUpdated_at().split("T")[0]);

        ((movieReviewsHolder)holder).content.setText(reviewModels.get(position).getContent());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+reviewModels.get(position).getAuthor_details().getAvatar_path())
                .placeholder(R.drawable.placeholder)
                .into(((movieReviewsHolder)holder).avatar);


    }

    @Override
    public int getItemCount() {
        if (reviewModels!=null){
        return reviewModels.size();
    }
    return 0;
    }


}
