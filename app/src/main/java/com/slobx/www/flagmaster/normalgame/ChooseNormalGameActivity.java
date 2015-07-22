package com.slobx.www.flagmaster.normalgame;

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


public class ChooseNormalGameActivity extends Activity {

    AdView adview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_normal_game);


        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);


        //check if ads are turned on/off and display them
        if (!removeAdsBoolean) {
            adview = (AdView) findViewById(R.id.adViewChooseMain);
            adview.loadAd(new AdRequest.Builder().build());
        }


    }


    public void startNormalGameNameFlag(View v) {
        Intent intent = new Intent(this, NormalGameNameFlagActivity.class);
        startActivity(intent);
        finish();
    };

    public void startNormalGameFlagName(View v) {
        Intent intent = new Intent(this, NormalGameFlagNameActivity.class);
        startActivity(intent);
        finish();
    };

    public void startNormalGameFlagCapital(View v) {
        Intent intent = new Intent(this, NormalGameFlagCapitalActivity.class);
        startActivity(intent);
        finish();
    };

    public void startNormalGameCapitalFlag(View v) {
        Intent intent = new Intent(this, NormalGameCapitalFlagActivity.class);
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


    

