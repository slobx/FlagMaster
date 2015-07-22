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
import com.slobx.www.flagmaster.customlists.UnrecognizedCountriesCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;


public class UnlockUnrecognizedCountriesActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    UnrecognizedCountriesCustomList adapter;
    SharedPreferences preferences2;
    int unrecognizedCountriesUnlocked;
    boolean unrecognizedCountriesCleared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_unrecognized_countries);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        unrecognizedCountriesUnlocked = preferences2.getInt("UNRECOGNIZEDCOUNTRIESUNLOCKED", 0);

        numberOfStars = preferencesStars.getInt("TOTALSTARS", 0);



        //locked countries
        unlockedFlagImageId.add(0, R.drawable.abkhazia);
        unlockedFlagImageId.add(1, R.drawable.kosovo);
        unlockedFlagImageId.add(2, R.drawable.northerncyprus);
        unlockedFlagImageId.add(3, R.drawable.palestine);
        unlockedFlagImageId.add(4, R.drawable.westernsahara);
        unlockedFlagImageId.add(5, R.drawable.southossetia);
        unlockedFlagImageId.add(6, R.drawable.nagornokarabakhrepublic);
        unlockedFlagImageId.add(7, R.drawable.pridnestrovianmoldavianrepublic);
        unlockedFlagImageId.add(8, R.drawable.somaliland);
        unlockedFlagImageId.add(9, R.drawable.questionmark);

        //locked countries
        countryNames.add(0, "Abkhazia");
        countryNames.add(1, "Kosovo");
        countryNames.add(2, "Northern Cyprus");
        countryNames.add(3, "Palestine");
        countryNames.add(4, "Western Sahara ");
        countryNames.add(5, "South Ossetia");
        countryNames.add(6, "Nagorno-Karabakh Republic");
        countryNames.add(7, "Pridnestrovian Moldavian Republic");
        countryNames.add(8, "Somaliland");

        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("UNRECOGNIZEDCOUNTRIESUNLOCKED", unrecognizedCountriesUnlocked);
        editor.commit();
        if (unrecognizedCountriesUnlocked == 9) {
            unrecognizedCountriesCleared = true;
            editor.putBoolean("UNRECOGNIZEDCOUNTRIESCLEAREDBOOLEAN", unrecognizedCountriesCleared);
            editor.commit();
        }




        adapter = new
                UnrecognizedCountriesCustomList(UnlockUnrecognizedCountriesActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.unrecognizedCountriesList);
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
                                if ((100*(position*2+2)) <= numberOfStars) {

                                    editor.putInt("FLAG" + position, flagImageId.get(position));
                                    editor.putBoolean("UNRECOGNIZEDBOOLEAN" + position, false);
                                    editor.commit();

                                    adapter.clear();

                                    setWeb();
                                    SharedPreferences.Editor editor = preferences2.edit();
                                    editor.putInt("UNRECOGNIZEDCOUNTRIESUNLOCKED", unrecognizedCountriesUnlocked);
                                    editor.commit();
                                    if (unrecognizedCountriesUnlocked == 9) {
                                        unrecognizedCountriesCleared = true;
                                        editor.putBoolean("UNRECOGNIZEDCOUNTRIESCLEAREDBOOLEAN", unrecognizedCountriesCleared);
                                        editor.commit();
                                    }
                                    numberOfStars = numberOfStars - (100*(position*2+2));
                                    editorStars.putInt("TOTALSTARS", numberOfStars);
                                    editorStars.commit();


                                    Toast toast = Toast.makeText(UnlockUnrecognizedCountriesActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockUnrecognizedCountriesActivity.this);
                                    builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }

                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(UnlockUnrecognizedCountriesActivity.this);
                builder.setMessage("Are you sure you want to spend " + (100*(position*2+2)) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();



            }
        });
    }



    public void setWeb(){
        SharedPreferences.Editor customEditor = preferences2.edit();

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"0", true)) {
            web.add(0, "Click here to unlock new flag. Cost 200 stars");
            flagImageId.add(0, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "0", false))
                unrecognizedCountriesUnlocked++;
            web.add(0, countryNames.get(0));
            flagImageId.add(0, unlockedFlagImageId.get(0));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "0", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"1", true)) {
            web.add(1, "Click here to unlock new flag. Cost 400 stars");
            flagImageId.add(1, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "1", false))
                unrecognizedCountriesUnlocked++;
            web.add(1, countryNames.get(1));
            flagImageId.add(1, unlockedFlagImageId.get(1));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"1", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"2", true)) {
            web.add(2, "Click here to unlock new flag. Cost 600 stars");
            flagImageId.add(2, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "2", false))
                unrecognizedCountriesUnlocked++;
            web.add(2, countryNames.get(2));
            flagImageId.add(2, unlockedFlagImageId.get(2));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"2", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"3", true)) {
            web.add(3, "Click here to unlock new flag. Cost 800 stars");
            flagImageId.add(3, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "3", false))
                unrecognizedCountriesUnlocked++;
            web.add(3, countryNames.get(3));
            flagImageId.add(3, unlockedFlagImageId.get(3));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"3", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"4", true)) {
            web.add(4, "Click here to unlock new flag. Cost 1000 stars");
            flagImageId.add(4, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "4", false))
                unrecognizedCountriesUnlocked++;
            web.add(4, countryNames.get(4));
            flagImageId.add(4, unlockedFlagImageId.get(4));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"4", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"5", true)) {
            web.add(5, "Click here to unlock new flag. Cost 1200 stars");
            flagImageId.add(5, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "5", false))
                unrecognizedCountriesUnlocked++;
            web.add(5, countryNames.get(5));
            flagImageId.add(5, unlockedFlagImageId.get(5));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"5", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"6", true)) {
            web.add(6, "Click here to unlock new flag. Cost 1400 stars");
            flagImageId.add(6, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "6", false))
                unrecognizedCountriesUnlocked++;
            web.add(6, countryNames.get(6));
            flagImageId.add(6, unlockedFlagImageId.get(6));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"6", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"7", true)) {
            web.add(7, "Click here to unlock new flag. Cost 1600 stars");
            flagImageId.add(7, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "7", false))
                unrecognizedCountriesUnlocked++;
            web.add(7, countryNames.get(7));
            flagImageId.add(7, unlockedFlagImageId.get(7));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"7", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("UNRECOGNIZEDBOOLEAN"+"8", true)) {
            web.add(8, "Click here to unlock new flag. Cost 1800 stars");
            flagImageId.add(8, unlockedFlagImageId.get(9));
        } else {
            if(!preferences2.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "8", false))
                unrecognizedCountriesUnlocked++;
            web.add(8, countryNames.get(8));
            flagImageId.add(8, unlockedFlagImageId.get(8));
            customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN"+"8", true);
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
