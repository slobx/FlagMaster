package com.slobx.www.flagmaster.normalgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.slobx.www.flagmaster.MainActivity;
import com.slobx.www.flagmaster.R;

import java.util.ArrayList;
import java.util.Random;


public class NormalGameNameFlagActivity extends Activity {

    public ImageView buttonFlag1;
    public ImageView buttonFlag2;
    public ImageView buttonFlag3;
    public ImageView buttonFlag4;

    public ImageView buttonFlagRedX1;
    public ImageView buttonFlagRedX2;
    public ImageView buttonFlagRedX3;
    public ImageView buttonFlagRedX4;

    public ImageView buttonFlagBackground1;
    public ImageView buttonFlagBackground2;
    public ImageView buttonFlagBackground3;
    public ImageView buttonFlagBackground4;

    TextView questionTextView;
    ImageView bottomImageView, scoreImageView;
    //TextView scoreTextView;

    Vibrator vibe;
    long[] vibratePattern = {0, 150, 100, 150, 100, 150};
    MediaPlayer rightSound;
    MediaPlayer wrongSound;
    boolean soundOnOff;
    boolean vibrationOnOff;

    boolean unrecognizedOnOff;
    boolean othersOnOff;


    ArrayList<String> countriesNameList;
    ArrayList<Integer> flagsList;

    //declaring arrays for achievement - guess X number of Europe, Asia...etc flags
    //methods updateEuropeanNumber, updateAsianNumber...etc below are counting right answers for this achievement
    //that methods are using this arrays as a model for comparison
    String[] europeArray = {"Russian Federation", "France", "Spain", "Sweden", "Norway",
            "Finland","Poland","Italy","United Kingdom","Greece","Iceland", "Austria","Czech Republic","Denmark","Switzerland",
            "Ukraine", "Romania","Belarus","Kazakhstan", "Bulgaria","Hungary","Portugal","Serbia",
            "Republic of Ireland","Lithuania", "Latvia","Croatia","Bosnia & Herzegovina","Slovakia","Estonia",
            "Netherlands","Republic of Moldova","Belgium","Albania","Republic of Macedonia","Turkey",
            "Slovenia","Cyprus","Luxembourg","Andorra","Malta","Liechtenstein","San Marino","Monaco",
            "Germany", "Montenegro", "Georgia", "Vatican City"};

    String[] asiaArray = {"Afghanistan", "China","India","Indonesia","Iran","Iraq","Israel","Japan","Malaysia","Democratic People's Republic of Korea",
            "Philippines","Qatar","Saudi Arabia","Republic of Korea","United Arab Emirates","Azerbaijan","Bahrain","Bangladesh", "Brunei",
            "Cambodia","Armenia", "Bhutan","Jordan","Kuwait","Kyrgyzstan","Lao People's Democratic Republic","Lebanon",
            "Maldives","Mongolia","Myanmar","Nepal","Oman","Pakistan","Singapore","Sri Lanka","Syrian Arab Republic","Tajikistan",
            "Thailand","Turkmenistan","Uzbekistan","Vietnam","Yemen"};

    String[] africaArray = {"Algeria","Angola","Cameroon","Congo","Cote d'Ivoire","Egypt","Ghana","Kenya","Mauritius","Morocco","Nigeria","Senegal","Tunisia",
            "Ethiopia","Benin","Botswana","Burkina Faso","Burundi","Cape Verde","Central African Republic","Chad","Comoros","Equatorial Guinea",
            "Eritrea","Gabon","Gambia","Guinea","Guinea Bissau","Lesotho","Liberia","Madagascar","Malawi","Mali","Mauritania","Mozambique","Namibia",
            "Rwanda","Sao Tome and Principe","Seychelles","Sierra Leone","Somalia","Sudan","Swaziland","Tanzania"};

    String[] northAmericaArray = {"United States of America","Bahamas","Mexico","Costa Rica","Cuba","Jamaica","Barbados","Trinidad and Tobago",
            "Antigua and Barbuda","Panama","Dominica","Grenada","Saint Lucia","Saint Vincent and the Grenadines",
            "Belize","El Salvador","Guatemala","Honduras","Nicaragua","Haiti"};

    String[] southAmericaArray = {"Argentina","Brazil","Colombia","Bolivia", "Chile","Ecuador","Guyana","Paraguay","Peru","Uruguay","Venezuela"};

    String[] oceaniaArray = {"Australia","Papua New Guinea","New Zealand","Fiji","Solomon Islands","Vanuatu","Samoa","Kiribati","Tonga","Marshall Islands","Palau", "Nauru"};






    public int returningID;
    public int left;
    public int totalNumberOfStars;
    public int correctAnswer;
    public int cleanSheet;
    int europeanInt;
    int asianInt;
    int africanInt;
    int northAmericanInt;
    int southAmericanInt;
    int oceaniaInt;
    int numberOfGames;
    int scoreDrawableNumber;
    boolean amateurBoolean;
    boolean beginnerOneBoolean;
    boolean beginnerTwoBoolean;
    boolean beginnerThreeBoolean;
    boolean beginnerFourBoolean;
    boolean europeanOneBoolean;
    boolean europeanTwoBoolean;
    boolean europeanThreeBoolean;
    boolean asianOneBoolean;
    boolean asianTwoBoolean;
    boolean asianThreeBoolean;
    boolean africanOneBoolean;
    boolean africanTwoBoolean;
    boolean africanThreeBoolean;
    boolean northAmericanOneBoolean;
    boolean northAmericanTwoBoolean;
    boolean northAmericanThreeBoolean;
    boolean southAmericanOneBoolean;
    boolean southAmericanTwoBoolean;
    boolean southAmericanThreeBoolean;
    boolean oceaniaOneBoolean;
    boolean oceaniaTwoBoolean;
    boolean oceaniaThreeBoolean;


    public boolean isCleanSheetFullFor10Correct;
    public boolean isCleanSheetFullFor25Correct;
    public boolean isCleanSheetFullFor50Correct;
    public boolean isCleanSheetFullFor100Correct;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game_name_flag);

        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);

        //getting information about sound preference
        SharedPreferences myPreference= PreferenceManager.getDefaultSharedPreferences(this);
        soundOnOff = myPreference.getBoolean("prefSoundOnOff", true);
        vibrationOnOff = myPreference.getBoolean("prefVibrateOnOff", true);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        unrecognizedOnOff = myPreference.getBoolean("unrecognizedTurnOnOff", true);
        othersOnOff = myPreference.getBoolean("othersTurnOnOff", true);

        preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        cleanSheet = loadCleanSheet();



        buttonFlag1 = (ImageView) findViewById(R.id.buttonImageView);
        buttonFlag2 = (ImageView) findViewById(R.id.buttonImageView2);
        buttonFlag3 = (ImageView) findViewById(R.id.buttonImageView3);
        buttonFlag4 = (ImageView) findViewById(R.id.buttonImageView4);

        buttonFlagRedX1 = (ImageView) findViewById(R.id.buttonImageViewRedX);
        buttonFlagRedX2 = (ImageView) findViewById(R.id.buttonImageViewRedX2);
        buttonFlagRedX3 = (ImageView) findViewById(R.id.buttonImageViewRedX3);
        buttonFlagRedX4 = (ImageView) findViewById(R.id.buttonImageViewRedX4);

        buttonFlagBackground1 = (ImageView) findViewById(R.id.buttonImageViewBackground1);
        buttonFlagBackground2 = (ImageView) findViewById(R.id.buttonImageViewBackground2);
        buttonFlagBackground3 = (ImageView) findViewById(R.id.buttonImageViewBackground3);
        buttonFlagBackground4 = (ImageView) findViewById(R.id.buttonImageViewBackground4);


        isCleanSheetFullFor10Correct = preferences.getBoolean("ISCLEANSHEETFULLFOR10", true);
        isCleanSheetFullFor25Correct = preferences.getBoolean("ISCLEANSHEETFULLFOR25", true);
        isCleanSheetFullFor50Correct = preferences.getBoolean("ISCLEANSHEETFULLFOR50", true);
        isCleanSheetFullFor100Correct = preferences.getBoolean("ISCLEANSHEETFULLFOR100", true);

        amateurBoolean = preferences.getBoolean("BOOLEANAMATEUR", true);
        beginnerOneBoolean = preferences.getBoolean("BOOLEANBEGINNERONE", true);
        beginnerTwoBoolean = preferences.getBoolean("BOOLEANBEGINNERTWO", true);
        beginnerThreeBoolean = preferences.getBoolean("BOOLEANBEGINNERTHREE", true);
        beginnerFourBoolean = preferences.getBoolean("BOOLEANBEGINNERFOUR", true);


        //int for counting right answers about different continent flags
        europeanInt = preferences.getInt("EUROPEANINT", 0);
        asianInt = preferences.getInt("ASIANINT", 0);
        africanInt = preferences.getInt("AFRICANINT", 0);
        northAmericanInt = preferences.getInt("NORTHAMERICANINT", 0);
        southAmericanInt = preferences.getInt("SOUTHAMERICANINT", 0);
        oceaniaInt = preferences.getInt("OCEANIAINT", 0);

        //getting achievements boolean for furhter use
        europeanOneBoolean = preferences.getBoolean("BOOLEANEUROPEANONE", true);
        europeanTwoBoolean = preferences.getBoolean("BOOLEANEUROPEANTWO", true);
        europeanThreeBoolean = preferences.getBoolean("BOOLEANEUROPEANTHREE", true);
        asianOneBoolean = preferences.getBoolean("BOOLEANASIANONE", true);
        asianTwoBoolean = preferences.getBoolean("BOOLEANASIANTWO", true);
        asianThreeBoolean = preferences.getBoolean("BOOLEANASIANTHREE", true);
        africanOneBoolean = preferences.getBoolean("BOOLEANAFRICANONE", true);
        africanTwoBoolean = preferences.getBoolean("BOOLEANAFRICANTWO", true);
        africanThreeBoolean = preferences.getBoolean("BOOLEANAFRICANTHREE", true);
        northAmericanOneBoolean = preferences.getBoolean("BOOLEANNORTHAMERICANONE", true);
        northAmericanTwoBoolean = preferences.getBoolean("BOOLEANNORTHAMERICANTWO", true);
        northAmericanThreeBoolean = preferences.getBoolean("BOOLEANNORTHAMERICANTHREE", true);
        southAmericanOneBoolean = preferences.getBoolean("BOOLEANSOUTHAMERICANONE", true);
        southAmericanTwoBoolean = preferences.getBoolean("BOOLEANSOUTHAMERICANTWO", true);
        southAmericanThreeBoolean = preferences.getBoolean("BOOLEANSOUTHAMERICANTHREE", true);
        oceaniaOneBoolean = preferences.getBoolean("BOOLEANOCEANIAONE", true);
        oceaniaTwoBoolean = preferences.getBoolean("BOOLEANOCEANIATWO", true);
        oceaniaThreeBoolean = preferences.getBoolean("BOOLEANOCEANIATHREE", true);



        questionTextView = (TextView) findViewById(R.id.countryTextView);
        bottomImageView = (ImageView) findViewById(R.id.bottomImageView);
        scoreImageView = (ImageView) findViewById(R.id.scoreImageView);
        //TextView = (TextView) findViewById(R.id.scoreTextViev);


        bottomImageView.setBackgroundResource(R.drawable.scoretextbackground_anim);
        AnimationDrawable anim = (AnimationDrawable)  bottomImageView.getBackground();
        anim.start();



        left = 1;


        totalNumberOfStars = 0;
        //scoreTextView.setText(""+totalNumberOfStars);



        countriesNameList = new ArrayList<String>();
        //unlocked European countries names
        countriesNameList.add(0, "Russian Federation");
        countriesNameList.add(1, "France");
        countriesNameList.add(2, "Spain");
        countriesNameList.add(3, "Sweden");
        countriesNameList.add(4, "Norway");
        countriesNameList.add(5, "Finland");
        countriesNameList.add(6, "Poland");
        countriesNameList.add(7, "Italy");
        countriesNameList.add(8, "United Kingdom");
        countriesNameList.add(9, "Greece");
        countriesNameList.add(10, "Iceland");
        countriesNameList.add(11, "Austria");
        countriesNameList.add(12, "Czech Republic");
        countriesNameList.add(13, "Denmark");
        countriesNameList.add(14, "Switzerland");
        //unlocked Asia countries names
        countriesNameList.add(15, "Afghanistan");
        countriesNameList.add(16, "China");
        countriesNameList.add(17, "India");
        countriesNameList.add(18, "Indonesia");
        countriesNameList.add(19, "Iran");
        countriesNameList.add(20, "Iraq");
        countriesNameList.add(21, "Israel");
        countriesNameList.add(22, "Japan");
        countriesNameList.add(23, "Malaysia");
        countriesNameList.add(24, "Democratic People's Republic of Korea");
        countriesNameList.add(25, "Philippines");
        countriesNameList.add(26, "Qatar");
        countriesNameList.add(27, "Saudi Arabia");
        countriesNameList.add(28, "Republic of Korea");
        countriesNameList.add(29, "United Arab Emirates");
        //unlocked Africa countries names
        countriesNameList.add(30, "Algeria");
        countriesNameList.add(31, "Angola");
        countriesNameList.add(32, "Cameroon");
        countriesNameList.add(33, "Congo");
        countriesNameList.add(34, "Cote d'Ivoire");
        countriesNameList.add(35, "Egypt");
        countriesNameList.add(36, "Ghana");
        countriesNameList.add(37, "Kenya");
        countriesNameList.add(38, "Mauritius");
        countriesNameList.add(39, "Morocco");
        countriesNameList.add(40, "Nigeria");
        countriesNameList.add(41, "Senegal");
        countriesNameList.add(42, "Tunisia");
        countriesNameList.add(43, "Ethiopia");
        countriesNameList.add(44, "Benin");
        countriesNameList.add(45, "Botswana");
        //unlocked North America countries names
        countriesNameList.add(46, "United States of America");
        countriesNameList.add(47, "Bahamas");
        countriesNameList.add(48, "Mexico");
        countriesNameList.add(49, "Costa Rica");
        countriesNameList.add(50, "Cuba");
        countriesNameList.add(51, "Jamaica");
        //unlocked South America countries names
        countriesNameList.add(52, "Argentina");
        countriesNameList.add(53, "Brazil");
        countriesNameList.add(54, "Colombia");
        //unlocked Oceania countries names
        countriesNameList.add(55, "Australia");
        countriesNameList.add(56, "Papua New Guinea");
        countriesNameList.add(57, "New Zealand");


        //European locked countries names
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN15", false))
            countriesNameList.add("Ukraine");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN16", false))
            countriesNameList.add("Romania");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN17", false))
            countriesNameList.add("Belarus");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN18", false))
            countriesNameList.add("Kazakhstan");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN19", false))
            countriesNameList.add("Bulgaria");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN20", false))
            countriesNameList.add("Hungary");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN21", false))
            countriesNameList.add("Portugal");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN22", false))
            countriesNameList.add("Serbia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN23", false))
            countriesNameList.add("Republic of Ireland");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN24", false))
            countriesNameList.add("Lithuania");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN25", false))
            countriesNameList.add("Latvia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN26", false))
            countriesNameList.add("Croatia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN27", false))
            countriesNameList.add("Bosnia & Herzegovina");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN28", false))
            countriesNameList.add("Slovakia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN29", false))
            countriesNameList.add("Estonia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN30", false))
            countriesNameList.add("Netherlands");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN31", false))
            countriesNameList.add("Republic of Moldova");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN32", false))
            countriesNameList.add("Belgium");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN33", false))
            countriesNameList.add("Albania");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN34", false))
            countriesNameList.add("Republic of Macedonia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN35", false))
            countriesNameList.add("Turkey");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN36", false))
            countriesNameList.add("Slovenia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN37", false))
            countriesNameList.add("Cyprus");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN38", false))
            countriesNameList.add("Luxembourg");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN39", false))
            countriesNameList.add("Andorra");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN40", false))
            countriesNameList.add("Malta");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN41", false))
            countriesNameList.add("Liechtenstein");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN42", false))
            countriesNameList.add("San Marino");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN43", false))
            countriesNameList.add("Monaco");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN44", false))
            countriesNameList.add("Germany");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN45", false))
            countriesNameList.add("Montenegro");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN46", false))
            countriesNameList.add("Georgia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN47", false))
            countriesNameList.add("Vatican city");
        //Asia locked countries
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"15", false))
            countriesNameList.add("Azerbaijan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Bahrain");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Bangladesh");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Brunei");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Cambodia");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"20", false))
            countriesNameList.add("Armenia");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"21", false))
            countriesNameList.add("Bhutan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"22", false))
            countriesNameList.add("Jordan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"23", false))
            countriesNameList.add("Kuwait");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"24", false))
            countriesNameList.add("Kyrgyzstan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"25", false))
            countriesNameList.add("Lao People's Democratic Republic");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"26", false))
            countriesNameList.add("Lebanon");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"27", false))
            countriesNameList.add("Maldives");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"28", false))
            countriesNameList.add("Mongolia");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"29", false))
            countriesNameList.add("Myanmar");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"30", false))
            countriesNameList.add("Nepal");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"31", false))
            countriesNameList.add("Oman");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"32", false))
            countriesNameList.add("Pakistan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"33", false))
            countriesNameList.add("Singapore");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"34", false))
            countriesNameList.add("Sri Lanka");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"35", false))
            countriesNameList.add("Syrian Arab Republic");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"36", false))
            countriesNameList.add("Tajikistan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"37", false))
            countriesNameList.add("Thailand");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"38", false))
            countriesNameList.add("Turkmenistan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"39", false))
            countriesNameList.add("Uzbekistan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"40", false))
            countriesNameList.add("Vietnam");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"41", false))
            countriesNameList.add("Yemen");
        //Africa locked countries
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Burkina Faso");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Burundi");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Cape Verde");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Central African Republic");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"20", false))
            countriesNameList.add("Chad");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"21", false))
            countriesNameList.add("Comoros");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"22", false))
            countriesNameList.add("Equatorial Guinea");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"23", false))
            countriesNameList.add("Eritrea");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"24", false))
            countriesNameList.add("Gabon");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"25", false))
            countriesNameList.add("Gambia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"26", false))
            countriesNameList.add("Guinea");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"27", false))
            countriesNameList.add("Guinea Bissau");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"28", false))
            countriesNameList.add("Lesotho");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"29", false))
            countriesNameList.add("Liberia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"30", false))
            countriesNameList.add("Madagascar");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"31", false))
            countriesNameList.add("Malawi");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"32", false))
            countriesNameList.add("Mali");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"33", false))
            countriesNameList.add("Mauritania");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"34", false))
            countriesNameList.add("Mozambique");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"35", false))
            countriesNameList.add("Namibia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"36", false))
            countriesNameList.add("Rwanda");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"37", false))
            countriesNameList.add("Sao Tome and Principe");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"38", false))
            countriesNameList.add("Seychelles");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"39", false))
            countriesNameList.add("Sierra Leone");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"40", false))
            countriesNameList.add("Somalia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"41", false))
            countriesNameList.add("Sudan");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"42", false))
            countriesNameList.add("Swaziland");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"43", false))
            countriesNameList.add("Tanzania");
        //North America locked countries
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Barbados");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("Trinidad and Tobago");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Antigua and Barbuda");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Panama");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Dominica");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"11", false))
            countriesNameList.add("Grenada");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"12", false))
            countriesNameList.add("Saint Lucia");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"13", false))
            countriesNameList.add("Saint Vincent and the Grenadines");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"14", false))
            countriesNameList.add("Belize");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"15", false))
            countriesNameList.add("El Salvador");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Guatemala");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Honduras");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Nicaragua");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Haiti");
        //South America locked countries
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"3", false))
            countriesNameList.add("Bolivia");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"4", false))
            countriesNameList.add("Chile");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"5", false))
            countriesNameList.add("Ecuador");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Guyana");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("Paraguay");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Peru");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Uruguay");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Venezuela");
        //Oceania locked countries
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"3", false))
            countriesNameList.add("Fiji");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"4", false))
            countriesNameList.add("Solomon Islands");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"5", false))
            countriesNameList.add("Vanuatu");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Samoa");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("Kiribati");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Tonga");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Marshall Islands");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Palau");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"11", false))
            countriesNameList.add("Nauru");

        //Unrecognized locked countries
        if(unrecognizedOnOff) {
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "0", false))
                countriesNameList.add("Abkhazia");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "1", false))
                countriesNameList.add("Kosovo");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "2", false))
                countriesNameList.add("Northern Cyprus");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "3", false))
                countriesNameList.add("Palestine");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "4", false))
                countriesNameList.add("Western Sahara");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "5", false))
                countriesNameList.add("South Ossetia");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "6", false))
                countriesNameList.add("Nagorno-Karabakh Republic");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "7", false))
                countriesNameList.add("Pridnestrovian Moldavian Republic");
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "8", false))
                countriesNameList.add("Somaliland");
        }

        if(othersOnOff) {
            //Others locked countries
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "0", false))
                countriesNameList.add("Christmas Island");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "1", false))
                countriesNameList.add("Cocos Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "2", false))
                countriesNameList.add("Norfolk Island");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "3", false))
                countriesNameList.add("Easter Island");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "4", false))
                countriesNameList.add("Hong Kong");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "5", false))
                countriesNameList.add("Macau");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "6", false))
                countriesNameList.add("Faroe Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "7", false))
                countriesNameList.add("Greenland");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "8", false))
                countriesNameList.add("Åland Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "9", false))
                countriesNameList.add("French Southern and Antarctic Lands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "10", false))
                countriesNameList.add("French Polynesia");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "11", false))
                countriesNameList.add("Wallis and Futuna");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "12", false))
                countriesNameList.add("Saint Barthelemy");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "13", false))
                countriesNameList.add("Saint Pierre and Miquelon");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "14", false))
                countriesNameList.add("New Caledonia");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "15", false))
                countriesNameList.add("Aruba");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "16", false))
                countriesNameList.add("Curaçao");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "17", false))
                countriesNameList.add("Sint Maarten");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "18", false))
                countriesNameList.add("Bonaire");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "19", false))
                countriesNameList.add("Sint Eustatius");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "20", false))
                countriesNameList.add("Saba");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "21", false))
                countriesNameList.add("Cook Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "22", false))
                countriesNameList.add("Niue");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "23", false))
                countriesNameList.add("Tokelau");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "24", false))
                countriesNameList.add("Alderney");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "25", false))
                countriesNameList.add("Guernsey");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "26", false))
                countriesNameList.add("Herm");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "27", false))
                countriesNameList.add("Isle of Man");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "28", false))
                countriesNameList.add("Jersey");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "29", false))
                countriesNameList.add("Sark");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "30", false))
                countriesNameList.add("Anguilla");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "31", false))
                countriesNameList.add("Ascension Island");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "32", false))
                countriesNameList.add("Bermuda");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "33", false))
                countriesNameList.add("British Antarctic Territory");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "34", false))
                countriesNameList.add("British Indian Ocean Territory");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "35", false))
                countriesNameList.add("British Virgin Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "36", false))
                countriesNameList.add("Cayman Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "37", false))
                countriesNameList.add("Falkland Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "38", false))
                countriesNameList.add("Gibraltar");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "39", false))
                countriesNameList.add("Montserrat");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "40", false))
                countriesNameList.add("Pitcairn Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "41", false))
                countriesNameList.add("Saint Helena");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "42", false))
                countriesNameList.add("South Georgia and the South Sandwich Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "43", false))
                countriesNameList.add("Tristan da Cunha");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "44", false))
                countriesNameList.add("Turks and Caicos Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "45", false))
                countriesNameList.add("American Samoa");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "46", false))
                countriesNameList.add("Guam");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "47", false))
                countriesNameList.add("Northern Mariana Islands");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "48", false))
                countriesNameList.add("Puerto Rico");
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "49", false))
                countriesNameList.add("U.S. Virgin Islands");
        }

        flagsList = new ArrayList<Integer>();
        //European unlocked countries flags
        flagsList.add(0, R.drawable.russianfederation);
        flagsList.add(1, R.drawable.france);
        flagsList.add(2, R.drawable.spain);
        flagsList.add(3, R.drawable.sweden);
        flagsList.add(4, R.drawable.norway);
        flagsList.add(5, R.drawable.finland);
        flagsList.add(6, R.drawable.poland);
        flagsList.add(7, R.drawable.italy);
        flagsList.add(8, R.drawable.greatbritain);
        flagsList.add(9, R.drawable.greece);
        flagsList.add(10, R.drawable.iceland);
        flagsList.add(11, R.drawable.austria);
        flagsList.add(12, R.drawable.czechrepublic);
        flagsList.add(13, R.drawable.denmark);
        flagsList.add(14, R.drawable.switzerland);
        //Asia unlocked countries flags
        flagsList.add(15, R.drawable.afghanistan);
        flagsList.add(16, R.drawable.china);
        flagsList.add(17, R.drawable.india);
        flagsList.add(18, R.drawable.indonesia);
        flagsList.add(19, R.drawable.islamicrepublicofiran);
        flagsList.add(20, R.drawable.iraq);
        flagsList.add(21, R.drawable.israel);
        flagsList.add(22, R.drawable.japan);
        flagsList.add(23, R.drawable.malaysia);
        flagsList.add(24, R.drawable.democraticpeoplesrepublicofkorea);
        flagsList.add(25, R.drawable.philippines);
        flagsList.add(26, R.drawable.qatar);
        flagsList.add(27, R.drawable.saudiarabia);
        flagsList.add(28, R.drawable.republicofkorea);
        flagsList.add(29, R.drawable.unitedarabemirates);
        //Africa unlocked countries flags
        flagsList.add(30, R.drawable.algeria);
        flagsList.add(31, R.drawable.angola);
        flagsList.add(32, R.drawable.cameroon);
        flagsList.add(33, R.drawable.congo);
        flagsList.add(34, R.drawable.cotedivoire);
        flagsList.add(35, R.drawable.egypt);
        flagsList.add(36, R.drawable.ghana);
        flagsList.add(37, R.drawable.kenya);
        flagsList.add(38, R.drawable.mauritius);
        flagsList.add(39, R.drawable.morocco);
        flagsList.add(40, R.drawable.nigeria);
        flagsList.add(41, R.drawable.senegal);
        flagsList.add(42, R.drawable.tunisia);
        flagsList.add(43, R.drawable.ethiopia);
        flagsList.add(44, R.drawable.benin);
        flagsList.add(45, R.drawable.botswana);
        //North America unlocked countries flags
        flagsList.add(46, R.drawable.unitedstates);
        flagsList.add(47, R.drawable.bahama);
        flagsList.add(48, R.drawable.mexico);
        flagsList.add(49, R.drawable.costarica);
        flagsList.add(50, R.drawable.cuba);
        flagsList.add(51, R.drawable.jamaica);
        //South America unlocked countries flags
        flagsList.add(52, R.drawable.argentina);
        flagsList.add(53, R.drawable.brazil);
        flagsList.add(54, R.drawable.colombia);
        //Oceania unlocked countries flags
        flagsList.add(55, R.drawable.australia);
        flagsList.add(56, R.drawable.papuanewguinea);
        flagsList.add(57, R.drawable.newzealand);

        //European locked countries
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN15", false))
            flagsList.add(R.drawable.ukraine);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN16", false))
            flagsList.add(R.drawable.romania);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN17", false))
            flagsList.add(R.drawable.belarus);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN18", false))
            flagsList.add(R.drawable.kazakhstan);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN19", false))
            flagsList.add(R.drawable.bulgaria);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN20", false))
            flagsList.add(R.drawable.hungary);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN21", false))
            flagsList.add(R.drawable.portugal);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN22", false))
            flagsList.add(R.drawable.serbia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN23", false))
            flagsList.add(R.drawable.ireland);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN24", false))
            flagsList.add(R.drawable.lithuania);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN25", false))
            flagsList.add(R.drawable.latvia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN26", false))
            flagsList.add(R.drawable.croatia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN27", false))
            flagsList.add(R.drawable.bosniaandherzegovina);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN28", false))
            flagsList.add(R.drawable.slovakia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN29", false))
            flagsList.add(R.drawable.estonia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN30", false))
            flagsList.add(R.drawable.netherlands);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN31", false))
            flagsList.add(R.drawable.republicofmoldova);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN32", false))
            flagsList.add(R.drawable.belgium);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN33", false))
            flagsList.add(R.drawable.albania);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN34", false))
            flagsList.add(R.drawable.republicofmacedonia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN35", false))
            flagsList.add(R.drawable.turkey);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN36", false))
            flagsList.add(R.drawable.slovenia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN37", false))
            flagsList.add(R.drawable.cyprus);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN38", false))
            flagsList.add(R.drawable.luxembourg);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN39", false))
            flagsList.add(R.drawable.andorra);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN40", false))
            flagsList.add(R.drawable.malta);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN41", false))
            flagsList.add(R.drawable.liechtenstein);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN42", false))
            flagsList.add(R.drawable.sanmarino);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN43", false))
            flagsList.add(R.drawable.monaco);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN44", false))
            flagsList.add(R.drawable.germany);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN45", false))
            flagsList.add(R.drawable.montenegro);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN46", false))
            flagsList.add(R.drawable.georgia);
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN47", false))
            flagsList.add(R.drawable.vatican);
        //Asia locked countries
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"15", false))
            flagsList.add(R.drawable.azerbaijan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"16", false))
            flagsList.add(R.drawable.bahrain);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"17", false))
            flagsList.add(R.drawable.bangladesh);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"18", false))
            flagsList.add(R.drawable.bruneidarussalam);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"19", false))
            flagsList.add(R.drawable.cambodia);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"20", false))
            flagsList.add(R.drawable.armenia);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"21", false))
            flagsList.add(R.drawable.bhutan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"22", false))
            flagsList.add(R.drawable.jordan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"23", false))
            flagsList.add(R.drawable.kuwait);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"24", false))
            flagsList.add(R.drawable.kyrgyzstan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"25", false))
            flagsList.add(R.drawable.laopeoplesdemocraticrepublic);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"26", false))
            flagsList.add(R.drawable.lebanon);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"27", false))
            flagsList.add(R.drawable.maldives);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"28", false))
            flagsList.add(R.drawable.mongolia);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"29", false))
            flagsList.add(R.drawable.myanmar);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"30", false))
            flagsList.add(R.drawable.nepal);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"31", false))
            flagsList.add(R.drawable.oman);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"32", false))
            flagsList.add(R.drawable.pakistan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"33", false))
            flagsList.add(R.drawable.singapore);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"34", false))
            flagsList.add(R.drawable.srilanka);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"35", false))
            flagsList.add(R.drawable.syrianarabrepublic);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"36", false))
            flagsList.add(R.drawable.tajikistan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"37", false))
            flagsList.add(R.drawable.thailand);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"38", false))
            flagsList.add(R.drawable.turkmenistan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"39", false))
            flagsList.add(R.drawable.uzbekistan);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"40", false))
            flagsList.add(R.drawable.vietnam);
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"41", false))
            flagsList.add(R.drawable.yemen);

        //Africa locked countries
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"16", false))
            flagsList.add(R.drawable.burkinafaso);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"17", false))
            flagsList.add(R.drawable.burundi);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"18", false))
            flagsList.add(R.drawable.capeverde);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"19", false))
            flagsList.add(R.drawable.centralafricanrepublic);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"20", false))
            flagsList.add(R.drawable.chad);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"21", false))
            flagsList.add(R.drawable.comoros);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"22", false))
            flagsList.add(R.drawable.equatorialguinea);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"23", false))
            flagsList.add(R.drawable.eritrea);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"24", false))
            flagsList.add(R.drawable.gabon);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"25", false))
            flagsList.add(R.drawable.gambia);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"26", false))
            flagsList.add(R.drawable.guinea);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"27", false))
            flagsList.add(R.drawable.guineabissau);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"28", false))
            flagsList.add(R.drawable.lesotho);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"29", false))
            flagsList.add(R.drawable.liberia);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"30", false))
            flagsList.add(R.drawable.madagascar);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"31", false))
            flagsList.add(R.drawable.malawi);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"32", false))
            flagsList.add(R.drawable.mali);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"33", false))
            flagsList.add(R.drawable.mauritania);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"34", false))
            flagsList.add(R.drawable.mozambique);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"35", false))
            flagsList.add(R.drawable.nambia);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"36", false))
            flagsList.add(R.drawable.rwanda);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"37", false))
            flagsList.add(R.drawable.saotomeandprincipe);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"38", false))
            flagsList.add(R.drawable.seychelles);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"39", false))
            flagsList.add(R.drawable.sierraleone);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"40", false))
            flagsList.add(R.drawable.somalia);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"41", false))
            flagsList.add(R.drawable.sudan);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"42", false))
            flagsList.add(R.drawable.swaziland);
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"43", false))
            flagsList.add(R.drawable.tanzania);

        //North America locked countries
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"6", false))
            flagsList.add(R.drawable.barbados);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"7", false))
            flagsList.add(R.drawable.trinidadandtobago);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"8", false))
            flagsList.add(R.drawable.antiguaandbarbuda);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"9", false))
            flagsList.add(R.drawable.panama);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"10", false))
            flagsList.add(R.drawable.dominica);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"11", false))
            flagsList.add(R.drawable.grenada);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"12", false))
            flagsList.add(R.drawable.saintlucia);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"13", false))
            flagsList.add(R.drawable.stvincentandgrenadines);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"14", false))
            flagsList.add(R.drawable.belize);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"15", false))
            flagsList.add(R.drawable.elsalvador);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"16", false))
            flagsList.add(R.drawable.guatemala);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"17", false))
            flagsList.add(R.drawable.honduras);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"18", false))
            flagsList.add(R.drawable.nicaragua);
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"19", false))
            flagsList.add(R.drawable.haiti);

        //South America locked countries
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"3", false))
            flagsList.add(R.drawable.bolivia);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"4", false))
            flagsList.add(R.drawable.chile);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"5", false))
            flagsList.add(R.drawable.ecuador);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"6", false))
            flagsList.add(R.drawable.guyana);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"7", false))
            flagsList.add(R.drawable.paraguay);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"8", false))
            flagsList.add(R.drawable.peru);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"9", false))
            flagsList.add(R.drawable.uruguay);
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"10", false))
            flagsList.add(R.drawable.venezuela);

        //Oceania locked countries
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"3", false))
            flagsList.add(R.drawable.fiji);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"4", false))
            flagsList.add(R.drawable.solomonislands);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"5", false))
            flagsList.add(R.drawable.vanuatu);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"6", false))
            flagsList.add(R.drawable.samoa);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"7", false))
            flagsList.add(R.drawable.kiribati);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"8", false))
            flagsList.add(R.drawable.tonga);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"9", false))
            flagsList.add(R.drawable.marshallislands);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"10", false))
            flagsList.add(R.drawable.palau);
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"11", false))
            flagsList.add(R.drawable.nauru);

        if(unrecognizedOnOff) {
            //Unrecognized locked countries
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "0", false))
                flagsList.add(R.drawable.abkhazia);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "1", false))
                flagsList.add(R.drawable.kosovo);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "2", false))
                flagsList.add(R.drawable.northerncyprus);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "3", false))
                flagsList.add(R.drawable.palestine);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "4", false))
                flagsList.add(R.drawable.westernsahara);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "5", false))
                flagsList.add(R.drawable.southossetia);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "6", false))
                flagsList.add(R.drawable.nagornokarabakhrepublic);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "7", false))
                flagsList.add(R.drawable.pridnestrovianmoldavianrepublic);
            if (preferences.getBoolean("UNRECOGNIZEDNEWFLAGBOOLEAN" + "8", false))
                flagsList.add(R.drawable.somaliland);
        }


        //Others locked countries
        if(othersOnOff) {
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "0", false))
                flagsList.add(R.drawable.christmasisland);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "1", false))
                flagsList.add(R.drawable.cocosislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "2", false))
                flagsList.add(R.drawable.norfolkisland);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "3", false))
                flagsList.add(R.drawable.easterisland);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "4", false))
                flagsList.add(R.drawable.hongkong);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "5", false))
                flagsList.add(R.drawable.macau);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "6", false))
                flagsList.add(R.drawable.faroeislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "7", false))
                flagsList.add(R.drawable.greenland);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "8", false))
                flagsList.add(R.drawable.alandislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "9", false))
                flagsList.add(R.drawable.frenchsouthernandantarcticlands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "10", false))
                flagsList.add(R.drawable.frenchpolynesia);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "11", false))
                flagsList.add(R.drawable.wallisandfutuna);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "12", false))
                flagsList.add(R.drawable.saintbarthelemy);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "13", false))
                flagsList.add(R.drawable.saintpierreandmiquelon);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "14", false))
                flagsList.add(R.drawable.newcaledonia);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "15", false))
                flagsList.add(R.drawable.aruba);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "16", false))
                flagsList.add(R.drawable.curacao);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "17", false))
                flagsList.add(R.drawable.sintmaarten);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "18", false))
                flagsList.add(R.drawable.bonaire);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "19", false))
                flagsList.add(R.drawable.sinteustatius);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "20", false))
                flagsList.add(R.drawable.saba);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "21", false))
                flagsList.add(R.drawable.cookislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "22", false))
                flagsList.add(R.drawable.niue);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "23", false))
                flagsList.add(R.drawable.tokelau);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "24", false))
                flagsList.add(R.drawable.alderney);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "25", false))
                flagsList.add(R.drawable.guernsey);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "26", false))
                flagsList.add(R.drawable.herm);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "27", false))
                flagsList.add(R.drawable.isleofman);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "28", false))
                flagsList.add(R.drawable.jersey);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "29", false))
                flagsList.add(R.drawable.sark);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "30", false))
                flagsList.add(R.drawable.anguilla);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "31", false))
                flagsList.add(R.drawable.ascensionisland);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "32", false))
                flagsList.add(R.drawable.bermuda);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "33", false))
                flagsList.add(R.drawable.britishantarcticterritory);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "34", false))
                flagsList.add(R.drawable.britishindianoceanterritory);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "35", false))
                flagsList.add(R.drawable.britishvirginislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "36", false))
                flagsList.add(R.drawable.caymanislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "37", false))
                flagsList.add(R.drawable.falklandislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "38", false))
                flagsList.add(R.drawable.gibraltar);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "39", false))
                flagsList.add(R.drawable.monserrat);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "40", false))
                flagsList.add(R.drawable.pitcairnislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "41", false))
                flagsList.add(R.drawable.sainthelena);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "42", false))
                flagsList.add(R.drawable.southgeorgiaandthesouthsandwichislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "43", false))
                flagsList.add(R.drawable.tristandacunha);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "44", false))
                flagsList.add(R.drawable.turksandcaicosislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "45", false))
                flagsList.add(R.drawable.americansamoa);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "46", false))
                flagsList.add(R.drawable.guam);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "47", false))
                flagsList.add(R.drawable.northernmarianaislands);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "48", false))
                flagsList.add(R.drawable.puertorico);
            if (preferences.getBoolean("OTHERSNEWFLAGBOOLEAN" + "49", false))
                flagsList.add(R.drawable.usvirginislands);
        }

        setFlags();

        if (!removeAdsBoolean) {
            AdView adview = (AdView) findViewById(R.id.adViewArcadeGame);
            adview.loadAd(new AdRequest.Builder().build());
        }

    }



    public int setFlags() {

        ArrayList<Integer> flagsListInMethod = new ArrayList<Integer>(flagsList);
        ArrayList<Integer> flagListCopy = new ArrayList<Integer>();
        ArrayList<String> countriesNameListInMethod = new ArrayList<String>(countriesNameList);
        ArrayList<String> countriesNameListCopy = new ArrayList<String>();

        buttonFlag1.setClickable(true);
        buttonFlag2.setClickable(true);
        buttonFlag3.setClickable(true);
        buttonFlag4.setClickable(true);

        buttonFlagRedX1.setVisibility(View.INVISIBLE);
        buttonFlagRedX2.setVisibility(View.INVISIBLE);
        buttonFlagRedX3.setVisibility(View.INVISIBLE);
        buttonFlagRedX4.setVisibility(View.INVISIBLE);

        buttonFlagBackground1.setVisibility(View.INVISIBLE);
        buttonFlagBackground2.setVisibility(View.INVISIBLE);
        buttonFlagBackground3.setVisibility(View.INVISIBLE);
        buttonFlagBackground4.setVisibility(View.INVISIBLE);

        //scoreTextView.setText("" + totalNumberOfStars);



        Random rand = new Random();



        //setting the flags and removing flag from array
        int firstFlagID = rand.nextInt(flagsListInMethod.size());
        buttonFlag1.setImageResource(flagsListInMethod.get(firstFlagID));
        buttonFlag1.setTag(flagsListInMethod.get(firstFlagID));
        flagListCopy.add(flagsListInMethod.get(firstFlagID));
        countriesNameListCopy.add(countriesNameListInMethod.get(firstFlagID));
        flagsListInMethod.remove(firstFlagID);
        countriesNameListInMethod.remove(firstFlagID);

        int secondFlagID = rand.nextInt(flagsListInMethod.size());
        buttonFlag2.setImageResource(flagsListInMethod.get(secondFlagID));
        buttonFlag2.setTag(flagsListInMethod.get(secondFlagID));
        flagListCopy.add(flagsListInMethod.get(secondFlagID));
        countriesNameListCopy.add(countriesNameListInMethod.get(secondFlagID));
        flagsListInMethod.remove(secondFlagID);
        countriesNameListInMethod.remove(secondFlagID);

        int thirdFlagID = rand.nextInt(flagsListInMethod.size());
        buttonFlag3.setImageResource(flagsListInMethod.get(thirdFlagID));
        buttonFlag3.setTag(flagsListInMethod.get(thirdFlagID));
        flagListCopy.add(flagsListInMethod.get(thirdFlagID));
        countriesNameListCopy.add(countriesNameListInMethod.get(thirdFlagID));
        flagsListInMethod.remove(thirdFlagID);
        countriesNameListInMethod.remove(thirdFlagID);

        int fourthFlagID = rand.nextInt(flagsListInMethod.size());
        buttonFlag4.setImageResource(flagsListInMethod.get(fourthFlagID));
        buttonFlag4.setTag(flagsListInMethod.get(fourthFlagID));
        flagListCopy.add(flagsListInMethod.get(fourthFlagID));
        countriesNameListCopy.add(countriesNameListInMethod.get(fourthFlagID));
        flagsListInMethod.remove(fourthFlagID);
        countriesNameListInMethod.remove(fourthFlagID);

        int randID = rand.nextInt(flagListCopy.size());
        String answer = countriesNameListCopy.get(randID);
        questionTextView.setText(answer);
        updateEuropeanNumber(answer);
        updateAsianNumber(answer);
        updateAfricanNumber(answer);
        updateNorthAmericanNumber(answer);
        updateSouthAmericanNumber(answer);
        updateOceaniaNumber(answer);

        for (int i = 0; i < countriesNameList.size(); i++) {
            if(countriesNameList.get(i).equals(countriesNameListCopy.get(randID))){
                returningID = i;
            }
        }



        return returningID;


    }

    public void buttonOnClickArcadeGame(View v) {
        switch (v.getId()) {
            case R.id.buttonImageView:
                if (soundOnOff) {
                    rightSound = MediaPlayer.create(this, R.raw.correct_tone);
                    wrongSound = MediaPlayer.create(this, R.raw.wrong_tone);

                    rightSound.setVolume(0.25f, 0.25f);
                    wrongSound.setVolume(0.25f, 0.25f);
                }
                if (returningID == flagsList.indexOf((Integer)buttonFlag1.getTag())){
                    buttonFlagRedX1.setVisibility(View.VISIBLE);
                    buttonFlagRedX1.setBackgroundResource(R.drawable.greencorrect_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX1.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(vibratePattern, -1);
                    if (soundOnOff) {
                        rightSound.start();
                    }
                    buttonFlag1.setClickable(false);
                    buttonFlag2.setClickable(false);
                    buttonFlag3.setClickable(false);
                    buttonFlag4.setClickable(false);
                    countriesNameList.remove(returningID);
                    flagsList.remove(returningID);
                    left++;
                    totalNumberOfStars = totalNumberOfStars + 2;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer ++;
                    scoreDrawableNumber = left;
                    if(left <= 20) {
                        String uri = "@drawable/scorebgtop" + scoreDrawableNumber;
                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                        Drawable res = getResources().getDrawable(imageResource);
                        scoreImageView.setImageDrawable(res);
                    }
                    europeanAchievementOne();
                    europeanAchievementTwo();
                    europeanAchievementThree();
                    asianAchievementOne();
                    asianAchievementTwo();
                    asianAchievementThree();
                    africanAchievementOne();
                    africanAchievementTwo();
                    africanAchievementThree();
                    northAmericanAchievementOne();
                    northAmericanAchievementTwo();
                    northAmericanAchievementThree();
                    southAmericanAchievementOne();
                    southAmericanAchievementTwo();
                    southAmericanAchievementThree();
                    oceaniaAchievementOne();
                    oceaniaAchievementTwo();
                    oceaniaAchievementThree();
                    if (correctAnswer >= 20) {
                        cleanSheet++;
                        if (cleanSheet == 10 && isCleanSheetFullFor10Correct){
                            toastForUnlockAchievement10();
                        }
                        if (cleanSheet == 25 && isCleanSheetFullFor25Correct){
                            toastForUnlockAchievement25();
                        }
                        if (cleanSheet == 50 && isCleanSheetFullFor50Correct){
                            toastForUnlockAchievement50();
                        }
                        if (cleanSheet == 100 && isCleanSheetFullFor100Correct){
                            toastForUnlockAchievement100();
                        }
                        saveCleanSheet(cleanSheet);
                        updateProgressBar(5, cleanSheet);
                        updateProgressBar(6, cleanSheet);
                        updateProgressBar(7, cleanSheet);
                        updateProgressBar(8, cleanSheet);

                        correctAnswer = 0;
                    }


                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            if (left < 21) {
                                setFlags();
                            }else{
                                updateNumberOfGames();
                                amateurAchievement();
                                beginnerAchievementOne();
                                beginnerAchievementTwo();
                                beginnerAchievementThree();
                                beginnerAchievementFour();
                                setDialog();
                            }
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 550);
            } else {
                    buttonFlag1.setClickable(false);
                    buttonFlagRedX1.setVisibility(View.VISIBLE);
                    buttonFlagBackground1.setVisibility(View.VISIBLE);
                    buttonFlagBackground1.getDrawable().setAlpha(120);
                    buttonFlagRedX1.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX1.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer = 0;
                };

                break;

            case R.id.buttonImageView2:
                if (soundOnOff) {
                    rightSound = MediaPlayer.create(this, R.raw.correct_tone);
                    wrongSound = MediaPlayer.create(this, R.raw.wrong_tone);

                    rightSound.setVolume(0.25f, 0.25f);
                    wrongSound.setVolume(0.25f, 0.25f);
                }
                if (returningID == flagsList.indexOf((Integer)buttonFlag2.getTag())){
                    buttonFlagRedX2.setVisibility(View.VISIBLE);
                    buttonFlagRedX2.setBackgroundResource(R.drawable.greencorrect_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX2.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(vibratePattern, -1);
                    if (soundOnOff) {
                        rightSound.start();
                    }
                    buttonFlag1.setClickable(false);
                    buttonFlag2.setClickable(false);
                    buttonFlag3.setClickable(false);
                    buttonFlag4.setClickable(false);
                    countriesNameList.remove(returningID);
                    flagsList.remove(returningID);
                    left++;
                    totalNumberOfStars = totalNumberOfStars + 2;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer ++;
                    scoreDrawableNumber = left;
                    if(left <= 20) {
                        String uri = "@drawable/scorebgtop" + scoreDrawableNumber;
                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                        Drawable res = getResources().getDrawable(imageResource);
                        scoreImageView.setImageDrawable(res);
                    }
                    europeanAchievementOne();
                    europeanAchievementTwo();
                    europeanAchievementThree();
                    asianAchievementOne();
                    asianAchievementTwo();
                    asianAchievementThree();
                    africanAchievementOne();
                    africanAchievementTwo();
                    africanAchievementThree();
                    northAmericanAchievementOne();
                    northAmericanAchievementTwo();
                    northAmericanAchievementThree();
                    southAmericanAchievementOne();
                    southAmericanAchievementTwo();
                    southAmericanAchievementThree();
                    oceaniaAchievementOne();
                    oceaniaAchievementTwo();
                    oceaniaAchievementThree();
                    if (correctAnswer >= 20) {
                        cleanSheet++;
                        if (cleanSheet == 10 && isCleanSheetFullFor10Correct){
                            toastForUnlockAchievement10();
                        }
                        if (cleanSheet == 25 && isCleanSheetFullFor25Correct){
                            toastForUnlockAchievement25();
                        }
                        if (cleanSheet == 50 && isCleanSheetFullFor50Correct){
                            toastForUnlockAchievement50();
                        }
                        if (cleanSheet == 100 && isCleanSheetFullFor100Correct){
                            toastForUnlockAchievement100();
                        }
                        saveCleanSheet(cleanSheet);
                        updateProgressBar(5, cleanSheet);
                        updateProgressBar(6, cleanSheet);
                        updateProgressBar(7, cleanSheet);
                        updateProgressBar(8, cleanSheet);
                        correctAnswer = 0;
                    }

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            if (left < 21) {
                                setFlags();
                            }else{
                                updateNumberOfGames();
                                amateurAchievement();
                                beginnerAchievementOne();
                                beginnerAchievementTwo();
                                beginnerAchievementThree();
                                beginnerAchievementFour();
                                setDialog();
                            }

                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 550);
                } else {
                    buttonFlag2.setClickable(false);
                    buttonFlagRedX2.setVisibility(View.VISIBLE);
                    buttonFlagBackground2.setVisibility(View.VISIBLE);
                    buttonFlagBackground2.getDrawable().setAlpha(120);
                    buttonFlagRedX2.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX2.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer = 0;
                };
                break;

            case R.id.buttonImageView3:
                if (soundOnOff) {
                    rightSound = MediaPlayer.create(this, R.raw.correct_tone);
                    wrongSound = MediaPlayer.create(this, R.raw.wrong_tone);

                    rightSound.setVolume(0.25f, 0.25f);
                    wrongSound.setVolume(0.25f, 0.25f);
                }
                if (returningID == flagsList.indexOf((Integer)buttonFlag3.getTag())){
                    buttonFlagRedX3.setVisibility(View.VISIBLE);
                    buttonFlagRedX3.setBackgroundResource(R.drawable.greencorrect_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX3.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(vibratePattern, -1);
                    if (soundOnOff) {
                        rightSound.start();
                    }
                    buttonFlag1.setClickable(false);
                    buttonFlag2.setClickable(false);
                    buttonFlag3.setClickable(false);
                    buttonFlag4.setClickable(false);
                    countriesNameList.remove(returningID);
                    flagsList.remove(returningID);
                    left++;
                    totalNumberOfStars = totalNumberOfStars + 2;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer ++;
                    scoreDrawableNumber = left;
                    if(left <= 20) {
                        String uri = "@drawable/scorebgtop" + scoreDrawableNumber;
                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                        Drawable res = getResources().getDrawable(imageResource);
                        scoreImageView.setImageDrawable(res);
                    }
                    europeanAchievementOne();
                    europeanAchievementTwo();
                    europeanAchievementThree();
                    asianAchievementOne();
                    asianAchievementTwo();
                    asianAchievementThree();
                    africanAchievementOne();
                    africanAchievementTwo();
                    africanAchievementThree();
                    northAmericanAchievementOne();
                    northAmericanAchievementTwo();
                    northAmericanAchievementThree();
                    southAmericanAchievementOne();
                    southAmericanAchievementTwo();
                    southAmericanAchievementThree();
                    oceaniaAchievementOne();
                    oceaniaAchievementTwo();
                    oceaniaAchievementThree();
                    if (correctAnswer >= 20) {
                        cleanSheet++;
                        if (cleanSheet == 10 && isCleanSheetFullFor10Correct){
                            toastForUnlockAchievement10();
                        }
                        if (cleanSheet == 25 && isCleanSheetFullFor25Correct){
                            toastForUnlockAchievement25();
                        }
                        if (cleanSheet == 50 && isCleanSheetFullFor50Correct){
                            toastForUnlockAchievement50();
                        }
                        if (cleanSheet == 100 && isCleanSheetFullFor100Correct){
                            toastForUnlockAchievement100();
                        }
                        saveCleanSheet(cleanSheet);
                        updateProgressBar(5, cleanSheet);
                        updateProgressBar(6, cleanSheet);
                        updateProgressBar(7, cleanSheet);
                        updateProgressBar(8, cleanSheet);
                        correctAnswer = 0;
                    }

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            if (left < 21) {
                                setFlags();
                            }else{
                                updateNumberOfGames();
                                amateurAchievement();
                                beginnerAchievementOne();
                                beginnerAchievementTwo();
                                beginnerAchievementThree();
                                beginnerAchievementFour();
                                setDialog();
                            }

                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 550);
                } else {
                    buttonFlag3.setClickable(false);
                    buttonFlagRedX3.setVisibility(View.VISIBLE);
                    buttonFlagBackground3.setVisibility(View.VISIBLE);
                    buttonFlagBackground3.getDrawable().setAlpha(120);
                    buttonFlagRedX3.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX3.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer = 0;
                };
                break;

            case R.id.buttonImageView4:
                if (soundOnOff) {
                    rightSound = MediaPlayer.create(this, R.raw.correct_tone);
                    wrongSound = MediaPlayer.create(this, R.raw.wrong_tone);

                    rightSound.setVolume(0.25f, 0.25f);
                    wrongSound.setVolume(0.25f, 0.25f);
                }
                if (returningID == flagsList.indexOf((Integer)buttonFlag4.getTag())){
                    buttonFlagRedX4.setVisibility(View.VISIBLE);
                    buttonFlagRedX4.setBackgroundResource(R.drawable.greencorrect_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX4.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(vibratePattern, -1);
                    if (soundOnOff) {
                        rightSound.start();
                    }
                    buttonFlag1.setClickable(false);
                    buttonFlag2.setClickable(false);
                    buttonFlag3.setClickable(false);
                    buttonFlag4.setClickable(false);
                    countriesNameList.remove(returningID);
                    flagsList.remove(returningID);
                    left++;
                    totalNumberOfStars = totalNumberOfStars + 2;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer ++;
                    scoreDrawableNumber = left;
                    if(left <= 20) {
                        String uri = "@drawable/scorebgtop" + scoreDrawableNumber;
                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                        Drawable res = getResources().getDrawable(imageResource);
                        scoreImageView.setImageDrawable(res);
                    }
                    europeanAchievementOne();
                    europeanAchievementTwo();
                    europeanAchievementThree();
                    asianAchievementOne();
                    asianAchievementTwo();
                    asianAchievementThree();
                    africanAchievementOne();
                    africanAchievementTwo();
                    africanAchievementThree();
                    northAmericanAchievementOne();
                    northAmericanAchievementTwo();
                    northAmericanAchievementThree();
                    southAmericanAchievementOne();
                    southAmericanAchievementTwo();
                    southAmericanAchievementThree();
                    oceaniaAchievementOne();
                    oceaniaAchievementTwo();
                    oceaniaAchievementThree();
                    if (correctAnswer >= 20) {
                        cleanSheet++;
                        if (cleanSheet == 10 && isCleanSheetFullFor10Correct){
                            toastForUnlockAchievement10();
                        }
                        if (cleanSheet == 25 && isCleanSheetFullFor25Correct){
                            toastForUnlockAchievement25();
                        }
                        if (cleanSheet == 50 && isCleanSheetFullFor50Correct){
                            toastForUnlockAchievement50();
                        }

                        if (cleanSheet == 100 && isCleanSheetFullFor100Correct){
                            toastForUnlockAchievement100();
                        }
                        saveCleanSheet(cleanSheet);
                        updateProgressBar(5, cleanSheet);
                        updateProgressBar(6, cleanSheet);
                        updateProgressBar(7, cleanSheet);
                        updateProgressBar(8, cleanSheet);
                        correctAnswer = 0;
                    }

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            if (left < 21) {
                                setFlags();
                            }else{
                                updateNumberOfGames();
                                amateurAchievement();
                                beginnerAchievementOne();
                                beginnerAchievementTwo();
                                beginnerAchievementThree();
                                beginnerAchievementFour();
                                setDialog();
                            }
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 550);
                } else {
                    buttonFlag4.setClickable(false);
                    buttonFlagRedX4.setVisibility(View.VISIBLE);
                    buttonFlagBackground4.setVisibility(View.VISIBLE);
                    buttonFlagBackground4.getDrawable().setAlpha(120);
                    buttonFlagRedX4.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX4.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    //scoreTextView.setText(""+totalNumberOfStars);
                    correctAnswer = 0;
                };
                break;
        }

    }

    public void setDialog() {

        int numberOfStars = totalNumberOfStars;
        int summaryStars;

        buttonFlag1.setVisibility(View.INVISIBLE);
        buttonFlag2.setVisibility(View.INVISIBLE);
        buttonFlag3.setVisibility(View.INVISIBLE);
        buttonFlag4.setVisibility(View.INVISIBLE);
        buttonFlagBackground1.setVisibility(View.INVISIBLE);
        buttonFlagBackground2.setVisibility(View.INVISIBLE);
        buttonFlagBackground3.setVisibility(View.INVISIBLE);
        buttonFlagBackground4.setVisibility(View.INVISIBLE);
        buttonFlagRedX1.setVisibility(View.INVISIBLE);
        buttonFlagRedX2.setVisibility(View.INVISIBLE);
        buttonFlagRedX3.setVisibility(View.INVISIBLE);
        buttonFlagRedX4.setVisibility(View.INVISIBLE);
        //scoreTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);



        SharedPreferences preferences = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        summaryStars = preferences.getInt("TOTALSTARS", 0);

        summaryStars = summaryStars + numberOfStars;

        editor.putInt("TOTALSTARS", summaryStars);
        editor.commit();



                // Create custom dialog object
        final Dialog dialog = new Dialog(NormalGameNameFlagActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.result_dialog);
        // Set dialog title
        dialog.setTitle("Congratulations");

        // set values for custom dialog components - text, image and button
//        final TextView textViewDialog = (TextView) dialog.findViewById(R.id.textDialog);
//        textViewDialog.setText("You received " + numberOfStars + " stars!");
//        ImageView imageViewDialog = (ImageView) dialog.findViewById(R.id.imageDialog);
//        imageViewDialog.setImageResource(R.drawable.greencorrect);
        int totalStars = preferences.getInt("TOTALSTARS1", 0);
        summaryStars = summaryStars + totalStars;
        editor.putInt("TOTALSTARS", summaryStars);
        editor.commit();


        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Button submitScoreButton = (Button) dialog.findViewById(R.id.submitScoreButton);

        submitScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

        final Button playAgainButton = (Button) dialog.findViewById(R.id.playAgainButton);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NormalGameNameFlagActivity.class);
                startActivity(intent);
                finish();
            }
        });



    };

    @Override
    public void onBackPressed() {

        final Dialog dialog = new Dialog(NormalGameNameFlagActivity.this, R.style.Theme_Dialog);


        dialog.setContentView(R.layout.dialog);
        Button dialogNo = (Button)dialog.findViewById(R.id.dialogNo);
        Button dialogYes = (Button)dialog.findViewById(R.id.dialogYes);

        dialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        dialog.show();



    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void updateProgressBar(int position, int procent){
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("PROGRESSBAR"+position, procent);
        editor.commit();
    }



    public void saveCleanSheet(int cleanSheetSum){
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("CLEANSHEET", cleanSheetSum);
        editor.commit();
    }

    public int loadCleanSheet (){
        SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        int cleanSheet = preferences.getInt("CLEANSHEET", 0);
        return cleanSheet;

    }

    public void toastForUnlockAchievement10(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ISCLEANSHEETFULLFOR10", false);
        editor.commit();

        SharedPreferences preferences2 = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor2 = preferences2.edit();

        editor2.putInt("100REWARD", 100);
        editor2.commit();

        editor.putBoolean("BOOLEANFOR10", true);
        editor.commit();

        Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked MASTER I achievement. 100 stars are added to your account", Toast.LENGTH_LONG);
        toast.show();

     }

    public void toastForUnlockAchievement25(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ISCLEANSHEETFULLFOR25", false);
        editor.commit();

        SharedPreferences preferences2 = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor2 = preferences2.edit();

        editor2.putInt("300REWARD", 300);
        editor2.commit();

        editor.putBoolean("BOOLEANFOR25", true);
        editor.commit();

        Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked MASTER II achievement. 300 stars are added to your account", Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastForUnlockAchievement50(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ISCLEANSHEETFULLFOR50", false);
        editor.commit();

        SharedPreferences preferences2 = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor2 = preferences2.edit();

        editor2.putInt("700REWARD", 700);
        editor2.commit();

        editor.putBoolean("BOOLEANFOR50", true);
        editor.commit();

        Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked MASTER III achievement. 700 stars are added to your account", Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastForUnlockAchievement100(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ISCLEANSHEETFULLFOR100", false);
        editor.commit();

        SharedPreferences preferences2 = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor2 = preferences2.edit();

        editor2.putInt("1500REWARD", 1500);
        editor2.commit();

        editor.putBoolean("BOOLEANFOR100", true);
        editor.commit();

        Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked MASTER IV achievement. 1500 stars are added to your account", Toast.LENGTH_LONG);
        toast.show();

    }

    public void updateNumberOfGames(){
        SharedPreferences preferences33 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        numberOfGames = preferences33.getInt("NUMBEROFGAMES", 0);
        numberOfGames++;
        SharedPreferences.Editor editor = preferences33.edit();
        editor.putInt("NUMBEROFGAMES", numberOfGames);
        editor.commit();
    }

    public void amateurAchievement(){
        if (numberOfGames == 1 && amateurBoolean){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("AMATEUR", 50);
            editor.putBoolean("BOOLEANAMATEUR", false);
            editor.putBoolean("BOOLEANFORAMATEUR",true);
            editor.commit();
            updateProgressBar(0, numberOfGames);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked AMATEUR achievement. 50 stars are added to your account. " +
                    "You can see other achievements in main screen.", Toast.LENGTH_LONG);
            toast.show();
        } else if (numberOfGames<1){
            updateProgressBar(0, numberOfGames);
        }

    }

    public void beginnerAchievementOne(){
        if (numberOfGames == 10 && beginnerOneBoolean){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("BEGINNERONE", 50);
            editor.putBoolean("BOOLEANBEGINNERONE", false);
            editor.putBoolean("BOOLEANFORBEGINNERONE",true);
            editor.commit();
            updateProgressBar(1, numberOfGames);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked BEGINNER I achievement. 50 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (numberOfGames<10){
            updateProgressBar(1, numberOfGames);
        }

    }

    public void beginnerAchievementTwo(){
        if (numberOfGames == 25 && beginnerTwoBoolean){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("BEGINNERTWO", 150);
            editor.putBoolean("BOOLEANBEGINNERTWO", false);
            editor.putBoolean("BOOLEANFORBEGINNERTWO",true);
            editor.commit();
            updateProgressBar(2, numberOfGames);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked BEGINNER II achievement. 150 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (numberOfGames<25) {
            updateProgressBar(2, numberOfGames);
        }

    }

    public void beginnerAchievementThree(){
        if (numberOfGames == 50 && beginnerThreeBoolean){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("BEGINNERTHREE", 400);
            editor.putBoolean("BOOLEANBEGINNERTHREE", false);
            editor.putBoolean("BOOLEANFORBEGINNERTHREE",true);
            editor.commit();
            updateProgressBar(3, numberOfGames);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked BEGINNER III achievement. 400 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (numberOfGames<50){
            updateProgressBar(3, numberOfGames);
        }

    }

    public void beginnerAchievementFour(){
        if (numberOfGames == 100 && beginnerFourBoolean){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("BEGINNERFOUR", 1000);
            editor.putBoolean("BOOLEANBEGINNERFOUR", false);
            editor.putBoolean("BOOLEANFORBEGINNERFOUR",true);
            editor.commit();
            updateProgressBar(4, numberOfGames);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked BEGINNER IV achievement. 1000 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (numberOfGames<100){
            updateProgressBar(4, numberOfGames);
        }

    }

    public void updateEuropeanNumber(String answer){
        for(int i = 0; i < europeArray.length; i++){
            if(answer.equals(europeArray[i])){
                europeanInt = preferences.getInt("EUROPEANINT", 0);
                europeanInt++;

            }
        }
    }

    public void updateAsianNumber(String answer){
        for(int i = 0; i < asiaArray.length; i++){
            if(answer.equals(asiaArray[i])){
                asianInt = preferences.getInt("ASIANINT", 0);
                asianInt++;

            }
        }
    }

    public void updateAfricanNumber(String answer){
        for(int i = 0; i < africaArray.length; i++){
            if(answer.equals(africaArray[i])){
                africanInt = preferences.getInt("AFRICANINT", 0);
                africanInt++;

            }
        }
    }

    public void updateNorthAmericanNumber(String answer){
        for(int i = 0; i < northAmericaArray.length; i++){
            if(answer.equals(northAmericaArray[i])){
                northAmericanInt = preferences.getInt("NORTHAMERICANINT", 0);
                northAmericanInt++;

            }
        }
    }

    public void updateSouthAmericanNumber(String answer){
        for(int i = 0; i < southAmericaArray.length; i++){
            if(answer.equals(southAmericaArray[i])){
                southAmericanInt = preferences.getInt("SOUTHAMERICANINT", 0);
                southAmericanInt++;

            }
        }
    }

    public void updateOceaniaNumber(String answer){
        for(int i = 0; i < oceaniaArray.length; i++){
            if(answer.equals(oceaniaArray[i])){
                oceaniaInt = preferences.getInt("OCEANIAINT", 0);
                oceaniaInt++;

            }
        }
    }

    public void europeanAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("EUROPEANINT", europeanInt);
        editor.commit();
        if (europeanInt == 100 && europeanOneBoolean){
            europeanOneBoolean = false;
            editor.putInt("EUROPEANONE", 200);
            editor.putBoolean("BOOLEANEUROPEANONE", false);
            editor.putBoolean("BOOLEANFOREUROPEANONE",true);
            editor.commit();
            updateProgressBar(13, europeanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked EUROPE I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (europeanInt<100){
            updateProgressBar(13, europeanInt);
        }

    }

    public void europeanAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("EUROPEANINT", europeanInt);
        editor.commit();
        if (europeanInt == 500 && europeanTwoBoolean){
            europeanTwoBoolean = false;
            editor.putInt("EUROPEANTWO", 1200);
            editor.putBoolean("BOOLEANEUROPEANTWO", false);
            editor.putBoolean("BOOLEANFOREUROPEANTWO",true);
            editor.commit();
            updateProgressBar(14, europeanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked EUROPE II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (europeanInt<500){
            updateProgressBar(14, europeanInt);
        }

    }

    public void europeanAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("EUROPEANINT", europeanInt);
        editor.commit();
        if (europeanInt == 1000 && europeanThreeBoolean){
            europeanThreeBoolean = false;
            editor.putInt("EUROPEANTHREE", 2500);
            editor.putBoolean("BOOLEANEUROPEANTHREE", false);
            editor.putBoolean("BOOLEANFOREUROPEANTHREE",true);
            editor.commit();
            updateProgressBar(15, europeanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked EUROPE III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (europeanInt<1000){
            updateProgressBar(15, europeanInt);
        }

    }

    public void asianAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("ASIANINT", asianInt);
        editor.commit();
        if (asianInt == 100 && asianOneBoolean){
            asianOneBoolean = false;
            editor.putInt("ASIANONE", 200);
            editor.putBoolean("BOOLEANASIANONE", false);
            editor.putBoolean("BOOLEANFORASIANONE",true);
            editor.commit();
            updateProgressBar(16, asianInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked ASIA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (asianInt<100){
            updateProgressBar(16, asianInt);
        }

    }

    public void asianAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("ASIANINT", asianInt);
        editor.commit();
        if (asianInt == 500 && asianTwoBoolean){
            asianTwoBoolean = false;
            editor.putInt("ASIANTWO", 1200);
            editor.putBoolean("BOOLEANASIANTWO", false);
            editor.putBoolean("BOOLEANFORASIANTWO",true);
            editor.commit();
            updateProgressBar(17, asianInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked ASIA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (asianInt<500){
            updateProgressBar(17, asianInt);
        }

    }

    public void asianAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("ASIANINT", asianInt);
        editor.commit();
        if (asianInt == 1000 && asianThreeBoolean){
            asianThreeBoolean = false;
            editor.putInt("ASIANTHREE", 2500);
            editor.putBoolean("BOOLEANASIANTHREE", false);
            editor.putBoolean("BOOLEANFORASIANTHREE",true);
            editor.commit();
            updateProgressBar(18, asianInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked ASIA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (asianInt<1000){
            updateProgressBar(18, asianInt);
        }

    }

    public void africanAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("AFRICANINT", africanInt);
        editor.commit();
        if (africanInt == 100 && africanOneBoolean){
            africanOneBoolean = false;
            editor.putInt("AFRICANONE", 200);
            editor.putBoolean("BOOLEANAFRICANONE", false);
            editor.putBoolean("BOOLEANFORAFRICANONE",true);
            editor.commit();
            updateProgressBar(19, africanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked AFRICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (africanInt<100){
            updateProgressBar(19, africanInt);
        }

    }

    public void africanAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("AFRICANINT", africanInt);
        editor.commit();
        if (africanInt == 500 && africanTwoBoolean){
            africanTwoBoolean = false;
            editor.putInt("AFRICANTWO", 1200);
            editor.putBoolean("BOOLEANAFRICANTWO", false);
            editor.putBoolean("BOOLEANFORAFRICANTWO",true);
            editor.commit();
            updateProgressBar(20, africanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked AFRICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (africanInt<500){
            updateProgressBar(20, africanInt);
        }

    }

    public void africanAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("AFRICANINT", africanInt);
        editor.commit();
        if (africanInt == 1000 && africanThreeBoolean){
            africanThreeBoolean = false;
            editor.putInt("AFRICANTHREE", 2500);
            editor.putBoolean("BOOLEANAFRICANTHREE", false);
            editor.putBoolean("BOOLEANFORAFRICANTHREE",true);
            editor.commit();
            updateProgressBar(21, africanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked AFRICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (africanInt<1000){
            updateProgressBar(21, africanInt);
        }

    }

    public void northAmericanAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NORTHAMERICANINT", northAmericanInt);
        editor.commit();
        if (northAmericanInt == 100 && northAmericanOneBoolean){
            northAmericanOneBoolean = false;
            editor.putInt("NORTHAMERICANONE", 200);
            editor.putBoolean("BOOLEANNORTHAMERICANONE", false);
            editor.putBoolean("BOOLEANFORNORTHAMERICANONE",true);
            editor.commit();
            updateProgressBar(22, northAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (northAmericanInt<100){
            updateProgressBar(22, northAmericanInt);
        }

    }

    public void northAmericanAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NORTHAMERICANINT", northAmericanInt);
        editor.commit();
        if (northAmericanInt == 500 && northAmericanTwoBoolean){
            northAmericanTwoBoolean = false;
            editor.putInt("NORTHAMERICANTWO", 1200);
            editor.putBoolean("BOOLEANNORTHAMERICANTWO", false);
            editor.putBoolean("BOOLEANFORNORTHAMERICANTWO",true);
            editor.commit();
            updateProgressBar(23, northAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (northAmericanInt<500){
            updateProgressBar(23, northAmericanInt);
        }

    }

    public void northAmericanAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NORTHAMERICANINT", northAmericanInt);
        editor.commit();
        if (northAmericanInt == 1000 && northAmericanThreeBoolean){
            northAmericanThreeBoolean = false;
            editor.putInt("NORTHAMERICANTHREE", 2500);
            editor.putBoolean("BOOLEANNORTHAMERICANTHREE", false);
            editor.putBoolean("BOOLEANFORNORTHAMERICANTHREE",true);
            editor.commit();
            updateProgressBar(24, northAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (northAmericanInt<1000){
            updateProgressBar(24, northAmericanInt);
        }

    }

    public void southAmericanAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SOUTHAMERICANINT", southAmericanInt);
        editor.commit();
        if (southAmericanInt == 100 && southAmericanOneBoolean){
            southAmericanOneBoolean = false;
            editor.putInt("SOUTHAMERICANONE", 200);
            editor.putBoolean("BOOLEANSOUTHAMERICANONE", false);
            editor.putBoolean("BOOLEANFORSOUTHAMERICANONE",true);
            editor.commit();
            updateProgressBar(25, southAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (southAmericanInt<100){
            updateProgressBar(25, southAmericanInt);
        }

    }

    public void southAmericanAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SOUTHAMERICANINT", southAmericanInt);
        editor.commit();
        if (southAmericanInt == 500 && southAmericanTwoBoolean){
            southAmericanTwoBoolean = false;
            editor.putInt("SOUTHAMERICANTWO", 1200);
            editor.putBoolean("BOOLEANSOUTHAMERICANTWO", false);
            editor.putBoolean("BOOLEANFORSOUTHAMERICANTWO",true);
            editor.commit();
            updateProgressBar(26, southAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (southAmericanInt<500){
            updateProgressBar(26, southAmericanInt);
        }

    }

    public void southAmericanAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SOUTHAMERICANINT", southAmericanInt);
        editor.commit();
        if (southAmericanInt == 1000 && southAmericanThreeBoolean){
            southAmericanThreeBoolean = false;
            editor.putInt("SOUTHAMERICANTHREE", 2500);
            editor.putBoolean("BOOLEANSOUTHAMERICANTHREE", false);
            editor.putBoolean("BOOLEANFORSOUTHAMERICANTHREE",true);
            editor.commit();
            updateProgressBar(27, southAmericanInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (southAmericanInt<1000){
            updateProgressBar(27, southAmericanInt);
        }

    }

    public void oceaniaAchievementOne(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("OCEANIAINT", oceaniaInt);
        editor.commit();
        if (oceaniaInt == 100 && oceaniaOneBoolean){
            oceaniaOneBoolean = false;
            editor.putInt("OCEANIAONE", 200);
            editor.putBoolean("BOOLEANOCEANIAONE", false);
            editor.putBoolean("BOOLEANFOROCEANIAONE",true);
            editor.commit();
            updateProgressBar(28, oceaniaInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked OCEANIA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (oceaniaInt<100){
            updateProgressBar(28, oceaniaInt);
        }

    }

    public void oceaniaAchievementTwo(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("OCEANIAINT", oceaniaInt);
        editor.commit();
        if (oceaniaInt == 500 && oceaniaTwoBoolean){
            oceaniaTwoBoolean = false;
            editor.putInt("OCEANIATWO", 1200);
            editor.putBoolean("BOOLEANOCEANIATWO", false);
            editor.putBoolean("BOOLEANFOROCEANIATWO",true);
            editor.commit();
            updateProgressBar(29, oceaniaInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked OCEANIA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (oceaniaInt<500){
            updateProgressBar(29, oceaniaInt);
        }

    }

    public void oceaniaAchievementThree(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("OCEANIAINT", oceaniaInt);
        editor.commit();
        if (oceaniaInt == 1000 && oceaniaThreeBoolean){
            oceaniaThreeBoolean = false;
            editor.putInt("OCEANIATHREE", 2500);
            editor.putBoolean("BOOLEANOCEANIATHREE", false);
            editor.putBoolean("BOOLEANFOROCEANIATHREE",true);
            editor.commit();
            updateProgressBar(30, oceaniaInt);

            Toast toast = Toast.makeText(NormalGameNameFlagActivity.this, "Congratulations! You unlocked OCEANIA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (oceaniaInt<1000){
            updateProgressBar(30, oceaniaInt);
        }

    }


}
