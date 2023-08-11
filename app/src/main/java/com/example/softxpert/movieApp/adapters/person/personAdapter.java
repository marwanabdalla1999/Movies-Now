package com.example.softxpert.movieApp.adapters.person;


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

public class personAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<personModel> personModels;


    public void setPersonModel(List<personModel> personModels) {
        this.personModels = personModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.persons_list_item,
                parent,false);
        return new personViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {




    //    ((movieViewHolder)holder).yearOfProduction.setText(movieModels.get(position).getRelease_date());
        ((personViewHolder)holder).job.setText(personModels.get(position).getKnown_for_department());
        ((personViewHolder)holder).name.setText(personModels.get(position).getName());
        ((personViewHolder)holder).trendingRank.setText((position+1)+"");
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+personModels.get(position).getProfile_path())
                .apply(new RequestOptions().override(400, 600))

                .into(((personViewHolder)holder).photo);

        holder.itemView.setOnClickListener(i->{
            Intent intent=new Intent(holder.itemView.getContext(), personProfile.class);
            intent.putExtra("Person_id",personModels.get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (personModels!=null){
        return personModels.size();
    }
    return 0;
    }


}
