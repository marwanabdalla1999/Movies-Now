package com.example.softxpert.movieApp.Views.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.Interfaces.notifyFragmentChanges;
import com.example.softxpert.movieApp.Views.Activities.categoryActivity;
import com.example.softxpert.movieApp.Views.Activities.searchActivity;
import com.example.softxpert.movieApp.adapters.categoriesForSearchFragment.categoriesAdapter;
import com.example.softxpert.movieApp.adapters.categoriesForSearchFragment.categoriesClickListener;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.viewModels.categoriesViewModel;
import java.util.List;




public class Search extends Fragment implements categoriesClickListener {
    //views
    private TextView Search;
    private RecyclerView filtersRecyclerView;
    private  NestedScrollView scrollView;


        //adapters
    private categoriesAdapter filtersRecyclerViewAdapter;


    //ViewModels
    private categoriesViewModel filtersViewModel;

    //Categories
    private List<filtersModel> filtersModelsTemp;

    //notifyFragmentChangesToActivity
    private notifyFragmentChanges notifyFragmentChanges;




    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        View view= LayoutInflater.from(getContext()).inflate(R.layout.search_fragment,container,false);
        //instantiate Views and Classes
        insIt(view);

        //setup RecyclerViews
        setupRecyclerView();

        //Call Categories
        filters();

        //Observe Changes in data
        observeAnyChange();

        //hide bottom nav
        hideBottomNav();


        Search.setOnTouchListener((v, event) -> {
            Search.setEnabled(false);
            performSearch();
            Search.postDelayed(
                    ()-> Search.setEnabled(true),
                    1000
            );
            return false;
        });







        return view;

    }
    private void performSearch() {

        Intent intent=new Intent(getContext(), searchActivity.class);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideBottomNav() {
        scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(!scrollView.canScrollVertically(1)){
                notifyFragmentChanges.navVisibility(0x00000008);
            }
            else {
                notifyFragmentChanges.navVisibility(0x00000000);

            }
        });
    }





    private void filters() {
        filtersViewModel.filters();
    }

    private void setupRecyclerView() {


            //filters recyclerview
        filtersRecyclerViewAdapter=new categoriesAdapter(this);
        filtersRecyclerView.setAdapter(filtersRecyclerViewAdapter);
        filtersRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        filtersRecyclerView.setItemViewCacheSize(5024);
    }

    private void insIt(View view) {
        filtersViewModel=new ViewModelProvider(this).get(categoriesViewModel.class);
        filtersRecyclerView=view.findViewById(R.id.filtersRecyclerView);
        Search=view.findViewById(R.id.searchView);
            scrollView=view.findViewById(R.id.scrollView);
    }

    //observe any change
    private void observeAnyChange(){
        // observe filters
        filtersViewModel.getFilters().observe(requireActivity(), filtersModels -> {
            if(filtersModels!=null){
                filtersRecyclerViewAdapter.setMovieModels(filtersModels);
                    filtersModelsTemp=filtersModels;
            }

        });


    }




    @Override
    public void onFilterClicked(int position) {

        Intent intent=new Intent(getContext(), categoryActivity.class);
        intent.putExtra("title",filtersModelsTemp.get(position).getName());
        intent.putExtra("categoryId",filtersModelsTemp.get(position).getId()+"");
        startActivity(intent);

    }


    public void notifyFragmentChanges(notifyFragmentChanges notifyFragmentChanges){
        this.notifyFragmentChanges=notifyFragmentChanges;
    }

}