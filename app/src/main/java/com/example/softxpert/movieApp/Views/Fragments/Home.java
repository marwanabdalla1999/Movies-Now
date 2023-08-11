package com.example.softxpert.movieApp.Views.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.softxpert.movieApp.Interfaces.notifyFragmentChanges;
import com.example.softxpert.movieApp.adapters.categoriesForHomeFragment.categoriesClickListener;
import com.example.softxpert.movieApp.adapters.categoriesForHomeFragment.categoriesAdapter;
import com.example.softxpert.movieApp.viewModels.arabicMovieListViewModel;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.softxpert.R;
import com.example.softxpert.movieApp.Views.Activities.categoryActivity;
import com.example.softxpert.movieApp.Views.Activities.viewSectionActivity;
import com.example.softxpert.movieApp.adapters.moviesLarge.moviesAdapterLargeLayout;
import com.example.softxpert.movieApp.adapters.person.personAdapter;
import com.example.softxpert.movieApp.adapters.trendingMovies.trendingAdapter;
import com.example.softxpert.movieApp.adapters.moviesMedium.moviesAdapterMediumLayout;
import com.example.softxpert.movieApp.models.filtersModel;
import com.example.softxpert.movieApp.models.movieModel;
import com.example.softxpert.movieApp.offlineData.sharedPreference;
import com.example.softxpert.movieApp.viewModels.categoriesViewModel;
import com.example.softxpert.movieApp.viewModels.popularMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.topRevenueMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.topVotedMovieListViewModel;
import com.example.softxpert.movieApp.viewModels.trendingMoviesViewModel;
import com.example.softxpert.movieApp.viewModels.trendingPersonsViewModel;
import com.example.softxpert.movieApp.viewModels.moviesPopularInEgyptViewModel;
import com.example.softxpert.movieApp.viewModels.upcomingMovieListViewModel;
import java.util.List;



public class Home extends Fragment implements categoriesClickListener {

    //View Models
    private topVotedMovieListViewModel topVotedMovies_ViewModel;
    private upcomingMovieListViewModel newMovies_ViewModel;
    private topRevenueMovieListViewModel revenue_ViewModel;
    private popularMovieListViewModel popularity_ViewModel;
    private trendingMoviesViewModel trendingMoviesViewModel;
    private trendingPersonsViewModel trendingPersonsViewModel;
    private arabicMovieListViewModel arabicMovieListViewModel;
    private moviesPopularInEgyptViewModel moviesPopularInEgyptViewModel;
    private categoriesViewModel filtersViewModel;



    // Views
    private RecyclerView topVoted,topRevenue,upcoming,popular,filtersRecyclerView,personsRecyclerView, ArabicRecyclerView,upcomingArabicMoviesRecyclerView;
    private ImageView[] dots;
    private LinearLayout sliderDotsPanel;
    private  ScrollView scrollView;
    private TextView viewTopRatedMovies,viewTopGrossesMovies,viewPopularMovies,viewNewMovies, viewArabicMovies,viewPopularMoviesInEgypt;
    private Button addToWatchList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager2 trending;


    //Adapters
    private moviesAdapterMediumLayout ArabicMoviesAdapter;
    private moviesAdapterMediumLayout upcomingArabicMoviesAdapter;
    private personAdapter trendingPersonsAdapter;
    private moviesAdapterLargeLayout topVotedAdapter,topRevenueAdapter;
    private moviesAdapterMediumLayout upcomingAdapter;
    private moviesAdapterMediumLayout popularAdapter;
    private categoriesAdapter filtersRecyclerViewAdapter;
    private trendingAdapter trendingAdapter;


    // data stores
   private int topVotedMovies_page=1;
    private int NewMovies_page=1, arabicMovies_page =1,upcomingArabicMovies_page=1;
    private int revenue_page=1;
    private int popularity_page=1;
    private List<filtersModel> filtersModelsTemp;
    private boolean isInWatchList=false;

    //SharedPreference
    sharedPreference sharedPreferenceInstance;


    //Interfaces
    notifyFragmentChanges notifyFragmentChanges;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        View view= LayoutInflater.from(getContext()).inflate(R.layout.activity_movies_list,container,false);

        //instantiate views and classes
        insIt(view);

        // setup RecyclerViews
        setupRecyclerView();

        // setup ViewPager
        setupViewPager();

        // get Movies Data
        getData();


        // observe changes of data
        observeAnyChange();


        //hide
        HandleScroll();



        onClickListener();



        onSwipe();


        return view;

    }

    private void getData() {
        TrendingPersons();
        TrendingMovies();
        newMovies();
        popularMovies();
        revenueMovies();
        votedMovies();
        filters();
        getArabicMovies();
        getLatestArabicMovies();
    }

    private void getLatestArabicMovies() {

        moviesPopularInEgyptViewModel.upcomingMovies(upcomingArabicMovies_page,"EG");
        upcomingArabicMoviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(upcomingArabicMovies_page<6){
                        upcomingArabicMovies_page++;
                        moviesPopularInEgyptViewModel.upcomingMovies(upcomingArabicMovies_page,"EG");
                    }


                }
            }
        });
    }

    private void getArabicMovies() {
        arabicMovieListViewModel.ArabicMovies(arabicMovies_page);
        ArabicRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(arabicMovies_page <6){
                        arabicMovies_page++;
                        arabicMovieListViewModel.ArabicMovies(arabicMovies_page);
                    }


                }
            }
        });
    }

    private void onSwipe() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.postDelayed(() -> swipeRefreshLayout.setRefreshing(false),2000);
            getData();

        });
    }

    private void onClickListener() {

        viewTopRatedMovies.setOnClickListener(i->{
            Intent intent=new Intent(getContext(), viewSectionActivity.class);
            intent.putExtra("title","Top rated movies");
            intent.putExtra("section","topRatedMovies");
            startActivity(intent);

        });
        viewPopularMovies.setOnClickListener(i->{
            Intent intent=new Intent(getContext(),viewSectionActivity.class);
            intent.putExtra("title","Popular movies");
            intent.putExtra("section","popularMovies");
            startActivity(intent);

        });
        viewNewMovies.setOnClickListener(i->{
            Intent intent=new Intent(getContext(),viewSectionActivity.class);
            intent.putExtra("title","New movies");
            intent.putExtra("section","newMovies");
            startActivity(intent);

        });
        viewTopGrossesMovies.setOnClickListener(i->{
            Intent intent=new Intent(getContext(),viewSectionActivity.class);
            intent.putExtra("title","Top lifetime grosses movies");
            intent.putExtra("section","topGrossesMovies");
            startActivity(intent);

        });

        viewArabicMovies.setOnClickListener(i->{
            Intent intent=new Intent(getContext(),viewSectionActivity.class);
            intent.putExtra("title","Arabic movies");
            intent.putExtra("section","arabicMovies");
            startActivity(intent);

        });
        viewPopularMoviesInEgypt.setOnClickListener(i->{
            Intent intent=new Intent(getContext(),viewSectionActivity.class);
            intent.putExtra("title","Popular movies in Egypt");
            intent.putExtra("section","popularMoviesInEgypt");
            startActivity(intent);

        });

        addToWatchList.setOnClickListener(v -> watchList());


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void watchList() {
        int position=  trending.getCurrentItem();

                if (!isInWatchList){
                    sharedPreferenceInstance.saveMovie(trendingAdapter.getPosters().get(position), requireContext());
                    addToWatchList.setCompoundDrawablesWithIntrinsicBounds(null, null, addToWatchList.getContext().getDrawable(R.drawable.ic_baseline_bookmark_24), null);

                }
                else{
                    sharedPreferenceInstance.removeMovie(trendingAdapter.getPosters().get(position),requireContext());
                    addToWatchList.setCompoundDrawablesWithIntrinsicBounds(null, null, addToWatchList.getContext().getDrawable(R.drawable.ic_outline_bookmark_border_24), null);

                }
                isInWatchList=isInWatchList();
        WatchList.notifyDataChanged(addToWatchList.getContext());

    }


    private boolean isInWatchList() {
        boolean retVal=false;
        if (getContext()!=null){
                 List<movieModel> watchList=sharedPreferenceInstance.getMovies(getContext());

        for(movieModel movie : watchList){
        if (movie.getMovie_id() == trendingAdapter.getPosters().get(trending.getCurrentItem()).getMovie_id()) {
            retVal = true;
            break;
        }
    }
}

        return retVal;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void HandleScroll() {
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(!scrollView.canScrollVertically(1)){
                notifyFragmentChanges.navVisibility(0x00000008);
            }
            else {
                notifyFragmentChanges.navVisibility(0x00000000);

            }
        });
    }


    private void TrendingPersons() {
        trendingPersonsViewModel.trendingPersons();


    }


    private void setupViewPager() {
        trendingAdapter=new trendingAdapter();

        trending.setAdapter(trendingAdapter);
        trending.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                isInWatchList=isInWatchList();
                if(isInWatchList ){
                    addToWatchList.setCompoundDrawablesWithIntrinsicBounds(null, null, addToWatchList.getContext().getDrawable(R.drawable.ic_baseline_bookmark_24), null);

                }
                else{
                    addToWatchList.setCompoundDrawablesWithIntrinsicBounds(null, null, addToWatchList.getContext().getDrawable(R.drawable.ic_outline_bookmark_border_24), null);

                }
                for(int i = 0; i< trendingAdapter.getItemCount(); i++){
                    dots[i].setImageDrawable(dots[i].getContext().getDrawable( R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(dots[position].getContext().getDrawable(R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

         final Handler handler = new Handler();
        Runnable ViewPagerVisibleScroll= new Runnable() {
            @Override
            public void run() {

                if(trendingAdapter.getItemCount()!=0) {
                    trending.setCurrentItem((trending.getCurrentItem() + 1) % trendingAdapter.getItemCount(), true);
                }
                handler.postDelayed(this, 12000);


            }
        };
        handler.postDelayed(ViewPagerVisibleScroll,12000);

    }

    private void TrendingMovies() {
        trendingMoviesViewModel.trendingMovies();

    }



    private void filters() {
        filtersViewModel.filters();
    }

    private void setupRecyclerView() {
        //trending persons recycler view
        trendingPersonsAdapter=new personAdapter();
        personsRecyclerView.setAdapter(trendingPersonsAdapter);
        personsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        personsRecyclerView.setItemViewCacheSize(5024);


        //popular movies recycler view
        popularAdapter=new moviesAdapterMediumLayout();
        popular.setAdapter(popularAdapter);
        popular.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        popular.setItemViewCacheSize(5024);

        //lastReleased recycler view
        upcomingAdapter=new moviesAdapterMediumLayout();
        upcoming.setAdapter(upcomingAdapter);
        upcoming.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        upcoming.setItemViewCacheSize(5024);

        //topVoted recycler view
        topVotedAdapter=new moviesAdapterLargeLayout();
        topVoted.setAdapter(topVotedAdapter);
        topVoted.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        topVoted.setItemViewCacheSize(5024);

        //topRevenue recycler view
        topRevenueAdapter=new moviesAdapterLargeLayout();
        topRevenue.setAdapter(topRevenueAdapter);
        topRevenue.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        topRevenue.setItemViewCacheSize(5024);


            //filters recyclerview
        filtersRecyclerViewAdapter=new categoriesAdapter(this);
        filtersRecyclerView.setAdapter(filtersRecyclerViewAdapter);
        filtersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        filtersRecyclerView.setItemViewCacheSize(5024);


        //Arabic Movies recyclerview
        ArabicMoviesAdapter =new moviesAdapterMediumLayout();
        ArabicRecyclerView.setAdapter(ArabicMoviesAdapter);
        ArabicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        ArabicRecyclerView.setItemViewCacheSize(5024);

        //Latest Arabic Movies recyclerview
        upcomingArabicMoviesAdapter =new moviesAdapterMediumLayout();
        upcomingArabicMoviesRecyclerView.setAdapter(upcomingArabicMoviesAdapter);
        upcomingArabicMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        upcomingArabicMoviesRecyclerView.setItemViewCacheSize(5024);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void insIt(View view) {

        //init shared preference
        sharedPreferenceInstance= sharedPreference.getInstance();


        //init viewModels
        topVotedMovies_ViewModel=new ViewModelProvider(this).get(topVotedMovieListViewModel.class);
        newMovies_ViewModel =new ViewModelProvider(this).get(upcomingMovieListViewModel.class);
        revenue_ViewModel=new ViewModelProvider(this).get(topRevenueMovieListViewModel.class);
        popularity_ViewModel=new ViewModelProvider(this).get(popularMovieListViewModel.class);
        trendingMoviesViewModel=new ViewModelProvider(this).get(trendingMoviesViewModel.class);
        trendingPersonsViewModel=new ViewModelProvider(this).get(trendingPersonsViewModel.class);
        filtersViewModel=new ViewModelProvider(this).get(categoriesViewModel.class);
        arabicMovieListViewModel =new ViewModelProvider(this).get(com.example.softxpert.movieApp.viewModels.arabicMovieListViewModel.class);
        moviesPopularInEgyptViewModel =new ViewModelProvider(this).get(moviesPopularInEgyptViewModel.class);


        // init views
        topVoted=view.findViewById(R.id.topVoted);
        topRevenue=view.findViewById(R.id.topRevenue);
        upcoming=view.findViewById(R.id.upcoming);
        popular=view.findViewById(R.id.popular);
        filtersRecyclerView=view.findViewById(R.id.filtersRecyclerView);
        trending=view.findViewById(R.id.trending);
        sliderDotsPanel = view.findViewById(R.id.SliderDots);
        personsRecyclerView=view.findViewById(R.id.trendingPersons);
        ArabicRecyclerView =view.findViewById(R.id.popularArabicMovies);
        upcomingArabicMoviesRecyclerView=view.findViewById(R.id.latestArabicMovies);
        scrollView=view.findViewById(R.id.scrollView);
        viewNewMovies=view.findViewById(R.id.viewNewMovies);
        viewPopularMovies=view.findViewById(R.id.viewPopularMovies);
        viewTopGrossesMovies=view.findViewById(R.id.viewTopGrossesMovies);
        viewTopRatedMovies=view.findViewById(R.id.viewTopRatedMovies);
        viewArabicMovies=view.findViewById(R.id.viewPopularArabicMovies);
        viewPopularMoviesInEgypt=view.findViewById(R.id.viewLatestArabicMovies);
        addToWatchList=view.findViewById(R.id.watch_list);
        swipeRefreshLayout=view.findViewById(R.id.swipeLayout);




    }

    //observe any change
    private void observeAnyChange(){
        // observe movies list changes
        revenue_ViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                topRevenueAdapter.setMovieModels(movieModels);

            }
        });

        topVotedMovies_ViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                topVotedAdapter.setMovieModels(movieModels);

            }
        });
        arabicMovieListViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                ArabicMoviesAdapter.setMovieModels(movieModels);

            }
        });
        moviesPopularInEgyptViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                upcomingArabicMoviesAdapter.setMovieModels(movieModels);

            }
        });
        newMovies_ViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                upcomingAdapter.setMovieModels(movieModels);

            }
        });
        popularity_ViewModel.getmovies().observe(getViewLifecycleOwner(), movieModels -> {
            if(movieModels!=null){
                popularAdapter.setMovieModels(movieModels);

            }
        });

        trendingPersonsViewModel.getTrendingPersons().observe(getViewLifecycleOwner(), personModels -> {
            if(personModels!=null){
            trendingPersonsAdapter.setPersonModel(personModels);
            }
        });

        // observe filters
        filtersViewModel.getFilters().observe(getViewLifecycleOwner(), filtersModels -> {
            if(filtersModels!=null){
                filtersRecyclerViewAdapter.setMovieModels(filtersModels);
                    filtersModelsTemp=filtersModels;
            }

        });

        //trendingMovies
        trendingMoviesViewModel.getTrendingmovies().observe(getViewLifecycleOwner(), movieModels -> {
            notifyFragmentChanges.hideProgressLayout();

            if(movieModels!=null){
                trendingAdapter.setPosters(movieModels);

                int dotsCount = Math.min(10,trendingAdapter.getItemCount());
                dots = new ImageView[dotsCount];
                sliderDotsPanel.removeAllViews();
                for(int i = 0; i < dotsCount; i++){

                    dots[i] = new ImageView(getContext());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(dots[i].getContext(), R.drawable.non_active_dot));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(8, 0, 8, 0);

                    sliderDotsPanel.addView(dots[i], params);

                }
                dots[trending.getCurrentItem()].setImageDrawable(ContextCompat.getDrawable(dots[trending.getCurrentItem()].getContext(), R.drawable.active_dot));


            }
        });
    }


    private void newMovies(){



        newMovies_ViewModel.upcomingMovies(NewMovies_page);
        upcoming.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(NewMovies_page<6){
                        NewMovies_page++;
                        newMovies_ViewModel.upcomingMovies(NewMovies_page);
                    }


                }
            }
        });
    }
    private void popularMovies(){
                popularity_ViewModel.popularMovies(popularity_page);
        popular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(popularity_page<6){
                        popularity_page++;
                        popularity_ViewModel.popularMovies(popularity_page);
                    }


                }
            }
        });
    }

    private void revenueMovies(){

                revenue_ViewModel.topRevenueMovies(revenue_page,"revenue.desc");
        topRevenue.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(revenue_page<6){
                        revenue_page++;
                        revenue_ViewModel.topRevenueMovies(revenue_page,"revenue.desc");
                    }


                }
            }
        });
    }

    private void votedMovies(){

                topVotedMovies_ViewModel.topVotedMovies(topVotedMovies_page,"vote_count.desc");
        topVoted.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(topVotedMovies_page<6){
                        topVotedMovies_page++;
                        topVotedMovies_ViewModel.topVotedMovies(topVotedMovies_page,"vote_average.desc");
                    }


                }
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



    public void scrollToTop() {

        scrollView.smoothScrollTo(0,0);

    }

    public void notifyFragmentChanges(com.example.softxpert.movieApp.Interfaces.notifyFragmentChanges notifyFragmentChanges){
        this.notifyFragmentChanges=notifyFragmentChanges;
    }


}