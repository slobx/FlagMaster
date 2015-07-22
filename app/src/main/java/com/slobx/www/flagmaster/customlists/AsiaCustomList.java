package com.slobx.www.flagmaster.customlists;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slobx.www.flagmaster.R;

import java.util.ArrayList;

public class AsiaCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> web;
    private final ArrayList<Integer> flagImageId;

    public AsiaCustomList(Activity context,
                          ArrayList<String> web, ArrayList<Integer> flagImageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.flagImageId = flagImageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.countryName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.flagImg);

        txtTitle.setText(web.get(position));
        imageView.setImageResource(flagImageId.get(position));

        SharedPreferences prefs = context.getSharedPreferences("CUSTOMLIST", Context.MODE_PRIVATE);

        //setting all new unlocked rows to be unclickable
        if (prefs.getBoolean("ASIABOOLEAN" + position, true)){
            rowView.setEnabled(true);
        } else {
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }


        //setting all default unocked rows to be unclickable
        if (position==0 || position==1 || position==2 || position==3 || position==4 || position==5 ||
                position==6 || position==7 || position==8 || position==9 || position==10 || position==11 ||
                position==12 || position==13 || position==14){
            rowView.setEnabled(false);
            rowView.setOnClickListener(null);
        }

        rowView.setBackgroundResource(R.drawable.asiabackground);

        return rowView;
    }




}