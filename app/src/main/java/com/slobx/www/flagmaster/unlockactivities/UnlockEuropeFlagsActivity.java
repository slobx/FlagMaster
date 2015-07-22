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
import com.slobx.www.flagmaster.customlists.EuropeCustomList;

import java.util.ArrayList;

import mp.MpUtils;
import mp.PaymentActivity;
import mp.PaymentRequest;

public class UnlockEuropeFlagsActivity extends PaymentActivity {

    ListView list;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ArrayList<Integer> unlockedFlagImageId = new ArrayList<>();
    int numberOfStars;
    EuropeCustomList adapter;
    SharedPreferences preferences2;
    boolean booleanFor10;
    boolean booleanFor25;
    int europeUnlocked;
    boolean europeCleared;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_europe_flags);

        SharedPreferences preferencesStars = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorStars = preferencesStars.edit();


        preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        europeUnlocked = preferences2.getInt("EUROPEUNLOCKED", 0);

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

        Toast toast = Toast.makeText(UnlockEuropeFlagsActivity.this, "Scroll down to unlock new flags", Toast.LENGTH_SHORT);
        toast.show();


        //unlocked countries
        unlockedFlagImageId.add(0, R.drawable.russianfederation);
        unlockedFlagImageId.add(1, R.drawable.france);
        unlockedFlagImageId.add(2, R.drawable.spain);
        unlockedFlagImageId.add(3, R.drawable.sweden);
        unlockedFlagImageId.add(4, R.drawable.norway);
        unlockedFlagImageId.add(5, R.drawable.finland);
        unlockedFlagImageId.add(6, R.drawable.poland);
        unlockedFlagImageId.add(7, R.drawable.italy);
        unlockedFlagImageId.add(8, R.drawable.greatbritain);
        unlockedFlagImageId.add(9, R.drawable.greece);
        unlockedFlagImageId.add(10, R.drawable.iceland);
        unlockedFlagImageId.add(11, R.drawable.austria);
        unlockedFlagImageId.add(12, R.drawable.czechrepublic);
        unlockedFlagImageId.add(13, R.drawable.denmark);
        unlockedFlagImageId.add(14, R.drawable.switzerland);
        //locked countries
        unlockedFlagImageId.add(15, R.drawable.ukraine);
        unlockedFlagImageId.add(16, R.drawable.romania);
        unlockedFlagImageId.add(17, R.drawable.belarus);
        unlockedFlagImageId.add(18, R.drawable.kazakhstan);
        unlockedFlagImageId.add(19, R.drawable.bulgaria);
        unlockedFlagImageId.add(20, R.drawable.hungary);
        unlockedFlagImageId.add(21, R.drawable.portugal);
        unlockedFlagImageId.add(22, R.drawable.serbia);
        unlockedFlagImageId.add(23, R.drawable.ireland);
        unlockedFlagImageId.add(24, R.drawable.lithuania);
        unlockedFlagImageId.add(25, R.drawable.latvia);
        unlockedFlagImageId.add(26, R.drawable.croatia);
        unlockedFlagImageId.add(27, R.drawable.bosniaandherzegovina);
        unlockedFlagImageId.add(28, R.drawable.slovakia);
        unlockedFlagImageId.add(29, R.drawable.estonia);
        unlockedFlagImageId.add(30, R.drawable.netherlands);
        unlockedFlagImageId.add(31, R.drawable.republicofmoldova);
        unlockedFlagImageId.add(32, R.drawable.belgium);
        unlockedFlagImageId.add(33, R.drawable.albania);
        unlockedFlagImageId.add(34, R.drawable.republicofmacedonia);
        unlockedFlagImageId.add(35, R.drawable.turkey);
        unlockedFlagImageId.add(36, R.drawable.slovenia);
        unlockedFlagImageId.add(37, R.drawable.cyprus);
        unlockedFlagImageId.add(38, R.drawable.luxembourg);
        unlockedFlagImageId.add(39, R.drawable.andorra);
        unlockedFlagImageId.add(40, R.drawable.malta);
        unlockedFlagImageId.add(41, R.drawable.liechtenstein);
        unlockedFlagImageId.add(42, R.drawable.sanmarino);
        unlockedFlagImageId.add(43, R.drawable.monaco);
        unlockedFlagImageId.add(44, R.drawable.germany);
        unlockedFlagImageId.add(45, R.drawable.montenegro);
        unlockedFlagImageId.add(46, R.drawable.georgia);
        unlockedFlagImageId.add(47, R.drawable.vatican);
        unlockedFlagImageId.add(48, R.drawable.questionmark);





        //unlocked countries
        countryNames.add(0, "\nCountry:Russian Federation" + "\n\n" + "Capital:Moscow");
        countryNames.add(1, "\nCountry:France" + "\n\n" + "Capital:Paris");
        countryNames.add(2, "\nCountry:Spain" + "\n\n" + "Capital:Madrid");
        countryNames.add(3, "\nCountry:Sweden" + "\n\n" + "Capital:Stockholm");
        countryNames.add(4, "\nCountry:Norway" + "\n\n" + "Capital:Oslo");
        countryNames.add(5, "\nCountry:Finland" + "\n\n" + "Capital:Helsinki");
        countryNames.add(6, "\nCountry:Poland" + "\n\n" + "Capital:Warsaw");
        countryNames.add(7, "\nCountry:Italy" + "\n\n" + "Capital:Rome");
        countryNames.add(8, "\nCountry:United Kingdom" + "\n\n" + "Capital:London");
        countryNames.add(9, "\nCountry:Greece" + "\n\n" + "Capital:Athens");
        countryNames.add(10, "\nCountry:Iceland" + "\n\n" + "Capital:Reykjavik");
        countryNames.add(11, "\nCountry:Austria" + "\n\n" + "Capital:Vienna");
        countryNames.add(12, "\nCountry:Czech Republic" + "\n\n" + "Capital:Prague");
        countryNames.add(13, "\nCountry:Denmark" + "\n\n" + "Capital:Copenhagen");
        countryNames.add(14, "\nCountry:Switzerland" + "\n\n" + "Capital:Bern");
        //locked countries
        countryNames.add(15, "\nCountry:Ukraine" + "\n\n" + "Capital:Kiev");
        countryNames.add(16, "\nCountry:Romania" + "\n\n" + "Capital:Bucharest");
        countryNames.add(17, "\nCountry:Belarus" + "\n\n" + "Capital:Minsk");
        countryNames.add(18, "\nCountry:Kazakhstan" + "\n\n" + "Capital:Astana");
        countryNames.add(19, "\nCountry:Bulgaria" + "\n\n" + "Capital:Sofia");
        countryNames.add(20, "\nCountry:Hungary" + "\n\n" + "Capital:Budapest");
        countryNames.add(21, "\nCountry:Portugal" + "\n\n" + "Capital:Lisbon");
        countryNames.add(22, "\nCountry:Serbia" + "\n\n" + "Capital:Belgrade");
        countryNames.add(23, "\nCountry:Republic of Ireland" + "\n\n" + "Capital:Dublin");
        countryNames.add(24, "\nCountry:Lithuania" + "\n\n" + "Capital:Vilnius");
        countryNames.add(25, "\nCountry:Latvia" + "\n\n" + "Capital:Riga");
        countryNames.add(26, "\nCountry:Croatia" + "\n\n" + "Capital:Zagreb");
        countryNames.add(27, "\nCountry:Bosnia & Herzegovina" + "\n\n" + "Capital:Sarajevo");
        countryNames.add(28, "\nCountry:Slovakia" + "\n\n" + "Capital:Bratislava");
        countryNames.add(29, "\nCountry:Estonia" + "\n\n" + "Capital:Tallinn");
        countryNames.add(30, "\nCountry:Netherlands" + "\n\n" + "Capital:Amsterdam");
        countryNames.add(31, "\nCountry:Republic of Moldova" + "\n\n" + "Capital:Chișinau");
        countryNames.add(32, "\nCountry:Belgium" + "\n\n" + "Capital:Brussels");
        countryNames.add(33, "\nCountry:Albania" + "\n\n" + "Capital:Tirana");
        countryNames.add(34, "\nCountry:Republic of Macedonia" + "\n\n" + "Capital:Skopje");
        countryNames.add(35, "\nCountry:Turkey" + "\n\n" + "Capital:Ankara");
        countryNames.add(36, "\nCountry:Slovenia" + "\n\n" + "Capital:Ljubljana");
        countryNames.add(37, "\nCountry:Cyprus" + "\n\n" + "Capital:Nicosia");
        countryNames.add(38, "\nCountry:Luxembourg" + "\n\n" + "Capital:Luxembourg");
        countryNames.add(39, "\nCountry:Andorra" + "\n\n" + "Capital:Andorra la Vella");
        countryNames.add(40, "\nCountry:Malta" + "\n\n" + "Capital:Valletta");
        countryNames.add(41, "\nCountry:Liechtenstein" + "\n\n" + "Capital:Vaduz");
        countryNames.add(42, "\nCountry:San Marino" + "\n\n" + "Capital:San Marino");
        countryNames.add(43, "\nCountry:Monaco" + "\n\n" + "Capital:Monaco");
        countryNames.add(44, "\nCountry:Germany" + "\n\n" + "Capital:Berlin");
        countryNames.add(45, "\nCountry:Montenegro" + "\n\n" + "Capital:Podgorica");
        countryNames.add(46, "\nCountry:Georgia" + "\n\n" + "Capital:Tbilisi");
        countryNames.add(47, "\nCountry:Vatican" + "\n\n" + "Capital:Vatican City");



        setWeb();

        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("EUROPEUNLOCKED", europeUnlocked);
        editor.commit();
        if (europeUnlocked==33) {
            europeCleared = true;
            editor.putBoolean("EUROPECLEAREDBOOLEAN", europeCleared);
            editor.commit();
        }



        adapter = new
                EuropeCustomList(UnlockEuropeFlagsActivity.this, web, flagImageId );
        list=(ListView)findViewById(R.id.europeList);
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
                                    if ((((position + 1) * 2) - 30) <= numberOfStars) {

                                        editor.putInt("FLAG" + position, flagImageId.get(position));
                                        editor.putBoolean("EUROPEBOOLEAN" + position, false);
                                        editor.commit();

                                        adapter.clear();

                                        setWeb();
                                        SharedPreferences.Editor editor = preferences2.edit();
                                        editor.putInt("EUROPEUNLOCKED", europeUnlocked);
                                        editor.commit();
                                        if (europeUnlocked==33) {
                                            europeCleared = true;
                                            editor.putBoolean("EUROPECLEAREDBOOLEAN", europeCleared);
                                            editor.commit();
                                        }
                                        numberOfStars = numberOfStars - (((position + 1) * 2)-30);
                                        editorStars.putInt("TOTALSTARS", numberOfStars);
                                        editorStars.commit();


                                        Toast toast = Toast.makeText(UnlockEuropeFlagsActivity.this, "You unlocked " + countryNames.get(position) + ". You have " + numberOfStars + " stars left!", Toast.LENGTH_SHORT);
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
                                        AlertDialog.Builder builder = new AlertDialog.Builder(UnlockEuropeFlagsActivity.this);
                                        builder.setMessage("You don't have enough stars. Do you want to buy more?").setPositiveButton("Yes", dialogClickListener)
                                                .setNegativeButton("No", dialogClickListener).show();
                                    }
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }

                        }
                    };


                    AlertDialog.Builder builder = new AlertDialog.Builder(UnlockEuropeFlagsActivity.this);
                    builder.setMessage("Are you sure you want to spend " + ((position + 1) * 2 -30) + " stars to unlock new flag?").setPositiveButton("Yes", dialogClickListener)
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


        if (preferences2.getBoolean("EUROPEBOOLEAN15", true)) {
            web.add(15, "Click here to unlock new flag. Cost 2 stars");
            flagImageId.add(15, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "15", false))
                europeUnlocked++;
            web.add(15, countryNames.get(15));
            flagImageId.add(15, unlockedFlagImageId.get(15));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN15", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN16", true)) {
            web.add(16, "Click here to unlock new flag. Cost 4 stars");
            flagImageId.add(16, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "16", false))
                europeUnlocked++;
            web.add(16, countryNames.get(16));
            flagImageId.add(16, unlockedFlagImageId.get(16));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN16", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN17", true)) {
            web.add(17, "Click here to unlock new flag. Cost 6 stars");
            flagImageId.add(17, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "17", false))
                europeUnlocked++;
            web.add(17, countryNames.get(17));
            flagImageId.add(17, unlockedFlagImageId.get(17));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN17", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN18", true)) {
            web.add(18, "Click here to unlock new flag. Cost 8 stars");
            flagImageId.add(18, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "18", false))
                europeUnlocked++;
            web.add(18, countryNames.get(18));
            flagImageId.add(18, unlockedFlagImageId.get(18));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN18", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN19", true)) {
            web.add(19, "Click here to unlock new flag. Cost 10 stars");
            flagImageId.add(19, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "19", false))
                europeUnlocked++;
            web.add(19, countryNames.get(19));
            flagImageId.add(19, unlockedFlagImageId.get(19));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN19", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN20", true)) {
            web.add(20, "Click here to unlock new flag. Cost 12 stars");
            flagImageId.add(20, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "20", false))
                europeUnlocked++;
            web.add(20, countryNames.get(20));
            flagImageId.add(20, unlockedFlagImageId.get(20));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN20", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN21", true)) {
            web.add(21, "Click here to unlock new flag. Cost 14 stars");
            flagImageId.add(21, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "21", false))
                europeUnlocked++;
            web.add(21, countryNames.get(21));
            flagImageId.add(21, unlockedFlagImageId.get(21));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN21", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN22", true)) {
            web.add(22, "Click here to unlock new flag. Cost 16 stars");
            flagImageId.add(22, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "22", false))
                europeUnlocked++;
            web.add(22, countryNames.get(22));
            flagImageId.add(22, unlockedFlagImageId.get(22));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN22", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN23", true)) {
            web.add(23, "Click here to unlock new flag. Cost 18 stars");
            flagImageId.add(23, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "23", false))
                europeUnlocked++;
            web.add(23, countryNames.get(23));
            flagImageId.add(23, unlockedFlagImageId.get(23));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN23", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN24", true)) {
            web.add(24, "Click here to unlock new flag. Cost 20 stars");
            flagImageId.add(24, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "24", false))
                europeUnlocked++;
            web.add(24, countryNames.get(24));
            flagImageId.add(24, unlockedFlagImageId.get(24));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN24", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN25", true)) {
            web.add(25, "Click here to unlock new flag. Cost 22 stars");
            flagImageId.add(25, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "25", false))
                europeUnlocked++;
            web.add(25, countryNames.get(25));
            flagImageId.add(25, unlockedFlagImageId.get(25));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN25", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN26", true)) {
            web.add(26, "Click here to unlock new flag. Cost 24 stars");
            flagImageId.add(26, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "26", false))
                europeUnlocked++;
            web.add(26, countryNames.get(26));
            flagImageId.add(26, unlockedFlagImageId.get(26));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN26", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN27", true)) {
            web.add(27, "Click here to unlock new flag. Cost 26 stars");
            flagImageId.add(27, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "27", false))
                europeUnlocked++;
            web.add(27, countryNames.get(27));
            flagImageId.add(27, unlockedFlagImageId.get(27));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN27", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN28", true)) {
            web.add(28, "Click here to unlock new flag. Cost 28 stars");
            flagImageId.add(28, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "28", false))
                europeUnlocked++;
            web.add(28, countryNames.get(28));
            flagImageId.add(28, unlockedFlagImageId.get(28));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN28", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN29", true)) {
            web.add(29, "Click here to unlock new flag. Cost 30 stars");
            flagImageId.add(29, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "29", false))
                europeUnlocked++;
            web.add(29, countryNames.get(29));
            flagImageId.add(29, unlockedFlagImageId.get(29));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN29", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN30", true)) {
            web.add(30, "Click here to unlock new flag. Cost 32 stars");
            flagImageId.add(30, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "30", false))
                europeUnlocked++;
            web.add(30, countryNames.get(30));
            flagImageId.add(30, unlockedFlagImageId.get(30));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN30", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN31", true)) {
            web.add(31, "Click here to unlock new flag. Cost 34 stars");
            flagImageId.add(31, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "31", false))
                europeUnlocked++;
            web.add(31, countryNames.get(31));
            flagImageId.add(31, unlockedFlagImageId.get(31));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN31", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN32", true)) {
            web.add(32, "Click here to unlock new flag. Cost 36 stars");
            flagImageId.add(32, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "32", false))
                europeUnlocked++;
            web.add(32, countryNames.get(32));
            flagImageId.add(32, unlockedFlagImageId.get(32));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN32", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN33", true)) {
            web.add(33, "Click here to unlock new flag. Cost 38 stars");
            flagImageId.add(33, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "33", false))
                europeUnlocked++;
            web.add(33, countryNames.get(33));
            flagImageId.add(33, unlockedFlagImageId.get(33));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN33", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN34", true)) {
            web.add(34, "Click here to unlock new flag. Cost 40 stars");
            flagImageId.add(34, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "34", false))
                europeUnlocked++;
            web.add(34, countryNames.get(34));
            flagImageId.add(34, unlockedFlagImageId.get(34));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN34", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN35", true)) {
            web.add(35, "Click here to unlock new flag. Cost 42 stars");
            flagImageId.add(35, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "35", false))
            europeUnlocked++;
            web.add(35, countryNames.get(35));
            flagImageId.add(35, unlockedFlagImageId.get(35));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN35", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN36", true)) {
            web.add(36, "Click here to unlock new flag. Cost 44 stars");
            flagImageId.add(36, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "36", false))
                europeUnlocked++;
            web.add(36, countryNames.get(36));
            flagImageId.add(36, unlockedFlagImageId.get(36));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN36", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN37", true)) {
            web.add(37, "Click here to unlock new flag. Cost 46 stars");
            flagImageId.add(37, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "37", false))
                europeUnlocked++;
            web.add(37, countryNames.get(37));
            flagImageId.add(37, unlockedFlagImageId.get(37));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN37", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN38", true)) {
            web.add(38, "Click here to unlock new flag. Cost 48 stars");
            flagImageId.add(38, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "38", false))
                europeUnlocked++;
            web.add(38, countryNames.get(38));
            flagImageId.add(38, unlockedFlagImageId.get(38));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN38", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN39", true)) {
            web.add(39, "Click here to unlock new flag. Cost 50 stars");
            flagImageId.add(39, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "39", false))
                europeUnlocked++;
            web.add(39, countryNames.get(39));
            flagImageId.add(39, unlockedFlagImageId.get(39));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN39", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN40", true)) {
            web.add(40, "Click here to unlock new flag. Cost 52 stars");
            flagImageId.add(40, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "40", false))
                europeUnlocked++;
            web.add(40, countryNames.get(40));
            flagImageId.add(40, unlockedFlagImageId.get(40));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN40", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN41", true)) {
            web.add(41, "Click here to unlock new flag. Cost 54 stars");
            flagImageId.add(41, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "41", false))
                europeUnlocked++;
            web.add(41, countryNames.get(41));
            flagImageId.add(41, unlockedFlagImageId.get(41));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN41", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN42", true)) {
            web.add(42, "Click here to unlock new flag. Cost 56 stars");
            flagImageId.add(42, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "42", false))
                europeUnlocked++;
            web.add(42, countryNames.get(42));
            flagImageId.add(42, unlockedFlagImageId.get(42));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN42", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN43", true)) {
            web.add(43, "Click here to unlock new flag. Cost 58 stars");
            flagImageId.add(43, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "43", false))
                europeUnlocked++;
            web.add(43, countryNames.get(43));
            flagImageId.add(43, unlockedFlagImageId.get(43));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN43", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN44", true)) {
            web.add(44, "Click here to unlock new flag. Cost 60 stars");
            flagImageId.add(44, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "44", false))
                europeUnlocked++;
            web.add(44, countryNames.get(44));
            flagImageId.add(44, unlockedFlagImageId.get(44));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN44", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN45", true)) {
            web.add(45, "Click here to unlock new flag. Cost 62 stars");
            flagImageId.add(45, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "45", false))
                europeUnlocked++;
            web.add(45, countryNames.get(45));
            flagImageId.add(45, unlockedFlagImageId.get(45));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN45", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN46", true)) {
            web.add(46, "Click here to unlock new flag. Cost 64 stars");
            flagImageId.add(46, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "46", false))
                europeUnlocked++;
            web.add(46, countryNames.get(46));
            flagImageId.add(46, unlockedFlagImageId.get(46));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN46", true);
            customEditor.commit();
        }

        if (preferences2.getBoolean("EUROPEBOOLEAN47", true)) {
            web.add(47, "Click here to unlock new flag. Cost 66 stars");
            flagImageId.add(47, unlockedFlagImageId.get(48));
        } else {
            if(!preferences2.getBoolean("EUROPENEWFLAGBOOLEAN" + "47", false))
                europeUnlocked++;
            web.add(47, countryNames.get(47));
            flagImageId.add(47, unlockedFlagImageId.get(47));
            customEditor.putBoolean("EUROPENEWFLAGBOOLEAN47", true);
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
