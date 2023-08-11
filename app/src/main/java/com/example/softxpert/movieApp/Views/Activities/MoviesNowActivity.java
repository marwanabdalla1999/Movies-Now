package com.example.softxpert.movieApp.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.Interfaces.notifyFragmentChanges;
import com.example.softxpert.movieApp.Views.Fragments.Home;
import com.example.softxpert.movieApp.Views.Fragments.Search;
import com.example.softxpert.movieApp.Views.Fragments.WatchList;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MoviesNowActivity extends AppCompatActivity implements notifyFragmentChanges {

    //Views
    private  ConstraintLayout progressLayout;
    private  BottomNavigationView bottomNav;

    // Fragments
    private Home homeFragment;
    private Search searchFragment;
    private WatchList watchListFragment;
    Fragment activeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // instantiate views and classes
        inIt();


        //set up fragments
        setupFragments();


        //set up bottom navigator
        setupBottomNav();

    }



    private void setupFragments() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, homeFragment,"Home")
                .add(R.id.fragmentContainer,searchFragment,"search").hide(searchFragment)
                .add(R.id.fragmentContainer,watchListFragment,"watchList").hide(watchListFragment)
                .commit();
    }




    private void inIt() {
        //instantiate Views
        bottomNav=findViewById(R.id.bottomNav);
        progressLayout=findViewById(R.id.progressLayout);
        progressLayout.setVisibility(View.VISIBLE);

        //instantiate Fragments
        homeFragment =new Home();
        searchFragment=new Search();
        watchListFragment=new WatchList();

        //listen changes in fragment
        homeFragment.notifyFragmentChanges(this);
        searchFragment.notifyFragmentChanges(this);


        //set default fragment
        activeFragment= homeFragment;

    }








    @SuppressLint("NonConstantResourceId")
    private void setupBottomNav() {

        //swipe between fragments
        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    if(bottomNav.getSelectedItemId()==R.id.home){
                        homeFragment.scrollToTop();
                    }
                    else {
                        loadFragment(homeFragment);
                        getDefaultStatusBar();
                    }

                break;

                case R.id.search:

                        loadFragment(searchFragment);
                    getDefaultStatusBar();

                break;

                case R.id.Watch_List:
                                if(watchListFragment.getListSize()) {
                                    loadFragment(watchListFragment);
                                    watchListFragment.scrollToStart();
                                }

                break;


            }


            return true;
        });
    }

    private void getDefaultStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.rgb(30,30,30));
    }


    private void loadFragment(Fragment fragment) {
            //set current fragment
         getSupportFragmentManager().beginTransaction().hide(activeFragment).show(fragment).commit();
            activeFragment=fragment;


    }






    @Override
    public void onBackPressed() {
        if(activeFragment!=homeFragment){
            bottomNav.setSelectedItemId(R.id.home);
        }
        else{
            super.onBackPressed();
        }

    }


    @Override
    public void hideProgressLayout() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void navVisibility(int Visibility) {
        bottomNav.setVisibility(Visibility);

    }
}