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
import com.slobx.www.flagmaster.customlists.AsiaCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockAsiaFlagsActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    AsiaCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int asiaUnlocked;
    boolean asiaCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_asia_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        asiaUnlocked = preferences2.getInt("ASIAUNLOCKED", 0);

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

        Toast toast = Toast.makeText(UnlockAsiaFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();





        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.afghanistan);
        unlockedFlagImageId.add(1, R.drawable.china);
        unlockedFlagImageId.add(2, R.drawable.india);
        unlockedFlagImageId.add(3, R.drawable.indonesia);
        unlockedFlagImageId.add(4, R.drawable.islamicrepublicofiran);
        unlockedFlagImageId.add(5, R.drawable.iraq);
        unlockedFlagImageId.add(6, R.drawable.israel);
        unlockedFlagImageId.add(7, R.drawable.japan);
        unlockedFlagImageId.add(8, R.drawable.malaysia);
        unlockedFlagImageId.add(9, R.drawable.democraticpeoplesrepublicofkorea);
        unlockedFlagImageId.add(10, R.drawable.philippines);
        unlockedFlagImageId.add(11, R.drawable.qatar);
        unlockedFlagImageId.add(12, R.drawable.saudiarabia);
        unlockedFlagImageId.add(13, R.drawable.republicofkorea);
        unlockedFlagImageId.add(14, R.drawable.unitedarabemirates);
        //locked countries
        unlockedFlagImageId.add(15, R.drawable.azerbaijan);
        unlockedFlagImageId.add(16, R.drawable.bahrain);
        unlockedFlagImageId.add(17, R.drawable.bangladesh);
        unlockedFlagImageId.add(18, R.drawable.bruneidarussalam);
        unlockedFlagImageId.add(19, R.drawable.cambodia);
        unlockedFlagImageId.add(20, R.drawable.armenia);
        unlockedFlagImageId.add(21, R.drawable.bhutan);
        unlockedFlagImageId.add(22, R.drawable.jordan);
        unlockedFlagImageId.add(23, R.drawable.kuwait);
        unlockedFlagImageId.add(24, R.drawable.kyrgyzstan);
        unlockedFlagImageId.add(25, R.drawable.laopeoplesdemocraticrepublic);
        unlockedFlagImageId.add(26, R.drawable.lebanon);
        unlockedFlagImageId.add(27, R.drawable.maldives);
        unlockedFlagImageId.add(28, R.drawable.mongolia);
        unlockedFlagImageId.add(29, R.drawable.myanmar);
        unlockedFlagImageId.add(30, R.drawable.nepal);
        unlockedFlagImageId.add(31, R.drawable.oman);
        unlockedFlagImageId.add(32, R.drawable.pakistan);
        unlockedFlagImageId.add(33, R.drawable.singapore);
        unlockedFlagImageId.add(34, R.drawable.srilanka);
        unlockedFlagImageId.add(35, R.drawable.syrianarabrepublic);
        unlockedFlagImageId.add(36, R.drawable.tajikistan);
        unlockedFlagImageId.add(37, R.drawable.thailand);
        unlockedFlagImageId.add(38, R.drawable.turkmenistan);
        unlockedFlagImageId.add(39, R.drawable.uzbekistan);
        unlockedFlagImageId.add(40, R.drawable.vietnam);
        unlockedFlagImageId.add(41, R.drawable.yemen);
        unlockedFlagImageId.add(42, R.drawable.questionmark);



        //unlocked countries
        countryNames.add(0, "Afghanistan");
        countryNames.add(1, "China");
        countryNames.add(2, "India");
        countryNames.add(3, "Indonesia");
        countryNames.add(4, "Iran");
        countryNames.add(5, "Iraq");
        countryNames.add(6, "Israel");
        countryNames.add(7, "Japan");
        countryNames.add(8, "Malaysia");
        countryNames.add(9, "Democratic People's Republic of Korea");
        countryNames.add(10, "Philippines");
        countryNames.add(11, "Qatar");
        countryNames.add(12, "Saudi Arabia");
        countryNames.add(13, "Republic of Korea");
        countryNames.add(14, "United Arab Emirates");
        //locked countries
        countryNames.add(15, "Azerbaijan");
        countryNames.add(16, "Bahrain");
        countryNames.add(17, "Bangladesh");
        countryNames.add(18, "Brunei");
        countryNames.add(19, "Cambodia");
        countryNames.add(20, "Armenia");
        countryNames.add(21, "Bhutan");
        countryNames.add(22, "Jordan");
        countryNames.add(23, "Kuwait");
        countryNames.add(24, "Kyrgyzstan");
        countryNames.add(25, "Lao People's Democratic Republic");
        countryNames.add(26, "Lebanon");
        countryNames.add(27, "Maldives");
        countryNames.add(28, "Mongolia");
        countryNames.add(29, "Myanmar");
        countryNames.add(30, "Nepal");
        countryNames.add(31, "Oman");
        countryNames.add(32, "Pakistan");
        countryNames.add(33, "Singapore");
        countryNames.add(34, "Sri Lanka");
        countryNames.add(35, "Syrian Arab Republic");
        countryNames.add(36, "Tajikistan");
        countryNames.add(37, "Thailand");
        countryNames.add(38, "Turkmenistan");
        countryNames.add(39, "Uzbekistan");
        countryNames.add(40, "Vietnam");
        countryNames.add(41, "Yemen");


        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("ASIAUNLOCKED", asiaUnlocked);
        editor.commit();
        if (asiaUnlocked==27) {
            asiaCleared = true;
            editor.putBoolean("ASIACLEAREDBOOLEAN", asiaCleared);
            editor.commit();
        }

        adapter = new
                AsiaCustomList(UnlockAsiaFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.asiaList);
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
                                if ((((position + 1) * 2) -30) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("ASIABOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("ASIAUNLOCKED", asiaUnlocked);
                                    editor.commit();
                                    if (asiaUnlocked==27) {
                                        asiaCleared = true;
                                        editor.putBoolean("ASIACLEAREDBOOLEAN", asiaCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (((position + 1) * 2) -30);
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockAsiaFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockAsiaFlagsActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockAsiaFlagsActivity.this);
                builder.setMessage("Are you sure you want to spend " + (((position + 1) * 2) -30) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
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


        if (preferences2.getBoolean("ASIABOOLEAN"+"15", true)) {
            web.add(15, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(15, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "15", false))
                asiaUnlocked++;
            web.add(15, countryNames.get(15));
            flagImageId.add(15, unlockedFlagImageId.get(15));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "15", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"16", true)) {
            web.add(16, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(16, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "16", false))
                asiaUnlocked++;
            web.add(16, countryNames.get(16));
            flagImageId.add(16, unlockedFlagImageId.get(16));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"16", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"17", true)) {
            web.add(17, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(17, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "17", false))
                asiaUnlocked++;
            web.add(17, countryNames.get(17));
            flagImageId.add(17, unlockedFlagImageId.get(17));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"17", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"18", true)) {
            web.add(18, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(18, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "18", false))
                asiaUnlocked++;
            web.add(18, countryNames.get(18));
            flagImageId.add(18, unlockedFlagImageId.get(18));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"18", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"19", true)) {
            web.add(19, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(19, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "19", false))
                asiaUnlocked++;
            web.add(19, countryNames.get(19));
            flagImageId.add(19, unlockedFlagImageId.get(19));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"19", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"20", true)) {
            web.add(20, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(20, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "20", false))
                asiaUnlocked++;
            web.add(20, countryNames.get(20));
            flagImageId.add(20, unlockedFlagImageId.get(20));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"20", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"21", true)) {
            web.add(21, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(21, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "21", false))
                asiaUnlocked++;
            web.add(21, countryNames.get(21));
            flagImageId.add(21, unlockedFlagImageId.get(21));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"21", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"22", true)) {
            web.add(22, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(22, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "22", false))
                asiaUnlocked++;
            web.add(22, countryNames.get(22));
            flagImageId.add(22, unlockedFlagImageId.get(22));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"22", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"23", true)) {
            web.add(23, "Click here to unlock new flag. Cost 18 stars");
            flagImageId.add(23, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "23", false))
                asiaUnlocked++;
            web.add(23, countryNames.get(23));
            flagImageId.add(23, unlockedFlagImageId.get(23));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"23", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"24", true)) {
            web.add(24, "Click here to unlock new flag. Cost 20 stars");
            flagImageId.add(24, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "24", false))
                asiaUnlocked++;
            web.add(24, countryNames.get(24));
            flagImageId.add(24, unlockedFlagImageId.get(24));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"24", true);
            customEditor.commit();
        }


        if (preferences2.getBoolean("ASIABOOLEAN"+"25", true)) {
            web.add(25, "Click here to unlock new flag. Cost 22 stars");
            flagImageId.add(25, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "25", false))
                asiaUnlocked++;
            web.add(25, countryNames.get(25));
            flagImageId.add(25, unlockedFlagImageId.get(25));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"25", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"26", true)) {
            web.add(26, "Click here to unlock new flag. Cost 24 stars");
            flagImageId.add(26, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "26", false))
                asiaUnlocked++;
            web.add(26, countryNames.get(26));
            flagImageId.add(26, unlockedFlagImageId.get(26));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"26", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"27", true)) {
            web.add(27, "Click here to unlock new flag. Cost 26 stars");
            flagImageId.add(27, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "27", false))
                asiaUnlocked++;
            web.add(27, countryNames.get(27));
            flagImageId.add(27, unlockedFlagImageId.get(27));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"27", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"28", true)) {
            web.add(28, "Click here to unlock new flag. Cost 26 stars");
            flagImageId.add(28, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "28", false))
                asiaUnlocked++;
            web.add(28, countryNames.get(28));
            flagImageId.add(28, unlockedFlagImageId.get(28));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"28", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"29", true)) {
            web.add(29, "Click here to unlock new flag. Cost 28 stars");
            flagImageId.add(29, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "29", false))
                asiaUnlocked++;
            web.add(29, countryNames.get(29));
            flagImageId.add(29, unlockedFlagImageId.get(29));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"29", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"30", true)) {
            web.add(30, "Click here to unlock new flag. Cost 30 stars");
            flagImageId.add(30, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "30", false))
                asiaUnlocked++;
            web.add(30, countryNames.get(30));
            flagImageId.add(30, unlockedFlagImageId.get(30));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"30", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"31", true)) {
            web.add(31, "Click here to unlock new flag. Cost 32 stars");
            flagImageId.add(31, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "31", false))
                asiaUnlocked++;
            web.add(31, countryNames.get(31));
            flagImageId.add(31, unlockedFlagImageId.get(31));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"31", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"32", true)) {
            web.add(32, "Click here to unlock new flag. Cost 34 stars");
            flagImageId.add(32, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "32", false))
                asiaUnlocked++;
            web.add(32, countryNames.get(32));
            flagImageId.add(32, unlockedFlagImageId.get(32));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"32", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"33", true)) {
            web.add(33, "Click here to unlock new flag. Cost 36 stars");
            flagImageId.add(33, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "33", false))
                asiaUnlocked++;
            web.add(33, countryNames.get(33));
            flagImageId.add(33, unlockedFlagImageId.get(33));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"33", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"34", true)) {
            web.add(34, "Click here to unlock new flag. Cost 38 stars");
            flagImageId.add(34, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "34", false))
                asiaUnlocked++;
            web.add(34, countryNames.get(34));
            flagImageId.add(34, unlockedFlagImageId.get(34));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"34", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"35", true)) {
            web.add(35, "Click here to unlock new flag. Cost 40 stars");
            flagImageId.add(35, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "35", false))
                asiaUnlocked++;
            web.add(35, countryNames.get(35));
            flagImageId.add(35, unlockedFlagImageId.get(35));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"35", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"36", true)) {
            web.add(36, "Click here to unlock new flag. Cost 42 stars");
            flagImageId.add(36, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "36", false))
                asiaUnlocked++;
            web.add(36, countryNames.get(36));
            flagImageId.add(36, unlockedFlagImageId.get(36));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"36", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"37", true)) {
            web.add(37, "Click here to unlock new flag. Cost 44 stars");
            flagImageId.add(37, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "37", false))
                asiaUnlocked++;
            web.add(37, countryNames.get(37));
            flagImageId.add(37, unlockedFlagImageId.get(37));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"37", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"38", true)) {
            web.add(38, "Click here to unlock new flag. Cost 46 stars");
            flagImageId.add(38, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "38", false))
                asiaUnlocked++;
            web.add(38, countryNames.get(38));
            flagImageId.add(38, unlockedFlagImageId.get(38));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"38", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"39", true)) {
            web.add(39, "Click here to unlock new flag. Cost 48 stars");
            flagImageId.add(39, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "39", false))
                asiaUnlocked++;
            web.add(39, countryNames.get(39));
            flagImageId.add(39, unlockedFlagImageId.get(39));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"39", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"40", true)) {
            web.add(40, "Click here to unlock new flag. Cost 50 stars");
            flagImageId.add(40, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "40", false))
                asiaUnlocked++;
            web.add(40, countryNames.get(40));
            flagImageId.add(40, unlockedFlagImageId.get(40));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"40", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("ASIABOOLEAN"+"41", true)) {
            web.add(41, "Click here to unlock new flag. Cost 52 stars");
            flagImageId.add(41, unlockedFlagImageId.get(42));
        } else {
            if(!preferences2.getBoolean("ASIANEWFLAGBOOLEAN" + "41", false))
                asiaUnlocked++;
            web.add(41, countryNames.get(41));
            flagImageId.add(41, unlockedFlagImageId.get(41));
            customEditor.putBoolean("ASIANEWFLAGBOOLEAN"+"41", true);
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