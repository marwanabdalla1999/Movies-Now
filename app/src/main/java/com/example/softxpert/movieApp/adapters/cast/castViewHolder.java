package com.example.softxpert.movieApp.adapters.cast;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class castViewHolder extends RecyclerView.ViewHolder  {
    ImageView photo;
    TextView name,Character;
    public castViewHolder(@NonNull View itemView) {
        super(itemView);

        photo=itemView.findViewById(R.id.cast);
        name=itemView.findViewById(R.id.name);
        Character=itemView.findViewById(R.id.Character);
    }


}
