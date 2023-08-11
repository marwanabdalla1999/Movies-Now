package com.example.softxpert.movieApp.Views.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.Fragments.WatchList;
import com.example.softxpert.movieApp.adapters.cast.castAdapter;
import com.example.softxpert.movieApp.adapters.crew.crewAdapter;
import com.example.softxpert.movieApp.adapters.movieReviews.movieReviewsAdapter;
import com.example.softxpert.movieApp.adapters.moviesSmall.moviesAdapterSmallLayout;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.models.videosModel;
import com.example.softxpert.movieApp.offlineData.sharedPreference;
import com.example.softxpert.movieApp.viewModels.creditsViewModel;
import com.example.softxpert.movieApp.viewModels.reviewsViewModel;
import com.example.softxpert.movieApp.viewModels.similarMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.videosViewModel;
import java.text.DecimalFormat;
import java.util.List;

public class movieDetailsActivity extends AppCompatActivity {
    //Views
    private ConstraintLayout background,progressLayout;
    private NestedScrollView scrollView;
    private LinearLayout backgroundTitle;
    private  TextView movieName,movieDesc,movieYearOfProduction,movieRate,title,numberOfReviews,similarMoviesTxt;
    private Button Trailer,watchList;
    private ImageView back,gradiantLayout,moviePoster,backDoor;
    private RecyclerView similar,cast,crew,reviews;

    //Adapters
    private crewAdapter crewViewAdapter;
    private com.example.softxpert.movieApp.adapters.cast.castAdapter castAdapter;
    private moviesAdapterSmallLayout similarAdapter;
    private movieReviewsAdapter reviewsAdapter;


    //ViewModel
    private similarMovieListViewModel similarMovieListViewModel;
    private videosViewModel videosViewModel;
    private creditsViewModel creditsViewModel;
    private reviewsViewModel reviewsViewModel;


    //primitives data type
    private int similarPage=1,darkenColor=Color.rgb(30,30,30);
    private boolean isInWatchList=false;


    //SharedPreference
    sharedPreference sharedPreferenceInstance;


    //decimal Format
    private static final DecimalFormat dfZero = new DecimalFormat("0.0");

    //models
    private movieModel movieModel;






    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        //instantiate Views
        insIt();

        //get is movie added in watch list or not
        isInWatchList=isInWatchList();

        // set data of the movie
         setMovieDetails();

         //add or remove movie from watch list
         watchList();

         // Handle what happen when scroll
         scrollHandler();

         // back to previous
        back.setOnClickListener(i->onBackPressed());

    }







    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scrollHandler() {
        scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            // on scroll down from top of the page
            if(scrollView.getScrollY()>350){
                title.setVisibility(View.VISIBLE);
               backgroundTitle.setBackgroundColor(darkenColor);

            }
            //return to the top of page
            else {

                title.setVisibility(View.GONE);
                backgroundTitle.setBackgroundColor(0x00FFFFFF);

            }

        });
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
          darkenColor=android.graphics.Color.HSVToColor(hsv);


         GradientDrawable gd=new GradientDrawable(
                 GradientDrawable.Orientation.TOP_BOTTOM,new int[]{0x00FFFFFF,darkenColor});
         gd.setCornerRadius(0f);
         gradiantLayout.setImageDrawable(gd);

         background.setBackgroundColor(darkenColor);


         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             Window window = getWindow();
             window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
             window.setStatusBarColor(darkenColor);
         }

    }



    private boolean isInWatchList() {
        //get watchList
        List<movieModel> watchList=sharedPreferenceInstance.getMovies(this);
          boolean retVal=false;


          // Search for this movie in watchList
          for(movieModel movie : watchList){
              if (movie.getMovie_id() == movieModel.getMovie_id()) {
                  retVal = true;
                  break;
              }
          }


          return retVal;
    }



    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void watchList() {

        // add or remove movie from watchList
        watchList.setOnClickListener(v -> {

                // if not found add it
            if (!isInWatchList){
                sharedPreferenceInstance.saveMovie(movieModel,movieDetailsActivity.this);
                watchList.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_baseline_bookmark_24), null);


            }

            //if found remove it
            else{
                sharedPreferenceInstance.removeMovie(movieModel,movieDetailsActivity.this);
                watchList.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_outline_bookmark_border_24), null);

            }

            //update state of the movie in watchList
            isInWatchList=isInWatchList();

            //update the list of watchList
            WatchList.notifyDataChanged(watchList.getContext());


        });
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void setMovieDetails() {
        //determine if this movie in watchList or not
       notifyWatchState();

        // set data of the movie
        setData();

        //setup the RecyclerViews
        setUpRecyclerViews();

        // set the movie cast
        getCast(movieModel.getMovie_id());


        // set trailer
        getVideos(movieModel.getMovie_id());

        // set reviews
        getReviews(movieModel.getMovie_id());

        //set similarMovies
        getSimilarMovies(movieModel.getMovie_id());

        observe();
    }





    private void getReviews(int movie_id) {
        reviewsViewModel.Reviews(movie_id);
    }




    private void setData() {

        // set data to Views
        title.setText(movieModel.getTitle());
        movieName.setText(movieModel.getTitle());
        movieDesc.setText(movieModel.getMovie_overview());
        movieYearOfProduction.setText(movieModel.getRelease_date().split("-")[0]);
        String vote=dfZero.format(movieModel.getVote_average());
        movieRate.setText(vote);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movieModel.getBackdrop_path()).addListener(new RequestListener<Drawable>() {
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
        }).into(backDoor);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movieModel.getPoster_path()).into(moviePoster);

    }




    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void notifyWatchState() {

        // found this movie in watchList
        if (isInWatchList){
            watchList.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_baseline_bookmark_24), null);
        }

        //didn't find this movie in watchList
        else{
            watchList.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_outline_bookmark_border_24), null);
        }
    }





    private void getVideos(int movie_id) {
        videosViewModel.videos(movie_id);
    }




    private void getCast(int movie_id) {


        creditsViewModel.Credits(movie_id);
    }




    @SuppressLint("SetTextI18n")
    private void observe() {
        // observe similarMovies list changes
        similarMovieListViewModel.getmovies().observe(this, movieModels -> {

            if(movieModels!=null && movieModels.size()>0){
                similarMoviesTxt.setVisibility(View.VISIBLE);
                similarAdapter.setMovieModels(movieModels);
            }
            else {
                similarMoviesTxt.setVisibility(View.GONE);
            }
        });


        // observe videos
        videosViewModel.getVideos().observe(this, videosModels -> {

            // trailer found
            if(videosModels !=null && videosModels.size()>0){

                    //search for movie trailer
                    for(videosModel video : videosModels){

                        if(video.getSite().equals("YouTube") && video.getType().equals("Trailer")){

                            // set up Trailer data
                            Trailer.setVisibility(View.VISIBLE);
                            Trailer.setOnClickListener(i->{
                                Intent intent=new Intent(movieDetailsActivity.this, trailerPreview.class);
                                intent.putExtra("videoId",video.getKey());
                                intent.putExtra("title",video.getName());
                                startActivity(intent);

                            });


                        }
                    }

            }

            //didn't find trailer
            else{
                Trailer.setVisibility(View.GONE);
            }


        });


        //observe Cast
        creditsViewModel.getCast().observe(this, personModels -> {

            progressLayout.setVisibility(View.GONE);

            if(personModels !=null) castAdapter.setPersonModel(personModels);



        });



        //observe Crew
        creditsViewModel.getCrew().observe(this, personModels -> {


            if(personModels !=null) crewViewAdapter.setPersonModel(personModels);


        });


        //observe Reviews
        reviewsViewModel.getReviews().observe(this,reviewModels ->{

            if (reviewModels!=null){
                reviewsAdapter.setReviewModels(reviewModels);
                numberOfReviews.setText("Reviews ("+reviewModels.size()+")");
            }

        });
    }




    private void getSimilarMovies(int movieId) {

        similarMovieListViewModel.similarMovies(similarPage,movieId);

        similar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(similarPage<2){
                        similarPage++;
                        similarMovieListViewModel.similarMovies(similarPage,movieId);
                    }


                }
            }
        });


    }





    private void setUpRecyclerViews() {
        //set up recycler view of cast
        castAdapter=new castAdapter();
        cast.setAdapter(castAdapter);
        cast.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        cast.setItemViewCacheSize(5024);

        //set up recycler view of crew
        crewViewAdapter=new crewAdapter();
        crew.setAdapter(crewViewAdapter);
        crew.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        crew.setItemViewCacheSize(5024);


        //set up recycler view of reviews
        reviewsAdapter=new movieReviewsAdapter();
        reviews.setAdapter(reviewsAdapter);
        reviews.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        reviews.setItemViewCacheSize(5024);


        //set up recycler view of Similar movies
        similarAdapter=new moviesAdapterSmallLayout();
        similar.setAdapter(similarAdapter);
        similar.setLayoutManager(new GridLayoutManager(this,3));
        similar.setItemViewCacheSize(5024);
    }



    private void insIt() {

        //View Models instantiate
        similarMovieListViewModel=new ViewModelProvider(this).get(similarMovieListViewModel.class);
        creditsViewModel=new ViewModelProvider(this).get(creditsViewModel.class);
        videosViewModel =new ViewModelProvider(this).get(videosViewModel.class);
        reviewsViewModel =new ViewModelProvider(this).get(reviewsViewModel.class);


        // instantiate movie model
        movieModel = getIntent().getExtras().getParcelable("movieDetails");


        // instantiate sharedPreference
        sharedPreferenceInstance= sharedPreference.getInstance();


        // instantiate views
        back=findViewById(R.id.back);
        movieName=findViewById(R.id.movieName);
        movieDesc=findViewById(R.id.movieDesc);
        moviePoster=findViewById(R.id.moviePoster);
        movieYearOfProduction=findViewById(R.id.movieYearOfProduction);
        movieRate=findViewById(R.id.movieRate);
        cast=findViewById(R.id.cast);
        crew=findViewById(R.id.crew);
        watchList=findViewById(R.id.watch_list);
        similar=findViewById(R.id.similar);
        backDoor=findViewById(R.id.backDoor);
        Trailer=findViewById(R.id.trailer);
        background=findViewById(R.id.background);
        gradiantLayout=findViewById(R.id.Gradiant);
        progressLayout=findViewById(R.id.progressLayout);
        title=findViewById(R.id.title);
        scrollView=findViewById(R.id.scrollview);
        backgroundTitle=findViewById(R.id.backgroundTitle);
        reviews=findViewById(R.id.reviews);
        numberOfReviews=findViewById(R.id.number_of_reviews);
        similarMoviesTxt=findViewById(R.id.similarMoviesTxt);


        // instantiate views state
        Trailer.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);



    }
}