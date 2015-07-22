package com.slobx.www.flagmaster.unlockactivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.slobx.www.flagmaster.R;
import com.slobx.www.flagmaster.UnlockFlagsMenuActivity;
import com.slobx.www.flagmaster.Wallet;
import com.slobx.www.flagmaster.customlists.NorthAmericaCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockNorthAmericaFlagsActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    NorthAmericaCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int northAmericaUnlocked;
    boolean northAmericaCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_north_america_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        northAmericaUnlocked = preferences2.getInt("NORTHAMERICAUNLOCKED", 0);

        numberOfStars = preferencesStars.getInt("TOTALSTARS", 0);

        booleanFor10 = preferences2.getBoolean("BOOLEANFOR10", true);
        if (booleanFor10){
            numberOfStars = numberOfStars + preferences2.getInt("TOTALSTARS1", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            editor.putBoolean("BOOLEANFOR10", false);
            editor.commit();
        }

        booleanFor25 = preferences2.getBoolean("BOOLEANFOR25", true);
        if (booleanFor25){
            numberOfStars = numberOfStars + preferences2.getInt("TOTALSTARS2", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            editor.putBoolean("BOOLEANFOR25", false);
            editor.commit();
        }

        Toast toast = Toast.makeText(UnlockNorthAmericaFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();


        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.unitedstates);
        unlockedFlagImageId.add(1, R.drawable.bahama);
        unlockedFlagImageId.add(2, R.drawable.mexico);
        unlockedFlagImageId.add(3, R.drawable.costarica);
        unlockedFlagImageId.add(4, R.drawable.cuba);
        unlockedFlagImageId.add(5, R.drawable.jamaica);
        //locked countries
        unlockedFlagImageId.add(6, R.drawable.barbados);
        unlockedFlagImageId.add(7, R.drawable.trinidadandtobago);
        unlockedFlagImageId.add(8, R.drawable.antiguaandbarbuda);
        unlockedFlagImageId.add(9, R.drawable.panama);
        unlockedFlagImageId.add(10, R.drawable.dominica);
        unlockedFlagImageId.add(11, R.drawable.grenada);
        unlockedFlagImageId.add(12, R.drawable.saintlucia);
        unlockedFlagImageId.add(13, R.drawable.stvincentandgrenadines);
        unlockedFlagImageId.add(14, R.drawable.belize);
        unlockedFlagImageId.add(15, R.drawable.elsalvador);
        unlockedFlagImageId.add(16, R.drawable.guatemala);
        unlockedFlagImageId.add(17, R.drawable.honduras);
        unlockedFlagImageId.add(18, R.drawable.nicaragua);
        unlockedFlagImageId.add(19, R.drawable.haiti);
        unlockedFlagImageId.add(20, R.drawable.questionmark);

        //unlocked countries
        countryNames.add(0, "United States of America");
        countryNames.add(1, "Bahamas");
        countryNames.add(2, "Mexico");
        countryNames.add(3, "Costa Rica");
        countryNames.add(4, "Cuba");
        countryNames.add(5, "Jamaica");
        //locked countries
        countryNames.add(6, "Barbados");
        countryNames.add(7, "Trinidad and Tobago");
        countryNames.add(8, "Antigua and Barbuda");
        countryNames.add(9, "Panama");
        countryNames.add(10, "Dominica");
        countryNames.add(11, "Grenada");
        countryNames.add(12, "Saint Lucia");
        countryNames.add(13, "Saint Vincent and the Grenadines");
        countryNames.add(14, "Belize");
        countryNames.add(15, "El Salvador");
        countryNames.add(16, "Guatemala");
        countryNames.add(17, "Honduras");
        countryNames.add(18, "Nicaragua");
        countryNames.add(19, "Haiti");



        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("NORTHAMERICAUNLOCKED", northAmericaUnlocked);
        editor.commit();
        if (northAmericaUnlocked==14) {
            northAmericaCleared = true;
            editor.putBoolean("NORTHAMERICACLEAREDBOOLEAN", northAmericaCleared);
            editor.commit();
        }


        adapter = new
                NorthAmericaCustomList(UnlockNorthAmericaFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.northAmericaList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    final int position, long id) {


                final SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if ((((position + 1) * 2) - 12) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("NORTHAMERICABOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("NORTHAMERICAUNLOCKED", northAmericaUnlocked);
                                    editor.commit();
                                    if (northAmericaUnlocked==14) {
                                        northAmericaCleared = true;
                                        editor.putBoolean("NORTHAMERICACLEAREDBOOLEAN", northAmericaCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (((position + 1) * 2) -12);
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockNorthAmericaFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
                                    toast.show();

                                }else {

                                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
                                                    builder.setService("b95c10b4e3bdf5d12ed7cc2741db5656", "3769b7c1775ca856e1927fdc026a442f");
                                                    builder.setDisplayString("Stars");      // shown on user receipt
                                                    builder.setProductName("Stars");  // non-consumable purchases are restored using this value
                                                    builder.setType(MpUtils.PRODUCT_TYPE_CONSUMABLE);              // non-consumable items can be later restored
                                                    builder.setIcon(R.drawable.ic_launcher);
                                                    PaymentRequest pr = builder.build();
                                                    makePayment(pr);


                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockNorthAmericaFlagsActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockNorthAmericaFlagsActivity.this);
                builder.setMessage("Are you sure you want to spend " + (((position + 1) * 2) - 12) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();



            }
        });
    }



    public void setWeb(){
        SharedPreferences.Editor customEditor = preferences2.edit();

        web.add(0, countryNames.get(0));
        flagImageId.add(0, unlockedFlagImageId.get(0));

        web.add(1, countryNames.get(1));
        flagImageId.add(1, unlockedFlagImageId.get(1));

        web.add(2, countryNames.get(2));
        flagImageId.add(2, unlockedFlagImageId.get(2));

        web.add(3, countryNames.get(3));
        flagImageId.add(3, unlockedFlagImageId.get(3));

        web.add(4, countryNames.get(4));
        flagImageId.add(4, unlockedFlagImageId.get(4));

        web.add(5, countryNames.get(5));
        flagImageId.add(5, unlockedFlagImageId.get(5));

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"6", true)) {
            web.add(6, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(6, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "6", false))
                northAmericaUnlocked++;
            web.add(6, countryNames.get(6));
            flagImageId.add(6, unlockedFlagImageId.get(6));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "6", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"7", true)) {
            web.add(7, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(7, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "7", false))
                northAmericaUnlocked++;
            web.add(7, countryNames.get(7));
            flagImageId.add(7, unlockedFlagImageId.get(7));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"7", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"8", true)) {
            web.add(8, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(8, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "8", false))
                northAmericaUnlocked++;
            web.add(8, countryNames.get(8));
            flagImageId.add(8, unlockedFlagImageId.get(8));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"8", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"9", true)) {
            web.add(9, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(9, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "9", false))
                northAmericaUnlocked++;
            web.add(9, countryNames.get(9));
            flagImageId.add(9, unlockedFlagImageId.get(9));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"9", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"10", true)) {
            web.add(10, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(10, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "10", false))
                northAmericaUnlocked++;
            web.add(10, countryNames.get(10));
            flagImageId.add(10, unlockedFlagImageId.get(10));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"10", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"11", true)) {
            web.add(11, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(11, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "11", false))
                northAmericaUnlocked++;
            web.add(11, countryNames.get(11));
            flagImageId.add(11, unlockedFlagImageId.get(11));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"11", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"12", true)) {
            web.add(12, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(12, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "12", false))
                northAmericaUnlocked++;
            web.add(12, countryNames.get(12));
            flagImageId.add(12, unlockedFlagImageId.get(12));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"12", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"13", true)) {
            web.add(13, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(13, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "13", false))
                northAmericaUnlocked++;
            web.add(13, countryNames.get(13));
            flagImageId.add(13, unlockedFlagImageId.get(13));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"13", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"14", true)) {
            web.add(14, "Click here to unlock new flag. Cost 18 stars");
            flagImageId.add(14, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "14", false))
                northAmericaUnlocked++;
            web.add(14, countryNames.get(14));
            flagImageId.add(14, unlockedFlagImageId.get(14));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"14", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"15", true)) {
            web.add(15, "Click here to unlock new flag. Cost 20 stars");
            flagImageId.add(15, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "15", false))
                northAmericaUnlocked++;
            web.add(15, countryNames.get(15));
            flagImageId.add(15, unlockedFlagImageId.get(15));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"15", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"16", true)) {
            web.add(16, "Click here to unlock new flag. Cost 22 stars");
            flagImageId.add(16, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "16", false))
                northAmericaUnlocked++;
            web.add(16, countryNames.get(16));
            flagImageId.add(16, unlockedFlagImageId.get(16));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"16", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"17", true)) {
            web.add(17, "Click here to unlock new flag. Cost 24 stars");
            flagImageId.add(17, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "17", false))
                northAmericaUnlocked++;
            web.add(17, countryNames.get(17));
            flagImageId.add(17, unlockedFlagImageId.get(17));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"17", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"18", true)) {
            web.add(18, "Click here to unlock new flag. Cost 26 stars");
            flagImageId.add(18, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "18", false))
                northAmericaUnlocked++;
            web.add(18, countryNames.get(18));
            flagImageId.add(18, unlockedFlagImageId.get(18));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"18", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("NORTHAMERICABOOLEAN"+"19", true)) {
            web.add(19, "Click here to unlock new flag. Cost 28 stars");
            flagImageId.add(19, unlockedFlagImageId.get(20));
        } else {
            if(!preferences2.getBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "19", false))
                northAmericaUnlocked++;
            web.add(19, countryNames.get(19));
            flagImageId.add(19, unlockedFlagImageId.get(19));
            customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"19", true);
            customEditor.commit();
        }

    }

    public void onBackPressed() {

        Intent intent = new Intent(this, UnlockFlagsMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        numberOfStars = Wallet.getCredits(this);
    }


}
