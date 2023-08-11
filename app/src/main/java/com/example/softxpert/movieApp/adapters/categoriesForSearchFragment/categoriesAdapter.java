package com.example.softxpert.movieApp.adapters.categoriesForSearchFragment;

import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.models.filtersModel;

import java.util.List;
import java.util.Random;


//Adapter for Filters Recycler View
public class categoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
      private List<filtersModel> filtersModels;
      categoriesClickListener FilterClickListener;

    public categoriesAdapter(categoriesClickListener filterClickListener) {

        FilterClickListener = filterClickListener;

    }

    public void setMovieModels(List<filtersModel> filtersModels) {
        this.filtersModels = filtersModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_for_search_fragment,
                parent,false);
        return new categoriesViewHolder(view,FilterClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((categoriesViewHolder)holder).Name.setText(filtersModels.get(position).getName());
        Random rnd = new Random();
        int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        float[] hsv=new float[3];
        android.graphics.Color.colorToHSV(color,hsv);
        hsv[2]*=0.4f;
        color=android.graphics.Color.HSVToColor(hsv);
        ((categoriesViewHolder)holder).cardView.setBackgroundColor(color);
        ((categoriesViewHolder)holder).poster.setRenderEffect(
                RenderEffect.createBlurEffect(
                        30f, //radius X
                        30f, //Radius Y
                        Shader.TileMode.MIRROR// X=CLAMP,DECAL,MIRROR,REPEAT
        ));
    }

    @Override
    public int getItemCount() {
        if (filtersModels!=null){
        return filtersModels.size();
    }
    return 0;
    }


}
