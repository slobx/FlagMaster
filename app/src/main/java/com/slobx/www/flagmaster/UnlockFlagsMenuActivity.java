package com.slobx.www.flagmaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slobx.www.flagmaster.unlockactivities.UnlockAfricaFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockAsiaFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockEuropeFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockNorthAmericaFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockOceaniaFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockOthersCountriesActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockSouthAmericaFlagsActivity;
import com.slobx.www.flagmaster.unlockactivities.UnlockUnrecognizedCountriesActivity;


public class UnlockFlagsMenuActivity extends Activity {

    int africaUnlocked, asiaUnlocked, europeUnlocked, northAmericaUnlocked, oceaniaUnlocked,
            othersUnlocked, southAmericaUnlocked, unrecognizedCountriesUnlocked;



    TextView europe, asia, africa, northAmerica, southAmerica, oceania, others, unrecognized;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_flags_menu);

        preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        africaUnlocked = 16 + preferences.getInt("AFRICAUNLOCKED", 0);
        asiaUnlocked = 15 + preferences.getInt("ASIAUNLOCKED", 0);
        europeUnlocked = 15 + preferences.getInt("EUROPEUNLOCKED", 0);
        northAmericaUnlocked = 6 + preferences.getInt("NORTHAMERICAUNLOCKED", 0);
        oceaniaUnlocked = 3 + preferences.getInt("OCEANIAUNLOCKED", 0);
        othersUnlocked = preferences.getInt("OTHERSUNLOCKED", 0);
        southAmericaUnlocked = 3 + preferences.getInt("SOUTHAMERICAUNLOCKED", 0);
        unrecognizedCountriesUnlocked = preferences.getInt("UNRECOGNIZEDCOUNTRIESUNLOCKED", 0);

        africa = (TextView) findViewById(R.id.africaButton);
        africa.setText("" + "   " + africaUnlocked + "/44");
        africa.setBackgroundResource(R.drawable.africaunlock);

        asia = (TextView) findViewById(R.id.asiaButton);
        asia.setText("" + "   " + asiaUnlocked + "/42");
        asia.setBackgroundResource(R.drawable.asiaunlock);

        europe = (TextView) findViewById(R.id.europeButton);
        europe.setText("" + "   " + europeUnlocked + "/48");
        europe.setBackgroundResource(R.drawable.europeunlock);

        northAmerica = (TextView) findViewById(R.id.northAmericaButton);
        northAmerica.setText(" " + "   " + northAmericaUnlocked + "/20");
        northAmerica.setBackgroundResource(R.drawable.northamericaunlock);

        oceania = (TextView) findViewById(R.id.oceaniaButton);
        oceania.setText("" + "   " + oceaniaUnlocked + "/12");
        oceania.setBackgroundResource(R.drawable.oceaniaunlock);


        others = (TextView) findViewById(R.id.othersButton);
        others.setText("" + "   " + othersUnlocked + "/50");
        others.setBackgroundResource(R.drawable.othersunlock);

        southAmerica = (TextView) findViewById(R.id.southAmericaButton);
        southAmerica.setText("" + "   " + southAmericaUnlocked + "/11");
        southAmerica.setBackgroundResource(R.drawable.southamericaunlock);

        unrecognized = (TextView) findViewById(R.id.unrecognizedCountriesButton);
        unrecognized.setText("" + "   " + unrecognizedCountriesUnlocked + "/9");
        unrecognized.setBackgroundResource(R.drawable.unrecognizedunlock);
    }



    public void startUnlockEuropeFlagsActivity(View v) {
        Intent intent = new Intent(this, UnlockEuropeFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockAsiaFlagsActivity(View v) {
        Intent intent = new Intent(this, UnlockAsiaFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockAfricaFlagsActivity(View v) {
        Intent intent = new Intent(this, UnlockAfricaFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockNorthAmericaFlagsActivity(View v) {
        Intent intent = new Intent(this, UnlockNorthAmericaFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockSouthAmericaFlagsActivity(View v) {
        Intent intent = new Intent(this, UnlockSouthAmericaFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockOceaniaActivity(View v) {
        Intent intent = new Intent(this, UnlockOceaniaFlagsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockUnrecognizedCountriesActivity(View v) {
        Intent intent = new Intent(this, UnlockUnrecognizedCountriesActivity.class);
        startActivity(intent);
        finish();
    }

    public void startUnlockOthersActivity(View v) {
        Intent intent = new Intent(this, UnlockOthersCountriesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}
