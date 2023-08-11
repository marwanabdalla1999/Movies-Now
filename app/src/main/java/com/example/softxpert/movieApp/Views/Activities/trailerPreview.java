package com.example.softxpert.movieApp.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.softxpert.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class trailerPreview extends AppCompatActivity {


private String videoId="";

private ImageView back;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trailer_preview);


        //instantiate views ,classes and get default data
        inIt();

        back.setOnClickListener(i-> onBackPressed());


    }

    private void inIt() {

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtubePlayer);
        back = findViewById(R.id.back);



        videoId=getIntent().getStringExtra("videoId");


        getLifecycle().addObserver(youTubePlayerView);


        youTubePlayerView.setEnableAutomaticInitialization(false);
        AbstractYouTubePlayerListener playerListener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                // using pre-made custom ui
                youTubePlayer.unMute();
                youTubePlayer.loadVideo(videoId, 0);

            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                if (state == PlayerConstants.PlayerState.ENDED) {

                    onBackPressed();
                    overridePendingTransition(0, R.anim.fade_out);

                }

            }
        };

        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(1).ccLoadPolicy(0).ivLoadPolicy(3).rel(0).build();
        youTubePlayerView.initialize(playerListener,options);


    }
}