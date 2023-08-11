package com.example.softxpert.movieApp.Views.Fragments;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;



import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.softxpert.movieApp.adapters.moviesWatchlist.moviesWatchlistAdapter;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.offlineData.sharedPreference;
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview;
import java.text.DecimalFormat;
import java.util.List;


public class WatchList extends Fragment  {

    ConstraintLayout background;

    private static List<movieModel> movieModels;

    //shared Preference
    static sharedPreference sharedPreferenceInstance ;


    //views
    private CarouselRecyclerview watchList;
    ImageView image;

    //adapters
    private static moviesWatchlistAdapter watchListAdapter;

    TextView name,rate,year,desc;

    private static final DecimalFormat dfZero = new DecimalFormat("0.0");


    int backgroundCurrentColor=Color.rgb(30,30,30);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        View view= LayoutInflater.from(getContext()).inflate(R.layout.watch_movies_list,container,false);


        insIt(view);

        setupRecyclerView();

        notifyDataChanged(getContext());

        return view;

    }



    public static void notifyDataChanged(Context context) {
        movieModels =sharedPreferenceInstance.getMovies(context);
        watchListAdapter.setMovieModels(movieModels);



    }


    private void setupRecyclerView() {


        //watchList recycler view
        watchListAdapter=new moviesWatchlistAdapter();
        watchList.setAdapter(watchListAdapter);

     watchList.set3DItem(true);
     watchList.setAlpha(true);
     watchList.setInfinite(true);

        watchList.setItemViewCacheSize(2000);

        watchList.setItemSelectListener(i -> {
           setData(i);
           changeBackgroundColor(i);

        });








    }
    public void scrollToStart(){
        if(watchList.getAdapter()!=null && watchList.getAdapter().getItemCount()>0) {
            watchList.scrollToPosition((watchList.getSelectedPosition() + 1) % watchList.getAdapter().getItemCount());

        }
    }

     void setData(int i) {
        name.setText(movieModels.get(i).getTitle());
        desc.setText(movieModels.get(i).getMovie_overview());
        String vote=dfZero.format(movieModels.get(i).getVote_average());

        rate.setText(vote);
        year.setText(movieModels.get(i).getRelease_date().split("-")[0]);
    }


     void changeBackgroundColor(int i) {
        Glide.with(background).asDrawable().load("https://image.tmdb.org/t/p/w500"+movieModels.get(i).getPoster_path()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                BitmapDrawable bitmapDrawable=((BitmapDrawable) resource);
                Bitmap  image = bitmapDrawable.getBitmap();
                createPaletteAsync(image);

                return false;
            }
        }).into(image);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void createPaletteAsync(Bitmap bitmap) {

        Palette.from(bitmap).generate(p -> {
            // Use generated instance
            if (p != null) animateColorValue(background,p.getDominantColor(Color.rgb(43,44,53)));

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    void animateColorValue(ConstraintLayout background, int Color) {
        float[] hsv=new float[3];
        android.graphics.Color.colorToHSV(Color,hsv);
        hsv[2]*=0.5f;
       int darkenColor=android.graphics.Color.HSVToColor(hsv);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),backgroundCurrentColor, darkenColor);
        backgroundCurrentColor=darkenColor;
        colorAnimation.setDuration(600); // milliseconds

        colorAnimation.addUpdateListener(animator -> {

            background.setBackgroundColor((int) animator.getAnimatedValue());
           changeStatusBarColor(animator.getAnimatedValue());

        });
        colorAnimation.start();








    }

    private void changeStatusBarColor(Object color) {
        if (getActivity()!=null){
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor((int) color);
        }

    }

    private void insIt(View view) {

        //instantiate classes
        sharedPreferenceInstance=sharedPreference.getInstance();


        //instantiate views
        watchList=view.findViewById(R.id.watch_list);


        background=view.findViewById(R.id.background);
        image=view.findViewById(R.id.image);

        name=view.findViewById(R.id.name);
        rate=view.findViewById(R.id.rate);
        year=view.findViewById(R.id.year);
        desc=view.findViewById(R.id.desc);
    }
    public boolean getListSize(){
        return movieModels != null && movieModels.size() > 0;
    }










}