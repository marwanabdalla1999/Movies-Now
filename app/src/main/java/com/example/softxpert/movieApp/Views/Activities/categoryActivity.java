package com.example.softxpert.movieApp.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.moviesSmall.moviesAdapterSmallLayout;
import com.example.softxpert.movieApp.viewModels.categoryMoviesViewModel;

public class categoryActivity extends AppCompatActivity {

    //Views
    private RecyclerView categoryMoviesRecyclerView;
    private ImageView back;

    //adapters
    private moviesAdapterSmallLayout moviesRecyclerViewAdapter;


    //View models
    private categoryMoviesViewModel categoryMoviesListViewModel;


    //primitive data types
    private int page=1;
    private String categoryId="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // instantiate views and classes
        insIt();

        //set up recycler views
        setupRecyclerView();

        //observe data changed
        observeAnyChange();

        //get category data
        Movies(categoryId);


        // return to previous
        back.setOnClickListener(i->onBackPressed());

    }
    private void setupRecyclerView() {
        moviesRecyclerViewAdapter=new moviesAdapterSmallLayout();
        categoryMoviesRecyclerView.setAdapter(moviesRecyclerViewAdapter);
        categoryMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        categoryMoviesRecyclerView.setItemViewCacheSize(5024);


    }

    private void insIt() {
        //instantiate category title  and id
        String category = getIntent().getStringExtra("title");
        categoryId=getIntent().getStringExtra("categoryId");

        // instantiate view models
        categoryMoviesListViewModel=new ViewModelProvider(this).get(categoryMoviesViewModel.class);

        //instantiate views
        categoryMoviesRecyclerView=findViewById(R.id.categoryRecyclerView);
        back=findViewById(R.id.back);
        TextView title = findViewById(R.id.category);

        // set title
        title.setText(category);
    }



    private void observeAnyChange(){
        // observe movies list changes
        categoryMoviesListViewModel.getCategoryMovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });

    }

    private void Movies(String category){

        categoryMoviesListViewModel.categoryMovies(page,category);
        categoryMoviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                    categoryMoviesListViewModel.categoryMovies(page,category);

                }
            }
        });
    }
}