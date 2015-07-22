package com.slobx.www.flagmaster.timegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.slobx.www.flagmaster.R;


public class SplashTimeGameFlagCapital extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_time_game);

        final ImageView splashImageView = (ImageView) findViewById(R.id.animImageView);
        splashImageView.setBackgroundResource(R.drawable.introsplash);
        final AnimationDrawable frameAnimation = (AnimationDrawable)splashImageView.getBackground();

        splashImageView.post(new Runnable(){
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

        Runnable runnable3sec = new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };

        android.os.Handler myHandler = new android.os.Handler();
        myHandler.postDelayed(runnable3sec, 3400);


    }

    public void nextActivity() {
        Intent intent = new Intent(this, TimeGameFlagCapitalActivity.class);
        startActivity(intent);

    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
