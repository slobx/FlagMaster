package com.slobx.www.flagmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable runnable3sec = new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };

        android.os.Handler myHandler = new android.os.Handler();
        myHandler.postDelayed(runnable3sec, 1300);


    }

    public void nextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    protected void onPause() {
        super.onPause();
        finish();
    }


}
