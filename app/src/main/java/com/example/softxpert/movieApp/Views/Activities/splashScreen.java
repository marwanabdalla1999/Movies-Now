package com.example.softxpert.movieApp.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.softxpert.R;

@SuppressLint("CustomSplashScreen")
public class splashScreen extends AppCompatActivity {


    private TextView movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //instantiate views
        insIt();

        //animate logo
        animateLogo();

    }

    @SuppressLint("SetTextI18n")
    private void insIt() {
        movies=findViewById(R.id.movies);
        TextView now = findViewById(R.id.now);
        movies.setText("Movies");
        now.setText("Now");
    }

    private void animateLogo() {
        TranslateAnimation animObj= new TranslateAnimation(300,0, 0, 0);
        animObj.setDuration(500);
        movies.startAnimation(animObj);


        int TIME_OUT = 2000;
        new Handler().postDelayed(() -> {

            Intent i;
            if (internetIsConnected()){

                i = new Intent(splashScreen.this, MoviesNowActivity.class);


            }
            else
            {
                i = new Intent(splashScreen.this, noInternetConnection.class);

            }
            startActivity(i);
            finish();

        }, TIME_OUT);
    }


    //get is connected to internet
    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }


}