package com.example.softxpert.movieApp.adapters.crew;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.Activities.personProfile;
import com.example.softxpert.movieApp.models.personModel;

import java.util.List;

public class crewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<personModel> personModels;


    public void setPersonModel(List<personModel> personModels) {
        this.personModels = personModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_list_item,
                parent,false);
        return new crewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {




    //    ((movieViewHolder)holder).yearOfProduction.setText(movieModels.get(position).getRelease_date());
        ((crewViewHolder)holder).name.setText(personModels.get(position).getName());
        ((crewViewHolder)holder).Character.setText(personModels.get(position).getJob());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+personModels.get(position).getProfile_path())
                .placeholder(R.drawable.placeholder)
                .apply(new RequestOptions().override(400, 600))

                .into(((crewViewHolder)holder).photo);

        holder.itemView.setOnClickListener(i->{
            Intent intent=new Intent(holder.itemView.getContext(), personProfile.class);
            intent.putExtra("Person_id",personModels.get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        if (personModels!=null){
            if(personModels.size()<20){
        return personModels.size();
            }
            else{
                return 20;
            }
    }
    return 0;
    }


}
