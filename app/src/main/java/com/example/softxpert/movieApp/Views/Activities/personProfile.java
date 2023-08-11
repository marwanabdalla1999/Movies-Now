package com.example.softxpert.movieApp.Views.Activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.softxpert.R;
import com.example.softxpert.movieApp.adapters.moviesSmall.moviesAdapterSmallLayout;
import com.example.softxpert.movieApp.models.personModel;
import com.example.softxpert.movieApp.viewModels.PersonDetailsViewModel;

public class personProfile extends AppCompatActivity {
        com.example.softxpert.movieApp.viewModels.personMoviesViewModel personMoviesViewModel;
        TextView name,bio,birthDate,birthPlace,knowAs;
        ImageView back,profilePhoto,gradiant;
        PersonDetailsViewModel personDetailsViewModel;
        LinearLayout details;
        int personId;
        private ConstraintLayout progressLayout;
        private ConstraintLayout background;
        private NestedScrollView scrollView;
        private TextView title;
        private LinearLayout backgroundTitle;
        private int darkenColor=Color.rgb(30,30,30) ;
        private RecyclerView projects;
  private moviesAdapterSmallLayout projectsAdapter;
  TextView projectText;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);

        inIt();

        PersonDetails(personId);
        personMovies(personId);
        scrollHandler();
        observe();

        setupRecyclerView();

    }

    private void personMovies(int personId) {
        personMoviesViewModel.personMovies(personId);
    }

    private void setupRecyclerView() {
        projectsAdapter=new moviesAdapterSmallLayout();
        projects.setAdapter(projectsAdapter);
        projects.setLayoutManager(new GridLayoutManager(this,3));

    }

    private void observe() {

        personDetailsViewModel.getPersonDetails().observe(this,personDetails->{
            progressLayout.setVisibility(View.GONE);
            if(personDetails!=null){
               personDetails(personDetails);

            }

        });

        personMoviesViewModel.getPersonMovies().observe(this,personMovies->{

            if(personMovies!=null && personMovies.size()>0){
                projectsAdapter.setMovieModels(personMovies);
                projectText.setVisibility(View.VISIBLE);

            }

        });
    }

    private void personDetails(personModel personDetails){
        title.setText(personDetails.getName());
        name.setText(personDetails.getName());
        bio.setText(personDetails.getBiography());
        birthDate.setText(personDetails.getBirthday());
        birthPlace.setText(personDetails.getPlace_of_birth());
        knowAs.setText(personDetails.getKnown_for_department());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+personDetails.getProfile_path()).placeholder(R.drawable.placeholder).addListener(new RequestListener<Drawable>() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                animateColorValue(background,Color.rgb(43,44,53));

                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                BitmapDrawable bitmapDrawable=((BitmapDrawable) resource);
                Bitmap image = bitmapDrawable.getBitmap();
                createPaletteAsync(image);

                return false;
            }
        }).into(profilePhoto);
        if(personDetails.getBiography().isEmpty() || personDetails.getBiography().equals("null")){
            details.setVisibility(View.GONE);
        }
        else{
            details.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void createPaletteAsync(Bitmap bitmap) {

        Palette.from(bitmap).generate(p -> {
            // Use generated instance
            if (p != null) {
                animateColorValue(background,p.getDominantColor(Color.rgb(43,44,53)));
            }

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
        gradiant.setImageDrawable(gd);
        background.setBackgroundColor(darkenColor);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(darkenColor);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scrollHandler() {
        scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollView.getScrollY()>450){
                title.setVisibility(View.VISIBLE);
                backgroundTitle.setBackgroundColor(darkenColor);

            }
            else {
                title.setVisibility(View.GONE);
                backgroundTitle.setBackgroundColor(0x00FFFFFF);

            }
        });
    }

    private void PersonDetails(int personId) {
        personDetailsViewModel.personDetails(personId);
    }

    private void inIt() {
        personDetailsViewModel=new ViewModelProvider(this).get(PersonDetailsViewModel.class);
        personMoviesViewModel=new ViewModelProvider(this).get(com.example.softxpert.movieApp.viewModels.personMoviesViewModel.class);
        name=findViewById(R.id.name);
        bio=findViewById(R.id.biography);
        profilePhoto=findViewById(R.id.photo);
        birthDate=findViewById(R.id.birth_date);
        birthPlace=findViewById(R.id.birth_place);
        back=findViewById(R.id.back);
        knowAs=findViewById(R.id.know_as);
        details=findViewById(R.id.details);
        progressLayout=findViewById(R.id.progressLayout);
        background=findViewById(R.id.background);
        gradiant=findViewById(R.id.Gradiant);
        scrollView=findViewById(R.id.scrollview);
        title=findViewById(R.id.title);
        backgroundTitle=findViewById(R.id.backgroundTitle);
        projects=findViewById(R.id.projects);
        projectText=findViewById(R.id.projectsText);
        progressLayout.setVisibility(View.VISIBLE);
        personId=getIntent().getIntExtra("Person_id",0);
        back.setOnClickListener(i-> onBackPressed());
        projectText.setVisibility(View.GONE);
    }
}