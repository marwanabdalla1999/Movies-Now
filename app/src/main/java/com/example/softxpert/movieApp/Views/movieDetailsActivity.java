package com.example.softxpert.movieApp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.postersViewPagerAdapter.postersAdapter;
import com.example.softxpert.movieApp.models.movieModel;
import java.util.ArrayList;
import java.util.List;

public class movieDetailsActivity extends AppCompatActivity {
      private movieModel movieModel;
    private  TextView movieName,movieDesc,movieYearOfProduction,movieRate,movieTitle;
    private   ImageView back;
    private   ViewPager2 moviePoster;
    private   postersAdapter postersAdapter;
    private   List<String> posters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        insIt();
         setMovieDetails();
        back.setOnClickListener(i->onBackPressed());

    }

    @SuppressLint("SetTextI18n")
    private void setMovieDetails() {
        movieName.setText(movieModel.getTitle());
        movieDesc.setText(movieModel.getMovie_overview());
        movieYearOfProduction.setText(movieModel.getRelease_date());
        movieRate.setText(Float.toString(movieModel.getVote_average()));
        movieTitle.setText(movieModel.getTitle());
        setupViewPager();

    }

    private void setupViewPager() {
        posters.add("https://image.tmdb.org/t/p/w500"+movieModel.getPoster_path());
        posters.add("https://image.tmdb.org/t/p/w500"+movieModel.getBackdrop_path());
        postersAdapter=new postersAdapter();
        postersAdapter.setPosters(posters);
        moviePoster.setAdapter(postersAdapter);
    }

    private void insIt() {
        movieModel = getIntent().getExtras().getParcelable("movieDetails");
        back=findViewById(R.id.back);
        movieName=findViewById(R.id.movieName);
        movieDesc=findViewById(R.id.movieDesc);
        moviePoster=findViewById(R.id.moviePosters);
        movieYearOfProduction=findViewById(R.id.movieYearOfProduction);
        movieRate=findViewById(R.id.movieRate);
        movieTitle=findViewById(R.id.movieTitle);
        posters=new ArrayList<>();

    }
}