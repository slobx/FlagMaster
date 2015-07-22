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
import com.slobx.www.flagmaster.customlists.OceaniaCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockOceaniaFlagsActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    OceaniaCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int oceaniaUnlocked;
    boolean oceaniaCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_oceania_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        oceaniaUnlocked = preferences2.getInt("OCEANIAUNLOCKED", 0);

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

        Toast toast = Toast.makeText(UnlockOceaniaFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();



        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.australia);
        unlockedFlagImageId.add(1, R.drawable.papuanewguinea);
        unlockedFlagImageId.add(2, R.drawable.newzealand);
        //locked countries
        unlockedFlagImageId.add(3, R.drawable.fiji);
        unlockedFlagImageId.add(4, R.drawable.solomonislands);
        unlockedFlagImageId.add(5, R.drawable.vanuatu);
        unlockedFlagImageId.add(6, R.drawable.samoa);
        unlockedFlagImageId.add(7, R.drawable.kiribati);
        unlockedFlagImageId.add(8, R.drawable.tonga);
        unlockedFlagImageId.add(9, R.drawable.marshallislands);
        unlockedFlagImageId.add(10, R.drawable.palau);
        unlockedFlagImageId.add(11, R.drawable.nauru);
        unlockedFlagImageId.add(12, R.drawable.questionmark);

        //unlocked countries
        countryNames.add(0, "Australia");
        countryNames.add(1, "Papua New Guinea");
        countryNames.add(2, "New Zealand");
        //locked countries
        countryNames.add(3, "Fiji");
        countryNames.add(4, "Solomon Islands");
        countryNames.add(5, "Vanuatu");
        countryNames.add(6, "Samoa");
        countryNames.add(7, "Kiribati");
        countryNames.add(8, "Tonga");
        countryNames.add(9, "Marshall Islands");
        countryNames.add(10, "Palau");
        countryNames.add(11, "Nauru");

        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("OCEANIAUNLOCKED", oceaniaUnlocked);
        editor.commit();
        if (oceaniaUnlocked==9) {
            oceaniaCleared = true;
            editor.putBoolean("OCEANIACLEAREDBOOLEAN", oceaniaCleared);
            editor.commit();
        }



        adapter = new
                OceaniaCustomList(UnlockOceaniaFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.oceaniaList);
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
                                if ((((position + 1) * 2) - 6) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("OCEANIABOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("OCEANIAUNLOCKED", oceaniaUnlocked);
                                    editor.commit();
                                    if (oceaniaUnlocked==9) {
                                        oceaniaCleared = true;
                                        editor.putBoolean("OCEANIACLEAREDBOOLEAN", oceaniaCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (((position + 1) * 2) - 6);
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockOceaniaFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockOceaniaFlagsActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockOceaniaFlagsActivity.this);
                builder.setMessage("Are you sure you want to spend " + (((position + 1) * 2) - 6)+ " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
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

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"3", true)) {
            web.add(3, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(3, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "3", false))
                oceaniaUnlocked++;
            web.add(3, countryNames.get(3));
            flagImageId.add(3, unlockedFlagImageId.get(3));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "3", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"4", true)) {
            web.add(4, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(4, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "4", false))
                oceaniaUnlocked++;
            web.add(4, countryNames.get(4));
            flagImageId.add(4, unlockedFlagImageId.get(4));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"4", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"5", true)) {
            web.add(5, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(5, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "5", false))
                oceaniaUnlocked++;
            web.add(5, countryNames.get(5));
            flagImageId.add(5, unlockedFlagImageId.get(5));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"5", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"6", true)) {
            web.add(6, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(6, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "6", false))
                oceaniaUnlocked++;
            web.add(6, countryNames.get(6));
            flagImageId.add(6, unlockedFlagImageId.get(6));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"6", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"7", true)) {
            web.add(7, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(7, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "7", false))
                oceaniaUnlocked++;
            web.add(7, countryNames.get(7));
            flagImageId.add(7, unlockedFlagImageId.get(7));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"7", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"8", true)) {
            web.add(8, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(8, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "8", false))
                oceaniaUnlocked++;
            web.add(8, countryNames.get(8));
            flagImageId.add(8, unlockedFlagImageId.get(8));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"8", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"9", true)) {
            web.add(9, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(9, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "9", false))
                oceaniaUnlocked++;
            web.add(9, countryNames.get(9));
            flagImageId.add(9, unlockedFlagImageId.get(9));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"9", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"10", true)) {
            web.add(10, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(10, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "10", false))
                oceaniaUnlocked++;
            web.add(10, countryNames.get(10));
            flagImageId.add(10, unlockedFlagImageId.get(10));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"10", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OCEANIABOOLEAN"+"11", true)) {
            web.add(11, "Click here to unlock new flag. Cost 18 stars");
            flagImageId.add(11, unlockedFlagImageId.get(12));
        } else {
            if(!preferences2.getBoolean("OCEANIANEWFLAGBOOLEAN" + "11", false))
                oceaniaUnlocked++;
            web.add(11, countryNames.get(11));
            flagImageId.add(11, unlockedFlagImageId.get(11));
            customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN"+"11", true);
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
