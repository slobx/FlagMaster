package com.slobx.www.flagmaster;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.slobx.www.flagmaster.normalgame.ChooseNormalGameActivity;
import com.slobx.www.flagmaster.timegame.ChooseTimeGameActivity;

import mp.MpUtils;
import mp.PaymentRequest;


public class ChooseGameActivity extends mp.PaymentActivity {

    AdView adview;
    boolean africaCleared, asiaCleared, europeCleared, northAmericaCleared, oceaniaCleared,
            othersCleared, southAmericaCleared, unrecognizedCountriesCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        MpUtils.enablePaymentBroadcast(this, com.slobx.www.flagmaster.Manifest.permission.PAYMENT_BROADCAST_PERMISSION);


        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);

        boolean checkIfFlagAreUnlocked = preferences3.getBoolean("UNLOCKEDFLAGSMASTER", true);


        if (!removeAdsBoolean) {
            adview = (AdView) findViewById(R.id.adViewChooseMain);
            adview.loadAd(new AdRequest.Builder().build());
        }

        africaCleared = preferences3.getBoolean("AFRICACLEAREDBOOLEAN", false);
        asiaCleared = preferences3.getBoolean("ASIACLEAREDBOOLEAN", false);
        europeCleared = preferences3.getBoolean("EUROPECLEAREDBOOLEAN", false);
        northAmericaCleared = preferences3.getBoolean("NORTHAMERICACLEAREDBOOLEAN", false);
        oceaniaCleared = preferences3.getBoolean("OCEANIACLEAREDBOOLEAN", false);
        othersCleared = preferences3.getBoolean("OTHERSACLEAREDBOOLEAN", false);
        southAmericaCleared = preferences3.getBoolean("SOUTHAMERICACLEAREDBOOLEAN", false);
        unrecognizedCountriesCleared = preferences3.getBoolean("UNRECOGNIZEDCOUNTRIESCLEAREDBOOLEAN", false);

        if (africaCleared && asiaCleared && europeCleared && northAmericaCleared &&
           oceaniaCleared && othersCleared && southAmericaCleared && unrecognizedCountriesCleared && checkIfFlagAreUnlocked){
            SharedPreferences.Editor customEditor = preferences3.edit();
            customEditor.putBoolean("UNLOCKEDFLAGSMASTER", false);
            customEditor.commit();
            Toast toast = Toast.makeText(ChooseGameActivity.this, "You unlocked all flags", Toast.LENGTH_LONG);
            toast.show();
            unlockAllFlags();
        }

    }


    public void startNormalGame(View v) {
        Intent intent = new Intent(this, ChooseNormalGameActivity.class);
        startActivity(intent);
        finish();
    };

    public void startTimeGame(View v) {
        Intent intent = new Intent(this, ChooseTimeGameActivity.class);
        startActivity(intent);
        finish();
    };

    public void startMasterGame(View v) {
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean checkIfFlagAreUnlocked = preferences.getBoolean("UNLOCKEDFLAGSMASTER", true);


        if (checkIfFlagAreUnlocked) {
            final Dialog dialog = new Dialog(ChooseGameActivity.this);


            dialog.setContentView(R.layout.master_dialog);
            Button dialogUnlockAllFlags = (Button) dialog.findViewById(R.id.unlockAllFlagsButton);
            Button dialogCancel = (Button) dialog.findViewById(R.id.cancelButton);

            dialogCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialogUnlockAllFlags.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
                    builder.setService("f058eb6c46d3e9d7021544a7bf3eda9f", "92a3f2a756e1ee67717f016d76226cff");
                    builder.setDisplayString("Unlock all flags");      // shown on user receipt
                    builder.setProductName("UnlockAllFlagsTest4");  // non-consumable purchases are restored using this value
                    builder.setType(MpUtils.PRODUCT_TYPE_NON_CONSUMABLE);              // non-consumable items can be later restored
                    builder.setIcon(R.drawable.ic_launcher);
                    PaymentRequest pr = builder.build();
                    makePayment(pr);
                    SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

                    dialog.dismiss();
                }
            });


            Window window = getWindow();
            window.setGravity(Gravity.CENTER);

            dialog.show();
        }else{
            Intent intent = new Intent(this, MasterGameActivity.class);
            startActivity(intent);
            finish();
        }


        };








    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor customEditor = preferences.edit();
        boolean checkIfFlagAreUnlocked = preferences.getBoolean("UNLOCKEDFLAGS", true);

        if ((MpUtils.getNonConsumablePaymentStatus(ChooseGameActivity.this,"f058eb6c46d3e9d7021544a7bf3eda9f","92a3f2a756e1ee67717f016d76226cff","UnlockAllFlagsTest4") == MpUtils.MESSAGE_STATUS_BILLED) && checkIfFlagAreUnlocked) {
            Toast toast = Toast.makeText(ChooseGameActivity.this, "You unlocked all flags", Toast.LENGTH_LONG);
            toast.show();
            unlockAllFlags();
            customEditor.putBoolean("UNLOCKEDFLAGS", false);
            customEditor.commit();
        }

    }

    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void unlockAllFlags(){
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor customEditor = preferences.edit();
        customEditor.putBoolean("AFRICABOOLEAN" + "16", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "17", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "18", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "19", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "20", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "21", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "22", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "23", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "24", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "25", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "26", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "27", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "28", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "29", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "30", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "31", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "32", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "33", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "34", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "35", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "36", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "37", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "38", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "39", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "40", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "41", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "42", false);
        customEditor.putBoolean("AFRICABOOLEAN" + "43", false);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "16", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "17", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "18", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "19", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "20", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "21", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "22", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "23", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "24", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "25", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "26", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "27", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "28", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "29", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "30", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "31", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "32", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "33", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "34", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "35", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "36", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "37", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "38", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "39", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "40", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "41", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "42", true);
        customEditor.putBoolean("AFRICANEWFLAGBOOLEAN" + "43", true);
        customEditor.putBoolean("ASIABOOLEAN" + "16", false);
        customEditor.putBoolean("ASIABOOLEAN" + "17", false);
        customEditor.putBoolean("ASIABOOLEAN" + "18", false);
        customEditor.putBoolean("ASIABOOLEAN" + "19", false);
        customEditor.putBoolean("ASIABOOLEAN" + "20", false);
        customEditor.putBoolean("ASIABOOLEAN" + "21", false);
        customEditor.putBoolean("ASIABOOLEAN" + "22", false);
        customEditor.putBoolean("ASIABOOLEAN" + "23", false);
        customEditor.putBoolean("ASIABOOLEAN" + "24", false);
        customEditor.putBoolean("ASIABOOLEAN" + "25", false);
        customEditor.putBoolean("ASIABOOLEAN" + "26", false);
        customEditor.putBoolean("ASIABOOLEAN" + "27", false);
        customEditor.putBoolean("ASIABOOLEAN" + "28", false);
        customEditor.putBoolean("ASIABOOLEAN" + "29", false);
        customEditor.putBoolean("ASIABOOLEAN" + "30", false);
        customEditor.putBoolean("ASIABOOLEAN" + "31", false);
        customEditor.putBoolean("ASIABOOLEAN" + "32", false);
        customEditor.putBoolean("ASIABOOLEAN" + "33", false);
        customEditor.putBoolean("ASIABOOLEAN" + "34", false);
        customEditor.putBoolean("ASIABOOLEAN" + "35", false);
        customEditor.putBoolean("ASIABOOLEAN" + "36", false);
        customEditor.putBoolean("ASIABOOLEAN" + "37", false);
        customEditor.putBoolean("ASIABOOLEAN" + "38", false);
        customEditor.putBoolean("ASIABOOLEAN" + "39", false);
        customEditor.putBoolean("ASIABOOLEAN" + "40", false);
        customEditor.putBoolean("ASIABOOLEAN" + "41", false);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "16", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "17", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "18", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "19", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "20", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "21", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "22", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "23", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "24", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "25", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "26", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "27", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "28", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "29", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "30", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "31", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "32", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "33", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "34", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "35", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "36", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "37", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "38", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "39", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "40", true);
        customEditor.putBoolean("ASIANEWFLAGBOOLEAN" + "41", true);
        customEditor.putBoolean("EUROPEBOOLEAN" + "15", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "16", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "17", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "18", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "19", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "20", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "21", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "22", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "23", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "24", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "25", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "26", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "27", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "28", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "29", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "30", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "31", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "32", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "33", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "34", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "35", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "36", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "37", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "38", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "39", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "40", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "41", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "42", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "43", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "44", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "45", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "46", false);
        customEditor.putBoolean("EUROPEBOOLEAN" + "47", false);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "15", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "16", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "17", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "18", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "19", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "20", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "21", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "22", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "23", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "24", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "25", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "26", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "27", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "28", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "29", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "30", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "31", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "32", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "33", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "34", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "35", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "36", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "37", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "38", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "39", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "40", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "41", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "42", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "43", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "44", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "45", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "46", true);
        customEditor.putBoolean("EUROPENEWFLAGBOOLEAN" + "47", true);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "6", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "7", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "8", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "9", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "10", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "11", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "12", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "13", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "14", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "15", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "16", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "17", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "18", false);
        customEditor.putBoolean("NORTHAMERICABOOLEAN" + "19", false);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "6", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "7", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "8", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "9", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "10", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "11", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "12", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "13", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "14", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "15", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "16", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "17", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "18", true);
        customEditor.putBoolean("NORTHAMERICANEWFLAGBOOLEAN" + "19", true);
        customEditor.putBoolean("OCEANIABOOLEAN" + "3", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "4", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "5", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "6", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "7", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "8", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "9", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "10", false);
        customEditor.putBoolean("OCEANIABOOLEAN" + "11", false);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "3", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "4", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "5", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "6", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "7", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "8", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "9", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "10", true);
        customEditor.putBoolean("OCEANIANEWFLAGBOOLEAN" + "11", true);
        customEditor.putBoolean("OTHERSBOOLEAN" + "0", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "1", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "2", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "3", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "4", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "5", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "6", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "7", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "8", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "9", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "10", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "11", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "12", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "13", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "14", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "15", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "16", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "17", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "18", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "19", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "20", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "21", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "22", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "23", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "24", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "25", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "26", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "27", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "28", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "29", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "30", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "31", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "32", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "33", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "34", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "35", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "36", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "37", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "38", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "39", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "40", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "41", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "42", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "43", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "44", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "45", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "46", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "47", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "48", false);
        customEditor.putBoolean("OTHERSBOOLEAN" + "49", false);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "0", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "1", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "2", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "3", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "4", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "5", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "6", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "7", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "8", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "9", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "10", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "11", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "12", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "13", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "14", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "15", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "16", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "17", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "18", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "19", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "20", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "21", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "22", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "23", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "24", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "25", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "26", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "27", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "28", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "29", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "30", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "31", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "32", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "33", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "34", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "35", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "36", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "37", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "38", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "39", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "40", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "41", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "42", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "43", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "44", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "45", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "46", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "47", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "48", true);
        customEditor.putBoolean("OTHERSNEWFLAGBOOLEAN" + "49", true);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "3", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "4", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "5", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "6", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "7", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "8", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "9", false);
        customEditor.putBoolean("SOUTHAMERICABOOLEAN" + "10", false);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "3", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "4", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "5", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "6", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "7", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "8", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "9", true);
        customEditor.putBoolean("SOUTHAMERICANEWFLAGBOOLEAN" + "10", true);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "0", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "1", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "2", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "3", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "4", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "5", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "6", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "7", false);
        customEditor.putBoolean("UNRECOGNIZEDBOOLEAN" + "8", false);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "0", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "1", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "2", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "3", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "4", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "5", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "6", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "7", true);
        customEditor.putBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "8", true);

        customEditor.commit();

    }




}


    

