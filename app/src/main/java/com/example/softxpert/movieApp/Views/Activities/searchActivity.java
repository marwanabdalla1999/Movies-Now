
package com.example.softxpert.movieApp.Views.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.moviesFullWidth.moviesFullWidth;
import com.example.softxpert.movieApp.viewModels.searchMovieListViewModel;

public class searchActivity extends AppCompatActivity {
    private RecyclerView searchRecyclerView;
    private ImageView back;

    private moviesFullWidth moviesRecyclerViewAdapter;
    private searchMovieListViewModel searchMovieListViewModel;
    private int page=1;
    TextView empty;
    EditText Search;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        insIt();
        setupRecyclerView();
        observeAnyChange();
            listenSearch();
        back.setOnClickListener(i->onBackPressed());
    }

    private void listenSearch() {
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    progressBar.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
                    searchRecyclerView.setVisibility(View.GONE);
                    Search.postDelayed(
                            ()->  searchMovies(s.toString()),
                            2000
                    );






            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setupRecyclerView() {
        moviesRecyclerViewAdapter=new moviesFullWidth();
        searchRecyclerView.setAdapter(moviesRecyclerViewAdapter);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setItemViewCacheSize(5024);


    }

    private void insIt() {
        searchMovieListViewModel=new ViewModelProvider(this).get(searchMovieListViewModel.class);
        searchRecyclerView=findViewById(R.id.searchRecyclerView);
        back=findViewById(R.id.back);
        progressBar=findViewById(R.id.progressBar);
        empty=findViewById(R.id.empty);
        Search=findViewById(R.id.searchView);
        empty.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.GONE);



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        Search.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);



        searchRecyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if(scrollY>oldScrollY){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Search.getWindowToken(), 0);
                }
        });
    }

    private void observeAnyChange(){
        // observe movies list changes
        searchMovieListViewModel.getmovies().observe(this, movieModels -> {
            progressBar.setVisibility(View.GONE);
            if(movieModels!=null && movieModels.size()>0) {
                moviesRecyclerViewAdapter.setMovieModels(movieModels);
                empty.setVisibility(View.GONE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
                else{
                    empty.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.GONE);
                }



        });

    }

    private void searchMovies(String query){
        if (!query.isEmpty()){
        page=1;
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
        else{
            progressBar.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            searchRecyclerView.setVisibility(View.GONE);
        }
    }
}