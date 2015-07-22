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
import com.slobx.www.flagmaster.customlists.SouthAmericaCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockSouthAmericaFlagsActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    SouthAmericaCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int southAmericaUnlocked;
    boolean southAmericaCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_south_america_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        southAmericaUnlocked = preferences2.getInt("SOUTHAMERICAUNLOCKED", 0);

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

        Toast toast = Toast.makeText(UnlockSouthAmericaFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();

        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.argentina);
        unlockedFlagImageId.add(1, R.drawable.brazil);
        unlockedFlagImageId.add(2, R.drawable.colombia);
        //locked countries
        unlockedFlagImageId.add(3, R.drawable.bolivia);
        unlockedFlagImageId.add(4, R.drawable.chile);
        unlockedFlagImageId.add(5, R.drawable.ecuador);
        unlockedFlagImageId.add(6, R.drawable.guyana);
        unlockedFlagImageId.add(7, R.drawable.paraguay);
        unlockedFlagImageId.add(8, R.drawable.peru);
        unlockedFlagImageId.add(9, R.drawable.uruguay);
        unlockedFlagImageId.add(10, R.drawable.venezuela);
        unlockedFlagImageId.add(11, R.drawable.questionmark);

        //unlocked countries
        countryNames.add(0, "Argentina");
        countryNames.add(1, "Brazil");
        countryNames.add(2, "Colombia");
        //locked countries
        countryNames.add(3, "Bolivia");
        countryNames.add(4, "Chile");
        countryNames.add(5, "Ecuador");
        countryNames.add(6, "Guyana");
        countryNames.add(7, "Paraguay");
        countryNames.add(8, "Peru");
        countryNames.add(9, "Uruguay");
        countryNames.add(10, "Venezuela");


        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("SOUTHAMERICAUNLOCKED", southAmericaUnlocked);
        editor.commit();
        if (southAmericaUnlocked==8) {
            southAmericaCleared = true;
            editor.putBoolean("SOUTHAMERICACLEAREDBOOLEAN", southAmericaCleared);
            editor.commit();
        }



        adapter = new
                SouthAmericaCustomList(UnlockSouthAmericaFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.southAmericaList);
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
                                if ((((position + 1) * 2) -6) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("SOUTHAMERICABOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("SOUTHAMERICAUNLOCKED", southAmericaUnlocked);
                                    editor.commit();
                                    if (southAmericaUnlocked==8) {
                                        southAmericaCleared = true;
                                        editor.putBoolean("SOUTHAMERICACLEAREDBOOLEAN", southAmericaCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (((position + 1) * 2) - 6);
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockSouthAmericaFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockSouthAmericaFlagsActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockSouthAmericaFlagsActivity.this);
                builder.setMessage("Are you sure you want to spend " + (((position + 1) * 2) - 6) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
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

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"3", true)) {
            web.add(3, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(3, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "3", false))
                southAmericaUnlocked++;
            web.add(3, countryNames.get(3));
            flagImageId.add(3, unlockedFlagImageId.get(3));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "3", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"4", true)) {
            web.add(4, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(4, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "4", false))
                southAmericaUnlocked++;
            web.add(4, countryNames.get(4));
            flagImageId.add(4, unlockedFlagImageId.get(4));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"4", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"5", true)) {
            web.add(5, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(5, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "5", false))
                southAmericaUnlocked++;
            web.add(5, countryNames.get(5));
            flagImageId.add(5, unlockedFlagImageId.get(5));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"5", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"6", true)) {
            web.add(6, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(6, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "6", false))
                southAmericaUnlocked++;
            web.add(6, countryNames.get(6));
            flagImageId.add(6, unlockedFlagImageId.get(6));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"6", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"7", true)) {
            web.add(7, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(7, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "7", false))
                southAmericaUnlocked++;
            web.add(7, countryNames.get(7));
            flagImageId.add(7, unlockedFlagImageId.get(7));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"7", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"8", true)) {
            web.add(8, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(8, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "8", false))
                southAmericaUnlocked++;
            web.add(8, countryNames.get(8));
            flagImageId.add(8, unlockedFlagImageId.get(8));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"8", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"9", true)) {
            web.add(9, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(9, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "9", false))
                southAmericaUnlocked++;
            web.add(9, countryNames.get(9));
            flagImageId.add(9, unlockedFlagImageId.get(9));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"9", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("SOUTHAMERICABOOLEAN"+"10", true)) {
            web.add(10, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(10, unlockedFlagImageId.get(11));
        } else {
            if(!preferences2.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "10", false))
                southAmericaUnlocked++;
            web.add(10, countryNames.get(10));
            flagImageId.add(10, unlockedFlagImageId.get(10));
            customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"10", true);
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
