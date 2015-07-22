package com.slobx.www.flagmaster;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BCA on 12/23/2014.
 */
public class Wallet {

    public static int addCredits(Context context, int amount) {
        SharedPreferences prefs = context.getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        int currentCredits = prefs.getInt("TOTALSTARS", 0);

        SharedPreferences.Editor editor = prefs.edit();
        currentCredits += amount;
        editor.putInt("TOTALSTARS", currentCredits);
        editor.commit();
        return currentCredits;
    }

    public static int getCredits(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("TOTALSTARS", Context.MODE_PRIVATE);
        return prefs.getInt("TOTALSTARS", 0);
    }
}
