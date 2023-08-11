package com.example.softxpert.movieApp.Views.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.moviesSmall.moviesAdapterSmallLayout;
import com.example.softxpert.movieApp.viewModels.arabicMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.popularMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.topRevenueMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.topVotedMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.moviesPopularInEgyptViewModel;
import com.example.softxpert.movieApp.viewModels.upcomingMovieListViewModel;

public class viewSectionActivity extends AppCompatActivity {

    //viewModels
    private topVotedMovieListViewModel topVotedMovies_ViewModel;
    private upcomingMovieListViewModel upcomingMovies_ViewModel;
    private moviesPopularInEgyptViewModel upcomingMovieListInEgyptViewModel;
    private topRevenueMovieListViewModel revenue_ViewModel;
    private popularMovieListViewModel popularity_ViewModel;
    private arabicMovieListViewModel arabicMovieListViewModel;


    //views
    private RecyclerView sectionRecyclerView;
    private ImageView back;
    private TextView title;

    //adapters
    private moviesAdapterSmallLayout moviesRecyclerViewAdapter;


    //Primitives data
    private int page=1;
    private String Section="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //instantiate views and classes
        insIt();

        //Call section data
        viewSection(Section);

        //observe data changes
        observeAnyChange();

        //setup recycler views
        setupRecyclerView();

        back.setOnClickListener(i->onBackPressed());

    }

    private void viewSection(String section) {

        switch (section) {
            case "newMovies":
                upcomingMovies();
                break;
            case "popularMovies":
                popularMovies();
                break;
            case "topRatedMovies":
                votedMovies();
                break;
            case "topGrossesMovies":
                revenueMovies();
                break;
            case "arabicMovies":
                getArabicMovies();
                break;
            case "popularMoviesInEgypt":
                getPopularMoviesInEgypt();
                break;
        }
    }

    private void getPopularMoviesInEgypt() {
        upcomingMovieListInEgyptViewModel.upcomingMovies(page,"EG");
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                    upcomingMovieListInEgyptViewModel.upcomingMovies(page,"");



                }
            }
        });
    }

    private void getArabicMovies() {
        arabicMovieListViewModel.ArabicMovies(page);
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                        page++;
                        arabicMovieListViewModel.ArabicMovies(page);



                }
            }
        });
    }

    private void setupRecyclerView() {
        moviesRecyclerViewAdapter=new moviesAdapterSmallLayout();
        sectionRecyclerView.setAdapter(moviesRecyclerViewAdapter);
        sectionRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        sectionRecyclerView.setItemViewCacheSize(5024);


    }

    private void insIt() {
        //init ViewModels
        topVotedMovies_ViewModel=new ViewModelProvider(this).get(topVotedMovieListViewModel.class);
        upcomingMovies_ViewModel=new ViewModelProvider(this).get(upcomingMovieListViewModel.class);
        revenue_ViewModel=new ViewModelProvider(this).get(topRevenueMovieListViewModel.class);
        popularity_ViewModel=new ViewModelProvider(this).get(popularMovieListViewModel.class);
        arabicMovieListViewModel=new ViewModelProvider(this).get(com.example.softxpert.movieApp.viewModels.arabicMovieListViewModel.class);
        upcomingMovieListInEgyptViewModel=new ViewModelProvider(this).get(moviesPopularInEgyptViewModel.class);


        //get data from prev activity
        String sectionTitle = getIntent().getStringExtra("title");
        Section=getIntent().getStringExtra("section");


        //set title
        title.setText(sectionTitle);



        //init views
        sectionRecyclerView =findViewById(R.id.categoryRecyclerView);
        back=findViewById(R.id.back);
        title=findViewById(R.id.category);

    }



    private void observeAnyChange(){
        // observe movies list changes

        switch (Section) {
            case "newMovies":
                upcomingMoviesObserver();
                break;
            case "popularMovies":
                popularMoviesObserver();
                break;
            case "topRatedMovies":
                votedMoviesObserver();
                break;
            case "topGrossesMovies":
                revenueMoviesObserver();
                break;
            case "arabicMovies":
                arabicMoviesObserver();
                break;
            case "popularMoviesInEgypt":
                popularMoviesInEgyptObserver();
                break;
        }










    }

    private void popularMoviesInEgyptObserver() {
        page=1;
        upcomingMovieListInEgyptViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });
    }

    private void revenueMoviesObserver() {
        page=1;
        revenue_ViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });
    }

    private void arabicMoviesObserver() {
        page=1;
        arabicMovieListViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });
    }

    private void votedMoviesObserver() {
        page=1;
        topVotedMovies_ViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });
    }

    private void popularMoviesObserver() {
        page=1;
        popularity_ViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });
    }

    private void upcomingMoviesObserver() {
        page=1;
        upcomingMovies_ViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });

    }


    private void upcomingMovies(){


            page=1;
        upcomingMovies_ViewModel.upcomingMovies(page);
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                        upcomingMovies_ViewModel.upcomingMovies(page);



                }
            }
        });
    }

    private void popularMovies(){
        page=1;
        popularity_ViewModel.popularMovies(page);
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                        page++;
                        popularity_ViewModel.popularMovies(page);



                }
            }
        });
    }

    private void revenueMovies(){
        page=1;
        revenue_ViewModel.topRevenueMovies(page,"revenue.desc");
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                        revenue_ViewModel.topRevenueMovies(page,"revenue.desc");



                }
            }
        });
    }

    private void votedMovies(){
        page=1;
        topVotedMovies_ViewModel.topVotedMovies(page,"vote_count.desc");
        sectionRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {

                        page++;
                        topVotedMovies_ViewModel.topVotedMovies(page,"vote_average.desc");



                }
            }
        });
    }



}