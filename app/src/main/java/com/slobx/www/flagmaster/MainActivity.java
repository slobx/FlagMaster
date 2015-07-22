package com.slobx.www.flagmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import mp.MpUtils;
import mp.PaymentRequest;


public class MainActivity extends mp.PaymentActivity {

    public int numberOfStars;
    public boolean booleanFor10;
    public boolean booleanFor25;
    public boolean booleanFor50;
    public boolean booleanFor100;
    public boolean booleanForAmateur;
    public boolean booleanForBeginnerOne;
    public boolean booleanForBeginnerTwo;
    public boolean booleanForBeginnerThree;
    public boolean booleanForBeginnerFour;
    public boolean booleanForUltimateOne;
    public boolean booleanForUltimateTwo;
    public boolean booleanForUltimateThree;
    public boolean booleanForUltimateFour;
    public boolean booleanForEuropeanOne;
    public boolean booleanForEuropeanTwo;
    public boolean booleanForEuropeanThree;
    public boolean booleanForAsianOne;
    public boolean booleanForAsianTwo;
    public boolean booleanForAsianThree;
    public boolean booleanForAfricanOne;
    public boolean booleanForAfricanTwo;
    public boolean booleanForAfricanThree;
    public boolean booleanForNorthAmericanOne;
    public boolean booleanForNorthAmericanTwo;
    public boolean booleanForNorthAmericanThree;
    public boolean booleanForSouthAmericanOne;
    public boolean booleanForSouthAmericanTwo;
    public boolean booleanForSouthAmericanThree;
    public boolean booleanForOceaniaOne;
    public boolean booleanForOceaniaTwo;
    public boolean booleanForOceaniaThree;
    AdView adview;
    private static final int SETTINGS_RESULT = 1;
    boolean removeAdsBoolean;
    Button normalGameButton, unlockMoreFlagsButton, achievementsButton, settingsButton, removeAdsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      normalGameButton = (Button) findViewById(R.id.buttonArcadeGame);
      unlockMoreFlagsButton = (Button) findViewById(R.id.buttonUnlockMoreFlags);
      achievementsButton = (Button) findViewById(R.id.buttonAchievements);
      settingsButton = (Button) findViewById(R.id.buttonSettings);
      removeAdsButton = (Button) findViewById(R.id.buttonRemoveAds);

              MpUtils.enablePaymentBroadcast(this, com.slobx.www.flagmaster.Manifest.permission.PAYMENT_BROADCAST_PERMISSION);

        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);

        if ((MpUtils.getNonConsumablePaymentStatus(MainActivity.this,"86ae10f3b4aa76e15b6f2e1eb56633de","048ebbc27bb25f7205f65781ebd62554","RemoveAdsTest3") == MpUtils.MESSAGE_STATUS_BILLED)) {
            removeAdsBoolean = true;

            SharedPreferences.Editor editor = preferences3.edit();

            editor.putBoolean("REMOVEADS", true);
            editor.commit();
        }



        if (removeAdsBoolean) {

            removeAdsButton.setEnabled(false);
            removeAdsButton.setVisibility(View.GONE);
        }

        TextView starTextView = (TextView) findViewById(R.id.starTextView);
        starTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
                builder.setService("b95c10b4e3bdf5d12ed7cc2741db5656", "3769b7c1775ca856e1927fdc026a442f");
                builder.setDisplayString("Stars");      // shown on user receipt
                builder.setProductName("Stars");  // non-consumable purchases are restored using this value
                builder.setType(MpUtils.PRODUCT_TYPE_CONSUMABLE);              // non-consumable items can be later restored
                builder.setIcon(R.drawable.ic_launcher);
                PaymentRequest pr = builder.build();
                makePayment(pr);
            } });

        SharedPreferences preferences = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        SharedPreferences preferences2 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        numberOfStars = preferences.getInt("TOTALSTARS", 0);
        booleanFor10 = preferences2.getBoolean("BOOLEANFOR10", true);
        if (booleanFor10){
            numberOfStars = numberOfStars + preferences.getInt("100REWARD", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOR10", false);
            editor.commit();

            editor2.putInt("TOTALSTARS", numberOfStars);
            editor2.commit();

        }

        booleanFor25 = preferences2.getBoolean("BOOLEANFOR25", true);
        if (booleanFor25){
            numberOfStars = numberOfStars + preferences.getInt("300REWARD", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOR25", false);
            editor.commit();

            editor2.putInt("TOTALSTARS", numberOfStars);
            editor2.commit();
        }

        booleanFor50 = preferences2.getBoolean("BOOLEANFOR50", true);
        if (booleanFor50){
            numberOfStars = numberOfStars + preferences.getInt("700REWARD", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOR50", false);
            editor.commit();

            editor2.putInt("TOTALSTARS", numberOfStars);
            editor2.commit();
        }

        booleanFor100 = preferences2.getBoolean("BOOLEANFOR100", true);
        if (booleanFor100){
            numberOfStars = numberOfStars + preferences.getInt("1500REWARD", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOR100", false);
            editor.commit();

            editor2.putInt("TOTALSTARS", numberOfStars);
            editor2.commit();
        }

        booleanForAmateur = preferences2.getBoolean("BOOLEANFORAMATEUR", true);
        if (booleanForAmateur){
            numberOfStars = numberOfStars + preferences2.getInt("AMATEUR", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORAMATEUR", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForBeginnerOne = preferences2.getBoolean("BOOLEANFORBEGINNERONE", true);
        if (booleanForBeginnerOne){
            numberOfStars = numberOfStars + preferences2.getInt("BEGINNERONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORBEGINNERONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForBeginnerTwo = preferences2.getBoolean("BOOLEANFORBEGINNERTWO", true);
        if (booleanForBeginnerTwo){
            numberOfStars = numberOfStars + preferences2.getInt("BEGINNERTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORBEGINNERTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForBeginnerThree = preferences2.getBoolean("BOOLEANFORBEGINNERTHREE", true);
        if (booleanForBeginnerThree){
            numberOfStars = numberOfStars + preferences2.getInt("BEGINNERTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORBEGINNERTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForBeginnerFour = preferences2.getBoolean("BOOLEANFORBEGINNERFOUR", true);
        if (booleanForBeginnerFour){
            numberOfStars = numberOfStars + preferences2.getInt("BEGINNERFOUR", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORBEGINNERFOUR", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForUltimateOne = preferences2.getBoolean("BOOLEANFORULTIMATEONE", true);
        if (booleanForUltimateOne){
            numberOfStars = numberOfStars + preferences2.getInt("ULTIMATEONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORULTIMATEONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForUltimateTwo = preferences2.getBoolean("BOOLEANFORULTIMATETWO", true);
        if (booleanForUltimateTwo){
            numberOfStars = numberOfStars + preferences2.getInt("ULTIMATETWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORULTIMATETWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForUltimateThree = preferences2.getBoolean("BOOLEANFORULTIMATETHREE", true);
        if (booleanForUltimateThree){
            numberOfStars = numberOfStars + preferences2.getInt("ULTIMATETHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORULTIMATETHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForUltimateFour = preferences2.getBoolean("BOOLEANFORULTIMATEFOUR", true);
        if (booleanForUltimateFour){
            numberOfStars = numberOfStars + preferences2.getInt("ULTIMATEFOUR", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORULTIMATEFOUR", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForEuropeanOne = preferences2.getBoolean("BOOLEANFOREUROPEANONE", true);
        if (booleanForEuropeanOne){
            numberOfStars = numberOfStars + preferences2.getInt("EUROPEANONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOREUROPEANONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForEuropeanTwo = preferences2.getBoolean("BOOLEANFOREUROPEANTWO", true);
        if (booleanForEuropeanTwo){
            numberOfStars = numberOfStars + preferences2.getInt("EUROPEANTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOREUROPEANTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForEuropeanThree = preferences2.getBoolean("BOOLEANFOREUROPEANTHREE", true);
        if (booleanForEuropeanThree){
            numberOfStars = numberOfStars + preferences2.getInt("EUROPEANTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOREUROPEANTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAsianOne = preferences2.getBoolean("BOOLEANFORASIANONE", true);
        if (booleanForAsianOne){
            numberOfStars = numberOfStars + preferences2.getInt("ASIANONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORASIANONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAsianTwo = preferences2.getBoolean("BOOLEANFORASIANTWO", true);
        if (booleanForAsianTwo){
            numberOfStars = numberOfStars + preferences2.getInt("ASIANTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORASIANTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAsianThree = preferences2.getBoolean("BOOLEANFORASIANTHREE", true);
        if (booleanForAsianThree){
            numberOfStars = numberOfStars + preferences2.getInt("ASIANTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORASIANTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAfricanOne = preferences2.getBoolean("BOOLEANFORAFRICANONE", true);
        if (booleanForAfricanOne){
            numberOfStars = numberOfStars + preferences2.getInt("AFRICANONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORAFRICANONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAfricanTwo = preferences2.getBoolean("BOOLEANFORAFRICANTWO", true);
        if (booleanForAfricanTwo){
            numberOfStars = numberOfStars + preferences2.getInt("AFRICANTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORAFRICANTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForAfricanThree = preferences2.getBoolean("BOOLEANFORAFRICANTHREE", true);
        if (booleanForAfricanThree){
            numberOfStars = numberOfStars + preferences2.getInt("AFRICANTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORAFRICANTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForNorthAmericanOne = preferences2.getBoolean("BOOLEANFORNORTHAMERICANONE", true);
        if (booleanForNorthAmericanOne){
            numberOfStars = numberOfStars + preferences2.getInt("NORTHAMERICANONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORNORTHAMERICANONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForNorthAmericanTwo = preferences2.getBoolean("BOOLEANFORNORTHAMERICANTWO", true);
        if (booleanForNorthAmericanTwo){
            numberOfStars = numberOfStars + preferences2.getInt("NORTHAMERICANTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORNORTHAMERICANTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForNorthAmericanThree = preferences2.getBoolean("BOOLEANFORNORTHAMERICANTHREE", true);
        if (booleanForNorthAmericanThree){
            numberOfStars = numberOfStars + preferences2.getInt("NORTHAMERICANTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORNORTHAMERICANTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForSouthAmericanOne = preferences2.getBoolean("BOOLEANFORSOUTHAMERICANONE", true);
        if (booleanForSouthAmericanOne){
            numberOfStars = numberOfStars + preferences2.getInt("SOUTHAMERICANONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORSOUTHAAMERICANONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForSouthAmericanTwo = preferences2.getBoolean("BOOLEANFORSOUTHAMERICANTWO", true);
        if (booleanForSouthAmericanTwo){
            numberOfStars = numberOfStars + preferences2.getInt("SOUTHAMERICANTWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORSOUTHAMERICANTWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForSouthAmericanThree = preferences2.getBoolean("BOOLEANFORSOUTHAMERICANTHREE", true);
        if (booleanForSouthAmericanThree){
            numberOfStars = numberOfStars + preferences2.getInt("SOUTHAMERICANTHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFORSOUTHAMERICANTHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForOceaniaOne = preferences2.getBoolean("BOOLEANFOROCEANIAONE", true);
        if (booleanForOceaniaOne){
            numberOfStars = numberOfStars + preferences2.getInt("OCEANIAONE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOROCEANIAONE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForOceaniaTwo = preferences2.getBoolean("BOOLEANFOROCEANIATWO", true);
        if (booleanForOceaniaTwo){
            numberOfStars = numberOfStars + preferences2.getInt("OCEANIATWO", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOROCEANIATWO", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }

        booleanForOceaniaThree = preferences2.getBoolean("BOOLEANFOROCEANIATHREE", true);
        if (booleanForOceaniaThree){
            numberOfStars = numberOfStars + preferences2.getInt("OCEANIATHREE", 0);
            SharedPreferences.Editor editor = preferences2.edit();
            SharedPreferences.Editor editor2 = preferences.edit();
            editor.putBoolean("BOOLEANFOROCEANIATHREE", false);
            editor2.putInt("TOTALSTARS", numberOfStars);
            editor.commit();
            editor2.commit();
        }


        starTextView.setText("X" + numberOfStars);

        if (!removeAdsBoolean) {
            adview = (AdView) findViewById(R.id.adViewMain);
            adview.loadAd(new AdRequest.Builder().build());
        }


        }


public void startChooseNormalGame(View v) {
    Intent intent = new Intent(this, ChooseGameActivity.class);
    startActivity(intent);
    finish();
};

public void startUnlockFlagsMenuActivity(View v) {
     Intent intent = new Intent(this, UnlockFlagsMenuActivity.class);
     startActivity(intent);
    finish();
};

public void startAchievementsActivity(View v) {
    Intent intent = new Intent(this, AchievementsActivity.class);
    startActivity(intent);
    finish();
};

public void startSettingsActivity(View v) {
    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
    startActivityForResult(intent, SETTINGS_RESULT);
};



protected void onResume() {
    super.onResume();
    TextView startv = (TextView) findViewById(R.id.starTextView);
    startv.setText("X" + Wallet.getCredits(this));

    SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
    boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);
    if (removeAdsBoolean) {
        Button removeAdsButton = (Button) findViewById(R.id.buttonRemoveAds);
        removeAdsButton.setEnabled(false);
        removeAdsButton.setVisibility(View.GONE);
    }

    if (removeAdsBoolean) {
        adview = (AdView) findViewById(R.id.adViewMain);
        adview.destroy();
    }


}

public void removeAdsMethod(View v) {
    SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
    boolean removeAdsBoolean = preferences.getBoolean("REMOVEADS", false);
    if (!removeAdsBoolean) {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.setService("86ae10f3b4aa76e15b6f2e1eb56633de", "048ebbc27bb25f7205f65781ebd62554");
        builder.setDisplayString("Remove ads");      // shown on user receipt
        builder.setProductName("RemoveAdsTest3");  // non-consumable purchases are restored using this value
        builder.setType(MpUtils.PRODUCT_TYPE_NON_CONSUMABLE);              // non-consumable items can be later restored
        builder.setIcon(R.drawable.ic_launcher);
        PaymentRequest pr = builder.build();
        makePayment(pr);

    }
}







}


    

