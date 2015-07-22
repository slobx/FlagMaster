package com.slobx.www.flagmaster.customlists;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.slobx.www.flagmaster.R;

import java.util.ArrayList;

public class AchievementsCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> web;
    private final ArrayList<Integer> flagImageId;
    private final ProgressBar progressBar;


    public AchievementsCustomList(Activity context,
                                  ArrayList<String> web, ArrayList<Integer> flagImageId, ProgressBar progressBar) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.flagImageId = flagImageId;
        this.progressBar = progressBar;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_with_progres_bar, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.countryName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.flagImg);

        SharedPreferences preferences = context.getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);
        int progressBarValue = preferences.getInt("PROGRESSBAR" + position, 0);

        //setting textview and image view inside row by position
        txtTitle.setText(web.get(position));
        imageView.setImageResource(flagImageId.get(position));

        //setting progress bar with value received from shared preference
        ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progresBar);
        progressBar.setProgress(progressBarValue);

        //setting progress bar value depending on type of p.bar (1, 10, 25, 50, 100)
        if (position == 0 && progressBarValue <1) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1");
            progressBar.setMax(1);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 0 && progressBarValue >= 1) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(1);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 1 && progressBarValue <10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/10");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 1 && progressBarValue >= 10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 2 && progressBarValue <25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/25");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 2 && progressBarValue >= 25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 3 && progressBarValue <50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/50");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 3 && progressBarValue >= 50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 4 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 4 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 5 && progressBarValue <10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/10");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 5 && progressBarValue >= 10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 6 && progressBarValue <25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/25");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 6 && progressBarValue >= 25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 7 && progressBarValue <50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/50");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 7 && progressBarValue >= 50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 8 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 8 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 9 && progressBarValue <10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/10");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 9 && progressBarValue >= 10) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(10);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 10 && progressBarValue <25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/25");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 10 && progressBarValue >= 25) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(25);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 11 && progressBarValue <50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/50");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 11 && progressBarValue >= 50) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(50);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 12 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 12 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 13 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 13 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 14 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 14 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 15 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 15 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 16 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 16 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 17 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 17 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 18 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 18 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 19 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 19 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 20 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 20 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 21 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 21 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 22 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 22 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 23 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 23 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 24 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 24 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 25 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 25 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 26 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 26 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 27 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 27 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 28 && progressBarValue <100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/100");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 28 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 29 && progressBarValue <500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/500");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 29 && progressBarValue >= 500) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(500);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        if (position == 30 && progressBarValue <1000) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText(progressBarValue + "/1000");
            progressBar.setMax(1000);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        } else if (position == 30 && progressBarValue >= 100) {
            TextView progressBarTextView = (TextView) rowView.findViewById(R.id.progresBarTextView);
            progressBarTextView.setText("Completed!");
            progressBar.setMax(100);
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }


        return rowView;
    }




}