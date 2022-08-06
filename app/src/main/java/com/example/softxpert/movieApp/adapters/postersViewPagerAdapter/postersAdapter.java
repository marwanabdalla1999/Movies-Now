package com.example.softxpert.movieApp.adapters.postersViewPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softxpert.R;

import java.util.List;

public class postersAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<String> posters;

    public void setPosters(List<String> posters) {
        this.posters = posters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_item,parent,false);
        return new posterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+posters.get(position)).into(((posterViewHolder)holder).poster);
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }
}
