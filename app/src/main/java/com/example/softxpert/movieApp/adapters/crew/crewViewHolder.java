package com.example.softxpert.movieApp.adapters.crew;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;

public class crewViewHolder extends RecyclerView.ViewHolder  {
    ImageView photo;
    TextView name,Character;
    public crewViewHolder(@NonNull View itemView) {
        super(itemView);

        photo=itemView.findViewById(R.id.cast);
        name=itemView.findViewById(R.id.name);
        Character=itemView.findViewById(R.id.Character);
    }


}
