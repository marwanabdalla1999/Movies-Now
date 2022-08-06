package com.example.softxpert.movieApp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.filtersAdapter.filterClickListener;
import com.example.softxpert.movieApp.adapters.filtersAdapter.filtersRecyclerViewAdapter;
import com.example.softxpert.movieApp.adapters.moviesAdapter.moviesRecyclerViewAdapter;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.viewModels.filtersViewModel;
import com.example.softxpert.movieApp.viewModels.movieListViewModel;
import java.util.List;

public class movieListActivity extends AppCompatActivity implements filterClickListener {
    private movieListViewModel movieListViewModel;
    private RecyclerView moviesRecyclerView,filtersRecyclerView;
    private moviesRecyclerViewAdapter movieRecyclerViewAdapter;
    private  filtersRecyclerViewAdapter filtersRecyclerViewAdapter;
    private  filtersViewModel filtersViewModel;
   private int page=1;
   private String genres="";
    private List<filtersModel> filtersModelsTemp;
   private TextView Filter;
    private ImageView searchMovies;
    private  EditText searchMoviesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
            insIt();
            setupRecyclerView();
        observeAnyChange();
        allMovies();
        filters();
        searchMovies.setOnClickListener(view -> {
            if (!searchMoviesEditText.getText().toString().isEmpty()){

            performSearch();

            }
        });

        searchMoviesEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!searchMoviesEditText.getText().toString().isEmpty()){

                performSearch();

                }
                return true;
            }
            return false;
        });

    }

    private void performSearch() {
        Intent intent=new Intent(movieListActivity.this,searchActivity.class);
        intent.putExtra("query",searchMoviesEditText.getText().toString());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(movieListActivity.this,searchMoviesEditText , "search");
        startActivity(intent, options.toBundle());
    }

    private void filters() {
        filtersViewModel.filters();
    }

    private void setupRecyclerView() {
        //movies recycler view
        movieRecyclerViewAdapter=new moviesRecyclerViewAdapter();
        moviesRecyclerView.setAdapter(movieRecyclerViewAdapter);
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));



            //filters recyclerview
        filtersRecyclerViewAdapter=new filtersRecyclerViewAdapter(this);
        filtersRecyclerView.setAdapter(filtersRecyclerViewAdapter);
        filtersRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

    }

    private void insIt() {
        movieListViewModel=new ViewModelProvider(this).get(movieListViewModel.class);
        filtersViewModel=new ViewModelProvider(this).get(filtersViewModel.class);
        moviesRecyclerView=findViewById(R.id.moviesRecyclerView);
        filtersRecyclerView=findViewById(R.id.filtersRecyclerView);
        Filter=findViewById(R.id.Filter);
        searchMovies=findViewById(R.id.searchMovies);
        searchMoviesEditText=findViewById(R.id.searchMoviesEditText);
    }

    //observe any change
    private void observeAnyChange(){
        // observe movies list changes
        movieListViewModel.getmovies().observe(this, movieModels -> {
            if(movieModels!=null){
                movieRecyclerViewAdapter.setMovieModels(movieModels);

            }
        });

        // observe filters
        filtersViewModel.getFilters().observe(this, filtersModels -> {
            if(filtersModels!=null){
                filtersRecyclerViewAdapter.setMovieModels(filtersModels);
                filtersModelsTemp=filtersModels;
            }

        });
    }

    //Call moviesAPI from View-model
    private void allMovies(){
        movieListViewModel.allMovies(page,genres);
        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    page++;
                    movieListViewModel.allMovies(page,genres);

                }
            }
        });
    }


    @Override
    public void onFilterClicked(int position) {
            page=1;
            genres=Integer.toString(filtersModelsTemp.get(position).getId());
            if (genres.equals("0")){
                genres="";
            }
            movieListViewModel.allMovies(page,genres);
            Filter.setText(filtersModelsTemp.get(position).getName());

    }
}