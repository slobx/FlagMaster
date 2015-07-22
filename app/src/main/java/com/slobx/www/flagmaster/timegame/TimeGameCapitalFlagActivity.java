package com.slobx.www.flagmaster.timegame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.concurrent.TimeUnit;


public class TimeGameCapitalFlagActivity extends Activity {

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

    TextView questionTextViewTimeGame;
    TextView timeTextView;
    TextView scoreTextViewTimeGame;

    Vibrator vibe;
    long[] vibratePattern = {0, 150, 100, 150, 100, 150};
    MediaPlayer rightSound;
    MediaPlayer wrongSound;
    boolean soundOnOff;
    boolean vibrationOnOff;


    ArrayList<String> countriesNameList;
    ArrayList<Integer> flagsList;

    String[] europeArray = {"Moscow", "Paris", "Madrid", "Stockholm", "Oslo",
            "Helsinki","Warsaw","Rome","London","Athens","Reykjavik", "Vienna","Prague","Copenhagen","Bern",
            "Kiev", "Bucharest","Minsk","Astana", "Sofia","Budapest","Lisbon","Belgrade",
            "Dublin","Vilnius", "Riga","Zagreb","Sarajevo","Bratislava","Tallinn",
            "Amsterdam","Chișinau","Brussels","Tirana","Skopje","Ankara",
            "Ljubljana","Nicosia","Luxembourg","Andorra la Vella","Valletta","Vaduz",
            "San Marino","Monaco", "Berlin", "Podgorica", "Tbilisi", "Vatican city"};

    String[] asiaArray = {"Kabul", "Beijing","New Delhi","Jakarta","Tehran","Baghdad","Jerusalem","Tokyo","Kuala Lumpur","Pyongyang",
            "Manila","Doha","Riyadh","Seoul","Abu Dhabi","Baku","Manama","Dhaka", "Bandar Seri Begawan",
            "Phnom Penh","Yerevan", "Thimphu","Amman","Kuwait City","Bishkek","Vientiane","Beirut",
            "Male","Ulan Bator","Naypyidaw","Kathmandu","Muscat","Islamabad","Singapore","Colombo","Damascus","Dushanbe",
            "Bangkok","Ashgabat","Tashkent","Hanoi","Sana'a"};

    String[] africaArray = {"Algiers","Luanda","Yaounde","Brazzaville","Yamoussoukro","Cairo","Accra","Nairobi","Port Louis","Rabat","Abuja","Dakar","Tunis",
            "Addis Ababa","Porto-Novo","Gaborone","Ouagadougou","Bujumbura","Praia","Bangui","N'Djamena","Moroni","Malabo",
            "Asmara","Libreville","Banjul","Conakry","Bissau","Maseru","Monrovia","Antananarivo","Lilongwe","Bamako","Nouakchott","Maputo","Windhoek",
            "Kigali","Sao Tome","Victoria","Freetown","Mogadishu","Khartoum","Mbabane","Dodoma"};

    String[] northAmericaArray = {"Washington","Nassau","Mexico City","San Jose","Havana","Kingston","Bridgetown","Port of Spain",
            "Saint John's","Panama City","Roseau","St. George's","Castries","Kingstown",
            "Belmopan","San Salvador","Guatemala City","Tegucigalpa","Managua","Port-au-Prince"};

    String[] southAmericaArray = {"Buenos Aires","Brasilia","Bogota","La Paz", "Santiago","Quito","Georgetown","Asuncion","Lima","Montevideo","Caracas"};

    String[] oceaniaArray = {"Canberra","Port Moresby","Wellington","Suva","Honiara","Port Vila","Apia","South Tarawa","Nukuʻalofa","Majuro","Ngerulmud", "Yaren"};

    public int returningID;
    public int totalNumberOfStars;
    int numberOfGames;
    int correctAnswer;
    int europeanInt;
    int asianInt;
    int africanInt;
    int northAmericanInt;
    int southAmericanInt;
    int oceaniaInt;
    int numberOfUltimateGames;
    int counter = 1;
    boolean amateurBoolean;
    boolean beginnerOneBoolean;
    boolean beginnerTwoBoolean;
    boolean beginnerThreeBoolean;
    boolean beginnerFourBoolean;
    boolean ultimateOneBoolean;
    boolean ultimateTwoBoolean;
    boolean ultimateThreeBoolean;
    boolean ultimateFourBoolean;
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
    SharedPreferences preferences;
    CounterClass timer1;
    CounterClass timer[] = new CounterClass[500];
    Long milis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_game);

        SharedPreferences preferences3 = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        boolean removeAdsBoolean = preferences3.getBoolean("REMOVEADS", false);

        SharedPreferences myPreference= PreferenceManager.getDefaultSharedPreferences(this);
        soundOnOff = myPreference.getBoolean("prefSoundOnOff", true);
        vibrationOnOff = myPreference.getBoolean("prefVibrateOnOff", true);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        buttonFlag1 = (ImageView) findViewById(R.id.buttonImageViewTimeGame);
        buttonFlag2 = (ImageView) findViewById(R.id.buttonImageViewTimeGame2);
        buttonFlag3 = (ImageView) findViewById(R.id.buttonImageViewTimeGame3);
        buttonFlag4 = (ImageView) findViewById(R.id.buttonImageViewTimeGame4);

        buttonFlagRedX1 = (ImageView) findViewById(R.id.buttonImageViewRedX);
        buttonFlagRedX2 = (ImageView) findViewById(R.id.buttonImageViewRedX2);
        buttonFlagRedX3 = (ImageView) findViewById(R.id.buttonImageViewRedX3);
        buttonFlagRedX4 = (ImageView) findViewById(R.id.buttonImageViewRedX4);

        buttonFlagBackground1 = (ImageView) findViewById(R.id.buttonImageViewBackground1);
        buttonFlagBackground2 = (ImageView) findViewById(R.id.buttonImageViewBackground2);
        buttonFlagBackground3 = (ImageView) findViewById(R.id.buttonImageViewBackground3);
        buttonFlagBackground4 = (ImageView) findViewById(R.id.buttonImageViewBackground4);

        amateurBoolean = preferences.getBoolean("BOOLEANAMATEUR", true);
        beginnerOneBoolean = preferences.getBoolean("BOOLEANBEGINNERONE", true);
        beginnerTwoBoolean = preferences.getBoolean("BOOLEANBEGINNERTWO", true);
        beginnerThreeBoolean = preferences.getBoolean("BOOLEANBEGINNERTHREE", true);
        beginnerFourBoolean = preferences.getBoolean("BOOLEANBEGINNERFOUR", true);

        ultimateOneBoolean = preferences.getBoolean("BOOLEANULTIMATEONE", true);
        ultimateTwoBoolean = preferences.getBoolean("BOOLEANULTIMATETWO", true);
        ultimateThreeBoolean = preferences.getBoolean("BOOLEANULTIMATETHREE", true);
        ultimateFourBoolean = preferences.getBoolean("BOOLEANULTIMATEFOUR", true);

        europeanInt = preferences.getInt("EUROPEANINT", 0);
        asianInt = preferences.getInt("ASIANINT", 0);
        africanInt = preferences.getInt("AFRICANINT", 0);
        northAmericanInt = preferences.getInt("NORTHAMERICANINT", 0);
        southAmericanInt = preferences.getInt("SOUTHAMERICANINT", 0);
        oceaniaInt = preferences.getInt("OCEANIAINT", 0);

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

        timer1 = new CounterClass(31000, 1000);
        timer1.start();


        questionTextViewTimeGame = (TextView) findViewById(R.id.countryTextViewTimeGame);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextViewTimeGame = (TextView) findViewById(R.id.scoreTextVievTimeGame);


        timeTextView.setText("30");

        totalNumberOfStars = 0;
        scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);


        countriesNameList = new ArrayList<String>();
        //unlocked European countries names
        countriesNameList.add(0, "Moscow");
        countriesNameList.add(1, "Paris");
        countriesNameList.add(2, "Madrid");
        countriesNameList.add(3, "Stockholm");
        countriesNameList.add(4, "Oslo");
        countriesNameList.add(5, "Helsinki");
        countriesNameList.add(6, "Warsaw");
        countriesNameList.add(7, "Rome");
        countriesNameList.add(8, "London");
        countriesNameList.add(9, "Athens");
        countriesNameList.add(10, "Reykjavik");
        countriesNameList.add(11, "Vienna");
        countriesNameList.add(12, "Prague");
        countriesNameList.add(13, "Copenhagen");
        countriesNameList.add(14, "Bern");
        //unlocked Asia countries names
        countriesNameList.add(15, "Kabul");
        countriesNameList.add(16, "Beijing");
        countriesNameList.add(17, "New Delhi");
        countriesNameList.add(18, "Jakarta");
        countriesNameList.add(19, "Tehran");
        countriesNameList.add(20, "Baghdad");
        countriesNameList.add(21, "Jerusalem");
        countriesNameList.add(22, "Tokyo");
        countriesNameList.add(23, "Kuala Lumpur");
        countriesNameList.add(24, "Pyongyang");
        countriesNameList.add(25, "Manila");
        countriesNameList.add(26, "Doha");
        countriesNameList.add(27, "Riyadh");
        countriesNameList.add(28, "Seoul");
        countriesNameList.add(29, "Abu Dhabi");
        //unlocked Africa countries names
        countriesNameList.add(30, "Algiers");
        countriesNameList.add(31, "Luanda");
        countriesNameList.add(32, "Yaounde");
        countriesNameList.add(33, "Brazzaville");
        countriesNameList.add(34, "Yamoussoukro");
        countriesNameList.add(35, "Cairo");
        countriesNameList.add(36, "Accra");
        countriesNameList.add(37, "Nairobi");
        countriesNameList.add(38, "Port Louis");
        countriesNameList.add(39, "Rabat");
        countriesNameList.add(40, "Abuja");
        countriesNameList.add(41, "Dakar");
        countriesNameList.add(42, "Tunis");
        countriesNameList.add(43, "Addis Ababa");
        countriesNameList.add(44, "Porto-Novo");
        countriesNameList.add(45, "Gaborone");
        //unlocked North America countries names
        countriesNameList.add(46, "Washington");
        countriesNameList.add(47, "Nassau");
        countriesNameList.add(48, "Mexico City");
        countriesNameList.add(49, "San Jose");
        countriesNameList.add(50, "Havana");
        countriesNameList.add(51, "Kingston");
        //unlocked South America countries names
        countriesNameList.add(52, "Buenos Aires");
        countriesNameList.add(53, "Brasilia");
        countriesNameList.add(54, "Bogota");
        //unlocked Oceania countries names
        countriesNameList.add(55, "Canberra");
        countriesNameList.add(56, "Port Moresby");
        countriesNameList.add(57, "Wellington");


        //European locked countries names
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN15", false))
            countriesNameList.add("Kiev");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN16", false))
            countriesNameList.add("Bucharest");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN17", false))
            countriesNameList.add("Minsk");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN18", false))
            countriesNameList.add("Astana");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN19", false))
            countriesNameList.add("Sofia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN20", false))
            countriesNameList.add("Budapest");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN21", false))
            countriesNameList.add("Lisbon");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN22", false))
            countriesNameList.add("Belgrade");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN23", false))
            countriesNameList.add("Dublin");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN24", false))
            countriesNameList.add("Vilnius");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN25", false))
            countriesNameList.add("Riga");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN26", false))
            countriesNameList.add("Zagreb");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN27", false))
            countriesNameList.add("Sarajevo");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN28", false))
            countriesNameList.add("Bratislava");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN29", false))
            countriesNameList.add("Tallinn");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN30", false))
            countriesNameList.add("Amsterdam");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN31", false))
            countriesNameList.add("Chișinau");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN32", false))
            countriesNameList.add("Brussels");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN33", false))
            countriesNameList.add("Tirana");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN34", false))
            countriesNameList.add("Skopje");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN35", false))
            countriesNameList.add("Ankara");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN36", false))
            countriesNameList.add("Ljubljana");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN37", false))
            countriesNameList.add("Nicosia");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN38", false))
            countriesNameList.add("Luxembourg");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN39", false))
            countriesNameList.add("Andorra la Vella");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN40", false))
            countriesNameList.add("Valletta");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN41", false))
            countriesNameList.add("Vaduz");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN42", false))
            countriesNameList.add("San Marino");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN43", false))
            countriesNameList.add("Monaco");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN44", false))
            countriesNameList.add("Berlin");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN45", false))
            countriesNameList.add("Podgorica");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN46", false))
            countriesNameList.add("Tbilisi");
        if(preferences.getBoolean("EUROPENEWFLAGBOOLEAN47", false))
            countriesNameList.add("Vatican city");
        //Asia locked countries
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"15", false))
            countriesNameList.add("Baku");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Manama");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Dhaka");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Bandar Seri Begawan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Phnom Penh");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"20", false))
            countriesNameList.add("Yerevan");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"21", false))
            countriesNameList.add("Thimphu");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"22", false))
            countriesNameList.add("Amman");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"23", false))
            countriesNameList.add("Kuwait City");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"24", false))
            countriesNameList.add("Bishkek");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"25", false))
            countriesNameList.add("Vientiane");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"26", false))
            countriesNameList.add("Beirut");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"27", false))
            countriesNameList.add("Male");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"28", false))
            countriesNameList.add("Ulan Bator");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"29", false))
            countriesNameList.add("Naypyidaw");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"30", false))
            countriesNameList.add("Kathmandu");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"31", false))
            countriesNameList.add("Muscat");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"32", false))
            countriesNameList.add("Islamabad");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"33", false))
            countriesNameList.add("Singapore");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"34", false))
            countriesNameList.add("Colombo");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"35", false))
            countriesNameList.add("Damascus");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"36", false))
            countriesNameList.add("Dushanbe");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"37", false))
            countriesNameList.add("Bangkok");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"38", false))
            countriesNameList.add("Ashgabat");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"39", false))
            countriesNameList.add("Tashkent");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"40", false))
            countriesNameList.add("Hanoi");
        if(preferences.getBoolean("ASIANEWFLAGBOOLEAN"+"41", false))
            countriesNameList.add("Sana'a");
        //Africa locked countries
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Ouagadougou");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Bujumbura");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Praia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Bangui");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"20", false))
            countriesNameList.add("N'Djamena");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"21", false))
            countriesNameList.add("Moroni");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"22", false))
            countriesNameList.add("Malabo");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"23", false))
            countriesNameList.add("Asmara");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"24", false))
            countriesNameList.add("Libreville");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"25", false))
            countriesNameList.add("Banjul");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"26", false))
            countriesNameList.add("Conakry");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"27", false))
            countriesNameList.add("Bissau");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"28", false))
            countriesNameList.add("Maseru");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"29", false))
            countriesNameList.add("Monrovia");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"30", false))
            countriesNameList.add("Antananarivo");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"31", false))
            countriesNameList.add("Lilongwe");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"32", false))
            countriesNameList.add("Bamako");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"33", false))
            countriesNameList.add("Nouakchott");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"34", false))
            countriesNameList.add("Maputo");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"35", false))
            countriesNameList.add("Windhoek");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"36", false))
            countriesNameList.add("Kigali");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"37", false))
            countriesNameList.add("Sao Tome");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"38", false))
            countriesNameList.add("Victoria");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"39", false))
            countriesNameList.add("Freetown");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"40", false))
            countriesNameList.add("Mogadishu");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"41", false))
            countriesNameList.add("Khartoum");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"42", false))
            countriesNameList.add("Mbabane");
        if(preferences.getBoolean("AFRICANEWFLAGBOOLEAN"+"43", false))
            countriesNameList.add("Dodoma");
        //North America locked countries
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Bridgetown");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("Port of Spain");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Saint John's");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Panama City");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Roseau");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"11", false))
            countriesNameList.add("St. George's");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"12", false))
            countriesNameList.add("Castries");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"13", false))
            countriesNameList.add("Kingstown");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"14", false))
            countriesNameList.add("Belmopan");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"15", false))
            countriesNameList.add("San Salvador");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"16", false))
            countriesNameList.add("Guatemala City");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"17", false))
            countriesNameList.add("Tegucigalpa");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"18", false))
            countriesNameList.add("Managua");
        if(preferences.getBoolean("NORTHAMERICANEWFLAGBOOLEAN"+"19", false))
            countriesNameList.add("Port-au-Prince");
        //South America locked countries
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"3", false))
            countriesNameList.add("La Paz");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"4", false))
            countriesNameList.add("Santiago");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"5", false))
            countriesNameList.add("Quito");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Georgetown");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("Asuncion");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Lima");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Montevideo");
        if(preferences.getBoolean("SOUTHAMERICANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Caracas");
        //Oceania locked countries
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"3", false))
            countriesNameList.add("Suva");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"4", false))
            countriesNameList.add("Honiara");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"5", false))
            countriesNameList.add("Port Vila");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"6", false))
            countriesNameList.add("Apia");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"7", false))
            countriesNameList.add("South Tarawa");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"8", false))
            countriesNameList.add("Nukuʻalofa");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"9", false))
            countriesNameList.add("Majuro");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"10", false))
            countriesNameList.add("Ngerulmud");
        if(preferences.getBoolean("OCEANIANEWFLAGBOOLEAN"+"11", false))
            countriesNameList.add("Yaren");

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

        setFlags();

        if (!removeAdsBoolean) {
            AdView adview = (AdView) findViewById(R.id.adViewTimeGame);
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

        scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);



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
        questionTextViewTimeGame.setText(countriesNameListCopy.get(randID));

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

    public void buttonOnClickTimeGame(View v) {
        switch (v.getId()) {
            case R.id.buttonImageViewTimeGame:
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
                    correctAnswer ++;
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
                    totalNumberOfStars = totalNumberOfStars + 2;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            setFlags();
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 500);


                } else {
                    buttonFlag1.setClickable(false);
                    buttonFlagRedX1.setVisibility(View.VISIBLE);
                    buttonFlagBackground1.setVisibility(View.VISIBLE);
                    buttonFlagBackground1.getDrawable().setAlpha(128);
                    buttonFlagRedX1.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX1.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);
                };

                break;

            case R.id.buttonImageViewTimeGame2:
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
                    correctAnswer ++;
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
                    totalNumberOfStars = totalNumberOfStars + 2;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            setFlags();
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 500);


                } else {
                    buttonFlag2.setClickable(false);
                    buttonFlagRedX2.setVisibility(View.VISIBLE);
                    buttonFlagBackground2.setVisibility(View.VISIBLE);
                    buttonFlagBackground2.getDrawable().setAlpha(128);
                    buttonFlagRedX2.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX2.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);
                };
                break;

            case R.id.buttonImageViewTimeGame3:
                if (soundOnOff) {
                    rightSound = MediaPlayer.create(this, R.raw.correct_tone);
                    wrongSound = MediaPlayer.create(this, R.raw.wrong_tone);

                    rightSound.setVolume(0.25f, 0.25f);
                    wrongSound.setVolume(0.25f, 0.25f);
                }
                if (returningID == flagsList.indexOf((Integer)buttonFlag3.getTag())) {
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
                    correctAnswer ++;
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
                    totalNumberOfStars = totalNumberOfStars + 2;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            setFlags();
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 500);



                } else {
                    buttonFlag3.setClickable(false);
                    buttonFlagRedX3.setVisibility(View.VISIBLE);
                    buttonFlagBackground3.setVisibility(View.VISIBLE);
                    buttonFlagBackground3.getDrawable().setAlpha(128);
                    buttonFlagRedX3.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX3.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);
                };
                break;

            case R.id.buttonImageViewTimeGame4:
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
                    correctAnswer ++;
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
                    totalNumberOfStars = totalNumberOfStars + 2;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);

                    Runnable runnable3sec = new Runnable() {
                        @Override
                        public void run() {
                            setFlags();
                        }
                    };

                    android.os.Handler myHandler = new android.os.Handler();
                    myHandler.postDelayed(runnable3sec, 500);
                } else {
                    buttonFlag4.setClickable(false);
                    buttonFlagRedX4.setVisibility(View.VISIBLE);
                    buttonFlagBackground4.setVisibility(View.VISIBLE);
                    buttonFlagBackground4.getDrawable().setAlpha(128);
                    buttonFlagRedX4.setBackgroundResource(R.drawable.redx_anim);
                    AnimationDrawable anim = (AnimationDrawable) buttonFlagRedX4.getBackground();
                    anim.start();
                    if (vibrationOnOff)
                        vibe.vibrate(100);
                    if (soundOnOff) {
                        wrongSound.start();
                    }
                    totalNumberOfStars = totalNumberOfStars - 1;
                    scoreTextViewTimeGame.setText("Earned stars: " + totalNumberOfStars);
                };
                break;
        }

    }

    public void setDialog() {

        int numberOfStars = totalNumberOfStars;
        int summaryStars;
        correctAnswer = 0;

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
        scoreTextViewTimeGame.setVisibility(View.INVISIBLE);
        timeTextView.setVisibility(View.INVISIBLE);
        questionTextViewTimeGame.setVisibility(View.INVISIBLE);


        SharedPreferences preferences = getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        summaryStars = preferences.getInt("TOTALSTARS", 0);

        summaryStars = summaryStars + numberOfStars;

        editor.putInt("TOTALSTARS", summaryStars);
        editor.commit();



        // Create custom dialog object
        final Dialog dialog = new Dialog(TimeGameCapitalFlagActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.result_dialog);
        // Set dialog title
        dialog.setTitle("Congratulations");

        // set values for custom dialog components - text, image and button
//        final TextView textViewDialog = (TextView) dialog.findViewById(R.id.textDialog);
//        textViewDialog.setText("You received " + numberOfStars + " stars!");
//        ImageView imageViewDialog = (ImageView) dialog.findViewById(R.id.imageDialog);
//        imageViewDialog.setImageResource(R.drawable.greencorrect);

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
                Intent intent = new Intent(getApplicationContext(), SplashTimeGameNameFlag.class);
                startActivity(intent);
                finish();
            }
        });



    };

    @Override
    public void onBackPressed() {

        timer1.cancel();
        timer[counter] = new CounterClass(milis, 1000);
        if (counter != 1)
            timer[counter-1].cancel();

        final Dialog dialog = new Dialog(TimeGameCapitalFlagActivity.this, R.style.Theme_Dialog);


        dialog.setContentView(R.layout.dialog);
        Button dialogNo = (Button)dialog.findViewById(R.id.dialogNo);
        Button dialogYes = (Button)dialog.findViewById(R.id.dialogYes);

        dialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer[counter] = new CounterClass(milis, 1000);
                timer[counter].start();
                counter++;

                dialog.dismiss();
            }
        });

        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                timer1.cancel();
                timer[counter].cancel();
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
        timer1.cancel();
        if (counter != 1)
            timer[counter-1].cancel();
        finish();
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countdownInterval) {
            super(millisInFuture, countdownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            milis = millisUntilFinished;
            String seconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(milis));

            timeTextView.setText(seconds);

        }

        @Override
        public void onFinish() {
            timeTextView.setText("Time's up!");
            updateNumberOfGames();
            amateurAchievement();
            beginnerAchievementOne();
            beginnerAchievementTwo();
            beginnerAchievementThree();
            beginnerAchievementFour();
            checkNumberOfCorrectAnswersUltimate();
            ultimateAchievementOne();
            ultimateAchievementTwo();
            ultimateAchievementThree();
            ultimateAchievementFour();
            setDialog();
        }
    }

        public void updateProgressBar(int position, int procent){
            SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("PROGRESSBAR"+position, procent);
            editor.commit();
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

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked AMATEUR achievement. 50 stars are added to your account. " +
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

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked BEGINNER I achievement. 50 stars are added to your account", Toast.LENGTH_LONG);
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

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked BEGINNER II achievement. 150 stars are added to your account", Toast.LENGTH_LONG);
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

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked BEGINNER III achievement. 400 stars are added to your account", Toast.LENGTH_LONG);
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

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked BEGINNER IV achievement. 1000 stars are added to your account", Toast.LENGTH_LONG);
                toast.show();
            } else if (numberOfGames<100){
                updateProgressBar(4, numberOfGames);
            }

        }

        public void checkNumberOfCorrectAnswersUltimate() {
            if(correctAnswer>=20){
                numberOfUltimateGames ++;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ULTIMATEGAMES", numberOfUltimateGames);
                editor.commit();
            }

        }

        public void ultimateAchievementOne(){
            SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
            if (numberOfUltimateGames == 10 && ultimateOneBoolean){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ULTIMATEONE", 150);
                editor.putBoolean("BOOLEANULTIMATEONE", false);
                editor.putBoolean("BOOLEANFORULTIMATEONE", true);
                editor.commit();
                updateProgressBar(9, numberOfUltimateGames);

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ULTIMATE I achievement. 150 stars are added to your account", Toast.LENGTH_LONG);
                toast.show();
            } else if (numberOfUltimateGames < 10) {
                updateProgressBar(9, numberOfUltimateGames);
            }

        }

        public void ultimateAchievementTwo(){
            SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
            if (numberOfUltimateGames == 25 && ultimateTwoBoolean){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ULTIMATETWO", 400);
                editor.putBoolean("BOOLEANULTIMATETWO", false);
                editor.putBoolean("BOOLEANFORULTIMATETWO", true);
                editor.commit();
                updateProgressBar(10, numberOfUltimateGames);

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ULTIMATE II achievement. 400 stars are added to your account", Toast.LENGTH_LONG);
                toast.show();
            } else if (numberOfUltimateGames < 25){
                updateProgressBar(10, numberOfUltimateGames);
            }

        }

        public void ultimateAchievementThree(){
            SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
            if (numberOfUltimateGames == 50 && ultimateThreeBoolean){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ULTIMATETHREE", 1000);
                editor.putBoolean("BOOLEANULTIMATETHREE", false);
                editor.putBoolean("BOOLEANFORULTIMATETHREE", true);
                editor.commit();
                updateProgressBar(11, numberOfUltimateGames);

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ULTIMATE III achievement. 1000 stars are added to your account", Toast.LENGTH_LONG);
                toast.show();
            } else if (numberOfUltimateGames < 50){
                updateProgressBar(11, numberOfUltimateGames);
            }
        }

        public void ultimateAchievementFour(){
            SharedPreferences preferences = getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
            if (numberOfUltimateGames == 100 && ultimateFourBoolean){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ULTIMATEFOUR", 2500);
                editor.putBoolean("BOOLEANULTIMATEFOUR", false);
                editor.putBoolean("BOOLEANFORULTIMATEFOUR", true);
                editor.commit();
                updateProgressBar(12, numberOfUltimateGames);

                Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ULTIMATE IV achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
                toast.show();
            } else if (numberOfUltimateGames < 100){
                updateProgressBar(12, numberOfUltimateGames);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked EUROPE I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked EUROPE II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked EUROPE III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ASIA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ASIA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked ASIA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked AFRICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked AFRICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked AFRICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked NORTH AMERICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked SOUTH AMERICA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked OCEANIA I achievement. 200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked OCEANIA II achievement. 1200 stars are added to your account", Toast.LENGTH_LONG);
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

            Toast toast = Toast.makeText(TimeGameCapitalFlagActivity.this, "Congratulations! You unlocked OCEANIA III achievement. 2500 stars are added to your account", Toast.LENGTH_LONG);
            toast.show();
        } else if (oceaniaInt<1000){
            updateProgressBar(30, oceaniaInt);
        }

    }




}






