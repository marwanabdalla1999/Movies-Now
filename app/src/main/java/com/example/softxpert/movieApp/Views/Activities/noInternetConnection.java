package com.example.softxpert.movieApp.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.softxpert.R;

public class noInternetConnection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);

       //instantiate view
        CardView refresh = findViewById(R.id.refresh);


       // refresh the app
        refresh.setOnClickListener(i->{
            Intent intent=new Intent(noInternetConnection.this, splashScreen.class);
            startActivity(intent);
            finish();
        });


    }
}