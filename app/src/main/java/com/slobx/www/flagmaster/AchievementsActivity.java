package com.slobx.www.flagmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.slobx.www.flagmaster.customlists.AchievementsCustomList;

import java.util.ArrayList;


public class AchievementsActivity extends Activity {

    ListView list;
    AchievementsCustomList adapter;
    ArrayList<String> web = new ArrayList<>();
    ArrayList<Integer> flagImageId = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        flagImageId.add(0, R.drawable.questionmark1);
        flagImageId.add(1, R.drawable.questionmark);
        flagImageId.add(2, R.drawable.questionmark);
        flagImageId.add(3, R.drawable.questionmark);
        flagImageId.add(4, R.drawable.questionmark);
        flagImageId.add(5, R.drawable.questionmark);
        flagImageId.add(6, R.drawable.questionmark);
        flagImageId.add(7, R.drawable.questionmark);
        flagImageId.add(8, R.drawable.questionmark);
        flagImageId.add(9, R.drawable.questionmark);
        flagImageId.add(10, R.drawable.questionmark);
        flagImageId.add(11, R.drawable.questionmark);
        flagImageId.add(12, R.drawable.questionmark);
        flagImageId.add(13, R.drawable.questionmark);
        flagImageId.add(14, R.drawable.questionmark);
        flagImageId.add(15, R.drawable.questionmark);
        flagImageId.add(16, R.drawable.questionmark);
        flagImageId.add(17, R.drawable.questionmark);
        flagImageId.add(18, R.drawable.questionmark);
        flagImageId.add(19, R.drawable.questionmark);
        flagImageId.add(20, R.drawable.questionmark);
        flagImageId.add(21, R.drawable.questionmark);
        flagImageId.add(22, R.drawable.questionmark);
        flagImageId.add(23, R.drawable.questionmark);
        flagImageId.add(24, R.drawable.questionmark);
        flagImageId.add(25, R.drawable.questionmark);
        flagImageId.add(26, R.drawable.questionmark);
        flagImageId.add(27, R.drawable.questionmark);
        flagImageId.add(28, R.drawable.questionmark);
        flagImageId.add(29, R.drawable.questionmark);
        flagImageId.add(30, R.drawable.questionmark);



        web.add(0, "AMATEUR" + "\n" + "Finish 1 game");
        web.add(1, "????????????" + "\n" + "Finish 10 games");
        web.add(2, "BEGINNER II" + "\n" + "Finish 25 games");
        web.add(3, "BEGINNER III" + "\n" + "Finish 50 games");
        web.add(4, "BEGINNER IV" + "\n" + "Finish 100 games");
        web.add(5, "MASTER I" + "\n" + "Finish 10 normal games with 100% correct answers");
        web.add(6, "MASTER II" + "\n" + "Finish 25 normal games with 100% correct answers");
        web.add(7, "MASTER III" + "\n" + "Finish 50 normal games with 100% correct answers");
        web.add(8, "MASTER IV" + "\n" + "Finish 100 normal games with 100% correct answers");
        web.add(9, "ULTIMATE I" + "\n" + "Finish 10 time games with 20 or more correct answers");
        web.add(10, "ULTIMATE II" + "\n" + "Finish 25 time games with 20 or more correct answers");
        web.add(11, "ULTIMATE III" + "\n" + "Finish 50 time games with 20 or more correct answers");
        web.add(12, "ULTIMATE IV" + "\n" + "Finish 100 time games with 20 or more correct answers");
        web.add(13, "EUROPE I" + "\n" + "Guess 100 flags from Europe");
        web.add(14, "EUROPE II" + "\n" + "Guess 500 flags from Europe");
        web.add(15, "EUROPE III" + "\n" + "Guess 1000 flags from Europe");
        web.add(16, "ASIA I" + "\n" + "Guess 100 flags from Asia");
        web.add(17, "ASIA II" + "\n" + "Guess 500 flags from Asia");
        web.add(18, "ASIA III" + "\n" + "Guess 1000 flags from Asia");
        web.add(19, "AFRICA I" + "\n" + "Guess 100 flags from Africa");
        web.add(20, "AFRICA II" + "\n" + "Guess 500 flags from Africa");
        web.add(21, "AFRICA III" + "\n" + "Guess 1000 flags from Africa");
        web.add(22, "NORTH AMERICA I" + "\n" + "Guess 100 flags from North America");
        web.add(23, "NORTH AMERICA II" + "\n" + "Guess 500 flags from North America");
        web.add(24, "NORTH AMERICA III" + "\n" + "Guess 1000 flags from North America");
        web.add(25, "SOUTH AMERICA I" + "\n" + "Guess 100 flags from South America");
        web.add(26, "SOUTH AMERICA II" + "\n" + "Guess 500 flags from South America");
        web.add(27, "SOUTH AMERICA III" + "\n" + "Guess 1000 flags from South America");
        web.add(28, "OCEANIA I" + "\n" + "Guess 100 flags from Oceania");
        web.add(29, "OCEANIA II" + "\n" + "Guess 500 flags from Oceania");
        web.add(30, "OCEANIA III" + "\n" + "Guess 1000 flags from Oceania");




        list=(ListView)findViewById(R.id.achievementList);
        progressBar = (ProgressBar) list.findViewById(R.id.progresBar);

        adapter = new
                AchievementsCustomList(AchievementsActivity.this, web, flagImageId, progressBar);

        list.setAdapter(adapter);




    }

    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }



}
