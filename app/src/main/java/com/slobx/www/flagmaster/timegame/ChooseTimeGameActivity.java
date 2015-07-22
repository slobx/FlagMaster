package com.slobx.www.flagmaster.timegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.slobx.www.flagmaster.MainActivity;
import com.slobx.www.flagmaster.R;


public class ChooseTimeGameActivity extends Activity {

    AdView adview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time_game);


        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);


        if (!removeAdsBoolean) {
            adview = (AdView) findViewById(R.id.adViewChooseMain);
            adview.loadAd(new AdRequest.Builder().build());
        }


    }


    public void startTimeGameNameFlag(View v) {
        Intent intent = new Intent(this, SplashTimeGameNameFlag.class);
        startActivity(intent);
        finish();
    };

    public void startTimeGameFlagName(View v) {
        Intent intent = new Intent(this, SplashTimeGameFlagName.class);
        startActivity(intent);
        finish();
    };

    public void startTimeGameFlagCapital(View v) {
        Intent intent = new Intent(this, SplashTimeGameFlagCapital.class);
        startActivity(intent);
        finish();
    };

    public void startTimeGameCapitalFlag(View v) {
        Intent intent = new Intent(this, SplashTimeGameCapitalFlag.class);
        startActivity(intent);
        finish();
    };





    protected void onResume() {
        super.onResume();

    }

    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }



}


    

