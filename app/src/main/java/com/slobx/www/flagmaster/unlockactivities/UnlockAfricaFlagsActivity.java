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
import com.slobx.www.flagmaster.customlists.AfricaCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentRequest;


public class UnlockAfricaFlagsActivity extends mp.PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    AfricaCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int africaUnlocked;
    boolean africaCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_africa_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

       africaUnlocked = preferences2.getInt("AFRICAUNLOCKED", 0);

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

        Toast toast = Toast.makeText(UnlockAfricaFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();



        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.algeria);
        unlockedFlagImageId.add(1, R.drawable.angola);
        unlockedFlagImageId.add(2, R.drawable.cameroon);
        unlockedFlagImageId.add(3, R.drawable.congo);
        unlockedFlagImageId.add(4, R.drawable.cotedivoire);
        unlockedFlagImageId.add(5, R.drawable.egypt);
        unlockedFlagImageId.add(6, R.drawable.ghana);
        unlockedFlagImageId.add(7, R.drawable.kenya);
        unlockedFlagImageId.add(8, R.drawable.mauritius);
        unlockedFlagImageId.add(9, R.drawable.morocco);
        unlockedFlagImageId.add(10, R.drawable.nigeria);
        unlockedFlagImageId.add(11, R.drawable.senegal);
        unlockedFlagImageId.add(12, R.drawable.tunisia);
        unlockedFlagImageId.add(13, R.drawable.ethiopia);
        unlockedFlagImageId.add(14, R.drawable.benin);
        unlockedFlagImageId.add(15, R.drawable.botswana);
        //locked countries
        unlockedFlagImageId.add(16, R.drawable.burkinafaso);
        unlockedFlagImageId.add(17, R.drawable.burundi);
        unlockedFlagImageId.add(18, R.drawable.capeverde);
        unlockedFlagImageId.add(19, R.drawable.centralafricanrepublic);
        unlockedFlagImageId.add(20, R.drawable.chad);
        unlockedFlagImageId.add(21, R.drawable.comoros);
        unlockedFlagImageId.add(22, R.drawable.equatorialguinea);
        unlockedFlagImageId.add(23, R.drawable.eritrea);
        unlockedFlagImageId.add(24, R.drawable.gabon);
        unlockedFlagImageId.add(25, R.drawable.gambia);
        unlockedFlagImageId.add(26, R.drawable.guinea);
        unlockedFlagImageId.add(27, R.drawable.guineabissau);
        unlockedFlagImageId.add(28, R.drawable.lesotho);
        unlockedFlagImageId.add(29, R.drawable.liberia);
        unlockedFlagImageId.add(30, R.drawable.madagascar);
        unlockedFlagImageId.add(31, R.drawable.malawi);
        unlockedFlagImageId.add(32, R.drawable.mali);
        unlockedFlagImageId.add(33, R.drawable.mauritania);
        unlockedFlagImageId.add(34, R.drawable.mozambique);
        unlockedFlagImageId.add(35, R.drawable.nambia);
        unlockedFlagImageId.add(36, R.drawable.rwanda);
        unlockedFlagImageId.add(37, R.drawable.saotomeandprincipe);
        unlockedFlagImageId.add(38, R.drawable.seychelles);
        unlockedFlagImageId.add(39, R.drawable.sierraleone);
        unlockedFlagImageId.add(40, R.drawable.somalia);
        unlockedFlagImageId.add(41, R.drawable.sudan);
        unlockedFlagImageId.add(42, R.drawable.swaziland);
        unlockedFlagImageId.add(43, R.drawable.tanzania);
        unlockedFlagImageId.add(44, R.drawable.questionmark);


        //unlocked countries
        countryNames.add(0, "Algeria");
        countryNames.add(1, "Angola");
        countryNames.add(2, "Cameroon");
        countryNames.add(3, "Congo");
        countryNames.add(4, "Cote d'Ivoire");
        countryNames.add(5, "Egypt");
        countryNames.add(6, "Ghana");
        countryNames.add(7, "Kenya");
        countryNames.add(8, "Mauritius");
        countryNames.add(9, "Morocco");
        countryNames.add(10, "Nigeria");
        countryNames.add(11, "Senegal");
        countryNames.add(12, "Tunisia");
        countryNames.add(13, "Ethiopia");
        countryNames.add(14, "Benin");
        countryNames.add(15, "Botswana");
        //locked countries
        countryNames.add(16, "Burkina Faso");
        countryNames.add(17, "Burundi");
        countryNames.add(18, "Cape Verde");
        countryNames.add(19, "Central African Republic");
        countryNames.add(20, "Chad");
        countryNames.add(21, "Comoros");
        countryNames.add(22, "Equatorial Guinea");
        countryNames.add(23, "Eritrea");
        countryNames.add(24, "Gabon");
        countryNames.add(25, "Gambia");
        countryNames.add(26, "Guinea");
        countryNames.add(27, "Guinea Bissau");
        countryNames.add(28, "Lesotho");
        countryNames.add(29, "Liberia");
        countryNames.add(30, "Madagascar");
        countryNames.add(31, "Malawi");
        countryNames.add(32, "Mali");
        countryNames.add(33, "Mauritania");
        countryNames.add(34, "Mozambique");
        countryNames.add(35, "Namibia");
        countryNames.add(36, "Rwanda");
        countryNames.add(37, "Sao Tome and Principe");
        countryNames.add(38, "Seychelles");
        countryNames.add(39, "Sierra Leone");
        countryNames.add(40, "Somalia");
        countryNames.add(41, "Sudan");
        countryNames.add(42, "Swaziland");
        countryNames.add(43, "Tanzania");


        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("AFRICAUNLOCKED", africaUnlocked);
        editor.commit();
        if (africaUnlocked==28) {
            africaCleared = true;
            editor.putBoolean("AFRICACLEAREDBOOLEAN", africaCleared);
            editor.commit();
        }

        adapter = new
                AfricaCustomList(UnlockAfricaFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.africaList);
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
                                if ((((position + 1) * 2) -32) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("AFRICABOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("AFRICAUNLOCKED", africaUnlocked);
                                    editor.commit();
                                    if (africaUnlocked==28) {
                                        africaCleared = true;
                                        editor.putBoolean("AFRICACLEAREDBOOLEAN", africaCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (((position + 1) * 2) - 32);
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockAfricaFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockAfricaFlagsActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();

                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockAfricaFlagsActivity.this);
                builder.setMessage("Are you sure you want to spend " + (((position + 1) * 2) -32) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
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

        web.add(6, countryNames.get(6));
        flagImageId.add(6, unlockedFlagImageId.get(6));

        web.add(7, countryNames.get(7));
        flagImageId.add(7, unlockedFlagImageId.get(7));

        web.add(8, countryNames.get(8));
        flagImageId.add(8, unlockedFlagImageId.get(8));

        web.add(9, countryNames.get(9));
        flagImageId.add(9, unlockedFlagImageId.get(9));

        web.add(10, countryNames.get(10));
        flagImageId.add(10, unlockedFlagImageId.get(10));

        web.add(11, countryNames.get(11));
        flagImageId.add(11, unlockedFlagImageId.get(11));

        web.add(12, countryNames.get(12));
        flagImageId.add(12, unlockedFlagImageId.get(12));

        web.add(13, countryNames.get(13));
        flagImageId.add(13, unlockedFlagImageId.get(13));

        web.add(14, countryNames.get(14));
        flagImageId.add(14, unlockedFlagImageId.get(14));

        web.add(15, countryNames.get(15));
        flagImageId.add(15, unlockedFlagImageId.get(15));

        if (preferences2.getBoolean("AFRICABOOLEAN"+"16", true)) {
            web.add(16, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(16, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "16", false))
                africaUnlocked++;
            web.add(16, countryNames.get(16));
            flagImageId.add(16, unlockedFlagImageId.get(16));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "16", true);
            customEditor.commit();

        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"17", true)) {
            web.add(17, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(17, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "17", false))
                africaUnlocked++;
            web.add(17, countryNames.get(17));
            flagImageId.add(17, unlockedFlagImageId.get(17));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"17", true);
            customEditor.commit();

        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"18", true)) {
            web.add(18, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(18, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "18", false))
                africaUnlocked++;
            web.add(18, countryNames.get(18));
            flagImageId.add(18, unlockedFlagImageId.get(18));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"18", true);
            customEditor.commit();

        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"19", true)) {
            web.add(19, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(19, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "19", false))
                africaUnlocked++;
            web.add(19, countryNames.get(19));
            flagImageId.add(19, unlockedFlagImageId.get(19));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"19", true);
            customEditor.commit();

        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"20", true)) {
            web.add(20, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(20, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "20", false))
                africaUnlocked++;
            web.add(20, countryNames.get(20));
            flagImageId.add(20, unlockedFlagImageId.get(20));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"20", true);
            customEditor.commit();

        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"21", true)) {
            web.add(21, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(21, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "21", false))
                africaUnlocked++;
            web.add(21, countryNames.get(21));
            flagImageId.add(21, unlockedFlagImageId.get(21));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"21", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"22", true)) {
            web.add(22, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(22, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "22", false))
                africaUnlocked++;
            web.add(22, countryNames.get(22));
            flagImageId.add(22, unlockedFlagImageId.get(22));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"22", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"23", true)) {
            web.add(23, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(23, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "23", false))
                africaUnlocked++;
            web.add(23, countryNames.get(23));
            flagImageId.add(23, unlockedFlagImageId.get(23));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"23", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"24", true)) {
            web.add(24, "Click here to unlock new flag. Cost 18 stars");
            flagImageId.add(24, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "24", false))
                africaUnlocked++;
            web.add(24, countryNames.get(24));
            flagImageId.add(24, unlockedFlagImageId.get(24));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"24", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"25", true)) {
            web.add(25, "Click here to unlock new flag. Cost 20 stars");
            flagImageId.add(25, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "25", false))
                africaUnlocked++;
            web.add(25, countryNames.get(25));
            flagImageId.add(25, unlockedFlagImageId.get(25));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"25", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"26", true)) {
            web.add(26, "Click here to unlock new flag. Cost 22 stars");
            flagImageId.add(26, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "26", false))
                africaUnlocked++;
            web.add(26, countryNames.get(26));
            flagImageId.add(26, unlockedFlagImageId.get(26));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"26", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"27", true)) {
            web.add(27, "Click here to unlock new flag. Cost 24 stars");
            flagImageId.add(27, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "27", false))
                africaUnlocked++;
            web.add(27, countryNames.get(27));
            flagImageId.add(27, unlockedFlagImageId.get(27));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"27", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"28", true)) {
            web.add(28, "Click here to unlock new flag. Cost 26 stars");
            flagImageId.add(28, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "28", false))
                africaUnlocked++;
            web.add(28, countryNames.get(28));
            flagImageId.add(28, unlockedFlagImageId.get(28));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"28", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"29", true)) {
            web.add(29, "Click here to unlock new flag. Cost 28 stars");
            flagImageId.add(29, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "29", false))
                africaUnlocked++;
            web.add(29, countryNames.get(29));
            flagImageId.add(29, unlockedFlagImageId.get(29));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"29", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"30", true)) {
            web.add(30, "Click here to unlock new flag. Cost 30 stars");
            flagImageId.add(30, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "30", false))
                africaUnlocked++;
            web.add(30, countryNames.get(30));
            flagImageId.add(30, unlockedFlagImageId.get(30));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"30", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"31", true)) {
            web.add(31, "Click here to unlock new flag. Cost 32 stars");
            flagImageId.add(31, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "31", false))
                africaUnlocked++;
            web.add(31, countryNames.get(31));
            flagImageId.add(31, unlockedFlagImageId.get(31));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"31", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"32", true)) {
            web.add(32, "Click here to unlock new flag. Cost 34 stars");
            flagImageId.add(32, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "32", false))
                africaUnlocked++;
            web.add(32, countryNames.get(32));
            flagImageId.add(32, unlockedFlagImageId.get(32));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"32", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"33", true)) {
            web.add(33, "Click here to unlock new flag. Cost 36 stars");
            flagImageId.add(33, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "33", false))
                africaUnlocked++;
            web.add(33, countryNames.get(33));
            flagImageId.add(33, unlockedFlagImageId.get(33));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"33", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"34", true)) {
            web.add(34, "Click here to unlock new flag. Cost 38 stars");
            flagImageId.add(34, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "34", false))
                africaUnlocked++;
            web.add(34, countryNames.get(34));
            flagImageId.add(34, unlockedFlagImageId.get(34));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"34", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"35", true)) {
            web.add(35, "Click here to unlock new flag. Cost 40 stars");
            flagImageId.add(35, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "35", false))
                africaUnlocked++;
            web.add(35, countryNames.get(35));
            flagImageId.add(35, unlockedFlagImageId.get(35));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"35", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"36", true)) {
            web.add(36, "Click here to unlock new flag. Cost 42 stars");
            flagImageId.add(36, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "36", false))
                africaUnlocked++;
            web.add(36, countryNames.get(36));
            flagImageId.add(36, unlockedFlagImageId.get(36));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"36", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"37", true)) {
            web.add(37, "Click here to unlock new flag. Cost 44 stars");
            flagImageId.add(37, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "37", false))
                africaUnlocked++;
            web.add(37, countryNames.get(37));
            flagImageId.add(37, unlockedFlagImageId.get(37));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"37", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"38", true)) {
            web.add(38, "Click here to unlock new flag. Cost 46 stars");
            flagImageId.add(38, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "38", false))
                africaUnlocked++;
            web.add(38, countryNames.get(38));
            flagImageId.add(38, unlockedFlagImageId.get(38));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"38", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"39", true)) {
            web.add(39, "Click here to unlock new flag. Cost 48 stars");
            flagImageId.add(39, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "39", false))
                africaUnlocked++;
            web.add(39, countryNames.get(39));
            flagImageId.add(39, unlockedFlagImageId.get(39));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"39", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"40", true)) {
            web.add(40, "Click here to unlock new flag. Cost 50 stars");
            flagImageId.add(40, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "40", false))
                africaUnlocked++;
            web.add(40, countryNames.get(40));
            flagImageId.add(40, unlockedFlagImageId.get(40));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"40", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"41", true)) {
            web.add(41, "Click here to unlock new flag. Cost 52 stars");
            flagImageId.add(41, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "41", false))
                africaUnlocked++;
            web.add(41, countryNames.get(41));
            flagImageId.add(41, unlockedFlagImageId.get(41));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"41", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"42", true)) {
            web.add(42, "Click here to unlock new flag. Cost 54 stars");
            flagImageId.add(42, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "42", false))
                africaUnlocked++;
            web.add(42, countryNames.get(42));
            flagImageId.add(42, unlockedFlagImageId.get(42));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"42", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("AFRICABOOLEAN"+"43", true)) {
            web.add(43, "Click here to unlock new flag. Cost 56 stars");
            flagImageId.add(43, unlockedFlagImageId.get(44));
        } else {
            if(!preferences2.getBoolean("AFRICANEWFLAGBOOLEAN" + "43", false))
                africaUnlocked++;
            web.add(43, countryNames.get(43));
            flagImageId.add(43, unlockedFlagImageId.get(43));
            customEditor.putBoolean("AFRICANEWFLAGBOOLEAN"+"43", true);
            customEditor.commit();
        }



    }

    public void onBackPressed() {

        Intent intent = new Intent(this, UnlockFlagsMenuActivity.class);
        startActivity(intent);
        finish();

    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        numberOfStars = Wallet.getCredits(this);
    }
}
