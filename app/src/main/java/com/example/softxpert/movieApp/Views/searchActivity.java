
package com.example.softxpert.movieApp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.moviesAdapter.moviesRecyclerViewAdapter;
import com.example.softxpert.movieApp.viewModels.searchMovieListViewModel;

public class searchActivity extends AppCompatActivity {
    private RecyclerView searchRecyclerView;
    private ImageView back;
    private TextView title;
    private moviesRecyclerViewAdapter moviesRecyclerViewAdapter;
    private searchMovieListViewModel searchMovieListViewModel;
    private int page=1;
    private String searchQuery="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        insIt();
        setupRecyclerView();
        observeAnyChange();
        searchMovies(searchQuery);
        back.setOnClickListener(i->onBackPressed());
        title.setText(searchQuery);
    }

    private void setupRecyclerView() {
        moviesRecyclerViewAdapter=new moviesRecyclerViewAdapter();
        searchRecyclerView.setAdapter(moviesRecyclerViewAdapter);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(this,2));


    }

    private void insIt() {
        searchQuery=getIntent().getStringExtra("query");
        searchMovieListViewModel=new ViewModelProvider(this).get(searchMovieListViewModel.class);
        searchRecyclerView=findViewById(R.id.searchRecyclerView);
        back=findViewById(R.id.back);
        title=findViewById(R.id.searchQuery);

    }



    private void observeAnyChange(){
        // observe movies list changes
        searchMovieListViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                moviesRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });

    }

    private void searchMovies(String query){

        searchMovieListViewModel.searchMovies(page,query);
        searchRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                    searchMovieListViewModel.searchMovies(page,query);

                }
            }
        });
    }
}