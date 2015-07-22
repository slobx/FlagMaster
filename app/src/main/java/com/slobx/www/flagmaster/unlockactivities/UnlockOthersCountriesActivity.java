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
import com.slobx.www.flagmaster.customlists.OthersCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockOthersCountriesActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    OthersCustomList adapter;
    SharedPreferences preferences2;
    int othersUnlocked;
    boolean othersCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_others);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        othersUnlocked = preferences2.getInt("OTHERSUNLOCKED", 0);

        numberOfStars = preferencesStars.getInt("TOTALSTARS", 0);



        //locked countries
        unlockedFlagImageId.add(0, R.drawable.christmasisland);
        unlockedFlagImageId.add(1, R.drawable.cocosislands);
        unlockedFlagImageId.add(2, R.drawable.norfolkisland);
        unlockedFlagImageId.add(3, R.drawable.easterisland);
        unlockedFlagImageId.add(4, R.drawable.hongkong);
        unlockedFlagImageId.add(5, R.drawable.macau);
        unlockedFlagImageId.add(6, R.drawable.faroeislands);
        unlockedFlagImageId.add(7, R.drawable.greenland);
        unlockedFlagImageId.add(8, R.drawable.alandislands);
        unlockedFlagImageId.add(9, R.drawable.frenchsouthernandantarcticlands);
        unlockedFlagImageId.add(10, R.drawable.frenchpolynesia);
        unlockedFlagImageId.add(11, R.drawable.wallisandfutuna);
        unlockedFlagImageId.add(12, R.drawable.saintbarthelemy);
        unlockedFlagImageId.add(13, R.drawable.saintpierreandmiquelon);
        unlockedFlagImageId.add(14, R.drawable.newcaledonia);
        unlockedFlagImageId.add(15, R.drawable.aruba);
        unlockedFlagImageId.add(16, R.drawable.curacao);
        unlockedFlagImageId.add(17, R.drawable.sintmaarten);
        unlockedFlagImageId.add(18, R.drawable.bonaire);
        unlockedFlagImageId.add(19, R.drawable.sinteustatius);
        unlockedFlagImageId.add(20, R.drawable.saba);
        unlockedFlagImageId.add(21, R.drawable.cookislands);
        unlockedFlagImageId.add(22, R.drawable.niue);
        unlockedFlagImageId.add(23, R.drawable.tokelau);
        unlockedFlagImageId.add(24, R.drawable.alderney);
        unlockedFlagImageId.add(25, R.drawable.guernsey);
        unlockedFlagImageId.add(26, R.drawable.herm);
        unlockedFlagImageId.add(27, R.drawable.isleofman);
        unlockedFlagImageId.add(28, R.drawable.jersey);
        unlockedFlagImageId.add(29, R.drawable.sark);
        unlockedFlagImageId.add(30, R.drawable.anguilla);
        unlockedFlagImageId.add(31, R.drawable.ascensionisland);
        unlockedFlagImageId.add(32, R.drawable.bermuda);
        unlockedFlagImageId.add(33, R.drawable.britishantarcticterritory);
        unlockedFlagImageId.add(34, R.drawable.britishindianoceanterritory);
        unlockedFlagImageId.add(35, R.drawable.britishvirginislands);
        unlockedFlagImageId.add(36, R.drawable.caymanislands);
        unlockedFlagImageId.add(37, R.drawable.falklandislands);
        unlockedFlagImageId.add(38, R.drawable.gibraltar);
        unlockedFlagImageId.add(39, R.drawable.monserrat);
        unlockedFlagImageId.add(40, R.drawable.pitcairnislands);
        unlockedFlagImageId.add(41, R.drawable.sainthelena);
        unlockedFlagImageId.add(42, R.drawable.southgeorgiaandthesouthsandwichislands);
        unlockedFlagImageId.add(43, R.drawable.tristandacunha);
        unlockedFlagImageId.add(44, R.drawable.turksandcaicosislands);
        unlockedFlagImageId.add(45, R.drawable.americansamoa);
        unlockedFlagImageId.add(46, R.drawable.guam);
        unlockedFlagImageId.add(47, R.drawable.northernmarianaislands);
        unlockedFlagImageId.add(48, R.drawable.puertorico);
        unlockedFlagImageId.add(49, R.drawable.usvirginislands);
        unlockedFlagImageId.add(50, R.drawable.questionmark);

        //locked countries
        countryNames.add(0, "Christmas Island");
        countryNames.add(1, "Cocos Islands");
        countryNames.add(2, "Norfolk Island");
        countryNames.add(3, "Easter Island");
        countryNames.add(4, "Hong Kong");
        countryNames.add(5, "Macau");
        countryNames.add(6, "Faroe Islands");
        countryNames.add(7, "Greenland");
        countryNames.add(8, "Åland Islands");
        countryNames.add(9, "French Southern and Antarctic Lands");
        countryNames.add(10, "French Polynesia");
        countryNames.add(11, "Wallis and Futuna");
        countryNames.add(12, "Saint Barthelemy");
        countryNames.add(13, "Saint Pierre and Miquelon");
        countryNames.add(14, "New Caledonia");
        countryNames.add(15, "Aruba");
        countryNames.add(16, "Curaçao");
        countryNames.add(17, "Sint Maarten");
        countryNames.add(18, "Bonaire");
        countryNames.add(19, "Sint Eustatius");
        countryNames.add(20, "Saba");
        countryNames.add(21, "Cook Islands");
        countryNames.add(22, "Niue");
        countryNames.add(23, "Tokelau");
        countryNames.add(24, "Alderney");
        countryNames.add(25, "Guernsey");
        countryNames.add(26, "Herm");
        countryNames.add(27, "Isle of Man");
        countryNames.add(28, "Jersey");
        countryNames.add(29, "Sark");
        countryNames.add(30, "Anguilla");
        countryNames.add(31, "Ascension Island");
        countryNames.add(32, "Bermuda");
        countryNames.add(33, "British Antarctic Territory");
        countryNames.add(34, "British Indian Ocean Territory");
        countryNames.add(35, "British Virgin Islands");
        countryNames.add(36, "Cayman Islands");
        countryNames.add(37, "Falkland Islands");
        countryNames.add(38, "Gibraltar");
        countryNames.add(39, "Montserrat");
        countryNames.add(40, "Pitcairn Islands");
        countryNames.add(41, "Saint Helena");
        countryNames.add(42, "South Georgia and the South Sandwich Islands");
        countryNames.add(43, "Tristan da Cunha");
        countryNames.add(44, "Turks and Caicos Islands");
        countryNames.add(45, "American Samoa");
        countryNames.add(46, "Guam");
        countryNames.add(47, "Northern Mariana Islands");
        countryNames.add(48, "Puerto Rico");
        countryNames.add(49, "U.S. Virgin Islands");





        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("OTHERSUNLOCKED", othersUnlocked);
        editor.commit();
        if (othersUnlocked==50) {
            othersCleared = true;
            editor.putBoolean("OTHERSACLEAREDBOOLEAN", othersCleared);
            editor.commit();
        }




        adapter = new
                OthersCustomList(UnlockOthersCountriesActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.othersList);
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
                                if ((10*(position*3+3)) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("OTHERSBOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("OTHERSUNLOCKED", othersUnlocked);
                                    editor.commit();
                                    if (othersUnlocked==50) {
                                        othersCleared = true;
                                        editor.putBoolean("OTHERSACLEAREDBOOLEAN", othersCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (10*(position*3+3));
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockOthersCountriesActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockOthersCountriesActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockOthersCountriesActivity.this);
                builder.setMessage("Are you sure you want to spend " + (10*(position*3+3)) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();



            }
        });
    }



    public void setWeb(){
        SharedPreferences.Editor customEditor = preferences2.edit();

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"0", true)) {
            web.add(0, "Click here to unlock new flag. Cost 30 stars");
            flagImageId.add(0, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "0", false))
                othersUnlocked++;
            web.add(0, countryNames.get(0));
            flagImageId.add(0, unlockedFlagImageId.get(0));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "0", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"1", true)) {
            web.add(1, "Click here to unlock new flag. Cost 60 stars");
            flagImageId.add(1, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "1", false))
                othersUnlocked++;
            web.add(1, countryNames.get(1));
            flagImageId.add(1, unlockedFlagImageId.get(1));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"1", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"2", true)) {
            web.add(2, "Click here to unlock new flag. Cost 90 stars");
            flagImageId.add(2, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "2", false))
                othersUnlocked++;
            web.add(2, countryNames.get(2));
            flagImageId.add(2, unlockedFlagImageId.get(2));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"2", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"3", true)) {
            web.add(3, "Click here to unlock new flag. Cost 120 stars");
            flagImageId.add(3, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "3", false))
                othersUnlocked++;
            web.add(3, countryNames.get(3));
            flagImageId.add(3, unlockedFlagImageId.get(3));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"3", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"4", true)) {
            web.add(4, "Click here to unlock new flag. Cost 150 stars");
            flagImageId.add(4, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "4", false))
                othersUnlocked++;
            web.add(4, countryNames.get(4));
            flagImageId.add(4, unlockedFlagImageId.get(4));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"4", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"5", true)) {
            web.add(5, "Click here to unlock new flag. Cost 180 stars");
            flagImageId.add(5, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "5", false))
                othersUnlocked++;
            web.add(5, countryNames.get(5));
            flagImageId.add(5, unlockedFlagImageId.get(5));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"5", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"6", true)) {
            web.add(6, "Click here to unlock new flag. Cost 210 stars");
            flagImageId.add(6, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "6", false))
                othersUnlocked++;
            web.add(6, countryNames.get(6));
            flagImageId.add(6, unlockedFlagImageId.get(6));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"6", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"7", true)) {
            web.add(7, "Click here to unlock new flag. Cost 240 stars");
            flagImageId.add(7, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "7", false))
                othersUnlocked++;
            web.add(7, countryNames.get(7));
            flagImageId.add(7, unlockedFlagImageId.get(7));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"7", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("OTHERSBOOLEAN"+"8", true)) {
            web.add(8, "Click here to unlock new flag. Cost 270 stars");
            flagImageId.add(8, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "8", false))
                othersUnlocked++;
            web.add(8, countryNames.get(8));
            flagImageId.add(8, unlockedFlagImageId.get(8));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"8", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"9", true)) {
            web.add(9, "Click here to unlock new flag. Cost 300 stars");
            flagImageId.add(9, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "9", false))
                othersUnlocked++;
            web.add(9, countryNames.get(9));
            flagImageId.add(9, unlockedFlagImageId.get(9));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"9", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"10", true)) {
            web.add(10, "Click here to unlock new flag. Cost 330 stars");
            flagImageId.add(10, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "10", false))
                othersUnlocked++;
            web.add(10, countryNames.get(10));
            flagImageId.add(10, unlockedFlagImageId.get(10));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"10", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"11", true)) {
            web.add(11, "Click here to unlock new flag. Cost 360 stars");
            flagImageId.add(11, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "11", false))
                othersUnlocked++;
            web.add(11, countryNames.get(11));
            flagImageId.add(11, unlockedFlagImageId.get(11));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"11", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"12", true)) {
            web.add(12, "Click here to unlock new flag. Cost 390 stars");
            flagImageId.add(12, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "12", false))
                othersUnlocked++;
            web.add(12, countryNames.get(12));
            flagImageId.add(12, unlockedFlagImageId.get(12));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"12", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"13", true)) {
            web.add(13, "Click here to unlock new flag. Cost 420 stars");
            flagImageId.add(13, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "13", false))
                othersUnlocked++;
            web.add(13, countryNames.get(13));
            flagImageId.add(13, unlockedFlagImageId.get(13));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"13", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"14", true)) {
            web.add(14, "Click here to unlock new flag. Cost 450 stars");
            flagImageId.add(14, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "14", false))
                othersUnlocked++;
            web.add(14, countryNames.get(14));
            flagImageId.add(14, unlockedFlagImageId.get(14));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"14", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"15", true)) {
            web.add(15, "Click here to unlock new flag. Cost 480 stars");
            flagImageId.add(15, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "15", false))
                othersUnlocked++;
            web.add(15, countryNames.get(15));
            flagImageId.add(15, unlockedFlagImageId.get(15));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"15", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"16", true)) {
            web.add(16, "Click here to unlock new flag. Cost 510 stars");
            flagImageId.add(16, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "16", false))
                othersUnlocked++;
            web.add(16, countryNames.get(16));
            flagImageId.add(16, unlockedFlagImageId.get(16));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"16", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"17", true)) {
            web.add(17, "Click here to unlock new flag. Cost 540 stars");
            flagImageId.add(17, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "17", false))
                othersUnlocked++;
            web.add(17, countryNames.get(17));
            flagImageId.add(17, unlockedFlagImageId.get(17));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"17", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"18", true)) {
            web.add(18, "Click here to unlock new flag. Cost 570 stars");
            flagImageId.add(18, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "18", false))
                othersUnlocked++;
            web.add(18, countryNames.get(18));
            flagImageId.add(18, unlockedFlagImageId.get(18));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"18", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"19", true)) {
            web.add(19, "Click here to unlock new flag. Cost 600 stars");
            flagImageId.add(19, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "19", false))
                othersUnlocked++;
            web.add(19, countryNames.get(19));
            flagImageId.add(19, unlockedFlagImageId.get(19));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"19", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"20", true)) {
            web.add(20, "Click here to unlock new flag. Cost 630 stars");
            flagImageId.add(20, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "20", false))
                othersUnlocked++;
            web.add(20, countryNames.get(20));
            flagImageId.add(20, unlockedFlagImageId.get(20));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"20", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"21", true)) {
            web.add(21, "Click here to unlock new flag. Cost 660 stars");
            flagImageId.add(21, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "21", false))
                othersUnlocked++;
            web.add(21, countryNames.get(21));
            flagImageId.add(21, unlockedFlagImageId.get(21));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"21", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"22", true)) {
            web.add(22, "Click here to unlock new flag. Cost 690 stars");
            flagImageId.add(22, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "22", false))
                othersUnlocked++;
            web.add(22, countryNames.get(22));
            flagImageId.add(22, unlockedFlagImageId.get(22));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"22", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"23", true)) {
            web.add(23, "Click here to unlock new flag. Cost 720 stars");
            flagImageId.add(23, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "23", false))
                othersUnlocked++;
            web.add(23, countryNames.get(23));
            flagImageId.add(23, unlockedFlagImageId.get(23));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"23", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"24", true)) {
            web.add(24, "Click here to unlock new flag. Cost 750 stars");
            flagImageId.add(24, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "24", false))
                othersUnlocked++;
            web.add(24, countryNames.get(24));
            flagImageId.add(24, unlockedFlagImageId.get(24));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"24", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"25", true)) {
            web.add(25, "Click here to unlock new flag. Cost 780 stars");
            flagImageId.add(25, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "25", false))
                othersUnlocked++;
            web.add(25, countryNames.get(25));
            flagImageId.add(25, unlockedFlagImageId.get(25));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"25", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"26", true)) {
            web.add(26, "Click here to unlock new flag. Cost 810 stars");
            flagImageId.add(26, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "26", false))
                othersUnlocked++;
            web.add(26, countryNames.get(26));
            flagImageId.add(26, unlockedFlagImageId.get(26));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"26", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"27", true)) {
            web.add(27, "Click here to unlock new flag. Cost 840 stars");
            flagImageId.add(27, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "27", false))
                othersUnlocked++;
            web.add(27, countryNames.get(27));
            flagImageId.add(27, unlockedFlagImageId.get(27));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"27", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"28", true)) {
            web.add(28, "Click here to unlock new flag. Cost 870 stars");
            flagImageId.add(28, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "28", false))
                othersUnlocked++;
            web.add(28, countryNames.get(28));
            flagImageId.add(28, unlockedFlagImageId.get(28));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"28", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"29", true)) {
            web.add(29, "Click here to unlock new flag. Cost 900 stars");
            flagImageId.add(29, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "29", false))
                othersUnlocked++;
            web.add(29, countryNames.get(29));
            flagImageId.add(29, unlockedFlagImageId.get(29));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"29", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"30", true)) {
            web.add(30, "Click here to unlock new flag. Cost 930 stars");
            flagImageId.add(30, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "30", false))
                othersUnlocked++;
            web.add(30, countryNames.get(30));
            flagImageId.add(30, unlockedFlagImageId.get(30));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"30", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"31", true)) {
            web.add(31, "Click here to unlock new flag. Cost 960 stars");
            flagImageId.add(31, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "31", false))
                othersUnlocked++;
            web.add(31, countryNames.get(31));
            flagImageId.add(31, unlockedFlagImageId.get(31));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"31", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"32", true)) {
            web.add(32, "Click here to unlock new flag. Cost 990 stars");
            flagImageId.add(32, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "32", false))
                othersUnlocked++;
            web.add(32, countryNames.get(32));
            flagImageId.add(32, unlockedFlagImageId.get(32));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"32", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"33", true)) {
            web.add(33, "Click here to unlock new flag. Cost 1020 stars");
            flagImageId.add(33, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "33", false))
                othersUnlocked++;
            web.add(33, countryNames.get(33));
            flagImageId.add(33, unlockedFlagImageId.get(33));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"33", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"34", true)) {
            web.add(34, "Click here to unlock new flag. Cost 1050 stars");
            flagImageId.add(34, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "34", false))
                othersUnlocked++;
            web.add(34, countryNames.get(34));
            flagImageId.add(34, unlockedFlagImageId.get(34));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"34", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"35", true)) {
            web.add(35, "Click here to unlock new flag. Cost 1080 stars");
            flagImageId.add(35, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "35", false))
                othersUnlocked++;
            web.add(35, countryNames.get(35));
            flagImageId.add(35, unlockedFlagImageId.get(35));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"35", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"36", true)) {
            web.add(36, "Click here to unlock new flag. Cost 1110 stars");
            flagImageId.add(36, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "36", false))
                othersUnlocked++;
            web.add(36, countryNames.get(36));
            flagImageId.add(36, unlockedFlagImageId.get(36));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"36", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"37", true)) {
            web.add(37, "Click here to unlock new flag. Cost 1140 stars");
            flagImageId.add(37, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "37", false))
                othersUnlocked++;
            web.add(37, countryNames.get(37));
            flagImageId.add(37, unlockedFlagImageId.get(37));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"37", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"38", true)) {
            web.add(38, "Click here to unlock new flag. Cost 1170 stars");
            flagImageId.add(38, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "38", false))
                othersUnlocked++;
            web.add(38, countryNames.get(38));
            flagImageId.add(38, unlockedFlagImageId.get(38));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"38", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"39", true)) {
            web.add(39, "Click here to unlock new flag. Cost 1200 stars");
            flagImageId.add(39, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "39", false))
                othersUnlocked++;
            web.add(39, countryNames.get(39));
            flagImageId.add(39, unlockedFlagImageId.get(39));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"39", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"40", true)) {
            web.add(40, "Click here to unlock new flag. Cost 1230 stars");
            flagImageId.add(40, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "40", false))
                othersUnlocked++;
            web.add(40, countryNames.get(40));
            flagImageId.add(40, unlockedFlagImageId.get(40));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"40", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"41", true)) {
            web.add(41, "Click here to unlock new flag. Cost 1260 stars");
            flagImageId.add(41, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "41", false))
                othersUnlocked++;
            web.add(41, countryNames.get(41));
            flagImageId.add(41, unlockedFlagImageId.get(41));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"41", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"42", true)) {
            web.add(42, "Click here to unlock new flag. Cost 1290 stars");
            flagImageId.add(42, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "42", false))
                othersUnlocked++;
            web.add(42, countryNames.get(42));
            flagImageId.add(42, unlockedFlagImageId.get(42));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"42", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"43", true)) {
            web.add(43, "Click here to unlock new flag. Cost 1320 stars");
            flagImageId.add(43, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "43", false))
                othersUnlocked++;
            web.add(43, countryNames.get(43));
            flagImageId.add(43, unlockedFlagImageId.get(43));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"43", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"44", true)) {
            web.add(44, "Click here to unlock new flag. Cost 1350 stars");
            flagImageId.add(44, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "44", false))
                othersUnlocked++;
            web.add(44, countryNames.get(44));
            flagImageId.add(44, unlockedFlagImageId.get(44));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"44", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"45", true)) {
            web.add(45, "Click here to unlock new flag. Cost 1380 stars");
            flagImageId.add(45, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "45", false))
                othersUnlocked++;
            web.add(45, countryNames.get(45));
            flagImageId.add(45, unlockedFlagImageId.get(45));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"45", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"46", true)) {
            web.add(46, "Click here to unlock new flag. Cost 1410 stars");
            flagImageId.add(46, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "46", false))
                othersUnlocked++;
            web.add(46, countryNames.get(46));
            flagImageId.add(46, unlockedFlagImageId.get(46));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"46", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"47", true)) {
            web.add(47, "Click here to unlock new flag. Cost 1440 stars");
            flagImageId.add(47, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "47", false))
                othersUnlocked++;
            web.add(47, countryNames.get(47));
            flagImageId.add(47, unlockedFlagImageId.get(47));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"47", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"48", true)) {
            web.add(48, "Click here to unlock new flag. Cost 1470 stars");
            flagImageId.add(48, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "48", false))
                othersUnlocked++;
            web.add(48, countryNames.get(48));
            flagImageId.add(48, unlockedFlagImageId.get(48));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"48", true);
            customEditor.commit();
        }
        if (preferences2.getBoolean("OTHERSBOOLEAN"+"49", true)) {
            web.add(49, "Click here to unlock new flag. Cost 1500 stars");
            flagImageId.add(49, unlockedFlagImageId.get(50));
        } else {
            if(!preferences2.getBoolean("OTHERSNEWFLAGBOOLEAN" + "49", false))
                othersUnlocked++;
            web.add(49, countryNames.get(49));
            flagImageId.add(49, unlockedFlagImageId.get(49));
            customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN"+"49", true);
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
